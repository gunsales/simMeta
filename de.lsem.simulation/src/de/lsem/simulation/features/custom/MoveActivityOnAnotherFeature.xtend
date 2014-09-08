package de.lsem.simulation.features.custom

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import java.util.ArrayList
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IMoveShapeContext
import org.eclipse.graphiti.features.context.impl.AddContext
import org.eclipse.graphiti.features.context.impl.AreaContext
import org.eclipse.graphiti.features.context.impl.DeleteContext
import org.eclipse.graphiti.features.context.impl.MultiDeleteInfo
import org.eclipse.graphiti.features.context.impl.RemoveContext
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramElement

import static de.lsem.simulation.features.custom.CustomActivityHelper.*
import static de.lsem.simulation.util.LSEMElementHelper.*

/**
 * This is a workaround-feature for realizing a drag&drop-feature from one
 * activity on another.
 * 
 * @author Lewin
 * 
 */
class MoveActivityOnAnotherFeature extends DefaultMoveShapeFeature {

	static val logger = Logger.getLogger(typeof(MoveActivityOnAnotherFeature).simpleName)

	new(IFeatureProvider fp) {
		super(fp)
	}

	override canMoveShape(IMoveShapeContext it) {

		//the drop-target
		val dropTargetBO = getBusinessObjectForPictogramElement(targetContainer)

		//the moving element
		val movingBO = getBusinessObjectForPictogramElement(pictogramElement)

		//moving-bo is a single activity --> enable move if it is not a sub-activity
		if (dropTargetBO == null && movingBO instanceof IActivity) {
			return isSubActivity(contents, movingBO as IActivity)
		}

		//multiple selected elements
		if (requirementsNotFullfilled(dropTargetBO)) {
			return false
		}

		// Reason 2 : Return false if target is already a sub-activity
		if (dropTargetBO instanceof IActivity) {
			val dropTargetActivity = dropTargetBO as IActivity
			if (isSubActivity(contents, dropTargetActivity)) {
				return false
			}
		}

		return true		
	}

	private def requirementsNotFullfilled(Object dropTarget) {
		selectedPictogramElements.map [
			val selectedBO = getBusinessObjectForPictogramElement
			// Reason 1.1 : Return false if one of the selected
			// pictogram-elements is a sub-activity
			if (selectedBO instanceof IActivity) {
				val selectedActivity = selectedBO as IActivity
				return !isSubActivity(contents, selectedActivity)
			}
			// Reason 1.2 : One of the selected pictogram-elements is not an
			// activity and the drop is on another pictogramElement 
			else if (dropTarget instanceof ISimulationElement) {
				return false
			}
			return true
		].contains(false)
	}

	override moveShape(IMoveShapeContext it) {

		if (targetContainer instanceof Diagram) {
			super.moveShape(it)
		}

		delegateGraphicalTransformation
	}

	def delegateGraphicalTransformation(IMoveShapeContext it) {
		var targetBO = targetContainer.getBusinessObjectForPictogramElement

		if (!(targetBO instanceof IActivity)) {
			return
		}

		for (s : selectedPictogramElements) {
			val selectedBO = s.getBusinessObjectForPictogramElement
			if (selectedBO == null || !(selectedBO instanceof IActivity) || !(targetBO instanceof IActivity)) {
			} else {

				val selectedActivity = selectedBO as IActivity
				val selectedIsMotherActivity = hasSubActivities(selectedActivity)
				val targetActivity = targetBO as IActivity
				val targetIsMotherActivity = hasSubActivities(targetActivity)

				// Fall1: ziehen einer oder mehrerer atomarer Aktivitäten auf eine andere atomare 
				// --> neue Oberaktivität erzeugen und vorhandene zuordnen
				if (!selectedIsMotherActivity && !targetIsMotherActivity) {
					logger.log(Level.INFO, "Merging activities: Atomar on atomar case.")
					targetBO = atomarOnAtomarCase(s, selectedActivity, targetActivity)
				}
				// Fall2: ziehe eine Oberaktivität auf eine atomare oder
				// umgekehrt -->
				// atomare Aktivität wird der Oberaktivität zugeordnet
				else if (selectedIsMotherActivity != targetIsMotherActivity) {

					// Fall2.1: ziehe eine Oberaktivität auf eine
					// atomare
					if (selectedIsMotherActivity) {
						logger.log(Level.INFO, "Merging activities: Source has sub-activities. Target is atomic.")
						targetBO = notAtomarOnAtomarCase(selectedActivity, targetActivity)
					} else {
						logger.log(Level.INFO, "Merging activities: Target has sub-activities. Source is atomic.")
						targetBO = notAtomarOnAtomarCase(targetActivity, selectedActivity)
					}
				}
				// Fall3: ziehe eine Oberaktivität auf eine andere oberaktivität 
				// --> eine neue Oberaktivität wird erstellt und alle atomaren werden dieser zugeordnet
				else {
					targetBO = notAtomarOnNotAtomarCase(s, selectedActivity, targetActivity)
				}
			}
		}
	}
	
	def notAtomarOnNotAtomarCase(PictogramElement element, IActivity sourceActivity, IActivity targetActivity) {
		val newSubActivities = newArrayList
		
		newSubActivities.addAll(sourceActivity.subActivities)
		newSubActivities.addAll(targetActivity.subActivities)
		
		val newMotherActivity = createActivity(newSubActivities, featureProvider)
		val newMotherPictogramElement = getPictogramElementForBO(newMotherActivity)
		
		newMotherActivity.subActivities.forEach[
			callAddActivityFeature(newMotherPictogramElement)
		]
		
		deleteElement(element)
		deleteElement(getPictogramElementForBO(targetActivity))
		
		newMotherActivity
	}
	
	private def deleteElement(PictogramElement element) {
		val deleteContext = new DeleteContext(element)
		deleteContext.multiDeleteInfo = new MultiDeleteInfo(false, false, 0)
		
		val deleteFeature = featureProvider.getDeleteFeature(deleteContext)
		deleteFeature.delete(deleteContext)
	}
	
	/**
	 * 
	 * @param motherActivity activity that will have the atomic activity as sub-activity
	 * @param atomarActivity will be added as sub-activity to the mother-activity
	 * @return the mother-activity
	 */
	def notAtomarOnAtomarCase(IActivity motherActivity, IActivity atomarActivity) {
		removeConnections(atomarActivity)
		
		val motherPic = getPictogramElementForBO(motherActivity)
		val subPic = getPictogramElementForBO(atomarActivity)
		
		removePictogramElement(subPic)
		
		motherActivity.subActivities.add(atomarActivity)
		
		callAddActivityFeature(atomarActivity, motherPic)
		
		motherActivity
	}
	
	/**
	 * @return the newly created mother-activity
	 */
	def atomarOnAtomarCase(PictogramElement element, IActivity sourceActivity, IActivity targetActivity) {
		// Target here is the newly created mother activity
		// and overwrites the existing target as there can
		// be multiple items that are merged. Hence, the
		// target changes because each iteration a
		// new object is created and added to canvas.
		removeConnections(sourceActivity)
		removeConnections(targetActivity)
		
		val newSubActivities = createSubActivityList(sourceActivity, targetActivity)
		val topActivity = createActivity(newSubActivities, featureProvider)
		val topPicto = featureProvider.getPictogramElementForBusinessObject(topActivity)
		
		removePictogramElement(element)
		val targetPicto = getPictogramElementForBO(targetActivity)
		removePictogramElement(targetPicto)
		
		callAddActivityFeature(sourceActivity, topPicto)
		callAddActivityFeature(targetActivity, topPicto)
		
		topActivity
	}
	
	private def callAddActivityFeature(IActivity activity, PictogramElement element) {
		val areaContext = new AreaContext
		val addContext = new AddContext(areaContext, activity)
		addContext.targetContainer = element as ContainerShape
		featureProvider.getAddFeature(addContext).add(addContext)
	}
	
	private def removePictogramElement(PictogramElement element) {
		val rc = new RemoveContext(element)
		val rf = featureProvider.getRemoveFeature(rc)
		rf.remove(rc)
	}
	
	private def getPictogramElementForBO(ISimulationElement element){
		featureProvider.getPictogramElementForBusinessObject(element)
	}
	
	private def createSubActivityList(IActivity activity, IActivity activity2) {
		val retVal = new ArrayList
		retVal.add(activity)
		retVal.add(activity2)
		retVal
	}
	
	/*
	 * If activity becomes a sub-activity, remove incomming and outgoing
	 * connections
	 */
	def removeConnections(IActivity it) {
		removeIncommingConnections
		removeOutgoingConnections
	}
	
	def removeOutgoingConnections(IActivity it) {
		outgoing.clear
	}
	
	private def removeIncommingConnections(IActivity it){
		val sources = getSourcesForIncommingConnections(contents, it).iterator
		
		while(sources.hasNext){
			val simElement = sources.next
			val outgoing = simElement.outgoing.iterator
			while(outgoing.hasNext){
				val relation = outgoing.next
				
				if(relation.target.equals(it)){
					outgoing.remove
				}
			}
			
		}
	}

	private def getContents() {
		diagram.eResource.contents
	}

	private def getSelectedPictogramElements() {
		diagramBehavior.diagramContainer.selectedPictogramElements
	}

}
