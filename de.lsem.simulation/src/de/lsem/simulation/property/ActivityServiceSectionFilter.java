package de.lsem.simulation.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import de.lsem.repository.model.simulation.IActivity;

public class ActivityServiceSectionFilter extends AbstractPropertySectionFilter{

	@Override
	protected boolean accept(PictogramElement pictogramElement) {

		EObject object = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pictogramElement);
		
		if ( object instanceof IActivity )
			return true;
		return false;
	}

}
