package de.lsem.simulation.features.delete

import de.lsem.repository.model.simulation.ISource
import org.eclipse.emf.ecore.EObject
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IDeleteContext
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature

class DeleteSourceFeature extends DefaultDeleteFeature{
	
	new(IFeatureProvider fp) {
		super(fp)
	}
	
	override delete(IDeleteContext it) {
		
		val bo = getBusinessObjectForPictogramElement(pictogramElement)
		
		if(bo != null && bo instanceof ISource){
		try{
			val s = bo as ISource
			
			r(s.firstEntity.period)
			r(s.firstEntity)
			r(s.newEntities.period)
			r(s.newEntities)
			r(s.processedObject)
			}catch(Exception e){}
		}
		super.delete(it)
	}
	
	private def getContents(){
		diagram.eResource.contents
	}
	
	private def r(EObject e){
		contents.remove(e)
	}
	
}