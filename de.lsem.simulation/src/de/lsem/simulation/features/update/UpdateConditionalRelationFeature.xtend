package de.lsem.simulation.features.update

import org.eclipse.graphiti.features.impl.AbstractUpdateFeature
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IUpdateContext
import de.lsem.repository.model.simulation.IConditionalRelation
import org.eclipse.graphiti.features.impl.Reason

import static de.lsem.simulation.util.ConditionalRelationLabelHelper.*
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection
import org.eclipse.graphiti.mm.algorithms.Text

class UpdateConditionalRelationFeature extends AbstractUpdateFeature {

	new(IFeatureProvider fp) {
		super(fp)
	}

	override canUpdate(IUpdateContext it) {
		val bo = getBusinessObjectForPictogramElement(pictogramElement)
		bo instanceof IConditionalRelation
	}

	override update(IUpdateContext it) {
		val cr = getBusinessObjectForPictogramElement(pictogramElement) as IConditionalRelation

		var conRelLabel = ""
		
		if (cr.condition != null && !cr.condition.empty) {
			conRelLabel = createConditionalLabel(cr)
		} else {
			conRelLabel = createProbabilityLabel(cr)
		}

		if (pictogramElement instanceof FreeFormConnection) {
			val ffc = pictogramElement as FreeFormConnection

			val cd = ffc.connectionDecorators.findFirst[dec|dec.graphicsAlgorithm instanceof Text]
			val text = cd.graphicsAlgorithm as Text
			text.value = conRelLabel
			return true
		}
		return false
		
//		String conRelLabel = null;
//		PictogramElement pictogramElement = context.getPictogramElement();
//		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
//		IConditionalRelation conRel = (IConditionalRelation) bo;
//		// If there is a condition, use the condition
//		if (conRel.getCondition() != null && !conRel.getCondition().isEmpty()) {
//			conRelLabel = createConditionalLabel(conRel);
//		} else /* if there is no condition, use the probability */{
//			conRelLabel = createProbabilityLabel(conRel);
//		}
//
//		// Set probability or condition in diagram
//		// Not updated in Diagram!!
//		if (pictogramElement instanceof FreeFormConnection) {
//			FreeFormConnection ffc = (FreeFormConnection) pictogramElement;
//
//			for (ConnectionDecorator dec : ffc.getConnectionDecorators()) {
//				if (dec.getGraphicsAlgorithm() instanceof Text) {
//					Text container = (Text) dec.getGraphicsAlgorithm();
//					container.setValue(conRelLabel);
//					return true;
//				}
//			}
//		}
//
//		return false;
	}

	override updateNeeded(IUpdateContext context) {
		Reason.createTrueReason
	}

}
