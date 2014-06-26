package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IActivity;

public abstract class ServiceTypeException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6792738918067327600L;

	
	public ServiceTypeException(IActivity activity, String reason) {
		super(activity, reason);
	}
	
	@Override
	public IActivity getElement() {
		return (IActivity) super.getElement();
	}

}
