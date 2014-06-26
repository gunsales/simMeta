package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.simulation.validation.exception.ServiceTypeNullException;
import de.lsem.simulation.validation.exception.ValidationException;

public class ActivityValidator extends NameValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject element) {
		List<ValidationException> exceptionList = super.validate(element);

		if (!(element instanceof IActivity)) {
			return exceptionList;
		}

		IActivity activity = (IActivity) element;

		EList<IActivity> subActivities = activity.getSubActivities();
		ITime timePeriod = activity.getTimePeriod();

		// Check capacity
		CapacityValidator cv = new CapacityValidator();
		exceptionList.addAll(cv.validate(activity));
		
		// Check outgoing relations
		OutgoingRelationValidator orv = new OutgoingRelationValidator();
		exceptionList.addAll(orv.validate(activity));
		
		// Check service-type
		exceptionList.addAll(checkServiceType(activity));
		
		// Check sub-activities
		for(IActivity subActivity : subActivities){
			ActivityValidator activityValidator = new ActivityValidator();
			exceptionList.addAll(activityValidator.validate(subActivity));
		}
			
		// Check timePeriod
		TimePeriodValidator tpv = new TimePeriodValidator(activity);
		exceptionList.addAll(tpv.validate(timePeriod));
		
		
		return exceptionList;
	}

	private List<ValidationException> checkServiceType(IActivity activity) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		if ( activity.getServiceType() == null ){
			retVal.add(new ServiceTypeNullException(activity, "Service-type equals null"));
		}
		return retVal;
	
	}

}
