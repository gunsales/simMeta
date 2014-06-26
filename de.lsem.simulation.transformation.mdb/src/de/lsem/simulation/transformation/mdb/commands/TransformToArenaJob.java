package de.lsem.simulation.transformation.mdb.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.xml.sax.SAXException;

import com.google.inject.Injector;

import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.transformation.mdb.Activator;
import de.lsem.simulation.transformation.mdb.generator.GeneratorInjector;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformer;

public class TransformToArenaJob extends Job {

	private IWorkbenchWindow workbench;

	public TransformToArenaJob(String name, final IWorkbenchWindow workbench) {
		super(name);
		this.workbench = workbench;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IWorkbenchPage page = workbench.getActivePage();
		IEditorPart editor = page.getActiveEditor();

		Status retVal = new Status(Status.INFO, Activator.PLUGIN_ID, "");

		monitor.beginTask(Messages.TransformToArena_4, IProgressMonitor.UNKNOWN);
		if (editor instanceof IDiagramContainer) {
			try {

				IDiagramContainer diagContainer = (IDiagramContainer) editor;
				XMIResourceImpl xmiResource = getXMIResource(diagContainer);

				if (xmiResource != null) {
					List<ISimulationElement> elementList = filterBusinessObjectsFromXMIResource(xmiResource);
					preCheckElements(elementList);

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

	private Status preCheckElements(List<ISimulationElement> elementList) {

		// Case 1 : One outgoing relation but it is a conditional relation
		for (ISimulationElement s : elementList) {
			for (IRelation r : s.getOutgoing()) {
				if (r instanceof IConditionalRelation
						&& s.getOutgoing().size() == 1) {

					return new Status(
							Status.WARNING,
							Activator.PLUGIN_ID,
							"Warning. Element "
									+ s.getName()
									+ " has only one outgoing relation, but it is set as a conditional relation. Please ajust your model.");
				}
			}
		}

		return new Status(Status.OK, Activator.PLUGIN_ID,
				"Pre-check successfully completed.");

	}

	private List<ISimulationElement> filterBusinessObjectsFromXMIResource(
			XMIResourceImpl xmiResource) {

		List<ISimulationElement> retVal = new ArrayList<ISimulationElement>();

		for (EObject e : xmiResource.getContents()) {
			if (e instanceof ISimulationElement) {
				retVal.add((ISimulationElement) e);
			}
		}

		return retVal;
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
