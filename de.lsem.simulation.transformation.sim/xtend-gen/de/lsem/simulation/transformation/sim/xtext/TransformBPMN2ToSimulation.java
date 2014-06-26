package de.lsem.simulation.transformation.sim.xtext;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.transformation.sim.xtext.CommonTransformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TransformBPMN2ToSimulation {
  /**
   * Used for simMeta-subelement-generation
   */
  @Inject
  @Extension
  private CommonTransformation _commonTransformation;
  
  private int relationCounter = 0;
  
  /**
   * Main-class for transformation from BPMN2-Process-Diagrams to simMeta-Diagrams.
   * The transformation includes the following flow-elements: Gateways, Start- and End-Events,
   * Tasks and sequence-flows. All other BPMN2-Elements will be ignored.
   */
  public TransformBPMN2ToSimulation() {
  }
  
  /**
   * Entry point for transformation. The transformation searches for start-elements.
   * Start-Element and CONNECTED successors will be transformed until all successors have been transformed.
   * Check for cycles in process before the transformation!
   * 
   * @return Transformed and connected simMeta-Elements
   */
  public Set<? extends ISimulationElement> startTransformation(final List<FlowElement> it) {
    Set<ISimulationElement> _xblockexpression = null;
    {
      HashSet<FlowNode> _filterValidElements = this.filterValidElements(it);
      Iterable<FlowNode> _startElements = this.getStartElements(_filterValidElements);
      final Function1<FlowNode,ISimulationElement> _function = new Function1<FlowNode,ISimulationElement>() {
        public ISimulationElement apply(final FlowNode it) {
          ISimulationElement _xifexpression = null;
          boolean _not = (!(it instanceof Gateway));
          if (_not) {
            ISimulationElement _transform = TransformBPMN2ToSimulation.this.transform(it);
            _xifexpression = _transform;
          }
          return _xifexpression;
        }
      };
      Iterable<ISimulationElement> _map = IterableExtensions.<FlowNode, ISimulationElement>map(_startElements, _function);
      final Set<ISimulationElement> starts = IterableExtensions.<ISimulationElement>toSet(_map);
      HashSet<ISimulationElement> _newHashSet = CollectionLiterals.<ISimulationElement>newHashSet();
      Set<ISimulationElement> _reachable = this.reachable(_newHashSet, starts);
      Iterable<ISimulationElement> _filterNull = IterableExtensions.<ISimulationElement>filterNull(_reachable);
      Set<ISimulationElement> _set = IterableExtensions.<ISimulationElement>toSet(_filterNull);
      _xblockexpression = (_set);
    }
    return _xblockexpression;
  }
  
  private Iterable<FlowNode> getStartElements(final Set<FlowNode> it) {
    final Function1<FlowNode,Boolean> _function = new Function1<FlowNode,Boolean>() {
      public Boolean apply(final FlowNode it) {
        boolean _or = false;
        List<SequenceFlow> _incoming = it.getIncoming();
        boolean _equals = Objects.equal(_incoming, null);
        if (_equals) {
          _or = true;
        } else {
          List<SequenceFlow> _incoming_1 = it.getIncoming();
          int _size = _incoming_1.size();
          boolean _lessThan = (_size < 1);
          _or = (_equals || _lessThan);
        }
        return Boolean.valueOf(_or);
      }
    };
    Iterable<FlowNode> _filter = IterableExtensions.<FlowNode>filter(it, _function);
    return _filter;
  }
  
  private HashSet<FlowNode> filterValidElements(final List<FlowElement> it) {
    HashSet<FlowNode> _xblockexpression = null;
    {
      HashSet<FlowNode> _hashSet = new HashSet<FlowNode>();
      final HashSet<FlowNode> retVal = _hashSet;
      Iterable<StartEvent> _filter = Iterables.<StartEvent>filter(it, StartEvent.class);
      Iterables.<FlowNode>addAll(retVal, _filter);
      Iterable<EndEvent> _filter_1 = Iterables.<EndEvent>filter(it, EndEvent.class);
      Iterables.<FlowNode>addAll(retVal, _filter_1);
      Iterable<Task> _filter_2 = Iterables.<Task>filter(it, Task.class);
      Iterables.<FlowNode>addAll(retVal, _filter_2);
      Iterable<Gateway> _filter_3 = Iterables.<Gateway>filter(it, Gateway.class);
      Iterables.<FlowNode>addAll(retVal, _filter_3);
      _xblockexpression = (retVal);
    }
    return _xblockexpression;
  }
  
  private Set<ISimulationElement> reachable(final Set<ISimulationElement> input, final Set<ISimulationElement> output) {
    Set<ISimulationElement> _xifexpression = null;
    int _size = input.size();
    int _size_1 = output.size();
    boolean _equals = (_size == _size_1);
    if (_equals) {
      _xifexpression = output;
    } else {
      Set<ISimulationElement> _xblockexpression = null;
      {
        HashSet<ISimulationElement> _hashSet = new HashSet<ISimulationElement>(output);
        final HashSet<ISimulationElement> temp = _hashSet;
        final Function1<ISimulationElement,Collection<IRelation>> _function = new Function1<ISimulationElement,Collection<IRelation>>() {
          public Collection<IRelation> apply(final ISimulationElement it) {
            Collection<IRelation> _xifexpression = null;
            boolean _or = false;
            boolean _equals = Objects.equal(it, null);
            if (_equals) {
              _or = true;
            } else {
              EList<IRelation> _outgoing = it.getOutgoing();
              boolean _equals_1 = Objects.equal(_outgoing, null);
              _or = (_equals || _equals_1);
            }
            if (_or) {
              HashSet<IRelation> _hashSet = new HashSet<IRelation>();
              _xifexpression = _hashSet;
            } else {
              EList<IRelation> _outgoing_1 = it.getOutgoing();
              _xifexpression = _outgoing_1;
            }
            return _xifexpression;
          }
        };
        Iterable<Collection<IRelation>> _map = IterableExtensions.<ISimulationElement, Collection<IRelation>>map(output, _function);
        Iterable<IRelation> _flatten = Iterables.<IRelation>concat(_map);
        final Function1<IRelation,ISimulationElement> _function_1 = new Function1<IRelation,ISimulationElement>() {
          public ISimulationElement apply(final IRelation it) {
            ISimulationElement _target = it.getTarget();
            return _target;
          }
        };
        Iterable<ISimulationElement> _map_1 = IterableExtensions.<IRelation, ISimulationElement>map(_flatten, _function_1);
        final Procedure1<ISimulationElement> _function_2 = new Procedure1<ISimulationElement>() {
          public void apply(final ISimulationElement it) {
            temp.add(it);
          }
        };
        IterableExtensions.<ISimulationElement>forEach(_map_1, _function_2);
        Set<ISimulationElement> _reachable = this.reachable(output, temp);
        _xblockexpression = (_reachable);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  private void transformFlow(final SequenceFlow sf, final FlowElement originalSource) {
    FlowNode _targetRef = sf.getTargetRef();
    if ((_targetRef instanceof Gateway)) {
      FlowNode _targetRef_1 = sf.getTargetRef();
      List<SequenceFlow> _outgoing = _targetRef_1.getOutgoing();
      final Procedure1<SequenceFlow> _function = new Procedure1<SequenceFlow>() {
        public void apply(final SequenceFlow it) {
          FlowNode _sourceRef = sf.getSourceRef();
          TransformBPMN2ToSimulation.this.transformFlow(it, _sourceRef);
        }
      };
      IterableExtensions.<SequenceFlow>forEach(_outgoing, _function);
    } else {
      FlowNode _targetRef_2 = sf.getTargetRef();
      ISimulationElement targ = this.transform(_targetRef_2);
      ISimulationElement sour = targ;
      FlowNode _sourceRef = sf.getSourceRef();
      if ((_sourceRef instanceof Gateway)) {
        ISimulationElement _transform = this.transform(originalSource);
        sour = _transform;
        FlowNode _sourceRef_1 = sf.getSourceRef();
        List<SequenceFlow> _outgoing_1 = _sourceRef_1.getOutgoing();
        int _size = _outgoing_1.size();
        boolean _greaterThan = (_size > 1);
        if (_greaterThan) {
          final IConditionalRelation conditionalRelation = SimulationFactory.eINSTANCE.createConditionalRelation();
          conditionalRelation.setProbability(0.0);
          conditionalRelation.setCondition("");
          this.setCommonRelationValues(conditionalRelation, sour, targ);
          boolean _equals = sour.equals(targ);
          boolean _not = (!_equals);
          if (_not) {
            EList<IRelation> _outgoing_2 = sour.getOutgoing();
            _outgoing_2.add(conditionalRelation);
          }
        } else {
          final IRelation relation = SimulationFactory.eINSTANCE.createRelation();
          this.setCommonRelationValues(relation, sour, targ);
        }
      } else {
        IRelation relation_1 = SimulationFactory.eINSTANCE.createRelation();
        FlowNode _sourceRef_2 = sf.getSourceRef();
        ISimulationElement _transform_1 = this.transform(_sourceRef_2);
        sour = _transform_1;
        this.setCommonRelationValues(relation_1, sour, targ);
      }
    }
  }
  
  private IRelation setCommonRelationValues(final IRelation it, final ISimulationElement sour, final ISimulationElement targ) {
    IRelation _xblockexpression = null;
    {
      it.setSource(sour);
      it.setTarget(targ);
      String _plus = ("relation_" + Integer.valueOf(this.relationCounter));
      it.setName(_plus);
      int _plus_1 = (this.relationCounter + 1);
      this.relationCounter = _plus_1;
      _xblockexpression = (it);
    }
    return _xblockexpression;
  }
  
  private ISimulationElement _transform(final FlowElement it) {
    return null;
  }
  
  private ISimulationElement _transform(final EndEvent it) {
    ISink _createTarget = this.createTarget(it);
    return _createTarget;
  }
  
  private ISimulationElement _transform(final StartEvent it) {
    ISource _xblockexpression = null;
    {
      final ISource so = this.createTarget(it);
      ITime _createTime = this._commonTransformation.createTime(so);
      so.setFirstEntity(_createTime);
      ITime _firstEntity = so.getFirstEntity();
      ITime _firstEntity_1 = so.getFirstEntity();
      IConstant _createTimePeriod = this._commonTransformation.createTimePeriod(((ITime) _firstEntity_1));
      _firstEntity.setPeriod(_createTimePeriod);
      ITime _createTime_1 = this._commonTransformation.createTime(so);
      so.setNewEntities(_createTime_1);
      ITime _newEntities = so.getNewEntities();
      ITime _newEntities_1 = so.getNewEntities();
      IConstant _createTimePeriod_1 = this._commonTransformation.createTimePeriod(((ITime) _newEntities_1));
      _newEntities.setPeriod(_createTimePeriod_1);
      IGood _createGood = this._commonTransformation.createGood(so);
      so.setProcessedObject(_createGood);
      _xblockexpression = (so);
    }
    return _xblockexpression;
  }
  
  private ISimulationElement _transform(final Task it) {
    IActivity _xblockexpression = null;
    {
      final IActivity ac = this.createTarget(it);
      ICapacity _createCapacity = this._commonTransformation.createCapacity(ac);
      ac.setCapacity(_createCapacity);
      ITime _createTime = this._commonTransformation.createTime(ac);
      ac.setTimePeriod(_createTime);
      ITime _timePeriod = ac.getTimePeriod();
      ITime _timePeriod_1 = ac.getTimePeriod();
      IConstant _createTimePeriod = this._commonTransformation.createTimePeriod(((ITime) _timePeriod_1));
      _timePeriod.setPeriod(_createTimePeriod);
      _xblockexpression = (ac);
    }
    return _xblockexpression;
  }
  
  private ISource createTarget(final StartEvent o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final ISource _result;
    synchronized (_createCache_createTarget) {
      if (_createCache_createTarget.containsKey(_cacheKey)) {
        return _createCache_createTarget.get(_cacheKey);
      }
      ISource _createSource = SimulationFactory.eINSTANCE.createSource();
      _result = _createSource;
      _createCache_createTarget.put(_cacheKey, _result);
    }
    _init_createTarget(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,ISource> _createCache_createTarget = CollectionLiterals.newHashMap();
  
  private void _init_createTarget(final ISource start, final StartEvent o) {
    this._commonTransformation.transferId(o, start);
    List<SequenceFlow> _outgoing = o.getOutgoing();
    final Procedure1<SequenceFlow> _function = new Procedure1<SequenceFlow>() {
      public void apply(final SequenceFlow it) {
        TransformBPMN2ToSimulation.this.transformFlow(it, o);
      }
    };
    IterableExtensions.<SequenceFlow>forEach(_outgoing, _function);
  }
  
  private ISink createTarget(final EndEvent o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final ISink _result;
    synchronized (_createCache_createTarget_1) {
      if (_createCache_createTarget_1.containsKey(_cacheKey)) {
        return _createCache_createTarget_1.get(_cacheKey);
      }
      ISink _createSink = SimulationFactory.eINSTANCE.createSink();
      _result = _createSink;
      _createCache_createTarget_1.put(_cacheKey, _result);
    }
    _init_createTarget_1(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,ISink> _createCache_createTarget_1 = CollectionLiterals.newHashMap();
  
  private void _init_createTarget_1(final ISink el, final EndEvent o) {
    this._commonTransformation.transferId(o, el);
  }
  
  private IActivity createTarget(final Task o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final IActivity _result;
    synchronized (_createCache_createTarget_2) {
      if (_createCache_createTarget_2.containsKey(_cacheKey)) {
        return _createCache_createTarget_2.get(_cacheKey);
      }
      IActivity _createActivity = SimulationFactory.eINSTANCE.createActivity();
      _result = _createActivity;
      _createCache_createTarget_2.put(_cacheKey, _result);
    }
    _init_createTarget_2(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,IActivity> _createCache_createTarget_2 = CollectionLiterals.newHashMap();
  
  private void _init_createTarget_2(final IActivity el, final Task o) {
    this._commonTransformation.transferId(o, el);
    List<SequenceFlow> _outgoing = o.getOutgoing();
    final Procedure1<SequenceFlow> _function = new Procedure1<SequenceFlow>() {
      public void apply(final SequenceFlow it) {
        TransformBPMN2ToSimulation.this.transformFlow(it, o);
      }
    };
    IterableExtensions.<SequenceFlow>forEach(_outgoing, _function);
  }
  
  private ISimulationElement transform(final FlowElement it) {
    if (it instanceof EndEvent) {
      return _transform((EndEvent)it);
    } else if (it instanceof StartEvent) {
      return _transform((StartEvent)it);
    } else if (it instanceof Task) {
      return _transform((Task)it);
    } else if (it != null) {
      return _transform(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
