package de.lsem.simulation.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.SimulationFactory;

public class CreateConditionalRelationFeature extends
		AbstractCreateConnectionFeature {

	// private static final String DIALOG_TITLE = "Condition";
	// private static final String DIALOG_MESSAGE = "Specify condition";

	public CreateConditionalRelationFeature(IFeatureProvider fp) {
		super(fp, "Conditional Relation",
				"Connection depending on its call condition");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();

		ISimulationElement targetObject = getBusinessObject(context
				.getTargetPictogramElement());
		ISimulationElement sourceObject = getBusinessObject(context
				.getSourcePictogramElement());

		return (sourceAnchor != null
				&& targetAnchor != null
				&& sourceAnchor != targetAnchor
				&& sourceObject != null
				&& targetObject != null
				&& !(sourceObject instanceof ISink)
				&& !(targetObject instanceof ISource)
				&& !isSourceAlreadyConnectedToTarget(sourceObject, targetObject));
	}

	private ISimulationElement getBusinessObject(
			PictogramElement pictogramElement) {
		try {
			return (ISimulationElement) getBusinessObjectForPictogramElement(pictogramElement);
		} catch (Exception e) { // Catch if not a Simulation Element or null
			return null;
		}
	}

	private boolean isSourceAlreadyConnectedToTarget(
			ISimulationElement sourceObject, ISimulationElement targetObject) {

		for (IRelation r : sourceObject.getOutgoing()) {
			if (targetObject.getName().equals(r.getTarget().getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		ISimulationElement source = getLSEMElement(context.getSourceAnchor());
		ISimulationElement target = getLSEMElement(context.getTargetAnchor());

		if (source != null && target != null) {
			IConditionalRelation cr = createCondRelation(source, target);

			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());

			addContext.setNewObject(cr);
			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);

		}

		// System.out.println(newConnection
		// +
		// " source:"+getBusinessObjectForPictogramElement(newConnection.getStart())
		// + " target:" +
		// getBusinessObjectForPictogramElement(newConnection.getEnd()));
		return newConnection;
	}

	private IConditionalRelation createCondRelation(ISimulationElement source,
			ISimulationElement target) {
		IConditionalRelation con = SimulationFactory.eINSTANCE
				.createConditionalRelation();

		con.setName(CreateRelationFeature.createRelationName(getDiagram()));
		con.setCondition(new String());
		con.setProbability(50);
		con.setSource(source);
		con.setTarget(target);

		return con;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return (context.getSourceAnchor() != null);
	}

	private ISimulationElement getLSEMElement(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof ISimulationElement) {
				return (ISimulationElement) object;
			}
		}
		return null;
	}
}
