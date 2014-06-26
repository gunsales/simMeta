package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISource;

public class MaxNewEntitiesException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -506009508330578778L;

	public MaxNewEntitiesException(ISource element, String reason) {
		super(element, reason);
	}
	
	@Override
	public String getMessage() {
		return "Source " + getElement().getName() + " is 0 or negative. Please fix the value.";
	}
	
	@Override
	public ISource getElement() {
		return (ISource) super.getElement();
	}

}
