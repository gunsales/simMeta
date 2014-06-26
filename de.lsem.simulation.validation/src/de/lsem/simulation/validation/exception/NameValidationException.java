package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationObject;

public class NameValidationException extends ValidationException {

	private static final long serialVersionUID = 2374303596593270722L;

	public NameValidationException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		return "Name of "
				+ getElement().getClass().getSimpleName()
				+ " with ID "
				+ getElement()
				+ " is null or empty. Please give it a name which is unique.";
//				+ "Reason: " + getReason();
	}

}
