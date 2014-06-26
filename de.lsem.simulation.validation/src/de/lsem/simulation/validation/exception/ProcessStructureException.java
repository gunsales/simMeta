package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.ISimulationObject;

public class ProcessStructureException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8938796070562311325L;

	public ProcessStructureException(ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {
		return "The process-structure is not valid. A process contains one or more sources, activities and one or more sinks all linked to eachother.";
	}

}
