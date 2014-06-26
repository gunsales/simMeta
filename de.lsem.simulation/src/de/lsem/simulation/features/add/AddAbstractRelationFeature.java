package de.lsem.simulation.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.lsem.repository.model.simulation.IRelation;

public class AddAbstractRelationFeature extends AbstractAddFeature {

	protected static final IColorConstant E_REFERENCE_COND_FOREGROUND = new ColorConstant(
			178, 034, 034);
	protected static final IColorConstant E_REFERENCE_COND_BACK = new ColorConstant(
			220, 220, 220);
	protected static final IColorConstant E_REFERENCE_COND_FORE = new ColorConstant(
			180, 180, 180);
	protected static final IColorConstant E_REFERENCE_RELATION_FOREGROUND = new ColorConstant(
			105, 105, 105);

	public AddAbstractRelationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (isAddConnectionContext(context) && isNewObjectRelation(context))
//				&& !isSourceAlreadyConnectedToTarget(context))
			return true;
		return false;
	}

	private boolean isNewObjectRelation(IAddContext context) {
		return context.getNewObject() instanceof IRelation;
	}

	private boolean isAddConnectionContext(IAddContext context) {
		return context instanceof IAddConnectionContext;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		return null;
	}

	protected Polyline createArrow(GraphicsAlgorithmContainer gac,
			IColorConstant foregroundColor) {
		IGaService gaService = Graphiti.getGaService();
		Polyline polyline = gaService.createPolyline(gac, new int[] { -15, 5,
				2, 0, -15, -5 });
		polyline.setForeground(manageColor(foregroundColor));
		polyline.setLineWidth(2);
		return polyline;
	}

}
