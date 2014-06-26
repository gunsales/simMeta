package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.validation.exception.MaxNewEntitiesException;
import de.lsem.simulation.validation.exception.ValidationException;

public class SourceValidator extends NameValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject element) {
		List<ValidationException> exceptionList = super.validate(element);

		if (!(element instanceof ISource)) {
			return exceptionList;
		}

		ISource source = (ISource) element;

		// General section
		exceptionList = validateMaxNewEntities(source);
		// First entities
		exceptionList = validateFirstEntity(source);
		// Following entities
		exceptionList = validateFollowingEntities(source);
		// Good
		exceptionList = validateGood(source);

		return exceptionList;
	}

	private List<ValidationException> validateGood(ISource source) {
		GoodValidator gVal = new GoodValidator();
		IGood good = source.getProcessedObject();
		
		return gVal.validate(good);
	}

	private List<ValidationException> validateFollowingEntities(ISource source) {
		TimePeriodValidator tpv = new TimePeriodValidator(source);
		return tpv.validate(source.getNewEntities());
	}

	private List<ValidationException> validateFirstEntity(ISource source) {
		TimePeriodValidator tpv = new TimePeriodValidator(source);
		return tpv.validate(source.getFirstEntity());
	}

	private List<ValidationException> validateMaxNewEntities(ISource source) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();
		
		int maxNewEntities = source.getMaxNewEntities();
		
		if(maxNewEntities <= 0){
			retVal.add(new MaxNewEntitiesException(source, "Value is 0 or less.")); 
		}
		return retVal;
		
	}

}
