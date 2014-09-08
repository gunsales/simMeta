package de.lsem.simulation.features.create

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.SimulationFactory
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateConnectionContext
import org.eclipse.graphiti.features.context.impl.AddConnectionContext
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature
import org.eclipse.graphiti.mm.pictograms.Connection

import static de.lsem.simulation.util.LSEMElementHelper.*
import static de.lsem.simulation.util.RelationHelper.*

class CreateRelationFeature extends AbstractCreateConnectionFeature {


	new(IFeatureProvider fp) {
		super(fp, "Relation", "Regular Connection");
	}

	override canCreate(ICreateConnectionContext it) {
		val sourceBO = getBusinessObjectForPictogramElement(sourcePictogramElement)
		val targetBO = getBusinessObjectForPictogramElement(targetPictogramElement)

		if (!(sourceBO instanceof ISimulationElement) || !(targetBO instanceof ISimulationElement)) {
			return false
		}

		val source = sourceBO as ISimulationElement
		val target = targetBO as ISimulationElement

		val isSub = isSourceOrTargetSubActivity(source, target)
		val alreadyConnected = isAlreadyConnected(source, target)
		val elementsNotNull = sourceAnchor != null && targetAnchor != null
		val anchorsNotEqual = sourceAnchor != targetAnchor
		val sourceNotSink = !(source instanceof ISink)
		val targetNotSource = !(target instanceof ISource)

		!isSub && !alreadyConnected && elementsNotNull && anchorsNotEqual && sourceNotSink && targetNotSource

	}

	private def isSourceOrTargetSubActivity(ISimulationElement source, ISimulationElement target) {
		if (source instanceof IActivity) {
			if (isSubActivity(diagram.eResource.contents, source as IActivity)) {
				return true
			}
		}
		if (target instanceof IActivity) {
			if (isSubActivity(diagram.eResource.contents, target as IActivity)) {
				return true
			}
		}
		return false
	}

	override canStartConnection(ICreateConnectionContext it) {
		if (sourceAnchor == null) {
			return false
		}

		// Disallow sub-activities as being start-element for connection
		val parentBO = getBusinessObjectForPictogramElement(sourceAnchor)
		if (parentBO != null && parentBO instanceof IActivity) {
			return isSubActivity(diagram.eResource.contents, parentBO as IActivity)
		}
		return true
	}

	override create(ICreateConnectionContext it) {
		val source = sourceAnchor.parent.businessObjectForPictogramElement as ISimulationElement
		val target = targetAnchor.parent.businessObjectForPictogramElement as ISimulationElement

		if (source != null && target != null) {
			val rel = createRelation(source, target)

			val addCC = new AddConnectionContext(sourceAnchor, targetAnchor)
			addCC.newObject = rel
			return featureProvider.addIfPossible(addCC) as Connection
		}
	}

	private def create it:SimulationFactory.eINSTANCE.createRelation() createRelation(ISimulationElement _source,
		ISimulationElement _target) {
		name = createRelationName(diagram)
		source = _source
		target = _target
	}

}
