package de.lsem.simulation.transformation.sim.xtext;

import com.google.common.base.Objects;
import de.lsem.process.model.ProcessNode;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.repository.model.simulation.UnitOfTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class CommonTransformation {
  protected ICapacity createCapacity(final IActivity o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final ICapacity _result;
    synchronized (_createCache_createCapacity) {
      if (_createCache_createCapacity.containsKey(_cacheKey)) {
        return _createCache_createCapacity.get(_cacheKey);
      }
      ICapacity _createCapacity = SimulationFactory.eINSTANCE.createCapacity();
      _result = _createCapacity;
      _createCache_createCapacity.put(_cacheKey, _result);
    }
    _init_createCapacity(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,ICapacity> _createCache_createCapacity = CollectionLiterals.newHashMap();
  
  private void _init_createCapacity(final ICapacity capa, final IActivity o) {
  }
  
  protected IGood createGood(final ISource o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final IGood _result;
    synchronized (_createCache_createGood) {
      if (_createCache_createGood.containsKey(_cacheKey)) {
        return _createCache_createGood.get(_cacheKey);
      }
      IGood _createGood = SimulationFactory.eINSTANCE.createGood();
      _result = _createGood;
      _createCache_createGood.put(_cacheKey, _result);
    }
    _init_createGood(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,IGood> _createCache_createGood = CollectionLiterals.newHashMap();
  
  private void _init_createGood(final IGood el, final ISource o) {
    el.setDescription("");
    el.setName("");
    el.setType("");
  }
  
  protected ITime createTime(final ISimulationElement o) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(o);
    final ITime _result;
    synchronized (_createCache_createTime) {
      if (_createCache_createTime.containsKey(_cacheKey)) {
        return _createCache_createTime.get(_cacheKey);
      }
      ITime _createTime = SimulationFactory.eINSTANCE.createTime();
      _result = _createTime;
      _createCache_createTime.put(_cacheKey, _result);
    }
    _init_createTime(_result, o);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,ITime> _createCache_createTime = CollectionLiterals.newHashMap();
  
  private void _init_createTime(final ITime el, final ISimulationElement o) {
    IConstant _createTimePeriod = this.createTimePeriod(el);
    el.setPeriod(_createTimePeriod);
    el.setUnit(UnitOfTime.HOUR);
  }
  
  protected IConstant createTimePeriod(final ITime time) {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(time);
    final IConstant _result;
    synchronized (_createCache_createTimePeriod) {
      if (_createCache_createTimePeriod.containsKey(_cacheKey)) {
        return _createCache_createTimePeriod.get(_cacheKey);
      }
      IConstant _createConstant = SimulationFactory.eINSTANCE.createConstant();
      _result = _createConstant;
      _createCache_createTimePeriod.put(_cacheKey, _result);
    }
    _init_createTimePeriod(_result, time);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,IConstant> _createCache_createTimePeriod = CollectionLiterals.newHashMap();
  
  private void _init_createTimePeriod(final IConstant el, final ITime time) {
    el.setValue(0.0);
  }
  
  protected void transferId(final ProcessNode s, final ISimulationElement t) {
    String _id = s.getId();
    String _label = s.getLabel();
    String _trimmedId = this.getTrimmedId(_id, _label);
    t.setName(_trimmedId);
  }
  
  protected void transferId(final /* FlowElement */Object s, final ISimulationElement t) {
    throw new Error("Unresolved compilation problems:"
      + "\nid cannot be resolved"
      + "\nname cannot be resolved");
  }
  
  private String getTrimmedId(final String id, final String label) {
    String _xifexpression = null;
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(label, null));
    if (!_notEquals) {
      _and = false;
    } else {
      boolean _isEmpty = label.isEmpty();
      boolean _not = (!_isEmpty);
      _and = (_notEquals && _not);
    }
    if (_and) {
      String _replaceUmlaute = CommonTransformation.replaceUmlaute(label);
      String _trim = _replaceUmlaute.trim();
      _xifexpression = _trim;
    } else {
      String _xifexpression_1 = null;
      boolean _and_1 = false;
      boolean _notEquals_1 = (!Objects.equal(id, null));
      if (!_notEquals_1) {
        _and_1 = false;
      } else {
        boolean _isEmpty_1 = id.isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        _and_1 = (_notEquals_1 && _not_1);
      }
      if (_and_1) {
        String _replaceUmlaute_1 = CommonTransformation.replaceUmlaute(id);
        String _trim_1 = _replaceUmlaute_1.trim();
        _xifexpression_1 = _trim_1;
      } else {
        _xifexpression_1 = "";
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public static String replaceUmlaute(final String it) {
    String _replace = it.replace("ü", "ue");
    String _replace_1 = _replace.replace("ö", "oe");
    String _replace_2 = _replace_1.replace("ä", "ae");
    String _replace_3 = _replace_2.replace("Ü", "Ue");
    String _replace_4 = _replace_3.replace("Ö", "Oe");
    String _replace_5 = _replace_4.replace("Ä", 
      "Ae");
    return _replace_5;
  }
}
