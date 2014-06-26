package de.lsem.simulation.features;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;


public class SimulationDiagramTypeProvider extends AbstractDiagramTypeProvider {
	private IToolBehaviorProvider[] toolBehaviorProviders;

	public SimulationDiagramTypeProvider() {
		super();		
		setFeatureProvider(new FeatureProvider(this));
	}		

	@Override
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}	

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {

		if(toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] {
					new SimulationToolBehaviorProvider(this)};
		};

		return toolBehaviorProviders;
	}
}
