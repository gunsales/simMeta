package de.lsem.simulation.features.update;

import static de.lsem.simulation.features.ConditionalRelationLabelHelper.createConditionalLabel;
import static de.lsem.simulation.features.ConditionalRelationLabelHelper.createProbabilityLabel;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.lsem.repository.model.simulation.IConditionalRelation;

public class UpdateConditionalRelationFeature extends AbstractUpdateFeature {

	public UpdateConditionalRelationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return (bo instanceof IConditionalRelation);

	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		return Reason.createTrueReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		// retrieve probability or condition from business model
		String conRelLabel = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		IConditionalRelation conRel = (IConditionalRelation) bo;
		// If there is a condition, use the condition
		if (conRel.getCondition() != null && !conRel.getCondition().isEmpty()) {
			conRelLabel = createConditionalLabel(conRel);
		} else /* if there is no condition, use the probability */{
			conRelLabel = createProbabilityLabel(conRel);
		}

		// Set probability or condition in diagram
		// Not updated in Diagram!!
		if (pictogramElement instanceof FreeFormConnection) {
			FreeFormConnection ffc = (FreeFormConnection) pictogramElement;

			for (ConnectionDecorator dec : ffc.getConnectionDecorators()) {
				if (dec.getGraphicsAlgorithm() instanceof Text) {
					Text container = (Text) dec.getGraphicsAlgorithm();
					container.setValue(conRelLabel);
					return true;
				}
			}
		}

		return false;
	}
}
