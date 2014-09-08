package de.lsem.simulation.features.add

import de.lsem.repository.model.simulation.IConditionalRelation
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddConnectionContext
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection
import org.eclipse.graphiti.util.IColorConstant

import static de.lsem.simulation.util.ConditionalRelationLabelHelper.*
import static org.eclipse.graphiti.services.Graphiti.*

class AddConditionalRelationFeature extends AddAbstractRelationFeature {

	new(IFeatureProvider fp) {
		super(fp)
	}

	override canAdd(IAddContext it) {

		//		super.canAdd(it)
		newObject instanceof IConditionalRelation
	}

	override add(IAddContext it) {
		val addConContext = it as IAddConnectionContext
		val conRel = newObject as IConditionalRelation

		// Create connection picto
		val con = peCreateService.createFreeFormConnection(diagram)
		con.start = addConContext.sourceAnchor
		con.end = addConContext.targetAnchor

		val polyline = gaService.createPolyline(con)
		polyline.lineWidth = 2
		polyline.foreground = manageColor(E_REFERENCE_COND_FOREGROUND)

		// Create text-field next to polyline
		createTextField(con, conRel)

		// Link BO and Picto
		link(con, conRel)

		// Create Arrow
		val conDec = peCreateService.createConnectionDecorator(con, false, 1.0, true)
		createArrow(conDec, E_REFERENCE_COND_FOREGROUND)

		con
	}
	
	private def createTextField(FreeFormConnection con, IConditionalRelation conRel) {
		val conDecText = peCreateService.createConnectionDecorator(con, true, 0.5, true)
		val text = gaService.createText(conDecText)
		text.foreground = manageColor(IColorConstant.BLACK)
		gaService.setLocation(text, 10, 0)
		text.value = createProbabilityLabel(conRel)
	}
	
}
