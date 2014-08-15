package de.lsem.simulation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class ProjectNature implements IProjectNature {

	public static final String NATURE_ID = "de.lsem.simulation.nature";
	private IProject project;
	
	@Override
	public void configure() throws CoreException {
		  try {
		      IProjectDescription description = project.getDescription();
		      String[] natures = description.getNatureIds();
		      String[] newNatures = new String[natures.length + 1];
		      System.arraycopy(natures, 0, newNatures, 0, natures.length);
		      newNatures[natures.length] = NATURE_ID;
		      description.setNatureIds(newNatures);
		      project.setDescription(description, null);
		   } catch (CoreException e) {
			   e.printStackTrace();
		   }
	}

	@Override
	public void deconfigure() throws CoreException {
		  try {
		      IProjectDescription description = project.getDescription();
		      String[] natures = description.getNatureIds();
		      String[] newNatures = new String[natures.length - 1];
		      System.arraycopy(natures, 0, newNatures, 0, newNatures.length);
		      description.setNatureIds(newNatures);
		      project.setDescription(description, null);
		   } catch (CoreException e) {
			   e.printStackTrace();
		   }
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
	
	

}
