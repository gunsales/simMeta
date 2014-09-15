package de.lsem.simulation.module

import com.google.inject.AbstractModule
import com.google.inject.Provides
import de.lsem.repository.model.simulation.ISimulationFactory
import de.lsem.repository.model.simulation.SimulationFactory
import org.eclipse.graphiti.dt.IDiagramTypeProvider
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.services.IGaCreateService
import org.eclipse.graphiti.services.IGaService
import org.eclipse.graphiti.services.ILinkService
import org.eclipse.graphiti.services.IPeCreateService
import org.eclipse.graphiti.services.IPeService

import static org.eclipse.graphiti.services.Graphiti.*

class SimulationModule extends AbstractModule {

	@Property IDiagramTypeProvider provider

	override protected configure() {

		bind(typeof(IDiagramTypeProvider)).toInstance(provider)

		bind(typeof(ILinkService)).toInstance(linkService)
		bind(typeof(IPeService)).toInstance(peService)
		bind(typeof(IPeCreateService)).toInstance(peCreateService)
		bind(typeof(IGaService)).toInstance(gaService)
		bind(typeof(IGaCreateService)).toInstance(gaCreateService)

		bind(typeof(ISimulationFactory)).toInstance(SimulationFactory.eINSTANCE)
		bind(typeof(SimulationModule)).toInstance(this)

	}

	@Provides
	def IFeatureProvider provideFeatureProvider() {
		provider.featureProvider
	}

}
