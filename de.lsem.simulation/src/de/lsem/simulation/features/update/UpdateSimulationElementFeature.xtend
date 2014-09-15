package de.lsem.simulation.features.update

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IUpdateContext
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature
import org.eclipse.graphiti.features.impl.Reason
import org.eclipse.graphiti.mm.algorithms.Text
import org.eclipse.graphiti.mm.pictograms.Connection
import org.eclipse.graphiti.mm.pictograms.ContainerShape

import static de.lsem.simulation.util.LSEMElementHelper.*

class UpdateSimulationElementFeature extends AbstractUpdateFeature {

	new(IFeatureProvider fp) {
		super(fp)
	}

	override canUpdate(IUpdateContext it) {
		val obj = pictogramElement.businessObjectForPictogramElement

		if (obj instanceof ISimulationElement) {
			return preCheck(obj as ISimulationElement)
		}
		false
	}

	private def preCheck(ISimulationElement it) {
		for (e : getSimulationElements(contents)) {
			if (!elementsEqual(e) && elementNamesEqual(e)) {
				return false
			}

			for (r : e.outgoing) {
				if (r.name != null && r.name.equals(name)) {
					return false
				}
			}
		}
		return true
	}

	private def elementNamesEqual(ISimulationElement element, ISimulationElement element2) {
		element.name.equals(element2.name)
	}

	private def elementsEqual(ISimulationElement e1, ISimulationElement e2) {
		e1.equals(e2)
	}

	private def getContents() {
		diagram.eResource.contents
	}

	override update(IUpdateContext it) {

		val bo = pictogramElement.businessObjectForPictogramElement

		if (bo instanceof IActivity) {
			val activity = bo as IActivity
			activity.handleActivityGraphics
		}

		val businessString = (bo as ISimulationElement).name ?: ""

		(pictogramElement as ContainerShape).children.forEach [
			switch graphicsAlgorithm {
				Text: {
					val t = graphicsAlgorithm as Text
					t.value = businessString
				}
				Connection: {
					val c = graphicsAlgorithm as Connection
					val t = c.connectionDecorators.findFirst[graphicsAlgorithm instanceof Text].graphicsAlgorithm as Text
					t.value = businessString
				}
			}
		]

		return true
	}

	private def handleActivityGraphics(IActivity it) {
		resetPositionOfSubActivities

		val picto = featureProvider.getPictogramElementForBusinessObject(it)

		if (subActivities.size > 3) {
			picto.graphicsAlgorithm.height = activityHeight
		}
	}

	private def resetPositionOfSubActivities(IActivity it) {
		subActivities.forEach [ a |
			val pe = featureProvider.getPictogramElementForBusinessObject(a)
			if (pe != null) {
				pe.graphicsAlgorithm.y = subActivities.indexOf(a).calculatePosition
			}
		]
	}

	private def getActivityHeight(IActivity it) {
		subActivities.size.calculatePosition
	}
	
	private def calculatePosition(int index){
		30 + 50 * index
	}

	override updateNeeded(IUpdateContext it) {

		var String pictogramString = null

		if (pictogramElement instanceof ContainerShape) {
			val cs = pictogramElement as ContainerShape

			val text = cs.children.findFirst[s|s.graphicsAlgorithm instanceof Text].graphicsAlgorithm as Text
			pictogramString = text.value
		}

		var String boString = null
		val obj = pictogramElement.businessObjectForPictogramElement

		if (obj instanceof ISimulationElement) {
			boString = (obj as ISimulationElement).name
		}

		if ((boString != null && pictogramString == null) ||
			(pictogramString != null && !pictogramString.equals(boString))) {
			return Reason.createTrueReason("Name out of date")
		}

		return Reason.createFalseReason

	}

}
