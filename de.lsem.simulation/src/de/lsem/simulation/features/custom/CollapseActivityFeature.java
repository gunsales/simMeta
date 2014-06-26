package de.lsem.simulation.features.custom;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.util.LSEMElementHelper;

public class CollapseActivityFeature extends AbstractCustomFeature {

	public CollapseActivityFeature(IFeatureProvider fp) {

		super(fp);
	}

	@Override
	public String getName() {
		return "Expand";
	}

	@Override
	public void execute(ICustomContext context) {
		PictogramElement[] pictogramElements = context.getPictogramElements();

		if (pictogramElements != null && pictogramElements.length == 1) {
			PictogramElement pictogramElement = pictogramElements[0];
			Object businessObject = getBusinessObjectForPictogramElement(pictogramElement);

			if (businessObject instanceof IActivity) {
				EList<EObject> contents = getDiagram().eResource().getContents();
				IActivity dummy = (IActivity) businessObject;
				boolean isSubActivity = LSEMElementHelper.isSubActivity(contents, dummy);
				if (!isSubActivity) {
					createColapseKlapse(pictogramElement);
				}
			}
		}
	}

	public void createColapseKlapse(PictogramElement pe) {
		ContainerShape cs = (ContainerShape) pe;
		int width = pe.getGraphicsAlgorithm().getWidth();
		int height = pe.getGraphicsAlgorithm().getHeight();

		// #################################################
		int changeWidth = 120;
		int changeHeight = 210;
		Object object = getBusinessObjectForPictogramElement(pe);
		if (object instanceof IActivity){
			if (((IActivity)object).getSubActivities().size() > 3 ) {
				changeHeight = ((IActivity)object).getSubActivities().size() * 50 + 50;
			}
		}
		// #################################################

		boolean visible = false;
		if (Graphiti.getPeService().getPropertyValue(pe, "isCollapsed") == null
				|| Graphiti.getPeService().getPropertyValue(pe, "isCollapsed")
				.equals("false")) {

			Graphiti.getPeService().setPropertyValue(pe, "initial_width",
					String.valueOf(width));
			Graphiti.getPeService().setPropertyValue(pe, "initial_height",
					String.valueOf(height));
			visible = true;
		} else if (Graphiti.getPeService().getPropertyValue(pe, "isCollapsed") != null
				&& Graphiti.getPeService().getPropertyValue(pe, "isCollapsed")
				.equals("true")) {
			changeWidth = Integer.parseInt(Graphiti.getPeService()
					.getPropertyValue(pe, "initial_width"));
			changeHeight = Integer.parseInt(Graphiti.getPeService()
					.getPropertyValue(pe, "initial_height"));
			Graphiti.getPeService()
			.setPropertyValue(pe, "isCollapsed", "false");
			visible = false;
		}

		ResizeShapeContext context1 = new ResizeShapeContext(cs);
		context1.setSize(changeWidth, changeHeight);
		context1.setLocation(cs.getGraphicsAlgorithm().getX(), cs
				.getGraphicsAlgorithm().getY());
		IResizeShapeFeature rsf = getFeatureProvider().getResizeShapeFeature(
				context1);
		if (rsf.canExecute(context1)) {
			rsf.execute(context1);
		}

		if (visible) {
			Graphiti.getPeService().setPropertyValue(pe, "isCollapsed", "true");
		}
		// visible/invisible all the children
		makeChildrenInvisible(cs, visible);
	}

	/**
	 * Recursive function that makes all the children inside a shape
	 * visible/invisible
	 * 
	 * @param cs
	 *            ContainerShape
	 * @param visible
	 *            true/false
	 */
	public void makeChildrenInvisible(ContainerShape cs, boolean visible) {
		if (cs.getChildren().isEmpty()) {
			return;
		} else {
			Iterator<Shape> iter = cs.getChildren().iterator();
			while (iter.hasNext()) {
				Shape shape = iter.next();

				if (shape instanceof ContainerShape) { // It is another shape
					makeChildrenInvisible((ContainerShape) shape, visible);

					shape.setVisible(visible);
					boolean initVisible = false;

					// Check whether the initial shape is visible or not
					for (Shape shape1 : ((ContainerShape) shape).getChildren()) {
						if (shape1.getGraphicsAlgorithm() instanceof Ellipse) {
							initVisible = shape1.isVisible();
						}
					}

					if (shape.getAnchors().size() > 0) {
						Anchor anchr = shape.getAnchors().get(0);

						changeIncommingConnectionVisibility(visible,
								initVisible, anchr);
						changeOutgoingConnectionVisibility(visible, anchr);
					}
				}
			}
		}
	}

	protected void changeIncommingConnectionVisibility(boolean visible,
			boolean initVisible, Anchor anchr) {
		for (int i = 0; i < anchr.getIncomingConnections()
				.size(); i++) {
			Connection conn = anchr.getIncomingConnections()
					.get(i);
			if (initVisible) { // Change visibility only to
				// visible connections
				conn.setVisible(visible);
				for (int j = 0; j < conn
						.getConnectionDecorators().size(); j++) {
					conn.getConnectionDecorators().get(j)
					.setVisible(visible);
				}
			}
		}
	}

	protected void changeOutgoingConnectionVisibility(boolean visible,
			Anchor anchr) {
		for (int i = 0; i < anchr.getOutgoingConnections()
				.size(); i++) {

			Connection conn = anchr.getOutgoingConnections()
					.get(i);
			conn.setVisible(visible);
			for (int j = 0; j < conn.getConnectionDecorators()
					.size(); j++) {
				conn.getConnectionDecorators().get(j)
				.setVisible(visible);
			}
		}
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pictogramElements = context.getPictogramElements();

		if (pictogramElements != null && pictogramElements.length == 1) {
			Object pictogramElement = getBusinessObjectForPictogramElement(pictogramElements[0]);

			return pictogramElement instanceof IActivity;
		}

		return false;
	}

	@Override
	public boolean isAvailable(IContext context) {
		return true;
	}

	public void update(PictogramElement pe) {
		createColapseKlapse(pe);
	}

}
