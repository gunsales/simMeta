package de.lsem.simulation.property;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.repository.model.simulation.ISource;

public class SourceFollowingEntitiesSection extends SourceAbstractEntitySection {

	private static final Logger logger = Logger
			.getLogger(SourceFollowingEntitiesSection.class.getSimpleName());

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

	}

	@Override
	public void refresh() {

		PictogramElement pe = getSelectedPictogramElement();
		ISource source = (ISource) Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pe);
		if (pe != null) {
			entity = source.getNewEntities();
			
			// Deviation Choice
			super.refresh();

			logger.log(Level.INFO, "Switching entity to " + entity.toString());
		}

	}


}
