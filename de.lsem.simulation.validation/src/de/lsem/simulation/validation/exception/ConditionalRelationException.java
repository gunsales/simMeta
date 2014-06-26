package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.ISimulationObject;

public class ConditionalRelationException extends ValidationException {

	private static final long serialVersionUID = -7802932951666718439L;

	public ConditionalRelationException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		IConditionalRelation r = (IConditionalRelation) getElement();
		return "Conditional-relation \""
				+ r.getName()
				+ "\" between \""
				+ r.getSource().getName()
				+ "\" and \""
				+ r.getTarget().getName()
				+ "\" has a wrong probability or its condition is null. Please fix.";
//				+"Reason: " + getReason();
	}
}
