package de.lsem.simulation.util

import de.lsem.repository.model.simulation.ISimulationElement
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

import static de.lsem.simulation.util.LSEMElementHelper.*

/**
 * Helper class for deleting elements of diagram
 */
class DeleteHelper {
/**
	 * Method iterates through pictogram-elements and removes incoming
	 * connections. Incoming connections are not directly linked to the activity
	 * that has to be removed.
	 */
	def static deleteIncomingConnections(EList<EObject> it, ISimulationElement target) {
		getSourcesForIncommingConnections(it, target).forEach [ s |
			deleteConnectionsFromSourceToTarget(s, target)
		]
	}

	/**
	 * Delete all outgoing relations, which target is @param _target
	 */
	def static deleteConnectionsFromSourceToTarget(ISimulationElement it, ISimulationElement _target) {
		val iter = outgoing.filter[target.equals(_target)].iterator

		while (iter.hasNext) {
			iter.remove
		}
	}

}
