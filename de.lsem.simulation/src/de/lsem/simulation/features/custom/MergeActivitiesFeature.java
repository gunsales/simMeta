package de.lsem.simulation.features.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.util.LSEMElementHelper;

public class MergeActivitiesFeature extends AbstractCustomFeature {

	public MergeActivitiesFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Merge";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pictogramElements = context.getPictogramElements();
		if (pictogramElements != null && pictogramElements.length > 1) {
			for (PictogramElement p : pictogramElements) {
				Object element = getBusinessObjectForPictogramElement(p);
				// Merging only allowed with Activities
				if (!(element instanceof IActivity)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {

		PictogramElement[] pictogramElements = context.getPictogramElements();

		// if pictogram-elements are selected
		if (pictogramElements != null && pictogramElements.length > 0) {

			// get selected
			ArrayList<PictogramElement> selectedPictogramElements = new ArrayList<PictogramElement>(
					Arrays.asList(pictogramElements));

			List<IActivity> businessObjects = getBusinessObjectsFor(pictogramElements);

			if (businessObjects.isEmpty())
				return;

			IActivity initialActivity = businessObjects.get(0);

			selectedPictogramElements.remove(getFeatureProvider()
					.getPictogramElementForBusinessObject(initialActivity));

			MoveActivityOnAnotherFeature moveActivityFeature = getMoveActivityFeature(context);
			PictogramElement sourcePictogramElement = getFeatureProvider()
					.getPictogramElementForBusinessObject(initialActivity);

			// TODO merging wents wrong, if at least three elements are merged
			// and the last selected element is a mother-activity. Problem
			// occurs because newly created mother-activity is not recognized in
			// algorithm, but old sub-activity
			for (PictogramElement p : selectedPictogramElements) {

				IActivity comparingActivity = (IActivity) getBusinessObjectForPictogramElement(p);

				boolean initialIsMotherActivity = LSEMElementHelper
						.hasSubActivities(initialActivity);
				boolean comparingIsMotherActivity = LSEMElementHelper
						.hasSubActivities(comparingActivity);

//				System.out.println(initialIsMotherActivity + " "
//						+ comparingIsMotherActivity);
				// Case 1 : initial & comparing are atomic
				if (!initialIsMotherActivity && !comparingIsMotherActivity) {
					initialActivity = moveActivityFeature.atomarOnAtomarCase(
							sourcePictogramElement, initialActivity,
							comparingActivity);
				}
				// Case 2 : initial XOR comparing are atomic
				else if (initialIsMotherActivity != comparingIsMotherActivity) {
					if (initialIsMotherActivity) {
						initialActivity = moveActivityFeature
								.notAtomarOnAtomarCase(initialActivity,
										comparingActivity);
					} else {
						initialActivity = moveActivityFeature
								.notAtomarOnAtomarCase(comparingActivity,
										initialActivity);
					}
				}
				// Case 3 : initial and comparing are not atomic
				else {
					initialActivity = moveActivityFeature
							.notAtomarOnNotAtomarCase(sourcePictogramElement,
									initialActivity, comparingActivity);
				}
				if (p != null && p.getGraphicsAlgorithm() != null) {
					PictogramElement newPicto = getFeatureProvider().getPictogramElementForBusinessObject(initialActivity);
					sourcePictogramElement = setXandY(newPicto, p);
				}
			}

		}
	}

	private PictogramElement setXandY(
			PictogramElement sourcePicto, PictogramElement targetPicto) {
		int x = sourcePicto.getGraphicsAlgorithm().getX();
		int y = sourcePicto.getGraphicsAlgorithm().getY();
		targetPicto.getGraphicsAlgorithm().setX(x);
		targetPicto.getGraphicsAlgorithm().setY(y);
		return targetPicto;
	}

	private MoveActivityOnAnotherFeature getMoveActivityFeature(
			ICustomContext context) {
		PictogramElement pictogramElement = context.getPictogramElements()[0];
		MoveShapeContext moveContext = new MoveShapeContext(
				(Shape) pictogramElement);
		return (MoveActivityOnAnotherFeature) getFeatureProvider()
				.getMoveShapeFeature(moveContext);
	}

	private List<IActivity> getBusinessObjectsFor(
			PictogramElement[] pictogramElements) {
		List<IActivity> elementList = new ArrayList<IActivity>();
		for (PictogramElement p : pictogramElements) {
			Object element = getBusinessObjectForPictogramElement(p);
			// Instance-of already tested in canExecute
			elementList.add((IActivity) element);
			// System.out.println(">> " + ((IActivity) element).getName());
		}
		return elementList;
	}

}
