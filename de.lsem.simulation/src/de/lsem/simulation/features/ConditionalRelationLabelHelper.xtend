package de.lsem.simulation.features

import de.lsem.repository.model.simulation.IConditionalRelation

class ConditionalRelationLabelHelper {
	
	
	static def createConditionalLabel(IConditionalRelation conRel){
		val length = conRel.condition.length 
		"(Cond): " + switch length { 
			case length < 5: conRel.condition
			case length >= 5: conRel.condition.substring(0, 5) + "..."
			default: ""
		}
	}
	
	static def createProbabilityLabel(IConditionalRelation conRel){
		var prob = conRel.probability
		prob = Math.round( prob * 100 ) / 100
		prob + "%"
	}
}