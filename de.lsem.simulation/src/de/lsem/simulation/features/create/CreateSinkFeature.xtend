package de.lsem.simulation.features.create

import de.lsem.repository.model.simulation.SimulationFactory
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext
import org.eclipse.graphiti.features.impl.AbstractCreateFeature
import org.eclipse.graphiti.mm.pictograms.Diagram

import static de.lsem.simulation.util.NamingHelper.*

class CreateSinkFeature extends AbstractCreateFeature {

	new(IFeatureProvider fp, String name, String description) {
		super(fp, name, description)
	}

	new(IFeatureProvider fp) {
		super(fp, "Sink", "Create Sink Element")
	}

	override canCreate(ICreateContext it) {
		targetContainer instanceof Diagram
	}

	override create(ICreateContext it) {
		#[fullyCreateSink]
	}

	private def create it:SimulationFactory.eINSTANCE.createSink fullyCreateSink(ICreateContext ct) {
		name = createName(contents, it)
		diagram.eResource.contents.add(it)

		addGraphicalRepresentation(ct, it)
	}
	
	private def getContents(){
		diagram.eResource.contents
	}
}
