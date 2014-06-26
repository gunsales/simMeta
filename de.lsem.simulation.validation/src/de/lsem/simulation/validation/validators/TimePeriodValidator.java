package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.simulation.validation.exception.UnitOfTimeIsNull;
import de.lsem.simulation.validation.exception.ValidationException;

public class TimePeriodValidator implements IValidator {

	private ISimulationElement superElement;

	public TimePeriodValidator(ISimulationElement superElement) {

		this.superElement = superElement;
	}

	@Override
	public List<ValidationException> validate(ISimulationObject object) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();

		if (!(object instanceof ITime)) {
			return retVal;
		}

		ITime time = (ITime) object;
		IDistribution period = time.getPeriod();

		// Check period
		retVal.addAll(check(period));

		// Check unit
		retVal.addAll(checkUnitOfTime(time));

		return retVal;
	}

	private List<ValidationException> checkUnitOfTime(ITime time) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		if (time.getUnit() == null) {
			retVal.add(new UnitOfTimeIsNull(time, "Unit of time equals null."));
		}
		return retVal;
	}

	private List<ValidationException> check(IDistribution period) {
		// Differentiate between function and constant value
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		if (period instanceof IConstant) {
			// IConstant constant = (IConstant) period;
			// constant.getValue();
			// No action to consider
		} else if (period instanceof IDistributionFunction) {
			IDistributionFunction distFunc = (IDistributionFunction) period;
			DistributionFunctionValidator distFuncVal = new DistributionFunctionValidator(
					superElement);
			retVal.addAll(distFuncVal.validate(distFunc));
		}

		return retVal;
	}

}
