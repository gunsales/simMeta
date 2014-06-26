package de.lsem.simulation.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class WizardDialogNewFileCreationPage extends WizardNewFileCreationPage {

	public WizardDialogNewFileCreationPage(IStructuredSelection selection) {
		super("Create a simulation-diagram file", selection);
		
		setTitle("New Simulation-Diagram");
		setDescription("Create a Simulation-Diagram-file in a project.");
		setFileExtension("diagram");
	}
	
	
	
	

}
