package de.lsem.simulation.transformation.anylogic.transform.xtend.generator;

import de.lsem.repository.model.simulation.Activity;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.Sink;
import de.lsem.repository.model.simulation.Source;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Label;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameter;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameters;
import de.lsem.simulation.transformation.anylogic.transform.helper.IDGenerator;
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.ActiveObjectClassInnerImpl;
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.AnylogicParameterNames;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.CreateHelper;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.DistributionFunctionHelper;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.PositionHelper;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@Singleton
@SuppressWarnings("all")
public class EmbeddedObjectGenerator implements AnylogicParameterNames {
  @Inject
  @Extension
  private CreateHelper _createHelper;
  
  @Inject
  @Extension
  private NamingHelper _namingHelper;
  
  @Inject
  @Extension
  private PositionHelper _positionHelper;
  
  @Inject
  @Extension
  private Variables _variables;
  
  @Inject
  @Extension
  private DistributionFunctionHelper _distributionFunctionHelper;
  
  public EmbeddedObjectGeneric transformGeneralObject(final ISimulationElement it) {
    EmbeddedObjectGeneric _xblockexpression = null;
    {
      EmbeddedObjectGeneric eo = this.dispatchForTransformation(it);
      String _name = it.getName();
      String _makeValidForAnyLogic = this._namingHelper.makeValidForAnyLogic(_name);
      eo.setName(_makeValidForAnyLogic);
      int _x = this._positionHelper.getX(it);
      eo.setX(_x);
      int _y = this._positionHelper.getY(it);
      eo.setY(_y);
      Class<? extends ISimulationElement> _class = it.getClass();
      String _createEntityTypeDeclaration = this._namingHelper.createEntityTypeDeclaration(_class);
      ActiveObjectClassInnerImpl _activeObjectClassInnerImpl = new ActiveObjectClassInnerImpl(_createEntityTypeDeclaration);
      eo.setActiveObjectClassInner(_activeObjectClassInnerImpl);
      int _minus = (-2);
      int _minus_1 = (-11);
      Label _createLabel = this._createHelper.createLabel(_minus, _minus_1);
      eo.setLabel(_createLabel);
      _xblockexpression = (eo);
    }
    return _xblockexpression;
  }
  
  private EmbeddedObjectGeneric dispatchForTransformation(final ISimulationElement it) {
    EmbeddedObjectGeneric _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (it instanceof Activity) {
        final Activity _activity = (Activity)it;
        _matched=true;
        EmbeddedObjectGeneric _transformObject = this.transformObject(((IActivity) _activity));
        _switchResult = _transformObject;
      }
    }
    if (!_matched) {
      if (it instanceof Source) {
        final Source _source = (Source)it;
        _matched=true;
        EmbeddedObjectGeneric _transformObject = this.transformObject(((ISource) _source));
        _switchResult = _transformObject;
      }
    }
    if (!_matched) {
      if (it instanceof Sink) {
        final Sink _sink = (Sink)it;
        _matched=true;
        EmbeddedObjectGeneric _transformObject = this.transformObject(((ISink) _sink));
        _switchResult = _transformObject;
      }
    }
    if (!_matched) {
      EmbeddedObjectGeneric _transformObject = this.transformObject(it);
      _switchResult = _transformObject;
    }
    return _switchResult;
  }
  
  private EmbeddedObjectGeneric _transformObject(final IActivity it) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(it);
    final EmbeddedObjectGeneric _result;
    synchronized (_createCache_transformObject) {
      if (_createCache_transformObject.containsKey(_cacheKey)) {
        return _createCache_transformObject.get(_cacheKey);
      }
      EmbeddedObjectGeneric _embeddedObjectGeneric = new EmbeddedObjectGeneric();
      _result = _embeddedObjectGeneric;
      _createCache_transformObject.put(_cacheKey, _result);
    }
    _init_transformObject(_result, it);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,EmbeddedObjectGeneric> _createCache_transformObject = CollectionLiterals.newHashMap();
  
  private void _init_transformObject(final EmbeddedObjectGeneric retVal, final IActivity it) {
    Parameters _parameters = new Parameters();
    final Parameters p = _parameters;
    Long _generateID = IDGenerator.generateID();
    retVal.setId((_generateID).longValue());
    Parameter _createEmptyParameter = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_QUANTITY);
    p.addParameter(_createEmptyParameter);
    ITime _timePeriod = it.getTimePeriod();
    IDistribution _period = _timePeriod.getPeriod();
    String _distributionFunctionFor = this._distributionFunctionHelper.getDistributionFunctionFor(_period);
    Parameter _createParameter = EmbeddedObjectGeneric.createParameter(AnylogicParameterNames.AP_DELAY_TIME, _distributionFunctionFor);
    p.addParameter(_createParameter);
    Parameter _createEmptyParameter_1 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_RESOURCE_POOL);
    p.addParameter(_createEmptyParameter_1);
    Parameter _createEmptyParameter_2 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_ON_ENTER);
    p.addParameter(_createEmptyParameter_2);
    Parameter _createEmptyParameter_3 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_ON_ENTER_DELAY);
    p.addParameter(_createEmptyParameter_3);
    Parameter _createEmptyParameter_4 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_ON_EXIT);
    p.addParameter(_createEmptyParameter_4);
    Parameter _queueCapacity = this.queueCapacity(it);
    p.addParameter(_queueCapacity);
    Parameter _createEmptyParameter_5 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_MAX_CAPACITY);
    p.addParameter(_createEmptyParameter_5);
    Parameter _createEmptyParameter_6 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_ENABLE_TIMEOUT);
    p.addParameter(_createEmptyParameter_6);
    Parameter _createEmptyParameter_7 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_TIMEOUT);
    p.addParameter(_createEmptyParameter_7);
    Parameter _createEmptyParameter_8 = EmbeddedObjectGeneric.createEmptyParameter(AnylogicParameterNames.AP_ON_EXIT_TIMEOUT);
    p.addParameter(_createEmptyParameter_8);
    Parameter _createEmptyParameter_9 = EmbeddedObjectGeneric.createEmptyParameter("enablePreemption");
    p.addParameter(_createEmptyParameter_9);
    Parameter _createEmptyParameter_10 = EmbeddedObjectGeneric.createEmptyParameter("priority");
    p.addParameter(_createEmptyParameter_10);
    Parameter _createEmptyParameter_11 = EmbeddedObjectGeneric.createEmptyParameter("onExitPreempted");
    p.addParameter(_createEmptyParameter_11);
    Parameter _createEmptyParameter_12 = EmbeddedObjectGeneric.createEmptyParameter("animationGuideQueue");
    p.addParameter(_createEmptyParameter_12);
    Parameter _createEmptyParameter_13 = EmbeddedObjectGeneric.createEmptyParameter("animationTypeQueue");
    p.addParameter(_createEmptyParameter_13);
    Parameter _createEmptyParameter_14 = EmbeddedObjectGeneric.createEmptyParameter("animationForwardQueue");
    p.addParameter(_createEmptyParameter_14);
    Parameter _createEmptyParameter_15 = EmbeddedObjectGeneric.createEmptyParameter("animationGuideDelay");
    p.addParameter(_createEmptyParameter_15);
    Parameter _createEmptyParameter_16 = EmbeddedObjectGeneric.createEmptyParameter("animationTypeDelay");
    p.addParameter(_createEmptyParameter_16);
    Parameter _createEmptyParameter_17 = EmbeddedObjectGeneric.createEmptyParameter("animationForwardDelay");
    p.addParameter(_createEmptyParameter_17);
    Parameter _createEmptyParameter_18 = EmbeddedObjectGeneric.createEmptyParameter("enableStats");
    p.addParameter(_createEmptyParameter_18);
    retVal.setParameters(p);
  }
  
  public Parameter queueCapacity(final IActivity it) {
    Parameter _xifexpression = null;
    ServiceType _serviceType = it.getServiceType();
    int _value = _serviceType.getValue();
    boolean _equals = (_value == ServiceType.STORAGE_VALUE);
    if (_equals) {
      ICapacity _capacity = it.getCapacity();
      int _maxCapacity = _capacity.getMaxCapacity();
      String _valueOf = String.valueOf(_maxCapacity);
      Parameter _createParameter = EmbeddedObjectGeneric.createParameter("queueCapacity", _valueOf);
      _xifexpression = _createParameter;
    } else {
      Parameter _createEmptyParameter = EmbeddedObjectGeneric.createEmptyParameter("queueCapacity");
      _xifexpression = _createEmptyParameter;
    }
    return _xifexpression;
  }
  
  private EmbeddedObjectGeneric _transformObject(final ISink it) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(it);
    final EmbeddedObjectGeneric _result;
    synchronized (_createCache_transformObject_1) {
      if (_createCache_transformObject_1.containsKey(_cacheKey)) {
        return _createCache_transformObject_1.get(_cacheKey);
      }
      EmbeddedObjectGeneric _embeddedObjectGeneric = new EmbeddedObjectGeneric();
      _result = _embeddedObjectGeneric;
      _createCache_transformObject_1.put(_cacheKey, _result);
    }
    _init_transformObject_1(_result, it);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,EmbeddedObjectGeneric> _createCache_transformObject_1 = CollectionLiterals.newHashMap();
  
  private void _init_transformObject_1(final EmbeddedObjectGeneric retVal, final ISink it) {
    Parameters _parameters = new Parameters();
    final Parameters p = _parameters;
    Parameter _createEmptyParameter = EmbeddedObjectGeneric.createEmptyParameter("onEnter");
    p.addParameter(_createEmptyParameter);
    retVal.setParameters(p);
    Long _generateID = IDGenerator.generateID();
    retVal.setId((_generateID).longValue());
  }
  
  private EmbeddedObjectGeneric _transformObject(final ISimulationElement it) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(it);
    final EmbeddedObjectGeneric _result;
    synchronized (_createCache_transformObject_2) {
      if (_createCache_transformObject_2.containsKey(_cacheKey)) {
        return _createCache_transformObject_2.get(_cacheKey);
      }
      EmbeddedObjectGeneric _embeddedObjectGeneric = new EmbeddedObjectGeneric();
      _result = _embeddedObjectGeneric;
      _createCache_transformObject_2.put(_cacheKey, _result);
    }
    _init_transformObject_2(_result, it);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,EmbeddedObjectGeneric> _createCache_transformObject_2 = CollectionLiterals.newHashMap();
  
  private void _init_transformObject_2(final EmbeddedObjectGeneric retVal, final ISimulationElement it) {
    Long _generateID = IDGenerator.generateID();
    retVal.setId((_generateID).longValue());
  }
  
  private EmbeddedObjectGeneric _transformObject(final ISource it) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(it);
    final EmbeddedObjectGeneric _result;
    synchronized (_createCache_transformObject_3) {
      if (_createCache_transformObject_3.containsKey(_cacheKey)) {
        return _createCache_transformObject_3.get(_cacheKey);
      }
      EmbeddedObjectGeneric _embeddedObjectGeneric = new EmbeddedObjectGeneric();
      _result = _embeddedObjectGeneric;
      _createCache_transformObject_3.put(_cacheKey, _result);
    }
    _init_transformObject_3(_result, it);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,EmbeddedObjectGeneric> _createCache_transformObject_3 = CollectionLiterals.newHashMap();
  
  private void _init_transformObject_3(final EmbeddedObjectGeneric retVal, final ISource it) {
    Parameters _parameters = new Parameters();
    final Parameters p = _parameters;
    Long _generateID = IDGenerator.generateID();
    retVal.setId((_generateID).longValue());
    Parameter _createEmptyParameter = EmbeddedObjectGeneric.createEmptyParameter("arrivalType");
    p.addParameter(_createEmptyParameter);
    Parameter _createEmptyParameter_1 = EmbeddedObjectGeneric.createEmptyParameter("rate");
    p.addParameter(_createEmptyParameter_1);
    Parameter _createEmptyParameter_2 = EmbeddedObjectGeneric.createEmptyParameter("interarrivalTime");
    p.addParameter(_createEmptyParameter_2);
    Parameter _createEmptyParameter_3 = EmbeddedObjectGeneric.createEmptyParameter("rateSchedule");
    p.addParameter(_createEmptyParameter_3);
    Parameter _createEmptyParameter_4 = EmbeddedObjectGeneric.createEmptyParameter("rateTable");
    p.addParameter(_createEmptyParameter_4);
    Parameter _createEmptyParameter_5 = EmbeddedObjectGeneric.createEmptyParameter("arrivalSchedule");
    p.addParameter(_createEmptyParameter_5);
    Parameter _createEmptyParameter_6 = EmbeddedObjectGeneric.createEmptyParameter("arrivalTable");
    p.addParameter(_createEmptyParameter_6);
    Parameter _createEmptyParameter_7 = EmbeddedObjectGeneric.createEmptyParameter("modifyRate");
    p.addParameter(_createEmptyParameter_7);
    Parameter _createEmptyParameter_8 = EmbeddedObjectGeneric.createEmptyParameter("rateExpression");
    p.addParameter(_createEmptyParameter_8);
    ITime _newEntities = it.getNewEntities();
    IDistribution _period = _newEntities.getPeriod();
    String _distributionFunctionFor = this._distributionFunctionHelper.getDistributionFunctionFor(_period);
    Parameter _createParameter = EmbeddedObjectGeneric.createParameter("entitiesPerArrival", _distributionFunctionFor);
    p.addParameter(_createParameter);
    this.setLimitAndMaxArrivals(it, p);
    Parameter _createEmptyParameter_9 = EmbeddedObjectGeneric.createEmptyParameter("newEntity");
    p.addParameter(_createEmptyParameter_9);
    Parameter _createEmptyParameter_10 = EmbeddedObjectGeneric.createEmptyParameter("onExit");
    p.addParameter(_createEmptyParameter_10);
    Parameter _createEmptyParameter_11 = EmbeddedObjectGeneric.createEmptyParameter("entityShape");
    p.addParameter(_createEmptyParameter_11);
    Parameter _createEmptyParameter_12 = EmbeddedObjectGeneric.createEmptyParameter("uniqueShape");
    p.addParameter(_createEmptyParameter_12);
    Parameter _createEmptyParameter_13 = EmbeddedObjectGeneric.createEmptyParameter("enableRotation");
    p.addParameter(_createEmptyParameter_13);
    Parameter _createEmptyParameter_14 = EmbeddedObjectGeneric.createEmptyParameter("enableVerticalRotation");
    p.addParameter(_createEmptyParameter_14);
    retVal.setParameters(p);
    int _sourceCounter = this._variables.getSourceCounter();
    String _plus = ("SourceA" + Integer.valueOf(_sourceCounter));
    retVal.setName(_plus);
  }
  
  private void setLimitAndMaxArrivals(final ISource it, final Parameters p) {
    int _maxNewEntities = it.getMaxNewEntities();
    boolean _notEquals = (_maxNewEntities != 0);
    if (_notEquals) {
      String _valueOf = String.valueOf(Boolean.TRUE);
      Parameter _createParameter = EmbeddedObjectGeneric.createParameter("limitArrivals", _valueOf);
      p.addParameter(_createParameter);
      int _maxNewEntities_1 = it.getMaxNewEntities();
      String _valueOf_1 = String.valueOf(_maxNewEntities_1);
      Parameter _createParameter_1 = EmbeddedObjectGeneric.createParameter("maxArrivals", _valueOf_1);
      p.addParameter(_createParameter_1);
    } else {
      Parameter _createEmptyParameter = EmbeddedObjectGeneric.createEmptyParameter("limitArrivals");
      p.addParameter(_createEmptyParameter);
      Parameter _createEmptyParameter_1 = EmbeddedObjectGeneric.createEmptyParameter("maxArrivals");
      p.addParameter(_createEmptyParameter_1);
    }
  }
  
  private EmbeddedObjectGeneric transformObject(final ISimulationElement it) {
    if (it instanceof IActivity) {
      return _transformObject((IActivity)it);
    } else if (it instanceof ISink) {
      return _transformObject((ISink)it);
    } else if (it instanceof ISource) {
      return _transformObject((ISource)it);
    } else if (it != null) {
      return _transformObject(it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
