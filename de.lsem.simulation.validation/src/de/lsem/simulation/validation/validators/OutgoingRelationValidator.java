package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.validation.exception.ConditionalRelationException;
import de.lsem.simulation.validation.exception.MissingOutgoingRelationException;
import de.lsem.simulation.validation.exception.SingleOutgoingRelationIsConditionalException;
import de.lsem.simulation.validation.exception.OutgoingNotConditionalRelationException;
import de.lsem.simulation.validation.exception.ValidationException;

public class OutgoingRelationValidator implements IValidator {

	@Override
	public List<ValidationException> validate(ISimulationObject object) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();
		List<IRelation> outgoing = new ArrayList<IRelation>();

		if (object instanceof ISource) {
			ISource source = (ISource) object;
			outgoing = source.getOutgoing();

		} else if (object instanceof IActivity) {
			IActivity activity = (IActivity) object;
			outgoing = activity.getOutgoing();
		}

		// Source and activity must have outgoing relations
		if (outgoing.size() == 0) {
			retVal.add(new MissingOutgoingRelationException(object,
					"There must be outgoing relations."));
		}

		// if multiple relations --> all must be ConditionalRelations
		if (outgoing.size() > 1) {
			retVal.addAll(checkAllRelationsAreConditional(outgoing));
		} else if (outgoing.size() == 1) {
			retVal.addAll(checkOnlyRelationIsUsual(outgoing));
		}

		// check names of relations
		retVal.addAll(checkRelationNames(outgoing));

		// if conditionalRelations --> check condition/probability
		retVal.addAll(checkConditionalConditionAndProbability(outgoing));

		return retVal;

	}

	private List<ValidationException> checkOnlyRelationIsUsual(
			List<IRelation> outgoing) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		if (outgoing.get(0) instanceof IConditionalRelation) {
			retVal.add(new SingleOutgoingRelationIsConditionalException(
					outgoing.get(0),
					"Element "
							+ outgoing.get(0).getName()
							+ " is the only outgoing relation, which should be a usual relation instead of a conditional-relation."));
		}

		return retVal;
	}

	private List<ValidationException> checkConditionalConditionAndProbability(
			List<IRelation> outgoing) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();

		for (IRelation r : outgoing) {
			if (!(r instanceof IConditionalRelation)) {
				return retVal;
			}

			IConditionalRelation cr = (IConditionalRelation) r;

			String condition = cr.getCondition();
			double probability = cr.getProbability();

			if (condition == null) {
				retVal.add(new ConditionalRelationException(cr,
						"Condition is NULL"));
			}
			if (probability < 0.0 || probability > 100) {
				retVal.add(new ConditionalRelationException(cr,
						"Probability is < 0 or > 100"));
			}

		}

		return retVal;
	}

	private List<ValidationException> checkRelationNames(
			List<IRelation> outgoing) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();
		for (IRelation r : outgoing) {
			NameValidator nameValidator = new NameValidator();
			retVal.addAll(nameValidator.validate(r));
		}
		return retVal;
	}

	private List<ValidationException> checkAllRelationsAreConditional(
			List<IRelation> outgoing) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		for (IRelation r : outgoing) {
			if (!(r instanceof IConditionalRelation)) {
				OutgoingNotConditionalRelationException exception = new OutgoingNotConditionalRelationException(
						r,
						"Outgoing-relation not conditional, but multiple exceptions exists from source. Name: "
								+ r.getName());
				retVal.add(exception);
			}
		}

		return retVal;
	}

}
