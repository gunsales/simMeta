package de.lsem.simulation.util

import de.lsem.repository.model.simulation.ISimulationElement
import org.eclipse.graphiti.mm.pictograms.Diagram
import static de.lsem.simulation.util.LSEMElementHelper.*
class RelationHelper{
	
	
	
	def static isAlreadyConnected(ISimulationElement it, ISimulationElement targetObject) {
		for (relation : outgoing) {
			if (targetObject.name.equals(relation.target.name)) {
				return true
			}
		}
		return false
	}
	
	private static def getInitialRelationNumber(Diagram it){
		val relations = getRelationsFromDiagram(eResource.contents)
		String.valueOf(relations.size + 1)
	}
	
	def static createRelationName(Diagram it){
		"Relation_" + getInitialRelationNumber
	}
}
