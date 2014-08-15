package de.lsem.simulation.metaModel.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;

public class ElementNameNotNullConstraint extends AbstractModelConstraint {

	public ElementNameNotNullConstraint() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IStatus validate(IValidationContext ctx) {

		EObject target = ctx.getTarget();
		EMFEventType eventType = ctx.getEventType();

		// IF batch mode, EventType is null
		if (eventType == EMFEventType.NULL) {
			String name = null;
			if (target instanceof ISource) {
				name = ((ISource) target).getName();
			} else if (target instanceof IActivity) {
				name = ((IActivity) target).getName();
			} else if (target instanceof ISink) {
				name = ((ISink) target).getName();
			} else if (target instanceof IRelation) {
				name = ((IRelation) target).getName();
			}

			if (name == null || name.length() == 0) {
				return ctx.createFailureStatus(new Object[] { target.eClass()
						.getName() });
			}
		} else {
			Object newValue = ctx.getFeatureNewValue();

			if (newValue == null || ((String) newValue).length() == 0) {
				return ctx.createFailureStatus(new Object[] { target.eClass()
						.getName() });
			}
		}

		return ctx.createSuccessStatus();
	}
}
