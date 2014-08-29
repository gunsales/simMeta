package de.lsem.simulation.features;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.features.custom.CollapseActivityFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class SimulationToolBehaviorProvider extends DefaultToolBehaviorProvider {
  public SimulationToolBehaviorProvider(final IDiagramTypeProvider diagramTypeProvider) {
    super(diagramTypeProvider);
  }
  
  public IContextButtonPadData getContextButtonPad(final IPictogramElementContext context) {
    IContextButtonPadData _xblockexpression = null;
    {
      final IContextButtonPadData cbp = super.getContextButtonPad(context);
      final PictogramElement picto = context.getPictogramElement();
      IFeatureProvider _featureProvider = this.getFeatureProvider();
      final Object bo = _featureProvider.getBusinessObjectForPictogramElement(picto);
      int _defaultGenericButtons = this.defaultGenericButtons();
      this.setGenericContextButtons(cbp, picto, _defaultGenericButtons);
      CustomContext _customContext = new CustomContext(new PictogramElement[] { picto });
      final CustomContext cc = _customContext;
      IFeatureProvider _featureProvider_1 = this.getFeatureProvider();
      final ICustomFeature[] cfs = _featureProvider_1.getCustomFeatures(cc);
      Iterable<CollapseActivityFeature> _filter = Iterables.<CollapseActivityFeature>filter(((Iterable<? extends Object>)Conversions.doWrapArray(cfs)), CollapseActivityFeature.class);
      final Function1<CollapseActivityFeature,Boolean> _function = new Function1<CollapseActivityFeature,Boolean>() {
        public Boolean apply(final CollapseActivityFeature it) {
          boolean _canExecute = it.canExecute(cc);
          return Boolean.valueOf(_canExecute);
        }
      };
      Iterable<CollapseActivityFeature> _filter_1 = IterableExtensions.<CollapseActivityFeature>filter(_filter, _function);
      final Procedure1<CollapseActivityFeature> _function_1 = new Procedure1<CollapseActivityFeature>() {
        public void apply(final CollapseActivityFeature it) {
          String image = IPlatformImageConstants.IMG_EDIT_EXPAND;
          String collapseExpand = CollapseActivityFeature.COLLAPSE;
          boolean _isPictogramElementCollapsed = SimulationToolBehaviorProvider.this.isPictogramElementCollapsed(picto);
          if (_isPictogramElementCollapsed) {
            image = IPlatformImageConstants.IMG_EDIT_COLLAPSE;
            collapseExpand = CollapseActivityFeature.EXPAND;
          }
          String name = "";
          boolean _and = false;
          if (!(bo instanceof IActivity)) {
            _and = false;
          } else {
            boolean _notEquals = (!Objects.equal(bo, null));
            _and = ((bo instanceof IActivity) && _notEquals);
          }
          if (_and) {
            String _elvis = null;
            String _name = ((IActivity) bo).getName();
            if (_name != null) {
              _elvis = _name;
            } else {
              _elvis = ObjectExtensions.<String>operator_elvis(_name, "");
            }
            name = _elvis;
          }
          ContextButtonEntry _createCollapseButton = SimulationToolBehaviorProvider.this.createCollapseButton(it, cc, collapseExpand, name, image);
          cbp.setCollapseContextButton(_createCollapseButton);
        }
      };
      IterableExtensions.<CollapseActivityFeature>forEach(_filter_1, _function_1);
      _xblockexpression = (cbp);
    }
    return _xblockexpression;
  }
  
  private int defaultGenericButtons() {
    int _bitwiseOr = (DefaultToolBehaviorProvider.CONTEXT_BUTTON_UPDATE | DefaultToolBehaviorProvider.CONTEXT_BUTTON_DELETE);
    return _bitwiseOr;
  }
  
  public ContextButtonEntry createCollapseButton(final CollapseActivityFeature it, final CustomContext cc, final String collapseExpand, final String name, final String image) {
    ContextButtonEntry _xblockexpression = null;
    {
      ContextButtonEntry _contextButtonEntry = new ContextButtonEntry(it, cc);
      final ContextButtonEntry collapseButton = _contextButtonEntry;
      String _collapseButtonDescription = this.collapseButtonDescription(collapseExpand, name);
      collapseButton.setDescription(_collapseButtonDescription);
      collapseButton.setText(collapseExpand);
      collapseButton.setIconId(image);
      _xblockexpression = (collapseButton);
    }
    return _xblockexpression;
  }
  
  public String collapseButtonDescription(final String collapseExpand, final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(collapseExpand, "");
    _builder.append(" ");
    _builder.append(name, "");
    return _builder.toString();
  }
  
  public boolean isPictogramElementCollapsed(final PictogramElement picto) {
    IPeService _peService = Graphiti.getPeService();
    String _propertyValue = _peService.getPropertyValue(picto, CollapseActivityFeature.IS_COLLAPSED);
    boolean _parseBoolean = Boolean.parseBoolean(_propertyValue);
    return _parseBoolean;
  }
}
