package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.simulation.transformation.anylogic.generator.persistant.Label
import de.lsem.simulation.transformation.anylogic.generator.persistant.Point
import de.lsem.simulation.transformation.anylogic.generator.persistant.Points
import javax.inject.Singleton

@Singleton
class CreateHelper {

	def create point:new Point() point(int x, int y) {
		point.setX(x)
		point.setY(y)
	}

	def create label:new Label() createLabel(int x, int y) {
		label.x = x
		label.y = y
	}
	
	def create points:new Points() createPoints(Point startPoint, Point targetPoint) {
		points.addPoint(startPoint)
		points.addPoint(targetPoint)
	}

}
