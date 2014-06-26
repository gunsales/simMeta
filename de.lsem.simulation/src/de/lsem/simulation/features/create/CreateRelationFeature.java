package de.lsem.simulation.features.create;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.LSEMElementHelper;

public class CreateRelationFeature extends AbstractCreateConnectionFeature {

	public CreateRelationFeature(IFeatureProvider fp) {
		super(fp, "Relation", "Regular Connection");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// One of the elements is a sub-activity
		return constraintsFulfilled(context);
	}

	private boolean constraintsFulfilled(ICreateConnectionContext context) {

		Object sourcePictogramElement = getBusinessObjectForPictogramElement(context
				.getSourcePictogramElement());

		Object targetPictogramElement = getBusinessObjectForPictogramElement(context
				.getTargetPictogramElement());
		
		if ( !(sourcePictogramElement instanceof ISimulationElement) || !(targetPictogramElement instanceof ISimulationElement)){
			return false;
		}

		ISimulationElement source = (ISimulationElement) sourcePictogramElement;
		ISimulationElement target = (ISimulationElement) targetPictogramElement;

		Anchor sourceAnchor = context.getSourceAnchor();
		Anchor targetAnchor = context.getTargetAnchor();
		
		boolean isSub = isSourceOrTargetSubActivity(source, target);
		// A Relation already exists
		boolean alreadyConnected = checkIfConnected(source, target);
		boolean elementsNotNull = sourceAnchor != null && targetAnchor != null;
		boolean anchorsNotEqual = sourceAnchor != targetAnchor;
		boolean sourceNotSink = !(source instanceof ISink);
		boolean targetNotSource = !(target instanceof ISource);

		return (targetAnchor != null && !isSub && elementsNotNull
				&& anchorsNotEqual && sourceNotSink && targetNotSource && !alreadyConnected);
	}

	private boolean checkIfConnected(ISimulationElement source,
			ISimulationElement target) {

		if (target == null) {
			return false;
		}
		for (IRelation r : source.getOutgoing()) {
			if (r.getTarget().getName().equals(target.getName())) {
				return true;
			}
		}
		return false;
	}

	private boolean isSourceOrTargetSubActivity(ISimulationElement source,
			ISimulationElement target) {

		EList<EObject> contents = getDiagram().eResource().getContents();

		if (source instanceof IActivity) {
			boolean sourceIsSub = LSEMElementHelper.isSubActivity(contents,
					(IActivity) source);
			if (sourceIsSub)
				return true;
		}
		if (target instanceof IActivity) {
			boolean targetIsSub = LSEMElementHelper.isSubActivity(contents,
					(IActivity) target);
			if (targetIsSub)
				return true;
		}

		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		ISimulationElement source = getLSEMElement(context.getSourceAnchor());
		ISimulationElement target = getLSEMElement(context.getTargetAnchor());

		if (source != null && target != null) {
			IRelation cr = createRelation(source, target);

			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());

			addContext.setNewObject(cr);
			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);
		}

		return newConnection;
	}

	private IRelation createRelation(ISimulationElement source,
			ISimulationElement target) {
		IRelation con = SimulationFactory.eINSTANCE.createRelation();
		con.setName(createRelationName(getDiagram()));
		con.setSource(source);
		con.setTarget(target);

		return con;
	}

	public static String createRelationName(Diagram diagram) {
		return "Relation_" + getInitialRelationNumber(diagram);
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		boolean isSourceAnchorNull = context.getSourceAnchor() == null;

		// If sourceAnchor is the diagram itself
		if (isSourceAnchorNull) {
			return false;
		}

		// Sub-Activity can not be a start-element for one connection. Hence,
		// return false if business-object for sourceAnchor is a sub-activity
		if (getLSEMElement(context.getSourceAnchor()) instanceof IActivity) {

			IActivity source = (IActivity) getLSEMElement(context
					.getSourceAnchor());
			EList<EObject> contents = getDiagram().eResource().getContents();
			boolean sourceIsSub = LSEMElementHelper.isSubActivity(contents,
					source);
			if (sourceIsSub) {
				return false;
			}
		}

		return true;
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

	public static String getInitialRelationNumber(Diagram diagram) {
		EList<EObject> contents = diagram.eResource().getContents();
		List<IRelation> relationsFromDiagram = LSEMElementHelper
				.getRelationsFromDiagram(contents);

		return String.valueOf(relationsFromDiagram.size() + 1);
	}

}
