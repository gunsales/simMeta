package de.lsem.simulation.features.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.context.impl.MultiDeleteInfo;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.util.LSEMElementHelper;

/**
 * This is a workaround-feature for realizing a drag&drop-feature from one
 * activity on another.
 * 
 * @author Lewin
 * 
 */
public class MoveActivityOnAnotherFeature extends DefaultMoveShapeFeature {

	private static final Logger logger = Logger
			.getLogger(MoveActivityOnAnotherFeature.class.getSimpleName());

	public MoveActivityOnAnotherFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {

		PictogramElement targetPicto = context.getTargetContainer();
		Object targetBusiness = getBusinessObjectForPictogramElement(targetPicto);
		Object contextPictogramElement = getBusinessObjectForPictogramElement(context
				.getPictogramElement());

		// True-Case: Pictogram-element moved on another place on diagram,
		// except its a
		// sub-activity
		if (targetBusiness == null
				&& contextPictogramElement instanceof IActivity) {

			IActivity activity = (IActivity) contextPictogramElement;
			EList<EObject> contents = getDiagram().eResource().getContents();

			boolean isSub = LSEMElementHelper.isSubActivity(contents, activity);
			if (isSub) {
				return false;
			}
			return true;
		}

		PictogramElement[] sources = getDiagramBehavior().getDiagramContainer()
				.getSelectedPictogramElements();

		for (PictogramElement p : sources) {
			Object object = getBusinessObjectForPictogramElement(p);

			// Reason 1.1 : Return false if one of the selected
			// pictogram-elements is a sub-activity
			if (object instanceof IActivity) {
				IActivity dummy = (IActivity) object;
				boolean isSubActivity = LSEMElementHelper.isSubActivity(
						getDiagram().eResource().getContents(), dummy);
				if (isSubActivity)
					return false;
			} else {
				// Reason 1.2 : One of the source-pictogram-elements is not an
				// activity and the drop is on another pictogramElement
				if (targetBusiness instanceof IActivity)
					return false;
			}
		}
		// Reason 2 : Return false if target is already a sub-activity
		if (targetBusiness instanceof IActivity) {
			IActivity target = (IActivity) getBusinessObjectForPictogramElement(targetPicto);
			if (LSEMElementHelper.isSubActivity(getDiagram().eResource()
					.getContents(), target)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void moveShape(IMoveShapeContext context) {

		Object object = getFeatureProvider()
				.getBusinessObjectForPictogramElement(
						context.getPictogramElement());

		Object source = getFeatureProvider()
				.getBusinessObjectForPictogramElement(
						context.getSourceContainer());

		if (context.getTargetContainer() instanceof Diagram) {
			super.moveShape(context);
		}

		// Both cases : Multiple Elements or just one element is selected and
		// moved
		else if (source instanceof IActivity) {
			IActivity dummy = (IActivity) object;
			boolean isSubActivity = LSEMElementHelper.isSubActivity(
					getDiagram().eResource().getContents(), dummy);
			if (isSubActivity) {
				return;
			}
		} else {
			// Depending on the selected business-objects having sub-activities
			// or not, the method delegates the appropriate transformation
			delegateGraphicalTransformation(context);
		}// else
	}

	protected boolean delegateGraphicalTransformation(IMoveShapeContext context) {

		// Target element - changes when multiple pictograms are selected each
		// iteration on selected items
		Object target = getBusinessObjectForPictogramElement(context
				.getTargetContainer());

		PictogramElement[] selectedPictogramElements = getDiagramBehavior()
				.getDiagramContainer().getSelectedPictogramElements();

		// Iterate over all selected pictos and execute the merge depending
		// on the activity having sub-activities or not
		for (PictogramElement sourcePictogramElement : selectedPictogramElements) {

			Object source = getBusinessObjectForPictogramElement(sourcePictogramElement);

			if (source == null)
				continue;
			if (source instanceof IActivity && target instanceof IActivity) {
				IActivity sourceActivity = (IActivity) source;
				IActivity targetActivity = (IActivity) target;

				boolean sourceIsMotherActivity = sourceActivity
						.getSubActivities().size() > 0;
				boolean targetIsMotherActivity = targetActivity
						.getSubActivities().size() > 0;

				// Fall1: ziehen einer oder mehrerer atomarer Aktivitäten
				// auf
				// eine
				// andere
				// atomare --> neue Oberaktivität erzeugen und
				// vorhandene
				// zuordnen
				if (!sourceIsMotherActivity && !targetIsMotherActivity) {
					logger.log(Level.INFO,
							"Merging activities: Atomar on atomar case.");

					target = atomarOnAtomarCase(sourcePictogramElement,
							sourceActivity, targetActivity);
				}

				// Fall2: ziehe eine oberaktivität auf eine atomare oder
				// umgekehrt -->
				// atomare Aktivität wird der Oberaktivität zugeordnet
				else if (sourceIsMotherActivity != targetIsMotherActivity) {

					// Fall2.1: ziehe eine oberaktivität auf eine
					// atomare
					if (sourceIsMotherActivity) {
						logger.log(Level.INFO,
								"Merging activities: Source has sub-activities. Target is atomar.");

						target = notAtomarOnAtomarCase(sourceActivity,
								targetActivity);
					}
					// Fall2.2: ziehe eine atomare auf eine
					// oberaktivität
					else {
						logger.log(Level.INFO,
								"Merging activities: Target has sub-activities. Source is atomar.");

						target = notAtomarOnAtomarCase(targetActivity,
								sourceActivity);

					}
				}
				// Fall3: ziehe eine oberaktivität auf eine andere
				// oberaktivität
				// -->
				// eine neue Oberaktivität wird erstellt und alle
				// atomaren
				// werden dieser zugeordnet
				else {
					logger.log(Level.INFO,
							"Merging activities: Source and target have sub-activities.");

					target = notAtomarOnNotAtomarCase(sourcePictogramElement,
							sourceActivity, targetActivity);
				}
			} // IF activities
		} // selected pictogram-elements
		return true;
	}

	private void removeConnections(IActivity sourceActivity) {
		removeIncommingConnectionsTo(sourceActivity);
		removeOutgoingConnections(sourceActivity);
	}

	private void removeOutgoingConnections(IActivity sourceActivity) {
		sourceActivity.getOutgoing().clear();
	}

	/*
	 * If activity becomes a sub-activity, remove incomming and outgoing
	 * connections
	 */
	private void removeIncommingConnectionsTo(IActivity activity) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		List<ISimulationElement> sources = LSEMElementHelper
				.getSourcesForIncommingConnections(contents, activity);

		for (Iterator<ISimulationElement> iter = sources.iterator(); iter
				.hasNext();) {
			ISimulationElement next = iter.next();
			for (Iterator<IRelation> iter2 = next.getOutgoing().iterator(); iter2
					.hasNext();) {
				IRelation next2 = iter2.next();
				if (next2.getTarget().equals(activity)) {
					iter2.remove();
				}
			}
		}

	}

	protected IActivity notAtomarOnNotAtomarCase(
			PictogramElement sourcePictogramElement, IActivity sourceActivity,
			IActivity targetActivity) {

		// Create sub-activity-list
		List<IActivity> newSubActivities = new ArrayList<IActivity>();
		
		newSubActivities.addAll(sourceActivity.getSubActivities());
		newSubActivities.addAll(targetActivity.getSubActivities());

		IActivity newMotherActivity = CustomFeatureHelper.createActivity(
				newSubActivities, getFeatureProvider());

		PictogramElement newMotherPictogramElement = getFeatureProvider()
				.getPictogramElementForBusinessObject(newMotherActivity);

		// Add new sub-activities as sub-activities on canvas
		for (IActivity a : newMotherActivity.getSubActivities()) {
			callAddActivityFeature(a, newMotherPictogramElement);
		}

		deleteElement(sourcePictogramElement);
		deleteElement(getFeatureProvider()
				.getPictogramElementForBusinessObject(targetActivity));

		return newMotherActivity;
	}

	private PictogramElement callAddActivityFeature(IActivity activity,
			PictogramElement topPicto) {
		IAreaContext areaContext = new AreaContext();

		AddContext addContext = new AddContext(areaContext, activity);
		addContext.setTargetContainer((ContainerShape) topPicto);
		return getFeatureProvider().getAddFeature(addContext).add(addContext);
	}

	/**
	 * 
	 * @param motherActivity activity that will have the atomic activity as sub-activity
	 * @param atomarActivity will be added as sub-activity to the mother-activity
	 * @return the mother-activity
	 */
	protected IActivity notAtomarOnAtomarCase(IActivity motherActivity,
			IActivity atomarActivity) {

		removeConnections(atomarActivity);

		PictogramElement motherPic = getFeatureProvider()
				.getPictogramElementForBusinessObject(motherActivity);

		removePictogramElement(getFeatureProvider()
				.getPictogramElementForBusinessObject(atomarActivity));

		motherActivity.getSubActivities().add(atomarActivity);

		callAddActivityFeature(atomarActivity, motherPic);

		return motherActivity;
	}

	/**
	 * 
	 * @param target
	 * @param sourcePictogramElement
	 * @param sourceActivity
	 * @param targetActivity
	 * @return the newly created mother-activity
	 */
	protected IActivity atomarOnAtomarCase(
			PictogramElement sourcePictogramElement, IActivity sourceActivity,
			IActivity targetActivity) {

		// Target here is the newly created mother activity
		// and overwrites the existing target as there can
		// be multiple items that are merged. Hence, the
		// target changes because each iteration a
		// new object is created and added to canvas.
		removeConnections(sourceActivity);
		removeConnections(targetActivity);

		// source and target are becoming new sub-activities
		List<IActivity> newSubActivities = createSubActivityList(
				sourceActivity, targetActivity);

		// Create new mother activity and add to diagram
		IActivity topActivity = CustomFeatureHelper.createActivity(
				newSubActivities, getFeatureProvider());

		PictogramElement topPicto = getFeatureProvider()
				.getPictogramElementForBusinessObject(topActivity);
		// Remove (Graphically delete old elements)
		removePictogramElement(sourcePictogramElement);
		PictogramElement targetPicto = getFeatureProvider()
				.getPictogramElementForBusinessObject(targetActivity);
		removePictogramElement(targetPicto);

		// Graphically add source-activity
		callAddActivityFeature(sourceActivity, topPicto);

		// Graphically Add target-activity
		callAddActivityFeature(targetActivity, topPicto);

		return topActivity;
	}

	private List<IActivity> createSubActivityList(IActivity sourceActivity,
			IActivity targetActivity) {
		List<IActivity> newSubActivities = new ArrayList<IActivity>();
		newSubActivities.add(sourceActivity);
		newSubActivities.add(targetActivity);
		return newSubActivities;
	}

	private void removePictogramElement(PictogramElement pictogramElement) {
		RemoveContext dc = new RemoveContext(pictogramElement);
		// dc.setMultiDeleteInfo(new MultiDeleteInfo(false, false, 0));
		IRemoveFeature removeFeature = getFeatureProvider()
				.getRemoveFeature(dc);
		removeFeature.remove(dc);
	}
	
	private void deleteElement(PictogramElement pictogramElement){
		DeleteContext dc = new DeleteContext(pictogramElement);
		IDeleteFeature deleteFeature = getFeatureProvider().getDeleteFeature(dc);
		MultiDeleteInfo info = new MultiDeleteInfo(false, false, 0);
		dc.setMultiDeleteInfo(info);
		deleteFeature.delete(dc);
	}
}
