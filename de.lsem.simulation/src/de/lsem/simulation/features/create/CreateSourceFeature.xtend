package de.lsem.simulation.features.create

import de.lsem.repository.model.simulation.IGood
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.ITime
import de.lsem.repository.model.simulation.SimulationFactory
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICreateContext
import org.eclipse.graphiti.features.impl.AbstractCreateFeature
import org.eclipse.graphiti.mm.pictograms.Diagram

import static de.lsem.simulation.util.SourceHelper.*

class CreateSourceFeature extends AbstractCreateFeature {

	new(IFeatureProvider fp, String name, String description) {
		super(fp, name, description)
	}

	new(IFeatureProvider fp) {
		super(fp, "Source", "Create Source Element")
	}

	override canCreate(ICreateContext it) {
		targetContainer instanceof Diagram
	}

	override create(ICreateContext it) {
		#[
			createFullSource
		]
	}

	private def createFullSource(ICreateContext context) {
		val it = instance.createSource
		name = "Source_" + createInitialSourceNumber

		val first = instance.createTime
		val following = instance.createTime
		val good = instance.createGood

		firstEntity = first
		newEntities = following
		processedObject = good

		addElementsToDiagram(it, first, following, good)

		addGraphicalRepresentation(context, it)
		it
	}
	
	private def addElementsToDiagram(ISource it, ITime first, ITime following, IGood good) {
		contents.add(it)
		contents.add(first)
		contents.add(following)
		contents.add(good)
	}

	private def getContents() {
		diagram.eResource.contents
	}

	private def getInstance() {
		SimulationFactory.eINSTANCE
	}

	def createInitialSourceNumber() {
		getISourcesFromDiagram(diagram.eResource.contents).size + 1
	}

}
