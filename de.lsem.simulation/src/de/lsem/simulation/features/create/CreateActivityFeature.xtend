package de.lsem.simulation.features.create

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ICapacity
import de.lsem.repository.model.simulation.IConstant
import de.lsem.repository.model.simulation.ITime
import de.lsem.repository.model.simulation.ServiceType
import de.lsem.repository.model.simulation.UnitOfTime
import de.lsem.simulation.features.FeatureProvider
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext
import org.eclipse.graphiti.features.impl.AbstractCreateFeature
import org.eclipse.graphiti.mm.pictograms.Diagram

import static de.lsem.repository.model.simulation.ISimulationFactory.*
import static de.lsem.simulation.util.LSEMElementHelper.*

class CreateActivityFeature extends AbstractCreateFeature {

	static val log = Logger.getLogger(typeof(CreateActivityFeature).simpleName)

	new(IFeatureProvider fp, String name, String description) {
		super(fp, name, description)
	}

	new(FeatureProvider fp) {
		super(fp, "Activity", "Create Activity Element")
	}

	override canCreate(ICreateContext it) {
		if (targetContainer instanceof Diagram) {
			true
		} else {
			try {
				val parentContainer = targetContainer.link.pictogramElement
				val parentBO = getBusinessObjectForPictogramElement(parentContainer)

				// Parent element must not be a sub-activity. Allowed are only top-level-activities and the diagram itself
				if (parentBO instanceof IActivity) {
					return !isSubActivity(contents, parentBO as IActivity)
				}
			} catch (NullPointerException e) {
				e.printStackTrace
			}
			false
		}
	}

	override create(ICreateContext it) {
		#[createFullActivityElement]
	}

	private def createFullActivityElement(ICreateContext it) {

		// Create Sub-Elements
		val capacity = eINSTANCE.createCapacity
		val time = eINSTANCE.createTime
		val unitOfTime = UnitOfTime.HOUR
		val constant = eINSTANCE.createConstant

		// Create Activity
		val activity = createActivityWithDefaultParameters(capacity, time, unitOfTime, constant)

		// Add top-element to model
		if (!contents.contains(activity)) {
			addElementsToContent(activity, capacity, time, constant)
		}

		// Activity is added as a sub-activity
		if (targetContainer != null && !(targetContainer instanceof Diagram)) {
			try {
				val bo = getBusinessObjectForPictogramElement(targetContainer)

				if (bo instanceof IActivity) {
					val topActivity = bo as IActivity
					topActivity.subActivities.add(activity)
				}
			} catch (NullPointerException e) {
				// No stack-trace needed. Case occurs if target-Container is the diagram itself.
			}
		}
		log.log(Level.INFO, "Activity created. " + activity.name)

		addGraphicalRepresentation(activity)

		activity
	}

	private def getContents() {
		diagram.eResource.contents
	}

	private def addElementsToContent(IActivity activity, ICapacity capacity, ITime time, IConstant constant) {

		contents.add(activity)

		// Add sub-elements to model
		contents.add(capacity)
		contents.add(time)
		contents.add(constant)
	}

	private def getInitialActivityNumber() {
		val activities = getActivitiesFromDiagram(contents)

		String.valueOf(activities.size + 1)
	}

	private def create it:eINSTANCE.createActivity createActivityWithDefaultParameters(ICapacity _capacity, ITime time,
		UnitOfTime unitOfTime, IConstant constant) {
		serviceType = ServiceType.DEFAULT
		name = createActivityName(it, initialActivityNumber)
		capacity = _capacity
		time.unit = unitOfTime
		timePeriod = time
		timePeriod.period = constant
	}

	private def String createActivityName(IActivity it, String activityNumber) '''
	«serviceType.literal»-Service_«activityNumber»'''

}
