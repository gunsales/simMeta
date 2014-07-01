package de.lsem.simulation.transformation.ed.commands;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.lsem.simulation.transformation.ed.Activator;
import de.lsem.simulation.validation.SimulationValidator;
import de.lsem.simulation.validation.exception.ValidationException;

public class TransformToEDCommand implements IHandler {

	private boolean isOngoing;
	private static final Logger log = Logger
			.getLogger(TransformToEDCommand.class.getSimpleName());

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {

		// Get workbench
		final IWorkbenchWindow workbench = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		IEditorPart editor = workbench.getActivePage().getActiveEditor();

		// Check state of editor - ask for save state if dirty
		if (editor.isDirty()) {
			displaySaveMessageBox(editor);
			if (!isOngoing) {
				return new Status(Status.ERROR, Activator.PLUGIN_ID,
						"Cannot perform transformation if the editor-state is not saved.");
			}
		}

		// OKTODO REACTIVATE!!!! pre-check business objects
		preCheckBusinessObjects(editor);

		// Create Job
		TransformToEDJob job = new TransformToEDJob(Messages.TransformToED_0,
				workbench);
		job.setUser(true);

		// Set jobchangelistener
		TransformToEDJobChangeListener jobChangeListener = new TransformToEDJobChangeListener(
				workbench);
		job.addJobChangeListener(jobChangeListener);

		// Execute job
		job.schedule();

		return null;
	}

	private void preCheckBusinessObjects(IEditorPart editor) {
		if (editor instanceof IDiagramContainer) {
			IDiagramContainer container = (IDiagramContainer) editor;
			EList<Resource> resources = container.getDiagramBehavior()
					.getEditingDomain().getResourceSet().getResources();
			for (Resource r : resources) {
				if (r instanceof XMIResource) {
					XMIResource xmiResource = (XMIResource) r;

					SimulationValidator simulationValidator = new SimulationValidator(
							xmiResource, Activator.PLUGIN_ID);

					List<ValidationException> foundProblems = simulationValidator
							.validate();
					
					ILog iLog = Activator.getDefault().getLog();

					for (ValidationException e : foundProblems) {

						log.log(Level.WARNING, e.getLocalizedMessage() + "\n"
								+ e.getMessage());
						

						iLog.log(e.getStatus());
						
					}
					
					if ( foundProblems.size() > 0){
						MessageBox messageBox = new MessageBox(editor.getSite().getShell(), SWT.ICON_INFORMATION);
						messageBox.setMessage("Your model contains errors or warnings. Please check the error log.");
						messageBox.setText("Errors found in model");
						messageBox.open();
					}
				}
			}
		}
	}


	private void setOngoing(boolean isOngoing) {
		this.isOngoing = isOngoing;
	}

	protected void displaySaveMessageBox(final IEditorPart edPart) {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				MessageBox box = new MessageBox(Display.getDefault()
						.getActiveShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				box.setText("Save the editor?");
				box.setMessage("The transformation will begin as soon as you save the diagram. Should the editor-state be saved?");
				int open = box.open();
				if (open == SWT.YES) {
					edPart.doSave(new NullProgressMonitor());
					setOngoing(true);
				} else {
					setOngoing(false);
				}
			}
		});
	}

	@Override
	public boolean isEnabled() {
		try {
			IEditorPart editor = getEditorPart();
			DiagramEditorInput ei = (DiagramEditorInput) editor
					.getEditorInput();
			return (editor instanceof IDiagramContainer && ei.getUri()
					.fileExtension().equals(Messages.TransformToED_13));
		} catch (Exception e) {
		}
		return false;
	}

	private IEditorPart getEditorPart() {
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return editor;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener arg0) {
	}

	@Override
	public void addHandlerListener(IHandlerListener arg0) {
	}

	@Override
	public void dispose() {
	}

}
