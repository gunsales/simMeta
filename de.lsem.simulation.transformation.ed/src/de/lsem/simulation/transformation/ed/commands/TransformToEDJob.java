package de.lsem.simulation.transformation.ed.commands;

import java.awt.Desktop;
import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.lsem.simulation.transformation.ed.Activator;
import de.lsem.simulation.transformation.ed.generator.GeneratorModule;
import de.lsem.simulation.transformation.ed.xtext.MainGenerator;

public class TransformToEDJob extends Job {

	private IWorkbenchWindow workbench;

	public TransformToEDJob(String name, final IWorkbenchWindow workbench) {
		super(name);
		this.workbench = workbench;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		// TASK 1 - Execute transformation and open file
		IEditorPart editor = getActiveEditor(monitor);
		try {
			// Execute Enterprise Dynamics
			File task = executeTransformationTask(monitor, editor);
			// Open file
			Desktop.getDesktop().open(task);
		} catch (Exception e) {
			e.printStackTrace();
			monitor.setCanceled(true);
			return new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage());
		}

		monitor.done();
		return Status.OK_STATUS;
	}
	

	private File executeTransformationTask(IProgressMonitor monitor,
			IEditorPart editor) throws Exception {
		monitor.beginTask(Messages.TransformToED_3, IProgressMonitor.UNKNOWN);
		if (editor instanceof IDiagramContainer) {
			IFile sourceFile = getFileFor((IDiagramContainer) editor);
			if (sourceFile != null) {
				monitor.beginTask(Messages.TransformToED_4,
						IProgressMonitor.UNKNOWN);
				IFolder targetFolder = getTargetFolderFor(sourceFile);
				IFile targetFile = createTargetFile(sourceFile, targetFolder);

				Injector inj = Guice.createInjector(new GeneratorModule());

				MainGenerator gen = inj.getInstance(MainGenerator.class);
				XMIResource resource = getSourceResource(editor);
				
				StringInputStream stream = gen.getStream(resource);
				
				targetFile.create(stream, true,
						new NullProgressMonitor());

				return createFileFromIFile(targetFile);
			}
		}
		return null;
	}

	private File createFileFromIFile(IFile targetFile) {
		return targetFile.getRawLocation().makeAbsolute().toFile();
	}

	private IFile createTargetFile(IFile sourceFile, IFolder targetFolder)
			throws CoreException {
		IFile targetFile = targetFolder.getFile(sourceFile.getName().replace(
				Messages.TransformToED_5, Messages.TransformToED_6));

		if (targetFile.exists())
			targetFile.delete(true, new NullProgressMonitor());
		return targetFile;
	}

	private IEditorPart getActiveEditor(IProgressMonitor monitor) {
		monitor.beginTask(Messages.TransformToED_7, 100);
		monitor.subTask(Messages.TransformToED_8);

		return workbench.getActivePage().getActiveEditor();
	}

	private XMIResource getSourceResource(IEditorPart editor) {

		IDiagramContainer container = (IDiagramContainer) editor;

		return (XMIResource)getResourcesFrom(container).get(0);
	}

	private IFolder getTargetFolderFor(IFile iFile) {
		return iFile.getProject().getFolder(Messages.TransformToED_9);
	}

	private IFile getFileFor(IDiagramContainer diagEditor) {
		for (Resource r : getResourcesFrom(diagEditor)) {
			if (r instanceof XMIResourceImpl) {
				XMIResourceImpl xmiResource = (XMIResourceImpl) r;
				IFile iFile = getSourceFileFrom(xmiResource);
				if (iFile != null)
					createTargetFolderFor(iFile.getProject());

				return iFile;
			}
		}

		return null;
	}

	private EList<Resource> getResourcesFrom(IDiagramContainer diagEditor) {
		return diagEditor.getDiagramBehavior().getEditingDomain()
				.getResourceSet().getResources();
	}

	private IFile getSourceFileFrom(XMIResourceImpl xmiResource) {
		URI uri = xmiResource.getURI();
		URI resolvedFile = CommonPlugin.resolve(uri);

		String projectNameString = getProjectNameFor(uri);
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectNameString);

		Path path = new Path(resolvedFile.toFileString());
		return project.getFile(path);
	}

	private String getProjectNameFor(URI uri) {
		String uriString = uri.toString();
		String trimmedFirst = uriString.substring(19, uriString.length());
		// TODO Windows-specific? Test Linux
		trimmedFirst = trimmedFirst.replaceAll("%20", " ");
		return trimmedFirst.substring(0,
				trimmedFirst.indexOf(Messages.TransformToED_10));
	}

	private void createTargetFolderFor(IProject project) {
		try {
			project.open(new NullProgressMonitor());
			IFolder srcGenFolder = project.getFolder(Messages.TransformToED_9);
			if (!srcGenFolder.exists())
				srcGenFolder.create(true, true, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
