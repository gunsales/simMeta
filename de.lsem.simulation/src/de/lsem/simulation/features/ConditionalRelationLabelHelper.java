package de.lsem.simulation.features;

import de.lsem.repository.model.simulation.IConditionalRelation;

public class ConditionalRelationLabelHelper {

	public static String createConditionalLabel(IConditionalRelation conRel) {
		String conRelLabel;
		String condString = conRel.getCondition().length() > 5 ? 
				conRel.getCondition().substring(0, 5) + "..." 
				: conRel.getCondition();
		conRelLabel = "(Cond): " + condString;
		return conRelLabel;
	}

	public static String createProbabilityLabel(IConditionalRelation conRel) {
		double prob = conRel.getProbability();
		prob = (double)Math.round( prob * 100 ) / 100;
		return ( prob + "%" );
	}

}
