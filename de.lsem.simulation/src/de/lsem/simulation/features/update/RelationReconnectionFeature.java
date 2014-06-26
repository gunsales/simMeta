package de.lsem.simulation.features.update;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;
import org.eclipse.graphiti.features.impl.DefaultReconnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;

public class RelationReconnectionFeature extends DefaultReconnectionFeature {

	private static final Logger logger = Logger.getLogger(RelationReconnectionFeature.class.getSimpleName());

	public RelationReconnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canReconnect(IReconnectionContext context) {
		if ( context.getNewAnchor() == null) {
			return false;
		} else if (context.getTargetPictogramElement() instanceof Diagram ){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postReconnect(IReconnectionContext context) {

		ISimulationElement oldSimulationElement = getOldBusinessObject(context);
		ISimulationElement newSimulationElement = getNewBusinessObject(context);
		IRelation relation = (IRelation) getBusinessObjectForPictogramElement(context.getConnection());

		//Source has changed
		if ( context.getReconnectType().equals(ReconnectionContext.RECONNECT_SOURCE) ) {
			log("Source has changed. Relation source now is: " + newSimulationElement + ". Target is: " + relation.getTarget());
			oldSimulationElement.getOutgoing().remove(relation);
			relation.setSource(newSimulationElement);
			addConnectionTo(newSimulationElement, relation);			
		} /*Target has changed*/else if (context.getReconnectType().equals(ReconnectionContext.RECONNECT_TARGET)) {
			log("Target has changed. Relation source is: " + relation.getSource() + ". Target is: " + newSimulationElement);
			relation.setTarget(newSimulationElement);
		}

		super.postReconnect(context);
	}

	private void addConnectionTo(ISimulationElement newSimulationElement, IRelation r) {
		newSimulationElement.getOutgoing().add(r);
	}

	private ISimulationElement getNewBusinessObject(IReconnectionContext context) {

		Anchor newAnchor = context.getNewAnchor();
		ContainerShape containerShape = (ContainerShape)newAnchor.eContainer();
		return (ISimulationElement) getBusinessObjectForPictogramElement(containerShape);
	}

	private ISimulationElement getOldBusinessObject(IReconnectionContext context) {

		Anchor oldAnchor = context.getOldAnchor();
		ContainerShape containerShape = (ContainerShape)oldAnchor.eContainer();
		return (ISimulationElement) getBusinessObjectForPictogramElement(containerShape);
	}

	private static void log(String msg) {
		logger.log(Level.INFO, msg);
	}

}
