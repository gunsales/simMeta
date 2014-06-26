package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationObject;

public class OutgoingNotConditionalRelationException extends
		ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1024801487196075561L;

	public OutgoingNotConditionalRelationException(ISimulationObject element,
			String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		IRelation r = (IRelation) getElement();
		return "Relation \""
				+ r.getName()
				+ "\" between \""
				+ r.getSource().getName()
				+ "\" and \""
				+ r.getTarget().getName()
				+ "\" is not a conditional-relation. If multiple outgoing relations exists, they must all be conditional. Please fix this.";
	}
}
