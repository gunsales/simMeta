package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.QueuingStrategy;
import de.lsem.simulation.validation.exception.StorageException;
import de.lsem.simulation.validation.exception.ValidationException;

public class CapacityValidator implements IValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject object) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		if(object instanceof IActivity){
			retVal = validate((IActivity)object, retVal);
		}
		return retVal;
	}
	
	private List<ValidationException> validate(IActivity activity, List<ValidationException> exceptions){
		
		ICapacity capacity = activity.getCapacity();
		
		int maxCapacity = capacity.getMaxCapacity();
		QueuingStrategy queueStrategy = capacity.getQueueStrategy();
		
		if(maxCapacity < 0){
			exceptions.add(new StorageException(activity, "Max-Capacity should be larger than zero"));
		}
		
		if(queueStrategy == null){
			exceptions.add(new StorageException(activity, "Queue-Strategy equals NULL. Please fix this."));
		}
		
		return exceptions;
	}

}
