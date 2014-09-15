package de.lsem.simulation.util

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * Main class for convenience of simulation-object-proceeding and naming.
 * @Author Lewin Böhlke 
 */
class LSEMElementHelper {

	/**
	 * Checks whether @parameter activity is a sub-activity
	 * @parameters it contents of diagram
	 */
	static def isSubActivity(EList<EObject> it, IActivity activity) {
		activities.map[a|a.subActivities.contains(activity)].toList.contains(true)
	}

	/**
	 * Filter all activities from diagram
	 */
	static def getActivities(EList<EObject> it) {
		filter(typeof(IActivity))
	}

	/**
	 * Filter all sources from diagram
	 */
	static def getSources(EList<EObject> it) {
		filter(typeof(ISource))
	}

	/**
 * Filter all simulation-elements from diagram
 */
	static def getSimulationElements(EList<EObject> it) {
		filter(typeof(ISimulationElement))
	}

	/**
	 * Filter all sinks from diagram
	 */
	static def getSinks(EList<EObject> it) {
		filter(typeof(ISink))
	}

	/**
	 * Get all incoming connection for one simulation-element.
	 * @parameter it all elements of diagram.
	 * @parameter sim element, for which incoming relations should be returned.
	 */
	static def getIncomingConnections(EList<EObject> it, ISimulationElement sim) {
		simulationElements.map[outgoing.filter[target.equals(sim)]].flatten
	}

	/**
	 * Get all simulation-elements which outgoing relations target the @parameter element
	 * @parameter it all elements of diagram
	 */
	static def getSourcesForIncommingConnections(EList<EObject> it, ISimulationElement element) {
		getIncomingConnections(element).map [ r |
			r.source
		].filterNull.toList
	}

	/**
	 * Delete incoming relations if their target is deleted.
	 * @parameter element target of relation
	 * @parameter it all elements of diagram
	 */
	static def removeFromIncommingConnections(EList<EObject> it, ISimulationElement element) {
		getSourcesForIncommingConnections(element).forEach [ s |
			s.outgoing.forEach [ r |
				if (r.target.equals(element)) {
					s.outgoing.remove(r)
				}
			]
		]
	}

	/**
	 * Check if a1 is sub-activity of a2 or vice versa.
	 * @return true, if a1 is sub-activity of a2 or vice versa. 
	 */
	static def checkIfSubActivityOfEachother(IActivity a1, IActivity a2) {
		a1.subActivities.contains(a2) || a2.subActivities.contains(a1)
	}

	/**
	 * @return the top-activity, which sub-activity is the parameter sub-activity
	 * @parameter it all contents of diagram
	 */
	static def getTopActivity(EList<EObject> it, IActivity subActivity) {
		activities.filter[subActivities.contains(subActivity)].head
	}

	/**
	 * @return all relations from diagram
	 * @parameter it all contents of diagram
	 */
	static def getRelations(EList<EObject> it) {
		filter(typeof(IRelation))
	}

	/**
	 * @return true, if sub-activities exist
	 */
	static def hasSubActivities(IActivity it) {
		subActivities.size > 0
	}

	/**
	 * Checks simulation-elements and relations if one is named like @param newName
	 * @param it all contents of diagram
	 * @return true, if newName is already taken 
	 */
	static def nameExists(EList<EObject> it, String newName) {
		simulationElementsContainsName(newName) || relationsContainsName(newName)
	}

	/**
	 * @return true, if relations contain @param newName
	 * @param it all contents of diagram
	 */
	static def relationsContainsName(EList<EObject> it, String newName) {
		relations.map [ r |
			r.name.equals(newName)
		].toList.contains(true)
	}

	/**
	 * @return true, if simulation-elements contain @param newName
	 * @param it all contents of diagram
	 */
	static def simulationElementsContainsName(EList<EObject> it, String newName) {
		simulationElements.map [ s |
			s.name.equals(newName)
		].toList.contains(true)
	}

}
