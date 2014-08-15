package de.lsem.simulation.features;

import com.google.common.base.Objects;
import de.lsem.simulation.features.FeatureProvider;
import de.lsem.simulation.features.SimulationToolBehaviorProvider;
import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

@SuppressWarnings("all")
public class SimulationDiagramTypeProvider extends AbstractDiagramTypeProvider {
  private IToolBehaviorProvider[] toolBehaviorProviders;
  
  public SimulationDiagramTypeProvider() {
    super();
    FeatureProvider _featureProvider = new FeatureProvider(this);
    this.setFeatureProvider(_featureProvider);
  }
  
  public boolean isAutoUpdateAtRuntime() {
    return true;
  }
  
  public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
    IToolBehaviorProvider[] _xblockexpression = null;
    {
      boolean _equals = Objects.equal(this.toolBehaviorProviders, null);
      if (_equals) {
        SimulationToolBehaviorProvider _simulationToolBehaviorProvider = new SimulationToolBehaviorProvider(this);
        this.toolBehaviorProviders = new IToolBehaviorProvider[] { _simulationToolBehaviorProvider };
      }
      _xblockexpression = (this.toolBehaviorProviders);
    }
    return _xblockexpression;
  }
}
