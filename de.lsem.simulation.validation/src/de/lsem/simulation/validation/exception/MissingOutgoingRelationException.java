package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;

public class MissingOutgoingRelationException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8444497281955452197L;

	public MissingOutgoingRelationException(ISimulationObject element,
			String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {

		if (getElement() instanceof ISimulationElement) {
			ISimulationElement e = (ISimulationElement) getElement();
			String name = e.getName() == null ? e.toString() : e.getName();
			return "Element "
					+ name
					+ " has no outgoing relation(s). Please add outgoing relations to this element.";
		}
		return "An element has no outgoing relation(s). Please fix it.";
	}

}
