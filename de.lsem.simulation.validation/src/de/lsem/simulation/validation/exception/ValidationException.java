package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationObject;

public abstract class ValidationException extends Exception {

	private static final long serialVersionUID = -1263325516550233064L;
	
	private ISimulationObject element;
	private String reason;
	
	public ValidationException(ISimulationObject element, String reason){
		this.element = element;
	}
	
	public ISimulationObject getElement() {
		return element;
	}
	
	public String getReason() {
		return reason;
	}
	
	@Override
	public synchronized Throwable getCause() {
		return new Throwable(reason);
	}

}
