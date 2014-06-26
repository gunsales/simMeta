package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.simulation.validation.SimulationValidator;
import de.lsem.simulation.validation.exception.ElementNullException;
import de.lsem.simulation.validation.exception.UniqueNameException;
import de.lsem.simulation.validation.exception.ValidationException;

public class NameValidator implements IValidator {

	private static final String REASON_NULL_EMPTY = "Name is null or empty";
	private static final String REASON_UNIQUE = "Name is not unique";

	@Override
	public List<ValidationException> validate(ISimulationObject element) {
		
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		
		if(!(element instanceof ISimulationElement)){
			return retVal;
		}
		
		ISimulationElement simElement = (ISimulationElement)element;
		
		String name = simElement.getName();

		// Name empty or null
		if(name == null || "".equals(name)) {
			ElementNullException valExc = new ElementNullException(element, REASON_NULL_EMPTY);
			retVal.add(valExc);
		} 
		// Name not unique
		else if(SimulationValidator.elementNames.contains(name)) {
			UniqueNameException une = new UniqueNameException(element, REASON_UNIQUE);
			retVal.add(une);
		} else {
			SimulationValidator.elementNames.add(name);
		}
		return retVal;
	}


}
