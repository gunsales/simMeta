package de.lsem.simulation.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import de.lsem.simulation.ProjectNature;

public class NewSimulationProject extends BasicNewProjectResourceWizard
		implements INewWizard {

	
	
	
	@Override
	public boolean performFinish() {
		try {
			WizardNewProjectCreationPage asd = (WizardNewProjectCreationPage)getPages()[0];
			IProject project = asd.getProjectHandle();
			
			ProjectNature projectNature = new ProjectNature();
			projectNature.setProject(project);
			
			project.create(new NullProgressMonitor());
			project.open(new NullProgressMonitor());
			project.build(IncrementalProjectBuilder.AUTO_BUILD,
					new NullProgressMonitor());
			projectNature.configure();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return true;
	}
}
