package de.lsem.simulation.validation.validators;

import java.util.List;

import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.simulation.validation.exception.ValidationException;

public class SinkValidator extends NameValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject element) {
		List<ValidationException> retVal = super.validate(element);
		
		if(!(element instanceof ISink)){
			return retVal;
		}
		
		// TODO implement check outgoing-relation-list must be empty
		
		return retVal;
	}
}
