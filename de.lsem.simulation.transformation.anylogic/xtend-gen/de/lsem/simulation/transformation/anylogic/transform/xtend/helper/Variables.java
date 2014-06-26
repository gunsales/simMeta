package de.lsem.simulation.transformation.anylogic.transform.xtend.helper;

import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Singleton;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@Singleton
@SuppressWarnings("all")
public class Variables {
  private Set<Connector> connectorSet = new Function0<Set<Connector>>() {
    public Set<Connector> apply() {
      HashSet<Connector> _newHashSet = CollectionLiterals.<Connector>newHashSet();
      return _newHashSet;
    }
  }.apply();
  
  private XMIResource resource;
  
  private int connectorCounter = 0;
  
  private int outStringCounter = 0;
  
  private int selectOutputCounter = 0;
  
  private int sourceCounter = 0;
  
  private int sinkCounter = 0;
  
  public int getSourceCounter() {
    int _plus = (this.sourceCounter + 1);
    int _sourceCounter = this.sourceCounter = _plus;
    return _sourceCounter;
  }
  
  public int getSinkCounter() {
    int _plus = (this.sinkCounter + 1);
    int _sinkCounter = this.sinkCounter = _plus;
    return _sinkCounter;
  }
  
  public int resetOutstringCounter() {
    int _outStringCounter = this.outStringCounter = 0;
    return _outStringCounter;
  }
  
  public int getOutStringCounter() {
    return this.outStringCounter;
  }
  
  public int setOutStringCounter(final int _counter) {
    int _outStringCounter = this.outStringCounter = _counter;
    return _outStringCounter;
  }
  
  public int getConnectorCounter() {
    int _plus = (this.connectorCounter + 1);
    int _connectorCounter = this.connectorCounter = _plus;
    return _connectorCounter;
  }
  
  public int getSelectOutputCounter() {
    int _plus = (this.selectOutputCounter + 1);
    int _selectOutputCounter = this.selectOutputCounter = _plus;
    return _selectOutputCounter;
  }
  
  public XMIResource setResource(final XMIResource r) {
    XMIResource _resource = this.resource = r;
    return _resource;
  }
  
  public XMIResource getResource() {
    return this.resource;
  }
  
  public Set<Connector> getConnectorSet() {
    return this.connectorSet;
  }
  
  public Diagram getDiagram() {
    TreeIterator<EObject> _allContents = this.resource.getAllContents();
    final Function1<EObject,Boolean> _function = new Function1<EObject,Boolean>() {
      public Boolean apply(final EObject it) {
        return Boolean.valueOf((it instanceof Diagram));
      }
    };
    EObject _findFirst = IteratorExtensions.<EObject>findFirst(_allContents, _function);
    return ((Diagram) _findFirst);
  }
}
