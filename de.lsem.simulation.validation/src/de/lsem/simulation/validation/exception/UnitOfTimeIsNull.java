package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;

public class UnitOfTimeIsNull extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932580805146618213L;

	public UnitOfTimeIsNull(ISimulationObject element, String reason) {
		super(element, reason);
	}
	
	@Override
	public String getMessage() {
		
		if(getElement() instanceof ISimulationElement){
			ISimulationElement element = (ISimulationElement) getElement();
			String name = element.getName() == null ? element.toString() : element.getName();
			
			return "Element \"" + name + "\" has a time-unit, which is not set. Please set the time-unit.";
		}
		
		return "In one process-element a time-unit is not set. Please fix this."; 
	}

}
