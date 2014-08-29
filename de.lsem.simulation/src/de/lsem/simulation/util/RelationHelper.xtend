package de.lsem.simulation.util

import de.lsem.repository.model.simulation.ISimulationElement
import org.eclipse.graphiti.mm.pictograms.Diagram

class RelationHelper {
	static def isAlreadyConnected(ISimulationElement it, ISimulationElement targetObject) {
		for (relation : outgoing) {
			if (targetObject.name.equals(relation.target.name)) {
				return true
			}
		}
		return false
	}
	
	static def getInitialRelationNumber(Diagram it){
		val relations = LSEMElementHelper.getRelationsFromDiagram(eResource.contents)
		String.valueOf(relations.size + 1)
	}
	
	static def createRelationName(Diagram it){
		"Relation_" + getInitialRelationNumber
	}
}
