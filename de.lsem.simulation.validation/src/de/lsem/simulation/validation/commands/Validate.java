package de.lsem.simulation.validation.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.lsem.simulation.validation.Activator;
import de.lsem.simulation.validation.SimulationValidator;

public class Validate implements IHandler {

	private static final Object GRAPHITI_DIAGRAM_EXTENSION = "diagram";
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorPart editor = getActiveEditor();
		
		if(editor instanceof IDiagramContainer){
			IDiagramContainer container = (IDiagramContainer)editor;
			ResourceSet resourceSet = container.getDiagramBehavior().getEditingDomain().getResourceSet();
			
			for(Resource r:resourceSet.getResources()){
				if(r instanceof XMIResource){
					XMIResource xmiResource = (XMIResource)r;
					SimulationValidator validator = new SimulationValidator(xmiResource, Activator.getDefault().getLog(), Activator.PLUGIN_ID);
					validator.validate();
					break;
				}
			}
		}
		
		
		
		return null;
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

	private IEditorPart getEditorPart() {
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		return editor;
	}
	
	private IEditorPart getActiveEditor() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
