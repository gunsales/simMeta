package de.lsem.simulation.transformation.sim.xtext;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.simulation.transformation.sim.xtext.CommonTransformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
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
  public Set<? extends ISimulationElement> startTransformation(final /* List<FlowElement> */Object it) {
    throw new Error("Unresolved compilation problems:"
      + "\nGateway cannot be resolved to a type.");
  }
  
  private /* Iterable<FlowNode> */Object getStartElements(final /* Set<FlowNode> */Object it) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field incoming is undefined for the type TransformBPMN2ToSimulation"
      + "\nThe method or field incoming is undefined for the type TransformBPMN2ToSimulation"
      + "\n== cannot be resolved"
      + "\n|| cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n< cannot be resolved");
  }
  
  private /* HashSet<FlowNode> */Object filterValidElements(final /* List<FlowElement> */Object it) {
    throw new Error("Unresolved compilation problems:"
      + "\nFlowNode cannot be resolved to a type."
      + "\nStartEvent cannot be resolved to a type."
      + "\nEndEvent cannot be resolved to a type."
      + "\nTask cannot be resolved to a type."
      + "\nGateway cannot be resolved to a type.");
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
  
  private void transformFlow(final /* SequenceFlow */Object sf, final /* FlowElement */Object originalSource) {
    throw new Error("Unresolved compilation problems:"
      + "\nGateway cannot be resolved to a type."
      + "\nGateway cannot be resolved to a type."
      + "\ntargetRef cannot be resolved"
      + "\ntargetRef cannot be resolved"
      + "\noutgoing cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\nsourceRef cannot be resolved"
      + "\ntargetRef cannot be resolved"
      + "\ntransform cannot be resolved"
      + "\nsourceRef cannot be resolved"
      + "\ntransform cannot be resolved"
      + "\nsourceRef cannot be resolved"
      + "\noutgoing cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n> cannot be resolved"
      + "\nequals cannot be resolved"
      + "\n! cannot be resolved"
      + "\noutgoing cannot be resolved"
      + "\nadd cannot be resolved"
      + "\nsourceRef cannot be resolved"
      + "\ntransform cannot be resolved");
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
  
  private ISimulationElement _transform(final /* FlowElement */Object it) {
    return null;
  }
  
  private ISimulationElement _transform(final /* EndEvent */Object it) {
    final TransformBPMN2ToSimulation _converted_this = (TransformBPMN2ToSimulation)this;
    ISource _createTarget = this.createTarget(_converted_this);
    return _createTarget;
  }
  
  private ISimulationElement _transform(final /* StartEvent */Object it) {
    ISource _xblockexpression = null;
    {
      final TransformBPMN2ToSimulation _converted_this = (TransformBPMN2ToSimulation)this;
      final ISource so = this.createTarget(_converted_this);
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
  
  private ISimulationElement _transform(final /* Task */Object it) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method setCapacity is undefined for the type TransformBPMN2ToSimulation"
      + "\nThe method setTimePeriod is undefined for the type TransformBPMN2ToSimulation"
      + "\nThe method getTimePeriod is undefined for the type TransformBPMN2ToSimulation"
      + "\nThe method timePeriod is undefined for the type TransformBPMN2ToSimulation"
      + "\nType mismatch: cannot convert from ISource to IActivity"
      + "\nsetPeriod cannot be resolved");
  }
  
  private ISource createTarget(final /* StartEvent */Object o) {
    throw new Error("Unresolved compilation problems:"
      + "\nStartEvent cannot be resolved to a type.");
  }
  
  private final HashMap<ArrayList<? extends Object>,ISource> _createCache_createTarget = CollectionLiterals.newHashMap();
  
  private void _init_createTarget(final ISource start, final /* StartEvent */Object o) {
    throw new Error("Unresolved compilation problems:"
      + "\noutgoing cannot be resolved"
      + "\nforEach cannot be resolved");
  }
  
  private ISink createTarget(final /* EndEvent */Object o) {
    throw new Error("Unresolved compilation problems:"
      + "\nEndEvent cannot be resolved to a type.");
  }
  
  private final HashMap<ArrayList<? extends Object>,ISink> _createCache_createTarget_1 = CollectionLiterals.newHashMap();
  
  private void _init_createTarget_1(final ISink el, final /* EndEvent */Object o) {
    this._commonTransformation.transferId(o, el);
  }
  
  private IActivity createTarget(final /* Task */Object o) {
    throw new Error("Unresolved compilation problems:"
      + "\nTask cannot be resolved to a type.");
  }
  
  private final HashMap<ArrayList<? extends Object>,IActivity> _createCache_createTarget_2 = CollectionLiterals.newHashMap();
  
  private void _init_createTarget_2(final IActivity el, final /* Task */Object o) {
    throw new Error("Unresolved compilation problems:"
      + "\noutgoing cannot be resolved"
      + "\nforEach cannot be resolved");
  }
  
  private ISimulationElement transform(final FlowElement it) {
    if (it != null) {
      return _transform(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
