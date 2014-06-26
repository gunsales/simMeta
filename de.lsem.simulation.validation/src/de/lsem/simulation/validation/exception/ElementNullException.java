package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationObject;

public class ElementNullException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4458637490537955993L;

	public ElementNullException(ISimulationObject element, String reason) {
		super(element, reason);
	}
	
	@Override
	public String getMessage() {
		return "The Element is null.";
	}

}
