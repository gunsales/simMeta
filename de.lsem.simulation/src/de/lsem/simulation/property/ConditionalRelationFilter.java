package de.lsem.simulation.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import de.lsem.repository.model.simulation.IConditionalRelation;

public class ConditionalRelationFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject obj = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pictogramElement);
		if(obj instanceof IConditionalRelation)
			return true;		
		return false;
	}

}
