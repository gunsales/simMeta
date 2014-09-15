package de.lsem.simulation.property.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ISink;

public class ActivityAndSinkFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pictogramElement) {
		EObject eObject =
				Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pictogramElement);
		return (eObject instanceof IActivity || eObject instanceof ISink);
		
	}

}
