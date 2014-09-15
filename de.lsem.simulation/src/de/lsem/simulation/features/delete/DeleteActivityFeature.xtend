package de.lsem.simulation.features.delete

import de.lsem.repository.model.simulation.IActivity
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IDeleteContext
import org.eclipse.graphiti.features.context.impl.UpdateContext
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature

import static de.lsem.simulation.util.DeleteHelper.*
import static de.lsem.simulation.util.LSEMElementHelper.*

class DeleteActivityFeature extends DefaultDeleteFeature{
	
	new(IFeatureProvider fp) {
		super(fp)
	}
	
	override canDelete(IDeleteContext it) {
		val obj = pictogramElement.businessObjectForPictogramElement
		obj instanceof IActivity
	}
	
	override delete(IDeleteContext it) {
		
		if( !userDecision ){
			return
		}
		
		try{
			deleteIncomingConnections(contents, activityFromContext)
			deleteActivityFromDiagram(activityFromContext)
			deleteFromTopActivity(activityFromContext)
		} catch (Exception e) {
			e.printStackTrace
		}
		
		super.delete(it)
	}
	
	private def deleteFromTopActivity(IActivity it) {
		val topActivity = getTopActivity(contents, it)
		
		if(topActivity != null){
			val pe = featureProvider.getPictogramElementForBusinessObject(topActivity)
			topActivity.subActivities.remove(it)
			
			val uc = new UpdateContext(pe)
			featureProvider.updateIfPossible(uc)
		}
	}
	
	private def deleteActivityFromDiagram(IActivity a) {
		contents => [
			remove(a.capacity)
			remove(a.timePeriod)
			remove(a.timePeriod.period)
		]
	}
	
	private def getActivityFromContext(IDeleteContext it){
		getBusinessObjectForPictogramElement(pictogramElement) as IActivity
	}
	
	private def getContents(){
		diagram.eResource.contents
	}
	
}