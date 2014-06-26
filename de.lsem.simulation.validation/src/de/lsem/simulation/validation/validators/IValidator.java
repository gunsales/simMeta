package de.lsem.simulation.validation.validators;

import java.util.List;

import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.simulation.validation.exception.ValidationException;

public interface IValidator {

	public List<ValidationException> validate(ISimulationObject object);
}
