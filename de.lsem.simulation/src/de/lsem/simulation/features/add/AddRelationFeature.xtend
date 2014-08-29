package de.lsem.simulation.features.add

import de.lsem.repository.model.simulation.IRelation
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddContext
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.IActivity

import static de.lsem.simulation.util.LSEMElementHelper.*
import org.eclipse.graphiti.features.context.IAddConnectionContext

import static org.eclipse.graphiti.services.Graphiti.*

class AddRelationFeature extends AddAbstractRelationFeature {

	new(IFeatureProvider fp) {
		super(fp)
	}

	override canAdd(IAddContext it) {
		if (!(newObject instanceof IRelation)) {
			return false
		} else {
			val relation = newObject as IRelation
			return !isSourceOrTargetSubActivity(relation.source, relation.target)
		}
	}

	override add(IAddContext it) {
		val addCC = it as IAddConnectionContext
		val relation = newObject as IRelation

		val con = peCreateService.createFreeFormConnection(diagram)
		con.start = addCC.sourceAnchor
		con.end = addCC.targetAnchor

		val polyline = gaService.createPolyline(con)
		polyline.lineWidth = 2
		polyline.foreground = manageColor(E_REFERENCE_RELATION_FOREGROUND)

		link(con, relation)

		val cd = peCreateService.createConnectionDecorator(con, false, 1.0, true)
		createArrow(cd, E_REFERENCE_RELATION_FOREGROUND)

		con
	}

	private def isSourceOrTargetSubActivity(ISimulationElement source, ISimulationElement target) {
		val contents = diagram.eResource.contents
		var subExists = false

		//Check source
		if (source instanceof IActivity) {
			subExists = isSubActivity(contents, source as IActivity)
		}

		//Check target
		if (target instanceof IActivity) {
			subExists = isSubActivity(contents, target as IActivity)
		}

		subExists
	}

}
