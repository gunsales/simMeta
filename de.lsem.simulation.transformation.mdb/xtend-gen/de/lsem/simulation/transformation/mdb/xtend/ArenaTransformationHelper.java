package de.lsem.simulation.transformation.mdb.xtend;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import com.healthmarketscience.jackcess.Database;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.simulation.transformation.mdb.commands.Messages;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@Singleton
@SuppressWarnings("all")
public class ArenaTransformationHelper implements ArenaTransformationConstants {
  private int counter = 0;
  
  private Diagram diagram;
  
  private Database saveFile;
  
  private XMIResource resource;
  
  private String savePath;
  
  private Map<ISimulationElement,Integer> lookupTable;
  
  public int generateID() {
    int _plus = (this.counter + 1);
    return this.counter = _plus;
  }
  
  public String toARENAServiceType(final ServiceType serviceType) {
    String _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(serviceType,ServiceType.TRANSPORT)) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_TRANSPORT;
      }
    }
    if (!_matched) {
      if (Objects.equal(serviceType,ServiceType.HANDLING)) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_HANDLING;
      }
    }
    if (!_matched) {
      if (Objects.equal(serviceType,ServiceType.PICKING)) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_PICKING;
      }
    }
    if (!_matched) {
      if (Objects.equal(serviceType,ServiceType.STORAGE)) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_STORAGE;
      }
    }
    if (!_matched) {
      if (Objects.equal(serviceType,ServiceType.VALUE_ADDED)) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_VALUE_ADDED;
      }
    }
    if (!_matched) {
      _switchResult = ArenaTransformationConstants.SERVICE_TYPE_ARENA_HANDLING;
    }
    return _switchResult;
  }
  
  public int stretch(final int coordinate) {
    return (coordinate * ArenaTransformationConstants.GRAPHICAL_STRETCH_FACTOR);
  }
  
  public int getX(final ISimulationElement it) {
    int _coordinate = this.getCoordinate(it, true);
    return _coordinate;
  }
  
  public int getY(final ISimulationElement it) {
    int _coordinate = this.getCoordinate(it, false);
    return _coordinate;
  }
  
  public int getCoordinate(final ISimulationElement e, final boolean returnX) {
    int _xblockexpression = (int) 0;
    {
      Diagram _diagram = this.getDiagram();
      EList<PictogramLink> _pictogramLinks = _diagram.getPictogramLinks();
      for (final PictogramLink p : _pictogramLinks) {
        EList<EObject> _businessObjects = p.getBusinessObjects();
        boolean _contains = _businessObjects.contains(e);
        if (_contains) {
          PictogramElement _pictogramElement = p.getPictogramElement();
          final GraphicsAlgorithm ga = _pictogramElement.getGraphicsAlgorithm();
          if (returnX) {
            return ga.getX();
          } else {
            return ga.getY();
          }
        }
      }
      _xblockexpression = (0);
    }
    return _xblockexpression;
  }
  
  public Map<ISimulationElement,Integer> getLookupTable() {
    boolean _equals = Objects.equal(this.lookupTable, null);
    if (_equals) {
      HashMap<ISimulationElement,Integer> _hashMap = new HashMap<ISimulationElement, Integer>();
      this.lookupTable = _hashMap;
    }
    return this.lookupTable;
  }
  
  public Diagram getDiagram() {
    boolean _equals = Objects.equal(this.diagram, null);
    if (_equals) {
      Diagram _diagramFromResource = this.getDiagramFromResource();
      this.diagram = _diagramFromResource;
    }
    return this.diagram;
  }
  
  private Diagram getDiagramFromResource() {
    TreeIterator<EObject> _allContents = this.resource.getAllContents();
    Iterator<Diagram> _filter = Iterators.<Diagram>filter(_allContents, Diagram.class);
    Diagram _next = _filter.next();
    return _next;
  }
  
  public Database getSaveFile() {
    return this.saveFile;
  }
  
  public Database setSaveFile(final Database _saveFile) {
    Database _saveFile_1 = this.saveFile = _saveFile;
    return _saveFile_1;
  }
  
  public XMIResource getResource() {
    return this.resource;
  }
  
  public XMIResource setResource(final XMIResource _resource) {
    XMIResource _resource_1 = this.resource = _resource;
    return _resource_1;
  }
  
  public String getSaveFileString() {
    boolean _equals = Objects.equal(this.savePath, null);
    if (_equals) {
      String _savePath = this.getSavePath();
      this.savePath = _savePath;
    }
    return this.savePath;
  }
  
  private String getSavePath() {
    final IFile sourceFile = this.getSourceFileFrom(this.resource);
    final IProject project = sourceFile.getProject();
    final IFolder targetFolder = project.getFolder(Messages.TransformToArena_13);
    String tempFileName = sourceFile.getName();
    String _replace = tempFileName.replace(ArenaTransformationConstants.DIAGRAM_EXTENSION_CONSTANT, ArenaTransformationConstants.MDB_EXTENSION_CONSTANT);
    tempFileName = _replace;
    IFile _file = targetFolder.getFile(tempFileName);
    IPath _rawLocation = _file.getRawLocation();
    return _rawLocation.toOSString();
  }
  
  private IFile getSourceFileFrom(final XMIResource xmiResource) {
    IFile _xblockexpression = null;
    {
      final URI uri = xmiResource.getURI();
      final URI resolvedFile = CommonPlugin.resolve(uri);
      final String projectNameString = this.getProjectNameFor(uri);
      IWorkspace _workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot _root = _workspace.getRoot();
      final IProject project = _root.getProject(projectNameString);
      String _fileString = resolvedFile.toFileString();
      Path _path = new Path(_fileString);
      final Path path = _path;
      IFile _file = project.getFile(path);
      _xblockexpression = (_file);
    }
    return _xblockexpression;
  }
  
  private String getProjectNameFor(final URI uri) {
    String _xblockexpression = null;
    {
      final String uriString = uri.toString();
      int _length = uriString.length();
      String trimmedFirst = uriString.substring(19, _length);
      String _replaceAll = trimmedFirst.replaceAll("%20", " ");
      trimmedFirst = _replaceAll;
      int _indexOf = trimmedFirst.indexOf("/");
      String _substring = trimmedFirst.substring(0, _indexOf);
      _xblockexpression = (_substring);
    }
    return _xblockexpression;
  }
  
  /**
   * Maps simulation-time to arena-time
   */
  public String mapTimeUnit(final UnitOfTime timeUnit) {
    String _switchResult = null;
    int _value = timeUnit.getValue();
    final int _switchValue = _value;
    boolean _matched = false;
    if (!_matched) {
      int _value_1 = timeUnit.getValue();
      boolean _equals = (_value_1 == UnitOfTime.DAY_VALUE);
      if (_equals) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.ARENA_TIME_DAY;
      }
    }
    if (!_matched) {
      int _value_2 = timeUnit.getValue();
      boolean _equals_1 = (_value_2 == UnitOfTime.HOUR_VALUE);
      if (_equals_1) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.ARENA_TIME_HOUR;
      }
    }
    if (!_matched) {
      int _value_3 = timeUnit.getValue();
      boolean _equals_2 = (_value_3 == UnitOfTime.MINUTE_VALUE);
      if (_equals_2) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.ARENA_TIME_MINUTE;
      }
    }
    if (!_matched) {
      int _value_4 = timeUnit.getValue();
      boolean _equals_3 = (_value_4 == UnitOfTime.SECOND_VALUE);
      if (_equals_3) {
        _matched=true;
        _switchResult = ArenaTransformationConstants.ARENA_TIME_SECOND;
      }
    }
    if (!_matched) {
      _switchResult = ArenaTransformationConstants.ARENA_TIME_HOUR;
    }
    return _switchResult;
  }
  
  public String setConnectionNWayType(final List<IConditionalRelation> allConnectionsFromOneSource) {
    String _xifexpression = null;
    IConditionalRelation _get = allConnectionsFromOneSource.get(0);
    String _condition = _get.getCondition();
    boolean _isEmpty = _condition.isEmpty();
    if (_isEmpty) {
      String _xifexpression_1 = null;
      int _size = allConnectionsFromOneSource.size();
      boolean _greaterThan = (_size > 2);
      if (_greaterThan) {
        _xifexpression_1 = ArenaTransformationConstants.VALUE_NWITH;
      } else {
        _xifexpression_1 = ArenaTransformationConstants.VALUE_WITH;
      }
      _xifexpression = _xifexpression_1;
    } else {
      String _xifexpression_2 = null;
      int _size_1 = allConnectionsFromOneSource.size();
      boolean _greaterThan_1 = (_size_1 > 2);
      if (_greaterThan_1) {
        _xifexpression_2 = ArenaTransformationConstants.VALUE_NIF;
      } else {
        _xifexpression_2 = ArenaTransformationConstants.VALUE_IF;
      }
      _xifexpression = _xifexpression_2;
    }
    return _xifexpression;
  }
  
  public Iterator<ISimulationElement> filterSimulationElementsFromXMIResource() {
    TreeIterator<EObject> _allContents = this.resource.getAllContents();
    Iterator<ISimulationElement> _filter = Iterators.<ISimulationElement>filter(_allContents, ISimulationElement.class);
    return _filter;
  }
  
  public List<ISimulationElement> filterSubActivities(final Iterator<ISimulationElement> iterator) {
    List<ISimulationElement> _xblockexpression = null;
    {
      List<ISimulationElement> _list = IteratorExtensions.<ISimulationElement>toList(iterator);
      ArrayList<ISimulationElement> _arrayList = new ArrayList<ISimulationElement>(_list);
      final List<ISimulationElement> retList = _arrayList;
      Iterator<IActivity> _filter = Iterators.<IActivity>filter(iterator, IActivity.class);
      final Procedure1<IActivity> _function = new Procedure1<IActivity>() {
        public void apply(final IActivity e1) {
          EList<IActivity> _subActivities = e1.getSubActivities();
          final Procedure1<IActivity> _function = new Procedure1<IActivity>() {
            public void apply(final IActivity e2) {
              retList.remove(e2);
            }
          };
          IterableExtensions.<IActivity>forEach(_subActivities, _function);
        }
      };
      IteratorExtensions.<IActivity>forEach(_filter, _function);
      _xblockexpression = (retList);
    }
    return _xblockexpression;
  }
}
