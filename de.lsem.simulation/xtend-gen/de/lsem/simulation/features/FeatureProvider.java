package de.lsem.simulation.features;

import com.google.common.base.Objects;
import de.lsem.repository.model.simulation.Activity;
import de.lsem.repository.model.simulation.ConditionalRelation;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.Relation;
import de.lsem.repository.model.simulation.Sink;
import de.lsem.repository.model.simulation.Source;
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

@SuppressWarnings("all")
public class FeatureProvider extends DefaultFeatureProvider {
  public FeatureProvider(final IDiagramTypeProvider dtp) {
    super(dtp);
  }
  
  public IReconnectionFeature getReconnectionFeature(final IReconnectionContext context) {
    RelationReconnectionFeature _relationReconnectionFeature = new RelationReconnectionFeature(this);
    return _relationReconnectionFeature;
  }
  
  public IAddFeature getAddFeature(final IAddContext context) {
    IAddFeature _switchResult = null;
    Object _newObject = context.getNewObject();
    final Object _switchValue = _newObject;
    boolean _matched = false;
    if (!_matched) {
      if (_switchValue instanceof Activity) {
        final Activity _activity = (Activity)_switchValue;
        _matched=true;
        AddActivityFeature _addActivityFeature = new AddActivityFeature(this);
        _switchResult = _addActivityFeature;
      }
    }
    if (!_matched) {
      if (_switchValue instanceof ConditionalRelation) {
        final ConditionalRelation _conditionalRelation = (ConditionalRelation)_switchValue;
        _matched=true;
        AddConditionalRelationFeature _addConditionalRelationFeature = new AddConditionalRelationFeature(this);
        _switchResult = _addConditionalRelationFeature;
      }
    }
    if (!_matched) {
      if (_switchValue instanceof Relation) {
        final Relation _relation = (Relation)_switchValue;
        _matched=true;
        AddRelationFeature _addRelationFeature = new AddRelationFeature(this);
        _switchResult = _addRelationFeature;
      }
    }
    if (!_matched) {
      if (_switchValue instanceof Sink) {
        final Sink _sink = (Sink)_switchValue;
        _matched=true;
        AddSinkFeature _addSinkFeature = new AddSinkFeature(this);
        _switchResult = _addSinkFeature;
      }
    }
    if (!_matched) {
      if (_switchValue instanceof Source) {
        final Source _source = (Source)_switchValue;
        _matched=true;
        AddSourceFeature _addSourceFeature = new AddSourceFeature(this);
        _switchResult = _addSourceFeature;
      }
    }
    if (!_matched) {
      IAddFeature _addFeature = super.getAddFeature(context);
      _switchResult = _addFeature;
    }
    return _switchResult;
  }
  
  public ICreateFeature[] getCreateFeatures() {
    CreateActivityFeature _createActivityFeature = new CreateActivityFeature(this);
    CreateSinkFeature _createSinkFeature = new CreateSinkFeature(this);
    CreateSourceFeature _createSourceFeature = new CreateSourceFeature(this);
    return new ICreateFeature[] { _createActivityFeature, _createSinkFeature, _createSourceFeature };
  }
  
  public IUpdateFeature getUpdateFeature(final IUpdateContext context) {
    IUpdateFeature _xblockexpression = null;
    {
      final PictogramElement pe = context.getPictogramElement();
      IUpdateFeature _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        if (Objects.equal(pe,FreeFormConnection.class)) {
          _matched=true;
          IUpdateFeature _xblockexpression_1 = null;
          {
            final Object element = this.getBusinessObjectForPictogramElement(((FreeFormConnection) pe));
            IUpdateFeature _xifexpression = null;
            if ((element instanceof IConditionalRelation)) {
              UpdateConditionalRelationFeature _updateConditionalRelationFeature = new UpdateConditionalRelationFeature(this);
              _xifexpression = _updateConditionalRelationFeature;
            } else {
              IUpdateFeature _updateFeature = super.getUpdateFeature(context);
              _xifexpression = _updateFeature;
            }
            _xblockexpression_1 = (_xifexpression);
          }
          _switchResult = _xblockexpression_1;
        }
      }
      if (!_matched) {
        if (Objects.equal(pe,ContainerShape.class)) {
          _matched=true;
          IUpdateFeature _xblockexpression_2 = null;
          {
            final Object element = this.getBusinessObjectForPictogramElement(pe);
            IUpdateFeature _xifexpression = null;
            if ((element instanceof ISimulationElement)) {
              UpdateLSEMElementFeature _updateLSEMElementFeature = new UpdateLSEMElementFeature(this);
              _xifexpression = _updateLSEMElementFeature;
            } else {
              IUpdateFeature _updateFeature = super.getUpdateFeature(context);
              _xifexpression = _updateFeature;
            }
            _xblockexpression_2 = (_xifexpression);
          }
          _switchResult = _xblockexpression_2;
        }
      }
      if (!_matched) {
        IUpdateFeature _updateFeature = super.getUpdateFeature(context);
        _switchResult = _updateFeature;
      }
      _xblockexpression = (_switchResult);
    }
    return _xblockexpression;
  }
  
  public ICreateConnectionFeature[] getCreateConnectionFeatures() {
    CreateConditionalRelationFeature _createConditionalRelationFeature = new CreateConditionalRelationFeature(this);
    CreateRelationFeature _createRelationFeature = new CreateRelationFeature(this);
    return new ICreateConnectionFeature[] { _createConditionalRelationFeature, _createRelationFeature };
  }
  
  public ICustomFeature[] getCustomFeatures(final ICustomContext context) {
    CollapseActivityFeature _collapseActivityFeature = new CollapseActivityFeature(this);
    MergeActivitiesFeature _mergeActivitiesFeature = new MergeActivitiesFeature(this);
    return new ICustomFeature[] { _collapseActivityFeature, _mergeActivitiesFeature };
  }
  
  public IMoveShapeFeature getMoveShapeFeature(final IMoveShapeContext context) {
    IMoveShapeFeature _xblockexpression = null;
    {
      PictogramElement _pictogramElement = context.getPictogramElement();
      final Object element = this.getBusinessObjectForPictogramElement(_pictogramElement);
      IMoveShapeFeature _xifexpression = null;
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(element, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (_notEquals && (element instanceof IActivity));
      }
      if (_and) {
        MoveActivityOnAnotherFeature _moveActivityOnAnotherFeature = new MoveActivityOnAnotherFeature(this);
        _xifexpression = _moveActivityOnAnotherFeature;
      } else {
        IMoveShapeFeature _moveShapeFeature = super.getMoveShapeFeature(context);
        _xifexpression = _moveShapeFeature;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public IDeleteFeature getDeleteFeature(final IDeleteContext context) {
    IDeleteFeature _xblockexpression = null;
    {
      PictogramElement _pictogramElement = context.getPictogramElement();
      final Object element = this.getBusinessObjectForPictogramElement(_pictogramElement);
      IDeleteFeature _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        if (Objects.equal(element,IActivity.class)) {
          _matched=true;
          DeleteActivityFeature _deleteActivityFeature = new DeleteActivityFeature(this);
          _switchResult = _deleteActivityFeature;
        }
      }
      if (!_matched) {
        if (Objects.equal(element,ISink.class)) {
          _matched=true;
          DeleteSinkFeature _deleteSinkFeature = new DeleteSinkFeature(this);
          _switchResult = _deleteSinkFeature;
        }
      }
      if (!_matched) {
        if (Objects.equal(element,ISource.class)) {
          _matched=true;
          DeleteSourceFeature _deleteSourceFeature = new DeleteSourceFeature(this);
          _switchResult = _deleteSourceFeature;
        }
      }
      if (!_matched) {
        IDeleteFeature _deleteFeature = super.getDeleteFeature(context);
        _switchResult = _deleteFeature;
      }
      _xblockexpression = (_switchResult);
    }
    return _xblockexpression;
  }
}
