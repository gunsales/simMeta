package de.lsem.simulation.transformation.anylogic.transform.xtend;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.transform.helper.Costants;
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric;
import de.lsem.simulation.transformation.anylogic.transform.xtend.generator.ObjectTransformer;
import de.lsem.simulation.transformation.anylogic.transform.xtend.generator.SelectOutputGenerator;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class MainTransformer implements Costants {
  @Inject
  @Extension
  private ObjectTransformer _objectTransformer;
  
  @Inject
  @Extension
  private SelectOutputGenerator _selectOutputGenerator;
  
  @Inject
  @Extension
  private Variables v;
  
  public ArrayList<EmbeddedObject> transform(final XMIResource r) throws NullPointerException {
    final ArrayList<?> _cacheKey = CollectionLiterals.newArrayList(r);
    final ArrayList<EmbeddedObject> _result;
    synchronized (_createCache_transform) {
      if (_createCache_transform.containsKey(_cacheKey)) {
        return _createCache_transform.get(_cacheKey);
      }
      ArrayList<EmbeddedObject> _arrayList = new ArrayList<EmbeddedObject>();
      _result = _arrayList;
      _createCache_transform.put(_cacheKey, _result);
    }
    _init_transform(_result, r);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,ArrayList<EmbeddedObject>> _createCache_transform = CollectionLiterals.newHashMap();
  
  private void _init_transform(final ArrayList<EmbeddedObject> retVal, final XMIResource r) throws NullPointerException {
    boolean _equals = Objects.equal(r, null);
    if (_equals) {
      NullPointerException _nullPointerException = new NullPointerException("Error. Resource must not be null.");
      throw _nullPointerException;
    }
    this.v.setResource(r);
    List<ISimulationElement> _simulationElementsWithoutSubActivities = this.simulationElementsWithoutSubActivities();
    final Procedure1<ISimulationElement> _function = new Procedure1<ISimulationElement>() {
      public void apply(final ISimulationElement it) {
        EmbeddedObjectGeneric _transformGeneralObject = MainTransformer.this._objectTransformer.transformGeneralObject(it);
        retVal.add(_transformGeneralObject);
        ArrayList<EmbeddedObjectGeneric> _dispatchOut = MainTransformer.this._selectOutputGenerator.dispatchOut(it);
        final Procedure1<EmbeddedObjectGeneric> _function = new Procedure1<EmbeddedObjectGeneric>() {
          public void apply(final EmbeddedObjectGeneric it) {
            retVal.add(it);
          }
        };
        IterableExtensions.<EmbeddedObjectGeneric>forEach(_dispatchOut, _function);
      }
    };
    IterableExtensions.<ISimulationElement>forEach(_simulationElementsWithoutSubActivities, _function);
  }
  
  public Set<Connector> getConnectorSet() {
    Set<Connector> _connectorSet = this.v.getConnectorSet();
    return _connectorSet;
  }
  
  private List<IActivity> activities() {
    XMIResource _resource = this.v.getResource();
    TreeIterator<EObject> _allContents = _resource.getAllContents();
    Iterator<IActivity> _filter = Iterators.<IActivity>filter(_allContents, IActivity.class);
    List<IActivity> _list = IteratorExtensions.<IActivity>toList(_filter);
    return _list;
  }
  
  private ArrayList<IActivity> getSubActivities() {
    ArrayList<IActivity> _xblockexpression = null;
    {
      final ArrayList<IActivity> retVal = CollectionLiterals.<IActivity>newArrayList();
      List<IActivity> _activities = this.activities();
      for (final IActivity aDummy : _activities) {
        EList<IActivity> _subActivities = aDummy.getSubActivities();
        for (final IActivity aDummy2 : _subActivities) {
          retVal.add(aDummy2);
        }
      }
      _xblockexpression = (retVal);
    }
    return _xblockexpression;
  }
  
  private List<ISimulationElement> simulationElementsWithoutSubActivities() {
    List<ISimulationElement> _xblockexpression = null;
    {
      XMIResource _resource = this.v.getResource();
      TreeIterator<EObject> _allContents = _resource.getAllContents();
      Iterator<ISimulationElement> _filter = Iterators.<ISimulationElement>filter(_allContents, ISimulationElement.class);
      final List<ISimulationElement> retVal = IteratorExtensions.<ISimulationElement>toList(_filter);
      ArrayList<IActivity> _subActivities = this.getSubActivities();
      retVal.removeAll(_subActivities);
      _xblockexpression = (retVal);
    }
    return _xblockexpression;
  }
}
