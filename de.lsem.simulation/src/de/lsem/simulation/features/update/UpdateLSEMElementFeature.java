package de.lsem.simulation.features.update;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;

/**
 * Update-feature only updates changes from business-object to pictogram-element
 * (One-way only).
 * 
 * @author Lewin
 * 
 */
public class UpdateLSEMElementFeature extends AbstractUpdateFeature {

	private static final Logger logger = Logger
			.getLogger(UpdateLSEMElementFeature.class.getSimpleName());

	public UpdateLSEMElementFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {

		Object obj = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		if (obj instanceof ISimulationElement) {
			ISimulationElement s = (ISimulationElement) obj;

			return preCheck(s);
		}

		return false;
	}

	/*
	 * Check if name equals another object in editor --> Do not allow, as names
	 * are treated like IDs
	 */
	private boolean preCheck(ISimulationElement obj) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		List<ISimulationElement> filteredContents = filterSimulationElements(contents);

		for (ISimulationElement e : filteredContents) {
			if (!obj.equals(e) && e.getName().equals(obj.getName())){
				return false;
			}

			for (IRelation r : e.getOutgoing()) {
				// System.out.println("" + r.getName() + " " + obj.getName());
				if (r.getName() != null && obj.getName() != null
						&& r.getName().equals(obj.getName())){
					return false;
				}
			}
		}

		return true;
	}

	private List<ISimulationElement> filterSimulationElements(
			EList<EObject> contents) {

		List<ISimulationElement> retVal = new ArrayList<ISimulationElement>();

		for (EObject e : contents) {
			if (e instanceof ISimulationElement) {
				retVal.add((ISimulationElement) e);
			}
		}

		return retVal;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		// From PE-Side
		String pictogramString = null;
		PictogramElement pe = context.getPictogramElement();

		if (pe instanceof ContainerShape) {
			ContainerShape shape = (ContainerShape) pe;
			for (Shape s : shape.getChildren()) {

				if (s.getGraphicsAlgorithm() instanceof Text) {
					Text text = (Text) s.getGraphicsAlgorithm();
					pictogramString = text.getValue();
				}
			}
		}

		// From BO-Side
		String boString = null;
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof ISimulationElement) {
			boString = ((ISimulationElement) bo).getName();
		}

		// Check if element label needs to be updated from business-object
		if (
		// Case 1 - Label is not set in editor but exist in business-object
		(boString != null && pictogramString == null)
		// Case 2 - name of business-object != label
				|| (pictogramString != null && !pictogramString
						.equals(boString))) {
			return Reason.createTrueReason("Name is out of date");
		}

		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		String businessString = null;
		PictogramElement pe = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pe);
		if (bo instanceof IActivity) {

			resetPositionOfSubActivities((IActivity) bo);

			PictogramElement pictogramElementForBusinessObject = getFeatureProvider()
					.getPictogramElementForBusinessObject(bo);

			if (((IActivity) bo).getSubActivities().size() > 3) {
				pictogramElementForBusinessObject.getGraphicsAlgorithm()
						.setHeight(
								50 + 50 * ((IActivity) bo).getSubActivities()
										.size());
			}
			log("Updated activity");
			// }
		}
		if (bo instanceof ISimulationElement) {
			ISimulationElement s = (ISimulationElement) bo;
			businessString = s.getName();
		}
		if (pe instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pe;

			for (Shape s : cs.getChildren()) {
				if (s.getGraphicsAlgorithm() instanceof Text) {
					Text t = (Text) s.getGraphicsAlgorithm();
					t.setValue(businessString);
					return true;
				} else if (s.getGraphicsAlgorithm() instanceof Connection) {

					Connection con = (Connection) s.getGraphicsAlgorithm();
					for (ConnectionDecorator cd : con.getConnectionDecorators()) {
						if (cd.getGraphicsAlgorithm() instanceof Text) {

							Text t = (Text) cd.getGraphicsAlgorithm();
							t.setValue(businessString);
						}
					}
					return true;
				}
			}
		}
		return true;
	}

	private void resetPositionOfSubActivities(IActivity activity) {
		int counter = 0;
		for (IActivity a : activity.getSubActivities()) {
			PictogramElement pictogramElement = getFeatureProvider()
					.getPictogramElementForBusinessObject(a);
			if (pictogramElement != null) {

				pictogramElement.getGraphicsAlgorithm().setY(
						(50 * counter) + 25);

				log("resetting name:" + a.getName() + " counter:" + counter
						+ " y-coord.:"
						+ pictogramElement.getGraphicsAlgorithm().getY());

				counter++;
			}
		}

		log("Position of sub-activities recalculated.");
	}

	private void log(String msg) {
		logger.log(Level.INFO, msg);
	}

}
