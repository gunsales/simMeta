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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
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
  
  /**
   * @param r - xmiresource that holds the graphical and object-based information to be transformed. The resource must be unique in injection-scope, meaning only one transformation allowed per injection.
   */
  public ArrayList<EmbeddedObject> transform(final XMIResource r) throws NullPointerException {
    try {
      ArrayList<EmbeddedObject> _xblockexpression = null;
      {
        ArrayList<EmbeddedObject> _arrayList = new ArrayList<EmbeddedObject>();
        final ArrayList<EmbeddedObject> retVal = _arrayList;
        boolean _equals = Objects.equal(r, null);
        if (_equals) {
          NullPointerException _nullPointerException = new NullPointerException("Error. Resource must not be null.");
          throw _nullPointerException;
        } else {
          XMIResource _resource = this.v.getResource();
          boolean _notEquals = (!Objects.equal(_resource, null));
          if (_notEquals) {
            Exception _exception = new Exception(
              "Transformation can not be started, as the xmi-resource is not unique. Please use a new injector.");
            throw _exception;
          }
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
        _xblockexpression = (retVal);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
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
