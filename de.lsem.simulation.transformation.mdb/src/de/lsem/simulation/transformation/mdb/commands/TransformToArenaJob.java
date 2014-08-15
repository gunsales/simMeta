package de.lsem.simulation.transformation.mdb.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.xml.sax.SAXException;

import com.google.inject.Injector;

import de.lsem.simulation.transformation.mdb.Activator;
import de.lsem.simulation.transformation.mdb.generator.GeneratorInjector;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformer;
import de.lsem.simulation.validation.SimulationValidator;

public class TransformToArenaJob extends Job {

	private IWorkbenchWindow workbench;
//	private static final Logger log = Logger.getLogger(TransformToArenaJob.class.getSimpleName());

	public TransformToArenaJob(String name, final IWorkbenchWindow workbench) {
		super(name);
		this.workbench = workbench;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IWorkbenchPage page = workbench.getActivePage();
		IEditorPart editor = page.getActiveEditor();

		monitor.beginTask("Validating elements...", IProgressMonitor.UNKNOWN);
		preCheckBusinessObjects(editor);
		
		Status retVal = new Status(Status.INFO, Activator.PLUGIN_ID, "");

		monitor.beginTask(Messages.TransformToArena_4, IProgressMonitor.UNKNOWN);
		if (editor instanceof IDiagramContainer) {
			try {

				IDiagramContainer diagContainer = (IDiagramContainer) editor;
				XMIResourceImpl xmiResource = getXMIResource(diagContainer);

				if (xmiResource != null) {
					
					if (!preCheckBusinessObjects(editor)) {
						return new Status(
								Status.ERROR,
								Activator.PLUGIN_ID,
								"Transformation can not be started. Please check the error-log for more information.");
					}
					
					monitor.beginTask(Messages.TransformToArena_8,
							IProgressMonitor.UNKNOWN);

					// TRANSFORMATION
					File saveDB = createInjectorAndStartTransformation(xmiResource);

					// Open file -- Admin-Rights might be used
					Desktop.getDesktop().open(saveDB);

					// Update project structure
					updateProject(xmiResource);

					retVal = new Status(Status.OK, Activator.PLUGIN_ID,
							"Transformation to ARENA completed successfully.");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				monitor.setCanceled(true);
				retVal = new Status(Status.ERROR, Activator.PLUGIN_ID,
						"An error occured when transforming to ARENA.", e1);

			}
		}
		monitor.done();

		return retVal;
	}

	private boolean preCheckBusinessObjects(IEditorPart editor) {
		if (editor instanceof IDiagramContainer) {
			IDiagramContainer container = (IDiagramContainer) editor;
			EList<Resource> resources = container.getDiagramBehavior()
					.getEditingDomain().getResourceSet().getResources();
			for (Resource r : resources) {
				if (r instanceof XMIResource) {

					ILog iLog = Activator.getDefault().getLog();
					
					XMIResource xmiResource = (XMIResource) r;

					SimulationValidator simulationValidator = new SimulationValidator(
							xmiResource, iLog, Activator.PLUGIN_ID);

					return simulationValidator.validate();
				}
			}
		}
		// Allow transformation even if pre-check is not possible.
		return true;
	}

	private void updateProject(XMIResourceImpl xmiResource)
			throws CoreException {
		String projectName = getProjectName(xmiResource.getURI());

		// refresh workspace if needed
		ResourcesPlugin
				.getWorkspace()
				.getRoot()
				.getProject(projectName)
				.refreshLocal(IResource.DEPTH_INFINITE,
						new NullProgressMonitor());
	}

	private File createInjectorAndStartTransformation(
			XMIResourceImpl xmiResource) throws IOException,
			ParserConfigurationException, SAXException,
			TransformerFactoryConfigurationError, TransformerException {
		GeneratorInjector gen = new GeneratorInjector();
		Injector injector = gen.createInjectorAndDoEMFRegistration();
		ArenaTransformer transformer = injector
				.getInstance(ArenaTransformer.class);
		return transformer.start(xmiResource);
	}

	private XMIResourceImpl getXMIResource(IDiagramContainer diagContainer) {
		for (Resource r : getResourcesFrom(diagContainer)) {
			if (r instanceof XMIResourceImpl) {
				IFile sourceFile = getSourceFile((XMIResourceImpl) r);
				createTargetFolder(sourceFile.getProject());
				return (XMIResourceImpl) r;
			}
		}
		return null;
	}

	private EList<Resource> getResourcesFrom(IDiagramContainer diagEditor) {
		return diagEditor.getDiagramBehavior().getEditingDomain()
				.getResourceSet().getResources();
	}

	private void createTargetFolder(IProject project) {
		try {
			project.open(new NullProgressMonitor());
			IFolder srcGenFolder = project
					.getFolder(Messages.TransformToArena_13);
			if (!srcGenFolder.exists())
				srcGenFolder.create(true, true, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private IFile getSourceFile(XMIResourceImpl xmiResource) {
		URI resolvedFile = getModelURI(xmiResource);

		String projectNameString = getProjectName(xmiResource.getURI());
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectNameString);

		Path path = new Path(resolvedFile.toFileString());
		return project.getFile(path);
	}

	private URI getModelURI(XMIResourceImpl xmiResource) {
		URI resolvedFile = CommonPlugin.resolve(xmiResource.getURI());
		return resolvedFile;
	}

	private String getProjectName(URI uri) {
		String uriString = uri.toString();
		String trimmedFirst = uriString.substring(19, uriString.length());
		trimmedFirst = trimmedFirst.replaceAll("%20", " ");
		return trimmedFirst.substring(0,
				trimmedFirst.indexOf(Messages.TransformToArena_7));
	}
}
