package de.lsem.simulation.features.create;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.simulation.util.LSEMElementHelper;

public class CreateActivityFeature extends AbstractCreateFeature {

	public static final Logger logger = Logger
			.getLogger(CreateActivityFeature.class.getSimpleName());

	public CreateActivityFeature(IFeatureProvider fp) {
		super(fp, "Activity", "Create Activity Element");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		if (context.getTargetContainer() instanceof Diagram) {
			return true;
		} else {
			try {
				PictogramElement targetElement = context.getTargetContainer()
						.getLink().getPictogramElement();
				if (getBusinessObjectForPictogramElement(targetElement) instanceof IActivity){
					IActivity object = (IActivity) getBusinessObjectForPictogramElement(targetElement);
					if(LSEMElementHelper.isSubActivity(getDiagram().eResource().getContents(), object)){
						return false;
					} else {
						return true;						
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			} // Raised when targetElement is the diagram itself
			return false;
		}
	}

	@Override
	public Object[] create(ICreateContext context) {
		return new Object[] { createFullActivityElement(context) };
	}

	private IActivity createFullActivityElement(ICreateContext context) {

		IActivity activity = SimulationFactory.eINSTANCE.createActivity();
		ICapacity capacity = SimulationFactory.eINSTANCE.createCapacity();
		ITime time = SimulationFactory.eINSTANCE.createTime();
		UnitOfTime unitOfTime = UnitOfTime.HOUR;
		IConstant constant = SimulationFactory.eINSTANCE.createConstant();

		// Default
		String activityName = getInitialActivityNumber();
		activity = setDefaultActivityParameters(activity, capacity, time, unitOfTime,
				constant, activityName);

		// Add to model
		if (!getDiagram().eResource().getContents().contains(activity)){
			getDiagram().eResource().getContents().add(activity);
		}

		addParametersToDiagram(capacity, time, constant);

		// If Activity is dropped on another Activity
		ContainerShape targetElement = context.getTargetContainer();
		if (targetElement != null && !(targetElement instanceof Diagram)){
			try {
				Object bo = getBusinessObjectForPictogramElement(targetElement);

				if (bo instanceof IActivity) {
					IActivity activityTop = (IActivity) bo;
					activityTop.getSubActivities().add(activity);
//					updatePictogramElement(targetElement);
				}
			} catch (NullPointerException e) {
			} // Thrown when targetElement is the diagram itself
		}
		logger.log(Level.INFO, "Activity created." + activity);
		addGraphicalRepresentation(context, activity);
		return activity;
	}

	private String getInitialActivityNumber() {
		EList<EObject> contents = getDiagram().eResource().getContents();
		List<IActivity> iActivitiesFromDiagram = LSEMElementHelper.getActivitiesFromDiagram(contents);
		
		return String.valueOf(iActivitiesFromDiagram.size()+1);
	}

	private void addParametersToDiagram(ICapacity capacity, ITime time,
			IConstant constant) {
		getDiagram().eResource().getContents().add(capacity);
		getDiagram().eResource().getContents().add(time);
		getDiagram().eResource().getContents().add(constant);
	}

	private IActivity setDefaultActivityParameters(IActivity activity,
			ICapacity capacity, ITime time, UnitOfTime unitOfTime,
			IConstant constant, String activityNumber) {
		activity.setServiceType(ServiceType.DEFAULT);
		activity.setName(activity.getServiceType().getLiteral() + "-Service_" + activityNumber);
		activity.setCapacity(capacity);
		time.setUnit(unitOfTime);
		activity.setTimePeriod(time);
		activity.getTimePeriod().setPeriod(constant);
		return activity;
	}
}
