package de.lsem.simulation.features.custom

import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.features.create.CreateActivityFeature
import java.util.ArrayList
import java.util.List
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.impl.CreateContext
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramElement

class CustomActivityHelper {

	static val ACTIVITY_HEIGHT = 300
	static val ACTIVITY_WIDTH = 120
	static val MERGE_SEPARATOR = "+"
	static val DEFAULT_ACTIVITY_NAME = "Activity"

	static var createPosY = 1
	static var createPosX = 1

	protected static def createActivity(List<IActivity> newSubActivities, IFeatureProvider featureProvider) {
		val pictos = getPictosFromActivities(newSubActivities, featureProvider)
		setCreatePosition(pictos)
		createTopActivity(featureProvider.activityCreateFeature, newSubActivities)
	}
	
	private def static getPictosFromActivities(List<IActivity> newSubActivities, IFeatureProvider featureProvider) {
		val pictos = newArrayList
		
		newSubActivities.forEach [ sa |
			val picto = featureProvider.getPictogramElementForBusinessObject(sa)
			pictos.add(picto)
		]
		pictos
	}

	private def static getActivityCreateFeature(IFeatureProvider featureProvider) {
		featureProvider.createFeatures.filter(typeof(CreateActivityFeature)).head
	}

	private def static setCreatePosition(ArrayList<PictogramElement> elements) {

		for (e : elements) {
			createPosX = createPosX + e.graphicsAlgorithm.x
			createPosY = createPosY + e.graphicsAlgorithm.y
		}

		if (elements.size > 0) {
			createPosX = createPosX / elements.size
			createPosY = createPosY / elements.size
		}
	}

	private def static createTopActivity(CreateActivityFeature caf, List<IActivity> newSubActivities) {
		val cc = getCreateContext(getDiagram(caf))
		
		val activity = caf.create(cc).get(0) as IActivity
		activity.subActivities.addAll(newSubActivities)
		activity.name = createMergedActivityName(newSubActivities)
		activity
	}
	
	private def static getDiagram(CreateActivityFeature caf) {
		caf.featureProvider.diagramTypeProvider.diagram
	}
	
	private def static createMergedActivityName(List<IActivity> activities) {
		val nameDummy = new StringBuffer
		
		activities.forEach[
			nameDummy.append(name ?: DEFAULT_ACTIVITY_NAME)
			nameDummy.append(MERGE_SEPARATOR)
		]
		
		cutLastChar(nameDummy)
	}
	
	private def static cutLastChar(StringBuffer nameDummy) {
		if(nameDummy.toString.length > 1){
			return nameDummy.toString.substring(0, nameDummy.toString.length - 1)
		} else {
			return nameDummy.toString
		}
	}
	
	private def static getCreateContext(Diagram diagram) {
		val createContext = new CreateContext
		
		createContext.targetContainer = diagram
		createContext.height = ACTIVITY_HEIGHT
		createContext.width = ACTIVITY_WIDTH
		createContext.setLocation(createPosX, createPosY)
		createContext
	}
	
}
