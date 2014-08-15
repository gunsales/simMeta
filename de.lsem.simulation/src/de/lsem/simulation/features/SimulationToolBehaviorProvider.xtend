package de.lsem.simulation.features

import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.features.context.IPictogramElementContext
import org.eclipse.graphiti.features.context.impl.CustomContext
import de.lsem.simulation.features.custom.CollapseActivityFeature
import org.eclipse.graphiti.platform.IPlatformImageConstants
import org.eclipse.graphiti.services.Graphiti
import de.lsem.repository.model.simulation.IActivity
import org.eclipse.graphiti.tb.ContextButtonEntry

class SimulationToolBehaviorProvider extends DefaultToolBehaviorProvider {

	new(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider)
	}

	override getContextButtonPad(IPictogramElementContext context) {
		
		val cbp = super.getContextButtonPad(context)
		val picto = context.pictogramElement
		val bo = featureProvider.getBusinessObjectForPictogramElement(picto)
		val cc = new CustomContext(#[picto])
		val cfs = featureProvider.getCustomFeatures(cc)

		setGenericContextButtons(cbp, picto, CONTEXT_BUTTON_DELETE)

		for (cf : cfs) {
			if (cf instanceof CollapseActivityFeature && cf.canExecute(cc)) {
				var image = IPlatformImageConstants.IMG_EDIT_EXPAND
				var collapseExpand = "Collapse"

				if (Boolean.parseBoolean(Graphiti.peService.getPropertyValue(picto, "isCollapsed"))) {
					image = IPlatformImageConstants.IMG_EDIT_COLLAPSE
					collapseExpand = "Expand"
				}

				var name = ""
				
				if( bo instanceof IActivity && bo != null) {
					name = (bo as IActivity).name ?: ""
				}

				val collapseButton = new ContextButtonEntry(cf, cc)
				collapseButton.description = collapseExpand + " " + name
				collapseButton.text = collapseExpand
				collapseButton.iconId = image
				
				cbp.collapseContextButton = collapseButton
				return cbp
			}
		}
		cbp
	}

}
