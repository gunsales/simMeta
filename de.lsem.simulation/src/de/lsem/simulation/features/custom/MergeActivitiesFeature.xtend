package de.lsem.simulation.features.custom

import de.lsem.repository.model.simulation.IActivity
import java.util.ArrayList
import java.util.Arrays
import java.util.List
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICustomContext
import org.eclipse.graphiti.features.context.impl.MoveShapeContext
import org.eclipse.graphiti.features.custom.AbstractCustomFeature
import org.eclipse.graphiti.mm.pictograms.PictogramElement
import org.eclipse.graphiti.mm.pictograms.Shape

import static de.lsem.simulation.util.LSEMElementHelper.*
class MergeActivitiesFeature extends AbstractCustomFeature {

	new(IFeatureProvider fp) {
		super(fp)
	}

	override getName() {
		"Merge"
	}

	override canExecute(ICustomContext it) {
		if (pictogramElements == null || pictogramElements.size <= 1) {
			return false
		}

		areAllSelectedPictosActivities
	}

	private def areAllSelectedPictosActivities(ICustomContext it) {
		for (p : pictogramElements) {
			val element = getBusinessObjectForPictogramElement(p)

			if (!(element instanceof IActivity)) {
				return false
			}
			return true
		}
	}

	override execute(ICustomContext it) {
		if (pictogramElements == null || pictogramElements.size <= 1) {
			return
		}

		val selectedPictos = newArrayList(Arrays.asList(pictogramElements)).flatten.toList
		val businessObjects = getBusinessObjectsFor(selectedPictos)

		if (businessObjects.empty) {
			return
		}

		var initialActivity = businessObjects.get(0)
		removeFirstActivity(selectedPictos, businessObjects)

		var sourcePictogramElement = getPictogramElementForFirstActivity(businessObjects)

		for (sp : selectedPictos) {

			val initialIsMotherActivity = hasSubActivities(initialActivity)
			val comparingIsMotherActivity = hasSubActivities(sp.activityForPicto)

			// Case 1 : initial & comparing are atomic
			if (!initialIsMotherActivity && !comparingIsMotherActivity) {
				initialActivity = moveActivityFeature.atomarOnAtomarCase(sourcePictogramElement, businessObjects.first,
					sp.activityForPicto)
			}

			// Case 2 : initial XOR comparing are atomic
			else if (initialIsMotherActivity != comparingIsMotherActivity) {
				if (initialIsMotherActivity) {
					initialActivity = moveActivityFeature.notAtomarOnAtomarCase(initialActivity, sp.activityForPicto)
				} else {
					initialActivity = moveActivityFeature.notAtomarOnAtomarCase(sp.activityForPicto, initialActivity)
				}
			} 
			
			// Case 3 : initial and comparing are not atomic
			else {
				initialActivity = moveActivityFeature.notAtomarOnNotAtomarCase(sourcePictogramElement, initialActivity,
					sp.activityForPicto)
			}

			if (sp != null && sp.graphicsAlgorithm != null) {
				val newPicto = featureProvider.getPictogramElementForBusinessObject(initialActivity)
				sourcePictogramElement = setXandY(newPicto, sp)
			}
		}
	}

	def setXandY(PictogramElement element, PictogramElement element2) {
		element2.graphicsAlgorithm.x = element.graphicsAlgorithm.x
		element2.graphicsAlgorithm.y = element.graphicsAlgorithm.y
		element2
	}

	def getActivityForPicto(PictogramElement sp) {
		getBusinessObjectForPictogramElement(sp) as IActivity
	}

	def getMoveActivityFeature(ICustomContext it) {
		val firstShape = pictogramElements.get(0) as Shape
		val moveShapeContext = new MoveShapeContext(firstShape)

		featureProvider.getMoveShapeFeature(moveShapeContext) as MoveActivityOnAnotherFeature
	}

	def removeFirstActivity(List<PictogramElement> selectedPictos, ArrayList<IActivity> businessObjects) {
		selectedPictos.remove(getPictogramElementForFirstActivity(businessObjects))
	}

	def getPictogramElementForFirstActivity(ArrayList<IActivity> businessObjects) {
		featureProvider.getPictogramElementForBusinessObject(businessObjects.first)
	}

	def first(ArrayList<IActivity> activities) {
		activities.get(0)
	}

	def getBusinessObjectsFor(List<PictogramElement> elements) {
		val retVal = newArrayList

		elements.forEach [
			val a = getBusinessObjectForPictogramElement as IActivity
			retVal.add(a)
		]
		retVal
	}

}
