package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.repository.model.simulation.ISimulationElement
import javax.inject.Inject

class PositionHelper {
	@Inject extension Variables
	
	private static int POSITION_DIVISOR = 1

	def recalcPosition(int i) {
		i / POSITION_DIVISOR // TODO try dividing by other values
	}

	def getX(ISimulationElement e) {
		val a = e.getGraphicsAlgorithm
		if (a != null) {
			return recalcPosition(a.x)
		}
		0
	}

	def getY(ISimulationElement e) {
		val b = e.getGraphicsAlgorithm
		if (b != null) {
			return recalcPosition(b.y)
		}
		0
	}

	def getGraphicsAlgorithm(ISimulationElement e) {
		for (picto : diagram.pictogramLinks) {
			if (picto.businessObjects.contains(e)) {
				return picto.pictogramElement.graphicsAlgorithm
			}
		}
	}
}
