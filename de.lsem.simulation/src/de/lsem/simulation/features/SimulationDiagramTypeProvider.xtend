package de.lsem.simulation.features

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider
import org.eclipse.graphiti.tb.IToolBehaviorProvider

class SimulationDiagramTypeProvider extends AbstractDiagramTypeProvider {
	var IToolBehaviorProvider[] toolBehaviorProviders
	
	new(){
		super()
		setFeatureProvider(new FeatureProvider(this))
	}
	
	override isAutoUpdateAtRuntime() {
		true
	}
	
	override getAvailableToolBehaviorProviders() {

		if(toolBehaviorProviders == null) {
			toolBehaviorProviders = #[ new SimulationToolBehaviorProvider(this) ]
		}
		
		toolBehaviorProviders
	}
	
}