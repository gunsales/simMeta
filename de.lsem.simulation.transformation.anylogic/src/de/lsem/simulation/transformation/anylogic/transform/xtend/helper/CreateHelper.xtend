package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.simulation.transformation.anylogic.generator.persistant.Label
import de.lsem.simulation.transformation.anylogic.generator.persistant.Point
import de.lsem.simulation.transformation.anylogic.generator.persistant.Points

class CreateHelper {
	
	def point(){
		point(0,0)
	}

	def point(int x, int y) {
		val point = new Point()
		point.setX(x)
		point.setY(y)
		point
	}

	def createLabel(int x, int y) {
		val label = new Label()
		label.x = x
		label.y = y
		label
	}
	
	def createPoints(Point startPoint, Point targetPoint) {
		val points = new Points()
		points.addPoint(startPoint)
		points.addPoint(targetPoint)
		points
	}

}
