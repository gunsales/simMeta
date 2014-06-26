package de.lsem.simulation.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import de.lsem.repository.model.simulation.IConditionalRelation;

import static de.lsem.simulation.features.ConditionalRelationLabelHelper.*;

public class AddConditionalRelationFeature extends AddAbstractRelationFeature {

	public AddConditionalRelationFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canAdd(IAddContext context) {
		return true;//super.canAdd(context);
	}

	@Override
	public PictogramElement add(IAddContext context) {
		IAddConnectionContext addConContext = (IAddConnectionContext) context;
		IConditionalRelation conditionalRelation = 
				(IConditionalRelation) context.getNewObject();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		// CONNECTION WITH POLYLINE
		Connection connection = peCreateService
				.createFreeFormConnection(getDiagram());

		connection.setStart(addConContext.getSourceAnchor());
		connection.setEnd(addConContext.getTargetAnchor());

		IGaService gaService = Graphiti.getGaService();
		Polyline polyline = gaService.createPolyline(connection);
		polyline.setLineWidth(2);
		polyline.setForeground(manageColor(E_REFERENCE_COND_FOREGROUND));

		// create link and wire it
		link(connection, conditionalRelation);

		ConnectionDecorator textDecorator =
				peCreateService.createConnectionDecorator(connection, true,
						0.5, true);
		Text text = gaService.createText(textDecorator);
		text.setForeground(manageColor(IColorConstant.BLACK));
		gaService.setLocation(text, 10, 0);

		// set reference name in the text decorator
		text.setValue(createProbabilityLabel(conditionalRelation));

		// add static graphical decorator (composition and navigable)
		ConnectionDecorator cd;
		cd = peCreateService
				.createConnectionDecorator(connection, false, 1.0, true);
		createArrow(cd, E_REFERENCE_COND_FOREGROUND);

		return connection;
	}
	
	
}
