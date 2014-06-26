package de.lsem.simulation.transformation.sim.commands;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2MultiPageEditor;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import de.lsem.process.io.ProcessModelReader;
import de.lsem.process.io.ProcessModelReaderDirectory;
import de.lsem.process.io.bpmn.JsonBpmnReader;
import de.lsem.process.model.ProcessModel;
import de.lsem.simulation.transformation.sim.helper.ExtensionNameHelper;

public class TransformToSimulationCommand implements IHandler,
		ExtensionNameHelper {

	public static final Logger logger = Logger
			.getLogger(TransformToSimulationCommand.class.getSimpleName());
	private String fileString;
	private String fileStringExtension;

	/**
	 * Executes the command depending on the opened editor and selected file,
	 * folder or project in the project-explorer. Routes between possible
	 * bpmn2-transformation-job or other generic transformation based on the
	 * jpmmt-project.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (preTransformationCheck(event) == null)
			return null;

		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (editor.isDirty()) {
			int open = createSaveMessageBox(event);
			if (open == SWT.YES) {
				editor.doSave(new NullProgressMonitor());
			} else {
				return null;
			}
		}
		
		Job job = null;
		if (getEditorPart(event) instanceof BPMN2MultiPageEditor) {
			job = handleBPMN2Transformation(event);
		} else {
			job = handleOtherTransformations(event);
		}

		if (job != null) {
			job.setUser(true);
			job.schedule();
		}
		return null;
	}

	
	protected int createSaveMessageBox(ExecutionEvent event) {
		MessageBox box = new MessageBox(HandlerUtil.getActiveShell(event),
				SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		box.setText("Save the editor?");
		box.setMessage("The transformation will begin as soon as you save the diagram. Should the editor-state be saved?");
		return box.open();
	}


	// Checks whether the selection after clicking is a folder, file or project
	private Object preTransformationCheck(ExecutionEvent event) {
		Object firstElement = null;
		try {
			firstElement = getFirstElementFromSelection();
			if (!(firstElement instanceof IProject)
					&& !(firstElement instanceof IFolder)
					&& !(firstElement instanceof IFile)) {
				createSelectionErrorMessage(event);
				return null;
			}
		} catch (NullPointerException e) {
			createSelectionErrorMessage(event);
			return null;
		}
		return firstElement;

	}

	// returns the first element selected or returns null when no object is
	// selected
	private Object getFirstElementFromSelection() throws NullPointerException {
		IWorkbench workbench = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getWorkbench();

		IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow()
				.getActivePage();

		if (activePage.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) activePage
					.getSelection();

			Object firstElement = selection.getFirstElement();
			return firstElement;
		}
		return null;

	}

	// Strips the extension from the file-name
	private String filterFileName(String fileString) {
		File file = new File(fileString);
		String name = file.getName();
		return name.split("\\.")[0];
	}

	// If user pressed cancel in file-dialog, which selects the external
	// source-file
	private void createMessageBox(ExecutionEvent event) {
		MessageBox msg = new MessageBox(HandlerUtil.getActiveShell(event),
				SWT.ICON_INFORMATION);
		msg.setText("A problem occured");
		msg.setMessage("A problem occured. Either the source-file could not be read, or the specific transformation is not supported.");
		msg.open();
	}

	// Handle non-bpmn2-editor-based transformations
	private Job handleOtherTransformations(ExecutionEvent event) {
		// Holder for ProcessModel
		ProcessModel processModel = null;

		// Get source-file
		IFile sourceFile = getCurrentFileFromEditor(event);

		boolean isSupportedTransformationFromEditor = isSupportedTransformationFromEditor(sourceFile);

		// open import-file-wizard if source-file in editor does not exist
		if (!isSupportedTransformationFromEditor) {
			log("sourceFile == null.");
			// External PNML & External ORYX BPMN
			processModel = fileImportCase(event);

			// User pressed abort in file-dialog
			if (fileString == null || fileStringExtension == null)
				return null;

		}

		// Use the opened diagram for Transformation
		else {
			processModel = genericTransformationFromEditor(processModel,
					sourceFile);
		}
		// Fallback
		// TODO Inform user about his useless usage of the system. Call him
		// little fucker or smthn.
		if (processModel == null) {
			log("ProcessModel == null");
			createMessageBox(event);
			return null;
		}

		processModel.setName(filterFileName(fileString));

		IFile saveFile = createSaveFile(event, processModel.getName());

		if (saveFile == null) {
			return null;
		}
		
		IWorkbenchPage page = getActivePage(event);

		return new TransformToSimulationJob(processModel, saveFile, page);
	}

	// delegates for creating a save-file based on the selection in
	// projects-view. selection might
	// be a file, folder or project.
	private IFile createSaveFile(ExecutionEvent event, String modelName) {

		IWorkbench workbench = HandlerUtil.getActiveWorkbenchWindow(event)
				.getWorkbench();
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
				.getActivePage();
		IWorkbenchPart activePart = page.getActivePart();

		while (!(activePart instanceof ProjectExplorer)) {
			createSelectionErrorMessage(event);
			return null;
		}
		IStructuredSelection selection = (IStructuredSelection) page
				.getSelection();

		IFile saveFile = null;

		// folder selected
		if (selection.getFirstElement() instanceof IFolder) {
			IFolder folder = (IFolder) selection.getFirstElement();
			saveFile = folderSelectedCase(modelName, folder);
		}
		// project selected
		else if (selection.getFirstElement() instanceof IProject) {
			IProject project = (IProject) selection.getFirstElement();
			saveFile = projectSelectedCase(modelName, project);
		}
		// file selected --> take parent folder or project to save file in
		else if (selection.getFirstElement() instanceof IFile) {
			saveFile = (IFile) selection.getFirstElement();
			IContainer parent = saveFile.getParent();
			if (parent instanceof IFolder) {
				// folder case
				saveFile = folderSelectedCase(modelName, (IFolder) parent);
			} else if (parent instanceof IProject) {
				// project case
				saveFile = projectSelectedCase(modelName, (IProject) parent);
			}
		}

		return saveFile;
	}

	// if a project is selected in the workbench, return a new file in the
	// project-root
	private IFile projectSelectedCase(String modelName, IProject project) {
		IFile saveFile = project.getFile(appendFileExtension(modelName));

		if (saveFile.exists()) {
			try {
				saveFile.delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return saveFile;
	}

	// if a folder is selected in the workbench, return a new file in the folder
	private IFile folderSelectedCase(String modelName, IFolder folder) {
		IFile saveFile;
		saveFile = folder.getFile(modelName);

		if (saveFile.exists()) {
			try {
				saveFile.delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		saveFile = folder.getFile(appendFileExtension(modelName));
		return saveFile;
	}

	private String appendFileExtension(String modelName) {
		return modelName + DIAGRAM_EXTENSION;
	}

//	private boolean createWarningOverwriteMessage(ExecutionEvent e) {
//		return MessageDialog
//				.openConfirm(
//						getActivePage(e).getActivePart().getSite().getShell(),
//						"Warning. File overwrite.",
//						"Warning. You are about to overwrite an existing file. Do you wish to continue?");
//	}

	private void createSelectionErrorMessage(ExecutionEvent e) {

		MessageBox msg = new MessageBox(HandlerUtil.getActiveShell(e),
				SWT.ICON_INFORMATION);
		msg.setText("Select a resource");
		msg.setMessage("Please select a project, folder or file in the projects-view, in which the file should be created before transforming.");
		msg.open();

	}

	private ProcessModel genericTransformationFromEditor(
			ProcessModel processModel, IFile sourceFile) {
		fileString = sourceFile.getRawLocation().toOSString();
		fileStringExtension = sourceFile.getFileExtension();

		// Differentiate between extensions to route transformation-types

		// EPML (EPC)
		if (fileStringExtension.equals(EPML_EXTENSION_STRING)) {
			log("Executing EPC-Model-Transformation...");
			// Get EPML-Reader
			ProcessModelReader reader = ProcessModelReaderDirectory
					.getEpmlReader();

			// Create model from source file
			processModel = reader.read(fileString);// sourceFile.getLocation().toOSString());
		}

		// PNML Eclipse plugin from university of kopenhagen - same
		// extension as in external transformation, but different workflow
		else if (fileStringExtension.equals(PNML_EXTENSION_STRING)) {
			log("Executing PNML-Transformation...");
			processModel = readPNMLFile(fileString);
		}
		return processModel;
	}

	private ProcessModel fileImportCase(ExecutionEvent event) {
		// Open File Import Dialog
		FileDialog fileImportDialog = createFileImportDialog(event);
		String fileDummy = fileImportDialog.open();
		if (fileDummy == null)
			return null;

		fileString = fileDummy;
		// Differentiate transformation via fileExtension
		fileStringExtension = fileImportDialog.getFileName().split("\\.")[1];
		// Case 1 - PNML
		if (fileStringExtension.equals(PNML_EXTENSION_STRING)) {
			log("Executing PNML-Model-Transformation...");
			return readPNMLFile(fileString);
		}
		// Case 2 - ORYX-Editor BPMN
		else if (fileStringExtension.equals(BPMN_ORYX_EDITOR_EXTENSION)) {
			log("Executing BPMN-ORYX-Model-Transformation...");
			return readORYXJSONFile(fileString);
		}
		return null;
	}

	private ProcessModel readORYXJSONFile(String fileString) {
		ProcessModelReader oryxBPMNReader = new JsonBpmnReader();
		return oryxBPMNReader.read(fileString);
	}

	/*
	 * Reads eclipse-plugin version of PNML and external yasper-pnml-files
	 */
	private ProcessModel readPNMLFile(String fileString) {
		ProcessModelReader yasperReader = ProcessModelReaderDirectory
				.getYasperPnmlReader();
		ProcessModel model = yasperReader.read(fileString);
		return model;
	}

	private FileDialog createFileImportDialog(ExecutionEvent event) {
		FileDialog fd = new FileDialog(HandlerUtil.getActiveShell(event));
		fd.setText("Please choose a file to import ... ");
		fd.setFilterExtensions(FILTER_EXTENSIONS);
		fd.setFilterNames(FILTER_NAMES);
		return fd;
	}

	private boolean isSupportedTransformationFromEditor(IFile sourceFile) {
		if (sourceFile == null || sourceFile.getFileExtension() == null) {
			return false;
		}
		if (sourceFile.getFileExtension().equals(BPMN_ECLIPSE_EDITOR_EXTENSION)
				|| sourceFile.getFileExtension().equals(EPML_EXTENSION_STRING)) {
			return true;
		}
		return false;
	}

	private Job handleBPMN2Transformation(ExecutionEvent event) {
		IWorkbenchPage page = getActivePage(event);
		BPMN2MultiPageEditor multipageBPMN2Editor = (BPMN2MultiPageEditor) getEditorPart(event);
		BPMNDiagram bpmnDiagram = multipageBPMN2Editor
				.getBpmnDiagram(multipageBPMN2Editor.getActivePage());

		// IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		URI uri = bpmnDiagram.eResource().getURI();
		// IProject iProject = workspace.getProject(uri.segment(1));
		String fileName = uri.lastSegment().substring(0,
				uri.lastSegment().indexOf("."));
		IFile saveFile = createSaveFile(event, fileName);

		if (saveFile == null) {
			return null;
		}
		return new TransformToSimulationJob(bpmnDiagram, saveFile, page);
	}

	private static IFile getCurrentFileFromEditor(ExecutionEvent e) {
		IWorkbenchWindow win = HandlerUtil.getActiveWorkbenchWindow(e);

		IWorkbenchPage page = win.getActivePage();

		if (page != null) {
			IEditorPart editor = page.getActiveEditor();
			if (editor != null) {
				IEditorInput input = editor.getEditorInput();
				if (input instanceof IFileEditorInput) {
					return ((IFileEditorInput) input).getFile();
				}
			}
		}
		// TODO Dirty, dirty
		if (ResourcesPlugin.getWorkspace().getRoot().getProjects().length == 0)
			return null;

		return ResourcesPlugin.getWorkspace().getRoot().getProjects()[0]
				.getFile("asd");
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private IEditorPart getEditorPart(ExecutionEvent event) {
		return getActivePage(event).getActiveEditor();
	}

	private IWorkbenchPage getActivePage(ExecutionEvent event) {
		return HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

	private static void log(String msg) {
		logger.log(Level.INFO, msg);
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {

	}
}
