package de.lsem.simulation.features;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.features.add.AddActivityFeature;
import de.lsem.simulation.features.add.AddConditionalRelationFeature;
import de.lsem.simulation.features.add.AddRelationFeature;
import de.lsem.simulation.features.add.AddSinkFeature;
import de.lsem.simulation.features.add.AddSourceFeature;
import de.lsem.simulation.features.create.CreateActivityFeature;
import de.lsem.simulation.features.create.CreateConditionalRelationFeature;
import de.lsem.simulation.features.create.CreateRelationFeature;
import de.lsem.simulation.features.create.CreateSinkFeature;
import de.lsem.simulation.features.create.CreateSourceFeature;
import de.lsem.simulation.features.custom.CollapseActivityFeature;
import de.lsem.simulation.features.custom.MergeActivitiesFeature;
import de.lsem.simulation.features.custom.MoveActivityOnAnotherFeature;
import de.lsem.simulation.features.delete.DeleteActivityFeature;
import de.lsem.simulation.features.delete.DeleteSinkFeature;
import de.lsem.simulation.features.delete.DeleteSourceFeature;
import de.lsem.simulation.features.update.RelationReconnectionFeature;
import de.lsem.simulation.features.update.UpdateConditionalRelationFeature;
import de.lsem.simulation.features.update.UpdateLSEMElementFeature;

public class FeatureProvider extends DefaultFeatureProvider {

	public FeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return new RelationReconnectionFeature(this);
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object object = context.getNewObject();
		if ( object instanceof IActivity) {
			return new AddActivityFeature(this);
		} else if (object instanceof IConditionalRelation) {
			return new AddConditionalRelationFeature(this);
		} else if (object instanceof IRelation) {
			return new AddRelationFeature(this);
		} else if (object instanceof ISink) {
			return new AddSinkFeature(this);
		} else if (object instanceof ISource) {
			return new AddSourceFeature(this);
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateActivityFeature(this),
				new CreateSinkFeature(this), new CreateSourceFeature(this) };
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();

//		 Relation changes moved to RelationReconnectionFeature
		if (pe instanceof FreeFormConnection) {
			FreeFormConnection cd = (FreeFormConnection) pe;
			Object element = getBusinessObjectForPictogramElement(cd);

			// ConditionalRelation
			if (element instanceof IConditionalRelation) {
				return new UpdateConditionalRelationFeature(this);
			} 
		}

		// Activity-, Start-, End-Event
		if (pe instanceof ContainerShape) {
			Object obj = getBusinessObjectForPictogramElement(pe);
			if (obj instanceof ISimulationElement) {
				return new UpdateLSEMElementFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateConditionalRelationFeature(this),
				new CreateRelationFeature(this) };
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return new ICustomFeature[] { 
				new CollapseActivityFeature(this),
				new MergeActivitiesFeature(this) };
	}
	
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object object = getBusinessObjectForPictogramElement(pictogramElement);
		
		if ( object != null && object instanceof IActivity) {
			return new MoveActivityOnAnotherFeature(this);
		}
		return super.getMoveShapeFeature(context);
	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object businessObjectForPictogramElement = getBusinessObjectForPictogramElement(pictogramElement);
		
		if (businessObjectForPictogramElement instanceof IActivity) {
			return new DeleteActivityFeature(this);
		}else if (businessObjectForPictogramElement instanceof ISink) {
			return new DeleteSinkFeature(this);
		} else if (businessObjectForPictogramElement instanceof ISource) {
			return new DeleteSourceFeature(this);
		}
		return super.getDeleteFeature(context);
	}

}
