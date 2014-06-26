package de.lsem.simulation.transformation.mdb.xtend;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.healthmarketscience.jackcess.Database;
import de.lsem.repository.model.simulation.Beta;
import de.lsem.repository.model.simulation.Constant;
import de.lsem.repository.model.simulation.Erlang;
import de.lsem.repository.model.simulation.Gamma;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IBeta;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IErlang;
import de.lsem.repository.model.simulation.IGamma;
import de.lsem.repository.model.simulation.ILogNormal;
import de.lsem.repository.model.simulation.INegExp;
import de.lsem.repository.model.simulation.INormal;
import de.lsem.repository.model.simulation.IPoisson;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ITriangular;
import de.lsem.repository.model.simulation.IUniform;
import de.lsem.repository.model.simulation.IWeibull;
import de.lsem.repository.model.simulation.LogNormal;
import de.lsem.repository.model.simulation.NegExp;
import de.lsem.repository.model.simulation.Normal;
import de.lsem.repository.model.simulation.Poisson;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.Triangular;
import de.lsem.repository.model.simulation.Uniform;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.repository.model.simulation.Weibull;
import de.lsem.simulation.transformation.mdb.commands.Messages;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationConstants;
import de.lsem.simulation.transformation.mdb.xtend.DistributionStringGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
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
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@Singleton
@SuppressWarnings("all")
public class ArenaTransformationHelper implements ArenaTransformationConstants {
  @Inject
  @Extension
  private DistributionStringGenerator _distributionStringGenerator;
  
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
  
  /**
   * Generates IDistributionFunction or IConstant
   */
  public CharSequence createDistributionTypeString(final IDistribution dist) {
    CharSequence _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      if (dist instanceof Weibull) {
        final Weibull _weibull = (Weibull)dist;
        _matched=true;
        CharSequence _createWeibull = this._distributionStringGenerator.createWeibull(((IWeibull) _weibull));
        _switchResult = _createWeibull;
      }
    }
    if (!_matched) {
      if (dist instanceof Beta) {
        final Beta _beta = (Beta)dist;
        _matched=true;
        CharSequence _createBeta = this._distributionStringGenerator.createBeta(((IBeta) _beta));
        _switchResult = _createBeta;
      }
    }
    if (!_matched) {
      if (dist instanceof Normal) {
        final Normal _normal = (Normal)dist;
        _matched=true;
        CharSequence _createNormal = this._distributionStringGenerator.createNormal(((INormal) _normal));
        _switchResult = _createNormal;
      }
    }
    if (!_matched) {
      if (dist instanceof Triangular) {
        final Triangular _triangular = (Triangular)dist;
        _matched=true;
        CharSequence _createTriangle = this._distributionStringGenerator.createTriangle(((ITriangular) _triangular));
        _switchResult = _createTriangle;
      }
    }
    if (!_matched) {
      if (dist instanceof LogNormal) {
        final LogNormal _logNormal = (LogNormal)dist;
        _matched=true;
        CharSequence _createLogN = this._distributionStringGenerator.createLogN(((ILogNormal) _logNormal));
        _switchResult = _createLogN;
      }
    }
    if (!_matched) {
      if (dist instanceof Uniform) {
        final Uniform _uniform = (Uniform)dist;
        _matched=true;
        CharSequence _createUniform = this._distributionStringGenerator.createUniform(((IUniform) _uniform));
        _switchResult = _createUniform;
      }
    }
    if (!_matched) {
      if (dist instanceof Poisson) {
        final Poisson _poisson = (Poisson)dist;
        _matched=true;
        CharSequence _createPoisson = this._distributionStringGenerator.createPoisson(((IPoisson) _poisson));
        _switchResult = _createPoisson;
      }
    }
    if (!_matched) {
      if (dist instanceof NegExp) {
        final NegExp _negExp = (NegExp)dist;
        _matched=true;
        CharSequence _createNegativeExpo = this._distributionStringGenerator.createNegativeExpo(((INegExp) _negExp));
        _switchResult = _createNegativeExpo;
      }
    }
    if (!_matched) {
      if (dist instanceof Erlang) {
        final Erlang _erlang = (Erlang)dist;
        _matched=true;
        CharSequence _createErlang = this._distributionStringGenerator.createErlang(((IErlang) _erlang));
        _switchResult = _createErlang;
      }
    }
    if (!_matched) {
      if (dist instanceof Gamma) {
        final Gamma _gamma = (Gamma)dist;
        _matched=true;
        CharSequence _createGamma = this._distributionStringGenerator.createGamma(((IGamma) _gamma));
        _switchResult = _createGamma;
      }
    }
    if (!_matched) {
      if (dist instanceof Constant) {
        final Constant _constant = (Constant)dist;
        _matched=true;
        CharSequence _createConstant = this._distributionStringGenerator.createConstant(((IConstant) _constant));
        _switchResult = _createConstant;
      }
    }
    if (!_matched) {
      _switchResult = "";
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
  
  public Iterable<ISimulationElement> filterSimulationElementsFromXMIResource() {
    TreeIterator<EObject> _allContents = this.resource.getAllContents();
    Iterator<ISimulationElement> _filter = Iterators.<ISimulationElement>filter(_allContents, ISimulationElement.class);
    Iterable<ISimulationElement> _iterable = IteratorExtensions.<ISimulationElement>toIterable(_filter);
    return _iterable;
  }
  
  public List<ISimulationElement> filterSubActivities(final List<ISimulationElement> list) {
    List<ISimulationElement> _xblockexpression = null;
    {
      ArrayList<ISimulationElement> _arrayList = new ArrayList<ISimulationElement>(list);
      final List<ISimulationElement> retList = _arrayList;
      Iterable<IActivity> _filter = Iterables.<IActivity>filter(list, IActivity.class);
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
      IterableExtensions.<IActivity>forEach(_filter, _function);
      _xblockexpression = (retList);
    }
    return _xblockexpression;
  }
}
