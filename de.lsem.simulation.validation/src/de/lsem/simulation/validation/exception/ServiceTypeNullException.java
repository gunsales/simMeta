package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ISimulationObject;

public class ServiceTypeNullException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4626597069624762633L;

	public ServiceTypeNullException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		if(getElement() instanceof IActivity){
		IActivity ac = (IActivity)getElement();
		String name = null == ac.getName() ? ac.toString() : ac.getName();
		
		return "The Service-type of activity " + name + " is null.";
		}
		else {
			return "A Service-type of an activity is null.";
		}
	}
}
