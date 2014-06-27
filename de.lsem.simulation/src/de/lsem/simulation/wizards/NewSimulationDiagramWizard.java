	package de.lsem.simulation.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.graphiti.examples.common.FileService;
import org.eclipse.graphiti.examples.common.Messages;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import de.lsem.simulation.Activator;

public class NewSimulationDiagramWizard extends Wizard implements INewWizard {

	private IWorkbench workbench;
	private IStructuredSelection selection;
	private WizardNewFileCreationPage pageOne;

	public NewSimulationDiagramWizard() {
		setWindowTitle("New Simulation Diagram");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
	}

	@Override
	public boolean performFinish() {

		IFile newFile = pageOne.createNewFile();
		Diagram diagram = createDiagram(newFile);
		openEditor(diagram);
		
		
		return true;
	}

	protected Diagram createDiagram(IFile newFile) {
		Diagram diagram = Graphiti.getPeService().createDiagram( Activator.PLUGIN_ID, newFile.getName(), false );
		URI uri = URI.createPlatformResourceURI( newFile.getFullPath().toOSString(), true );
		FileService.createEmfFileForDiagram( uri, diagram );
		return diagram;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		pageOne = new WizardDialogNewFileCreationPage(selection);
		
		addPage(pageOne);
	}
	
	private void openEditor(final Diagram diagram) {
		//Open Editor
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				try {
					
					//Get diagramType-ID = de.lsem.simulation
					final String diagramTypeID = diagram.getDiagramTypeId();
					//provider id = diagramTypeID + ".SimulationDiagram"
					final String providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(diagramTypeID);
					//Create lightweight input instance
					final DiagramEditorInput editorInput = DiagramEditorInput.createEditorInput(diagram, providerId);
					//Create editor based on diagram and providerId off of the editorInput
					IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
					activePage.openEditor(editorInput, DiagramEditor.DIAGRAM_EDITOR_ID);
				
				} catch (PartInitException e) {
					e.printStackTrace();
					createErrorMessage(e);
				}
			}
			
			private void createErrorMessage(PartInitException e) {
				String error = Messages.CreateDiagramWizard_OpeningEditorError;
				IStatus status = new Status(IStatus.ERROR, ExamplesCommonPlugin.getID(), error, e);
				ErrorDialog.openError(new Shell(), Messages.CreateDiagramWizard_ErrorOccuredTitle, null, status);
			}
		});
	}

}
