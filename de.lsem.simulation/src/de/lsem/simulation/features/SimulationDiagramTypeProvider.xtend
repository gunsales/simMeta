package de.lsem.simulation.features

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider
import org.eclipse.graphiti.tb.IToolBehaviorProvider
import com.google.inject.Injector
import de.lsem.simulation.module.SimulationModule

import static com.google.inject.Guice.*

class SimulationDiagramTypeProvider extends AbstractDiagramTypeProvider {
	
	IToolBehaviorProvider[] toolBehaviorProviders
	
	extension Injector injector

	new() {
		val simModule = new SimulationModule
		simModule.provider = this
		injector = createInjector(simModule) 
		setFeatureProvider(typeof(FeatureProvider).instance)
	}

	override isAutoUpdateAtRuntime() {
		true
	}

	override getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = #[typeof(SimulationToolBehaviorProvider).instance]
		}
		toolBehaviorProviders
	}

	def getInjector(){
		injector
	}
}
