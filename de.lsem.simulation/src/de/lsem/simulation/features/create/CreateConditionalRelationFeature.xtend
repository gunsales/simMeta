package de.lsem.simulation.features.create

import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.SimulationFactory
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateConnectionContext
import org.eclipse.graphiti.features.context.impl.AddConnectionContext
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature
import org.eclipse.graphiti.mm.pictograms.Anchor
import org.eclipse.graphiti.mm.pictograms.Connection

import static de.lsem.simulation.util.RelationHelper.*

class CreateConditionalRelationFeature extends AbstractCreateConnectionFeature {

	new(IFeatureProvider fp, String name, String description) {
		super(fp, name, description)
	}

	new(IFeatureProvider fp) {
		super(fp, "Conditional Relation", "Connection depending on its call condition")
	}

	override canCreate(ICreateConnectionContext it) {
		
		
		val targetObject = getBusinessObjectForPictogramElement(targetPictogramElement) as ISimulationElement
		val sourceObject = getBusinessObjectForPictogramElement(sourcePictogramElement) as ISimulationElement

		if(!(targetObject instanceof ISimulationElement) || !(sourceObject instanceof ISimulationElement)){
			return false
		}


		// ******* Anchors != null *****
		sourceAnchor != null && targetAnchor != null &&
			// ******* Anchors not equal *****
			sourceAnchor != targetAnchor && 
			// ******* BOs != null *****
			sourceObject != null && targetObject != null &&
			// ******* Start-BO is not a Sink *****
			!(sourceObject instanceof ISink) &&
			// ******* Target-BO is not a Source *****
			!(targetObject instanceof ISource) &&
			// *******  Source and target not already connected *****
			!(isAlreadyConnected(sourceObject, targetObject))

	}

	override canStartConnection(ICreateConnectionContext it) {
		sourceAnchor != null
	}

	override create(ICreateConnectionContext it) {
		val source = getLSEMElement(sourceAnchor)
		val target = getLSEMElement(targetAnchor)
		
		if(source != null && target != null){
			val cr = createConditionalRelation(source, target)
			
			val addConnectionContext = new AddConnectionContext(sourceAnchor, targetAnchor)
			
			addConnectionContext.newObject = cr
			
			return featureProvider.addIfPossible(addConnectionContext) as Connection
		}
	}
	
	private def create it:SimulationFactory.eINSTANCE.createConditionalRelation() createConditionalRelation(ISimulationElement _source, ISimulationElement _target){
		name = createRelationName(diagram)
		condition = ""
		probability = 50
		source = _source
		target = _target
	}
	
	private def getLSEMElement(Anchor it){
		if( it != null){
			val obj = parent.businessObjectForPictogramElement
			if(obj instanceof ISimulationElement){
				return obj as ISimulationElement
			}
		}
	}

}
