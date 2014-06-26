package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IActivity;

public class StorageException extends ServiceTypeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4383240452264341316L;
	
	public StorageException(IActivity activity, String reason) {
		super(activity, reason);
	}


	@Override
	public String getMessage() {
		return "Warning. Values of storage-service in activity " + getElement().getName()
				+ " threw an exception.";
	}

}
