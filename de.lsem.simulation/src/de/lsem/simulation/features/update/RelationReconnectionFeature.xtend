package de.lsem.simulation.features.update

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IReconnectionContext
import org.eclipse.graphiti.features.context.impl.ReconnectionContext
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature
import org.eclipse.graphiti.mm.pictograms.Anchor
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.Diagram

class RelationReconnectionFeature extends DefaultReconnectionFeature {

	extension Logger logger = Logger.getLogger(typeof(RelationReconnectionFeature).simpleName)

	@Inject
	new(IFeatureProvider fp) {
		super(fp)
	}

	override canReconnect(IReconnectionContext it) {
		if (newAnchor == null || targetPictogramElement instanceof Diagram) {
			false
		} else {
			true
		}
	}

	override postReconnect(IReconnectionContext it) {

		val oldSimulationElement = getSimulationElement(oldAnchor)
		val newSimulationElement = getSimulationElement(newAnchor)

		val relation = getBusinessObjectForPictogramElement(connection) as IRelation

		if (sourceHasChanged) {
			oldSimulationElement.outgoing.remove(relation)
			relation.source = newSimulationElement
			newSimulationElement.outgoing.add(relation)

			logger.log(Level.INFO,
				"Source has changed. Relation source now is: " + newSimulationElement + ". Target is: " +
					relation.getTarget());

		} else if (targetHasChanged) {
			relation.target = newSimulationElement

			logger.log(Level.INFO,
				"Target has changed. Relation source is: " + relation.getSource() + ". Target is: " +
					newSimulationElement);

		}

		super.postReconnect(it)
	}

	private def targetHasChanged(IReconnectionContext it) {
		reconnectType.equals(ReconnectionContext.RECONNECT_TARGET)
	}

	private def sourceHasChanged(IReconnectionContext it) {
		reconnectType.equals(ReconnectionContext.RECONNECT_SOURCE)
	}

	private def getSimulationElement(Anchor it) {
		val anc = eContainer as ContainerShape
		getBusinessObjectForPictogramElement(anc) as ISimulationElement
	}

}
