package de.lsem.simulation.transformation.anylogic.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class TransformToAnylogicCommand implements IHandler {

	private static final Object GRAPHITI_DIAGRAM_EXTENSION = "diagram";

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}

	private IEditorPart getActiveEditor() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorPart editor = getActiveEditor();
		if (editor.isDirty()) {
			int open = createSaveMessageBox(event);
			if (open == SWT.YES) {
				editor.doSave(new NullProgressMonitor());
			} else {
				return null;
			}
		}
		if (editor instanceof IDiagramContainer) {
			// Get Editor as DiagramContainer
			IDiagramContainer container = (IDiagramContainer) editor;

			// Get resources from diagram-container
			EList<Resource> resources = getResourcesFrom(container);

			// Filter xmiResource from editor-resources
			XMIResource xmiResource = filterXMIResource(resources);

			// Execute transformation
			TransformToAnylogicJob transformJob = new TransformToAnylogicJob(
					xmiResource);
			transformJob.setUser(true);

			// Final writing to file-task is executed in jobchangelistener
			transformJob.addJobChangeListener(new TransformToAnylogicJobChangeListener(
					transformJob, xmiResource));

			transformJob.schedule();

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

	


	private XMIResource filterXMIResource(EList<Resource> resources) {
		for (Resource r : resources) {
			if (r instanceof XMIResource)
				return (XMIResource) r;
		}
		return null;
	}

	private EList<Resource> getResourcesFrom(IDiagramContainer diagEditor) {
		return diagEditor.getDiagramBehavior().getEditingDomain()
				.getResourceSet().getResources();
	}



	@Override
	public boolean isEnabled() {
		try {
			IEditorPart editor = getEditorPart();
			DiagramEditorInput ei = (DiagramEditorInput) editor
					.getEditorInput();
			return (editor instanceof IDiagramContainer && ei.getUri()
					.fileExtension().equals(GRAPHITI_DIAGRAM_EXTENSION));
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

	private IEditorPart getEditorPart() {
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return editor;
	}

	

	
}
