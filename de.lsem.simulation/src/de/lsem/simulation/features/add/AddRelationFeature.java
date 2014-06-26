package de.lsem.simulation.features.add;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.util.LSEMElementHelper;

public class AddRelationFeature extends AddAbstractRelationFeature {

	public AddRelationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {

		IRelation relation = (IRelation) context.getNewObject();
 
		boolean sourceOrTargetSubActivity = isSourceOrTargetSubActivity(relation.getSource(), relation.getTarget());
		if (sourceOrTargetSubActivity) {
			return false;
		}
		
		return super.canAdd(context);
	}


	private boolean isSourceOrTargetSubActivity(ISimulationElement source,
			ISimulationElement target) {

		EList<EObject> contents = getDiagram().eResource().getContents();

		if ( source instanceof IActivity ) {
			boolean sourceIsSub = LSEMElementHelper.isSubActivity(contents, (IActivity)source);
			if (sourceIsSub)
				return true;
		}  
		if ( target instanceof IActivity ) {
			boolean targetIsSub = LSEMElementHelper.isSubActivity(contents, (IActivity)target);

			if (targetIsSub)
				return true;
		}

		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		IRelation relation = 
				(IRelation) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		// CONNECTION WITH POLYLINE
		Connection connection = peCreateService
				.createFreeFormConnection(getDiagram());

		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());

		IGaService gaService = Graphiti.getGaService();
		Polyline polyline = gaService.createPolyline(connection);
		polyline.setLineWidth(2);
		polyline.setForeground(manageColor(E_REFERENCE_RELATION_FOREGROUND));

		// create link and wire it
		link(connection, relation);

	
		// add static graphical decorator (composition and navigable)
		ConnectionDecorator cd = peCreateService
				.createConnectionDecorator(connection, false, 1.0, true);
		createArrow(cd, E_REFERENCE_RELATION_FOREGROUND);

		return connection;
	}
}
