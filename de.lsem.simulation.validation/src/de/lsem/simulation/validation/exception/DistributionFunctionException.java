package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationElement;

public class DistributionFunctionException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4296457708960706159L;

	public DistributionFunctionException(ISimulationElement element,
			String reason) {
		super(element, reason);
	}
	
	@Override
	public String getMessage() {
		return "The distribution-function in element " + getElement().getName() + " contains (an) error(s). Please fix the error(s).";
	}
	
	@Override
	public ISimulationElement getElement() {
		return (ISimulationElement) super.getElement();
	}

}
