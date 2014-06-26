package de.lsem.simulation.features.add;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.features.custom.CollapseActivityFeature;
import de.lsem.simulation.util.LSEMElementHelper;

public class AddActivityFeature extends AbstractAddShapeFeature {

	public static final IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;

	public static final IColorConstant E_CLASS_FOREGROUND = new ColorConstant(
			105, 105, 105);

	public static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(
			229, 229, 229);

	private static final Integer LINE_WIDTH = 1;

	private static final Logger logger = Logger
			.getLogger(AddActivityFeature.class.getSimpleName());

	private static final int INITIAL_DISTANCE_TO_TOP = 25;

	public AddActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		Object newObject = context.getNewObject();
		ContainerShape targetContainer = context.getTargetContainer();

		if (targetContainer instanceof ContainerShape) {
			if (getBusinessObjectForPictogramElement(targetContainer) instanceof IActivity) {
				IActivity element = (IActivity) getBusinessObjectForPictogramElement(targetContainer);
				boolean isSubActivity = LSEMElementHelper.isSubActivity(
						getDiagram().eResource().getContents(), element);
				if (isSubActivity) {
					return false;
				}
			}
		}

		if (newObject instanceof IActivity)
			// && context.getTargetContainer() instanceof Diagram )
			return true;
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {

		// Add parent activity
		PictogramElement picto = createActivityGraphicalRepresentation(context);

		// Anchor
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		peCreateService.createChopboxAnchor((ContainerShape) picto);
		layoutPictogramElement((ContainerShape) picto);

		return picto;
	}

	public PictogramElement createActivityGraphicalRepresentation(
			IAddContext context) {

		IGaService gaService = Graphiti.getGaService();
		ContainerShape targetContainer;
		int width = 100;
		int height = 50;

		int collapsed_width = 120;
		// int collapsed_height = 210;

		boolean dropOnActivity = false;
		// Drop on diagram
		if (context.getTargetContainer() instanceof Diagram) {
			targetContainer = (Diagram) context.getTargetContainer();
			dropOnActivity = false;

		} else {

			// Drop on Activity
			targetContainer = context.getTargetContainer();

			// Collapse top-activity
			collapseTopActivity(targetContainer);

			dropOnActivity = true;
		}

		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		IActivity newActivity = (IActivity) context.getNewObject();

		ContainerShape containerShape = peCreateService.createContainerShape(
				targetContainer, true);

		{
			RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape,
					5, 5);
			roundedRectangle.setForeground(manageColor(E_CLASS_FOREGROUND));
			roundedRectangle.setBackground(manageColor(E_CLASS_BACKGROUND));
			roundedRectangle.setLineWidth(LINE_WIDTH);

			int x = context.getX();
			int y = context.getY();
			// Set position based on location in top-activity
			if (dropOnActivity) {

				x = 10;
				y = 25;
				IActivity topActivity = (IActivity) getBusinessObjectForPictogramElement(targetContainer);
				if (topActivity != null) {
					y = calculateYCoordinate(newActivity, topActivity);
				}
			}
			gaService.setLocationAndSize(roundedRectangle, x, y, width, height);
//			link(roundedRectangle.getPictogramElement(), newActivity);
		}
		{
			// create shape for line
			Shape shape = peCreateService.createShape(containerShape, false);

			// Create line
			Polyline polyline = gaService.createPolyline(shape, new int[] { 0,
					20, collapsed_width, 20 });
			polyline.setForeground(manageColor(E_CLASS_FOREGROUND));
			polyline.setLineWidth(LINE_WIDTH);

//			 link(polyline.getPictogramElement(), newActivity);
		}
		{
			Shape shape = peCreateService.createShape(containerShape, false);

			Text text = gaService.createText(
					shape,
					newActivity.getName() == null ? newActivity
							.getServiceType().getLiteral() : newActivity
							.getName());
			
//			text.setValue(text.getValue() + " X:" + context.getX() + " Y:" + context.getY());
			text.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));

			// OKTODO Check parameters! Default: text, 0, 0, width, 20
			// OKTODO Test collapsed_width
			gaService.setLocationAndSize(text, 0, 0, width, 20);

//			link(text.getPictogramElement(), newActivity);
		}
		logger.log(Level.INFO, "Fully added activity " + newActivity);

		Graphiti.getPeService().setPropertyValue(containerShape, "isCollapsed",
				"false");
		link(containerShape, newActivity);
		return containerShape;
	}

	protected int calculateYCoordinate(IActivity newActivity,
			IActivity topActivity) {
		int y;
		// Drop from palette
		if (topActivity.getSubActivities().contains(newActivity)) {
			// Drop from activity existing in editor
			// Calculate position depending on the index as
			// sub-activity
			y = (topActivity.getSubActivities().indexOf(newActivity)) * 50
					+ INITIAL_DISTANCE_TO_TOP;
		} else {
			// Append new sub-activity as last sub-activity
			y = (topActivity.getSubActivities().size()) * 50
					+ INITIAL_DISTANCE_TO_TOP;
		}
		return y;
	}

//	protected IActivity addActivityToDiagram(IActivity newActivity) {
//		EList<EObject> contents = getDiagram().eResource().getContents();
//		contents.add(newActivity);
//		contents.add(newActivity.getCapacity());
//		contents.add(newActivity.getTimePeriod());
//		contents.add(newActivity.getTimePeriod().getPeriod());
//
//		return newActivity;
//	}

	protected void collapseTopActivity(ContainerShape targetContainer) {
		CustomContext cc = new CustomContext();
		cc.setInnerPictogramElement(targetContainer);
		ICustomFeature[] customFeatures = getFeatureProvider()
				.getCustomFeatures(cc);
		for (ICustomFeature customFeature : customFeatures) {
			if (customFeature instanceof CollapseActivityFeature) {
				CollapseActivityFeature collapseFeature = (CollapseActivityFeature) customFeature;

				String isCollapsed = Graphiti.getPeService().getPropertyValue(
						targetContainer, "isCollapsed");
				if (isCollapsed != null) {
					boolean collapsed = Boolean.parseBoolean(isCollapsed);
					if (!collapsed)
						collapseFeature.createColapseKlapse(targetContainer);
				}

			}
		}
	}

}
