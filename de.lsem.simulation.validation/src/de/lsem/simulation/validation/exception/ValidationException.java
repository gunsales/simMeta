package de.lsem.simulation.validation.exception;

import org.eclipse.core.runtime.Status;

import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.simulation.validation.SimulationValidator;

public abstract class ValidationException extends Exception {

	private static final long serialVersionUID = -1263325516550233064L;
	private final Status status;
	
	private ISimulationObject element;
	private String reason;
	
//	public ValidationException(ISimulationObject element, String reason, Status status){
//		this.element = element;
//		this.status = status;
//		this.reason = reason;
//	}
	
	public ValidationException(ISimulationObject element, String reason){
		this.element = element;
		this.reason = reason;
		this.status = new Status(Status.WARNING, SimulationValidator.getPluginId(), getMessage(), getCause());
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
	
	public Status getStatus() {
		return status;
	}

}
