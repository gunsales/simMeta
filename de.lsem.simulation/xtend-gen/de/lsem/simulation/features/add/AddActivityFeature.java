package de.lsem.simulation.features.add;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.simulation.features.custom.CollapseActivityFeature;
import de.lsem.simulation.util.LSEMElementHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class AddActivityFeature extends AbstractAddShapeFeature {
  public final static IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;
  
  public final static IColorConstant E_CLASS_FOREGROUND = new Function0<IColorConstant>() {
    public IColorConstant apply() {
      ColorConstant _colorConstant = new ColorConstant(105, 105, 105);
      return _colorConstant;
    }
  }.apply();
  
  public final static IColorConstant E_CLASS_BACKGROUND = new Function0<IColorConstant>() {
    public IColorConstant apply() {
      ColorConstant _colorConstant = new ColorConstant(229, 229, 229);
      return _colorConstant;
    }
  }.apply();
  
  private final int LINE_WIDTH = 1;
  
  private final static int INITIAL_DISTANCE_TO_TOP = 25;
  
  private final static Logger logger = new Function0<Logger>() {
    public Logger apply() {
      String _simpleName = AddActivityFeature.class.getSimpleName();
      Logger _logger = Logger.getLogger(_simpleName);
      return _logger;
    }
  }.apply();
  
  public AddActivityFeature(final IFeatureProvider fp) {
    super(fp);
  }
  
  public PictogramElement add(final IAddContext context) {
    ContainerShape _xblockexpression = null;
    {
      final ContainerShape picto = this.createActivityGraphicalRepresentation(context);
      IPeCreateService _peCreateService = Graphiti.getPeCreateService();
      _peCreateService.createChopboxAnchor(((ContainerShape) picto));
      this.layoutPictogramElement(picto);
      _xblockexpression = (picto);
    }
    return _xblockexpression;
  }
  
  public boolean canAdd(final IAddContext it) {
    boolean _xblockexpression = false;
    {
      ContainerShape _targetContainer = it.getTargetContainer();
      if ((_targetContainer instanceof ContainerShape)) {
        ContainerShape _targetContainer_1 = it.getTargetContainer();
        final Object oldActivity = this.getBusinessObjectForPictogramElement(_targetContainer_1);
        boolean _and = false;
        if (!(oldActivity instanceof IActivity)) {
          _and = false;
        } else {
          Diagram _diagram = this.getDiagram();
          Resource _eResource = _diagram.eResource();
          EList<EObject> _contents = _eResource.getContents();
          boolean _isSubActivity = LSEMElementHelper.isSubActivity(_contents, ((IActivity) oldActivity));
          _and = ((oldActivity instanceof IActivity) && _isSubActivity);
        }
        if (_and) {
          return false;
        }
      }
      Object _newObject = it.getNewObject();
      _xblockexpression = ((_newObject instanceof IActivity));
    }
    return _xblockexpression;
  }
  
  private ContainerShape createActivityGraphicalRepresentation(final IAddContext it) {
    ContainerShape _xblockexpression = null;
    {
      final int width = 100;
      final int height = 50;
      final int colapsed_width = 120;
      Object _newObject = it.getNewObject();
      final IActivity newActivity = ((IActivity) _newObject);
      IPeCreateService _peCreateService = Graphiti.getPeCreateService();
      ContainerShape _activityTargetContainer = this.getActivityTargetContainer(it);
      final ContainerShape containerShape = _peCreateService.createContainerShape(_activityTargetContainer, true);
      this.setRoundedRectangle(containerShape, it, width, height);
      this.setPictogramMiddleLine(containerShape, colapsed_width);
      this.setPictogramText(containerShape, newActivity, width);
      String _plus = ("Fully added activity " + newActivity);
      AddActivityFeature.logger.log(Level.INFO, _plus);
      IPeService _peService = Graphiti.getPeService();
      String _valueOf = String.valueOf(Boolean.FALSE);
      _peService.setPropertyValue(containerShape, "isCollapsed", _valueOf);
      this.link(containerShape, newActivity);
      _xblockexpression = (containerShape);
    }
    return _xblockexpression;
  }
  
  private void setRoundedRectangle(final ContainerShape containerShape, final IAddContext it, final int width, final int height) {
    IGaService _gaService = Graphiti.getGaService();
    final RoundedRectangle roundedRectangle = _gaService.createRoundedRectangle(containerShape, 5, 5);
    Color _manageColor = this.manageColor(AddActivityFeature.E_CLASS_FOREGROUND);
    roundedRectangle.setForeground(_manageColor);
    Color _manageColor_1 = this.manageColor(AddActivityFeature.E_CLASS_BACKGROUND);
    roundedRectangle.setBackground(_manageColor_1);
    roundedRectangle.setLineWidth(Integer.valueOf(this.LINE_WIDTH));
    ContainerShape _targetContainer = it.getTargetContainer();
    final boolean dropOnActivity = (!(_targetContainer instanceof Diagram));
    if (dropOnActivity) {
      this.positionOnActivity(it, roundedRectangle, width, height);
    }
    IGaService _gaService_1 = Graphiti.getGaService();
    int _x = it.getX();
    int _y = it.getY();
    _gaService_1.setLocationAndSize(roundedRectangle, _x, _y, width, height);
  }
  
  private void setPictogramMiddleLine(final ContainerShape containerShape, final int colapsed_width) {
    IPeCreateService _peCreateService = Graphiti.getPeCreateService();
    final Shape shapeForLine = _peCreateService.createShape(containerShape, false);
    IGaService _gaService = Graphiti.getGaService();
    final Polyline polyline = _gaService.createPolyline(shapeForLine, new int[] { 0, 20, colapsed_width, 20 });
    Color _manageColor = this.manageColor(AddActivityFeature.E_CLASS_FOREGROUND);
    polyline.setForeground(_manageColor);
    polyline.setLineWidth(Integer.valueOf(this.LINE_WIDTH));
  }
  
  private void setPictogramText(final ContainerShape containerShape, final IActivity newActivity, final int width) {
    IPeCreateService _peCreateService = Graphiti.getPeCreateService();
    final Shape nameShape = _peCreateService.createShape(containerShape, false);
    IGaService _gaService = Graphiti.getGaService();
    String _elvis = null;
    String _name = newActivity.getName();
    if (_name != null) {
      _elvis = _name;
    } else {
      ServiceType _serviceType = newActivity.getServiceType();
      String _literal = _serviceType.getLiteral();
      _elvis = ObjectExtensions.<String>operator_elvis(_name, _literal);
    }
    final Text text = _gaService.createText(nameShape, _elvis);
    Color _manageColor = this.manageColor(AddActivityFeature.E_CLASS_TEXT_FOREGROUND);
    text.setForeground(_manageColor);
    text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
    IGaService _gaService_1 = Graphiti.getGaService();
    Diagram _diagram = this.getDiagram();
    Font _manageDefaultFont = _gaService_1.manageDefaultFont(_diagram, false, true);
    text.setFont(_manageDefaultFont);
    IGaService _gaService_2 = Graphiti.getGaService();
    _gaService_2.setLocationAndSize(text, 0, 0, width, 20);
  }
  
  private void positionOnActivity(final IAddContext it, final RoundedRectangle roundedRectangle, final int width, final int height) {
    ContainerShape _targetContainer = it.getTargetContainer();
    Object _businessObjectForPictogramElement = this.getBusinessObjectForPictogramElement(_targetContainer);
    final IActivity topActivity = ((IActivity) _businessObjectForPictogramElement);
    int y = 25;
    boolean _notEquals = (!Objects.equal(topActivity, null));
    if (_notEquals) {
      Object _newObject = it.getNewObject();
      int _calculateYCoordinate = this.calculateYCoordinate(((IActivity) _newObject), topActivity);
      y = _calculateYCoordinate;
    }
    IGaService _gaService = Graphiti.getGaService();
    _gaService.setLocationAndSize(roundedRectangle, 10, y, width, height);
  }
  
  private int calculateYCoordinate(final IActivity newActivity, final IActivity topActivity) {
    EList<IActivity> _subActivities = topActivity.getSubActivities();
    boolean _contains = _subActivities.contains(newActivity);
    if (_contains) {
      EList<IActivity> _subActivities_1 = topActivity.getSubActivities();
      int _indexOf = _subActivities_1.indexOf(newActivity);
      int _multiply = (_indexOf * 50);
      return (_multiply + AddActivityFeature.INITIAL_DISTANCE_TO_TOP);
    } else {
      EList<IActivity> _subActivities_2 = topActivity.getSubActivities();
      int _size = _subActivities_2.size();
      int _multiply_1 = (_size * 50);
      return (_multiply_1 + AddActivityFeature.INITIAL_DISTANCE_TO_TOP);
    }
  }
  
  private ContainerShape getActivityTargetContainer(final IAddContext it) {
    ContainerShape _xblockexpression = null;
    {
      ContainerShape _targetContainer = it.getTargetContainer();
      if ((_targetContainer instanceof Diagram)) {
        ContainerShape _targetContainer_1 = it.getTargetContainer();
        return ((Diagram) _targetContainer_1);
      }
      ContainerShape _targetContainer_2 = it.getTargetContainer();
      this.collapseTopActivity(_targetContainer_2);
      ContainerShape _targetContainer_3 = it.getTargetContainer();
      _xblockexpression = (_targetContainer_3);
    }
    return _xblockexpression;
  }
  
  private void collapseTopActivity(final ContainerShape shape) {
    CustomContext _customContext = new CustomContext();
    final CustomContext cc = _customContext;
    cc.setInnerPictogramElement(shape);
    IFeatureProvider _featureProvider = this.getFeatureProvider();
    final ICustomFeature[] customFeatures = _featureProvider.getCustomFeatures(cc);
    Iterable<CollapseActivityFeature> _filter = Iterables.<CollapseActivityFeature>filter(((Iterable<? extends Object>)Conversions.doWrapArray(customFeatures)), CollapseActivityFeature.class);
    final CollapseActivityFeature collapseFeature = IterableExtensions.<CollapseActivityFeature>head(_filter);
    IPeService _peService = Graphiti.getPeService();
    final String collapsed = _peService.getPropertyValue(shape, "isCollapsed");
    boolean _notEquals = (!Objects.equal(collapsed, null));
    if (_notEquals) {
      final boolean isCollapsed = Boolean.parseBoolean(collapsed);
      boolean _not = (!isCollapsed);
      if (_not) {
        collapseFeature.createCollapsedPictogramElement(shape);
      }
    }
  }
}
