package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;

public class UniqueNameException extends ValidationException {

	public UniqueNameException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6026014148847861863L;

	@Override
	public String getMessage() {
		try {
			ISimulationElement simElement = (ISimulationElement) getElement();
			return "The name of element \""
					+ simElement.getName()
					+ "\" is not unique. Please give it a unique name.";
//					+ "Reason: " + getReason();
		} catch (Exception e) {
			return "";
		}
	}

}
