package de.lsem.simulation.features.delete

import de.lsem.repository.model.simulation.ISink
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IDeleteContext
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature

import static de.lsem.simulation.util.DeleteHelper.*

class DeleteSinkFeature extends DefaultDeleteFeature{
	
	new(IFeatureProvider fp) {
		super(fp)
	}
	
	override delete(IDeleteContext it) {
		val bo = getBusinessObjectForPictogramElement(pictogramElement)
		
		if(bo != null && bo instanceof ISink){
			deleteIncomingConnections(diagram.eResource.contents, bo as ISink)					
		}
		
		super.delete(it)
	}
	
}