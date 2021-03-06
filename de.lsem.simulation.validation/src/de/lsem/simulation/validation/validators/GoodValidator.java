package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.simulation.validation.exception.AttributeValidationException;
import de.lsem.simulation.validation.exception.ValidationException;

public class GoodValidator implements IValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject object) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();

		if (object instanceof IGood) {
			retVal = validate((IGood) object, retVal);
		}

		return retVal;
	}

	private List<ValidationException> validate(IGood good,
			List<ValidationException> retVal) {

		String description = good.getDescription();
		String name = good.getName();
		String type = good.getType();

		if (null == description || "".equals(description)) {
			AttributeValidationException nve = new AttributeValidationException(good,
					"Description is empty");
			retVal.add(nve);
		}
		if (null == name || "".equals(name)) {
			AttributeValidationException nve = new AttributeValidationException(good,
					"Name is empty");
			retVal.add(nve);
		}
		if (null == type || "".equals(type)) {
			AttributeValidationException nve = new AttributeValidationException(good,
					"Type is empty");
			retVal.add(nve);
		}

		return retVal;
	}

}
