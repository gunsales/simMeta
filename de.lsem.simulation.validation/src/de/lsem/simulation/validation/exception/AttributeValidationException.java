package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationObject;

public class AttributeValidationException extends ValidationException {

	private static final long serialVersionUID = 2374303596593270722L;

	public AttributeValidationException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		return "Attribute of Element"
				+ getElement().getClass().getSimpleName()
				+ " with ID "
				+ getElement().toString()
				+ " is null or empty. Please give it a name which is unique."
				+ "Reason: " + getReason();
	}

}
