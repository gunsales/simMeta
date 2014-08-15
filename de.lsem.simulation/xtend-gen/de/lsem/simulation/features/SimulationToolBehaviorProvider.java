package de.lsem.simulation.features;

import com.google.common.base.Objects;
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
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

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
      CustomContext _customContext = new CustomContext(new PictogramElement[] { picto });
      final CustomContext cc = _customContext;
      IFeatureProvider _featureProvider_1 = this.getFeatureProvider();
      final ICustomFeature[] cfs = _featureProvider_1.getCustomFeatures(cc);
      this.setGenericContextButtons(cbp, picto, DefaultToolBehaviorProvider.CONTEXT_BUTTON_DELETE);
      for (final ICustomFeature cf : cfs) {
        boolean _and = false;
        if (!(cf instanceof CollapseActivityFeature)) {
          _and = false;
        } else {
          boolean _canExecute = cf.canExecute(cc);
          _and = ((cf instanceof CollapseActivityFeature) && _canExecute);
        }
        if (_and) {
          String image = IPlatformImageConstants.IMG_EDIT_EXPAND;
          String collapseExpand = "Collapse";
          IPeService _peService = Graphiti.getPeService();
          String _propertyValue = _peService.getPropertyValue(picto, "isCollapsed");
          boolean _parseBoolean = Boolean.parseBoolean(_propertyValue);
          if (_parseBoolean) {
            image = IPlatformImageConstants.IMG_EDIT_COLLAPSE;
            collapseExpand = "Expand";
          }
          String name = "";
          boolean _and_1 = false;
          if (!(bo instanceof IActivity)) {
            _and_1 = false;
          } else {
            boolean _notEquals = (!Objects.equal(bo, null));
            _and_1 = ((bo instanceof IActivity) && _notEquals);
          }
          if (_and_1) {
            String _elvis = null;
            String _name = ((IActivity) bo).getName();
            if (_name != null) {
              _elvis = _name;
            } else {
              _elvis = ObjectExtensions.<String>operator_elvis(_name, "");
            }
            name = _elvis;
          }
          ContextButtonEntry _contextButtonEntry = new ContextButtonEntry(cf, cc);
          final ContextButtonEntry collapseButton = _contextButtonEntry;
          String _plus = (collapseExpand + " ");
          String _plus_1 = (_plus + name);
          collapseButton.setDescription(_plus_1);
          collapseButton.setText(collapseExpand);
          collapseButton.setIconId(image);
          cbp.setCollapseContextButton(collapseButton);
          return cbp;
        }
      }
      _xblockexpression = (cbp);
    }
    return _xblockexpression;
  }
}
