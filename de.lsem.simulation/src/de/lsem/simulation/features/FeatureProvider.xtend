package de.lsem.simulation.features

import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.ConditionalRelation
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.Relation
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import de.lsem.simulation.features.add.AddActivityFeature
import de.lsem.simulation.features.add.AddConditionalRelationFeature
import de.lsem.simulation.features.add.AddRelationFeature
import de.lsem.simulation.features.add.AddSinkFeature
import de.lsem.simulation.features.add.AddSourceFeature
import de.lsem.simulation.features.create.CreateActivityFeature
import de.lsem.simulation.features.create.CreateConditionalRelationFeature
import de.lsem.simulation.features.create.CreateRelationFeature
import de.lsem.simulation.features.create.CreateSinkFeature
import de.lsem.simulation.features.create.CreateSourceFeature
import de.lsem.simulation.features.custom.CollapseActivityFeature
import de.lsem.simulation.features.custom.MergeActivitiesFeature
import de.lsem.simulation.features.custom.MoveActivityOnAnotherFeature
import de.lsem.simulation.features.delete.DeleteActivityFeature
import de.lsem.simulation.features.delete.DeleteSinkFeature
import de.lsem.simulation.features.delete.DeleteSourceFeature
import de.lsem.simulation.features.update.RelationReconnectionFeature
import de.lsem.simulation.features.update.UpdateConditionalRelationFeature
import de.lsem.simulation.features.update.UpdateLSEMElementFeature
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.context.ICustomContext
import org.eclipse.graphiti.features.context.IDeleteContext
import org.eclipse.graphiti.features.context.IMoveShapeContext
import org.eclipse.graphiti.features.context.IReconnectionContext
import org.eclipse.graphiti.features.context.IUpdateContext
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider

class FeatureProvider extends DefaultFeatureProvider {

	new(IDiagramTypeProvider dtp) {
		super(dtp)
	}

	override getReconnectionFeature(IReconnectionContext context) {
		new RelationReconnectionFeature(this)
	}

	override getAddFeature(IAddContext context) {

		switch context.newObject {
			Activity: new AddActivityFeature(this)
			ConditionalRelation: new AddConditionalRelationFeature(this)
			Relation: new AddRelationFeature(this)
			Sink: new AddSinkFeature(this)
			Source: new AddSourceFeature(this)
			default: super.getAddFeature(context)
		}
	}

	override getCreateFeatures() {
		#[
			new CreateActivityFeature(this),
			new CreateSinkFeature(this),
			new CreateSourceFeature(this)
		]
	}

	override getUpdateFeature(IUpdateContext context) {
		val pe = context.pictogramElement

		//		 Relation changes moved to RelationReconnectionFeature
		switch pe {
			case FreeFormConnection: {
				val element = getBusinessObjectForPictogramElement(pe as FreeFormConnection)

				// ConditionalRelation
				if (element instanceof IConditionalRelation) {
					new UpdateConditionalRelationFeature(this)
				} else {
					super.getUpdateFeature(context)
				}
			}
			// Activity-, Start-, End-Event
			case ContainerShape: {
				val element = getBusinessObjectForPictogramElement(pe)
				if (element instanceof ISimulationElement) {
					new UpdateLSEMElementFeature(this)
				} else {
					super.getUpdateFeature(context)
				}
			}
			default:
				super.getUpdateFeature(context)
		}
	}

	override getCreateConnectionFeatures() {
		#[
			new CreateConditionalRelationFeature(this),
			new CreateRelationFeature(this)
		]
	}

	override getCustomFeatures(ICustomContext context) {
		#[
			new CollapseActivityFeature(this),
			new MergeActivitiesFeature(this)
		]
	}

	override getMoveShapeFeature(IMoveShapeContext context) {
		val element = getBusinessObjectForPictogramElement(context.pictogramElement)
		if (element != null && element instanceof IActivity) {
			new MoveActivityOnAnotherFeature(this)
		} else {
			super.getMoveShapeFeature(context)
		}
	}

	override getDeleteFeature(IDeleteContext context) {
		val element = getBusinessObjectForPictogramElement(context.pictogramElement)
		switch element {
			case IActivity: new DeleteActivityFeature(this)
			case ISink: new DeleteSinkFeature(this)
			case ISource: new DeleteSourceFeature(this)
			default: super.getDeleteFeature(context)
		}
	}
	
}
