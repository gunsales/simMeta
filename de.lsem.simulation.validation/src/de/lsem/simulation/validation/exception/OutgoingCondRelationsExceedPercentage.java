package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;

public class OutgoingCondRelationsExceedPercentage extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3680357584801524738L;

	public OutgoingCondRelationsExceedPercentage(
			ISimulationObject sourceForRelation, String reason) {
		super(sourceForRelation, reason);
	}

	@Override
	public String getMessage() {
		ISimulationElement simEl = (ISimulationElement) getElement();

		return "Probability of outgoing conditional-relations from \""
				+ simEl.getName() + "\"exceed 100%. Please fix this.";

	}

}
