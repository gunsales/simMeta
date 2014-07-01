package de.lsem.simulation.validation.exception;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;

public class SingleOutgoingRelationIsConditionalException extends
		ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3683931833803878267L;

	public SingleOutgoingRelationIsConditionalException(
			ISimulationObject element, String reason) {
		super(element, reason);
	}

	@Override
	public String getMessage() {

		if (getElement() instanceof ISimulationElement) {
			ISimulationElement element = (ISimulationElement) getElement();
			String name = element.getName() == null ? element.toString()
					: element.getName();

			return "The only outgoing relation of element "
					+ name
					+ " is conditional, but should be a usual relation. Please fix this.";
		} else if (getElement() instanceof IRelation) {
			IRelation r = (IRelation) getElement();

			ISimulationElement source = r.getSource();

			if (source != null) {
				return "The only outgoing relation of element \""
						+ source.getName()
						+ "\" is conditional, but should be a usual relation. Please fix this.";
			}
		}

		return "One element of your process has only one outgoing relation, which is conditional. It should be a usual relation. Please fix this. Object-ID:"
				+ getElement().toString();
	}

}
