package de.lsem.simulation.features

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.features.custom.CollapseActivityFeature
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.features.context.IPictogramElementContext
import org.eclipse.graphiti.features.context.impl.CustomContext
import org.eclipse.graphiti.mm.pictograms.PictogramElement
import org.eclipse.graphiti.platform.IPlatformImageConstants
import org.eclipse.graphiti.services.Graphiti
import org.eclipse.graphiti.tb.ContextButtonEntry
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider

import static org.eclipse.xtext.xbase.lib.IntegerExtensions.*

class SimulationToolBehaviorProvider extends DefaultToolBehaviorProvider {

	@Inject
	new(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider)
	}

	override getContextButtonPad(IPictogramElementContext context) {

		val cbp = super.getContextButtonPad(context)
		val picto = context.pictogramElement
		val bo = featureProvider.getBusinessObjectForPictogramElement(picto)

		setGenericContextButtons(cbp, picto, defaultGenericButtons)

		val cc = new CustomContext(#[picto])
		val cfs = featureProvider.getCustomFeatures(cc)

		cfs.filter(typeof(CollapseActivityFeature)).filter[canExecute(cc)].forEach [
			var image = IPlatformImageConstants.IMG_EDIT_EXPAND
			var collapseExpand = CollapseActivityFeature.COLLAPSE
			if (isPictogramElementCollapsed(picto)) {
				image = IPlatformImageConstants.IMG_EDIT_COLLAPSE
				collapseExpand = CollapseActivityFeature.EXPAND
			}
			var name = ""
			if (bo instanceof IActivity && bo != null) {
				name = (bo as IActivity).name ?: ""
			}
			cbp.collapseContextButton = createCollapseButton(it, cc, collapseExpand, name, image)
		]
		cbp
	}

	private def defaultGenericButtons() {
		bitwiseOr(CONTEXT_BUTTON_UPDATE, CONTEXT_BUTTON_DELETE)
	}

	def createCollapseButton(CollapseActivityFeature it, CustomContext cc, String collapseExpand, String name,
		String image) {
		val collapseButton = new ContextButtonEntry(it, cc)
		collapseButton.description = collapseButtonDescription(collapseExpand, name)
		collapseButton.text = collapseExpand
		collapseButton.iconId = image
		collapseButton
	}

	def String collapseButtonDescription(String collapseExpand, String name) '''
	«collapseExpand» «name»'''

	def isPictogramElementCollapsed(PictogramElement picto) {
		Boolean.parseBoolean(Graphiti.peService.getPropertyValue(picto, CollapseActivityFeature.IS_COLLAPSED))
	}

	def <T> T getInstance(Class<T> type) {
		(diagramTypeProvider as SimulationDiagramTypeProvider).injector.getInstance(type)
	}
}
