package de.lsem.simulation.features.add

import de.lsem.repository.model.simulation.IRelation
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddConnectionContext
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.impl.AbstractAddFeature
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer
import org.eclipse.graphiti.services.Graphiti
import org.eclipse.graphiti.util.ColorConstant
import org.eclipse.graphiti.util.IColorConstant

class AddAbstractRelationFeature extends AbstractAddFeature {

	protected static val IColorConstant E_REFERENCE_COND_FOREGROUND = new ColorConstant(178, 034, 034)
	protected static val IColorConstant E_REFERENCE_COND_BACK = new ColorConstant(220, 220, 220)
	protected static val IColorConstant E_REFERENCE_COND_FORE = new ColorConstant(180, 180, 180)
	protected static val IColorConstant E_REFERENCE_RELATION_FOREGROUND = new ColorConstant(105, 105, 105)

	new(IFeatureProvider fp) {
		super(fp)
	}

	override add(IAddContext it) {
		null
	}

	override canAdd(IAddContext it) {
		isAddContext && isNewObjectRelation
	}

	def isNewObjectRelation(IAddContext it) {
		newObject instanceof IRelation
	}

	def isAddContext(IAddContext it) {
		it instanceof IAddConnectionContext
	}

	protected def create polyline:Graphiti.gaService.createPolyline(gac, #[ -15, 5, 2, 0, -15, -5]) createArrow(
		GraphicsAlgorithmContainer gac, IColorConstant foregroundColor) {
		polyline.foreground = manageColor(foregroundColor)
		polyline.lineWidth = 2
	}

}
