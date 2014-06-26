package de.lsem.simulation.transformation.anylogic.transform.xtend.generator

import de.lsem.repository.model.simulation.IRelation
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject
import de.lsem.simulation.transformation.anylogic.generator.persistant.Label
import de.lsem.simulation.transformation.anylogic.transform.helper.Costants
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.CreateHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.PositionHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables
import javax.inject.Inject

import static de.lsem.simulation.transformation.anylogic.transform.helper.IDGenerator.*

class ConnectorGenerator implements Costants {

	@Inject extension CreateHelper
	@Inject extension NamingHelper
	@Inject extension PositionHelper
	@Inject extension Variables

	def create retVal:new Connector() transformRelation(IRelation it, EmbeddedObject transformedSource,
		EmbeddedObject transformedTarget, String outString) {

		// X- and Y-Values are set from the upper-left of the pictogram-element
		// Source needs +40 in x-direction and +10 in y-direction
		val xValue = source.x
		val yValue = source.y

		retVal.setConnectionSourceAndTargetName(outString, IN_STRING)

		// points graphical information - source
		setCommonConnectorValues(transformedSource, transformedTarget, xValue, yValue, retVal)

	}
	
	def create retVal:new Connector() transformRelation(IRelation it, EmbeddedObject transformedSource,
		EmbeddedObject transformedTarget) {
		transformRelation(transformedSource, transformedTarget, OUT_STRING)
	}

	def create retVal:new Connector createConnector(EmbeddedObject source, EmbeddedObject target,
		String sourceConnectableName, String targetConnectableName) {
		setCommonConnectorValues(source, target, source.x, source.y, retVal)
		retVal.setSourceConnectableName(sourceConnectableName)
		retVal.setTargetConnectableName(targetConnectableName)
	}

	private def setCommonConnectorValues(EmbeddedObject source, EmbeddedObject target, int xValue, int yValue,
		Connector retVal) {

		retVal.setId(generateID)
		retVal.setName(("connector" + connectorCounter).wrapCDATA)

		retVal.x = xValue
		retVal.y = yValue

		val startPoint = point(0, 0) //getX(source), getY(source))

		val correctedTargetX = Math::abs(target.x - xValue)
		val correctedTargetY = Math::abs(target.y - yValue)

		// points graphical information - target
		val targetPoint = point(correctedTargetX, correctedTargetY)

		// Wrapper for graphical-point-items
		val points = createPoints(startPoint, targetPoint)
		retVal.setPoints(points)

		// set source and target depending on connection starting from source
		retVal.setTargetEmbeddedObject(target.id)
		retVal.setSourceEmbeddedObject(source.id)

		val Label label = createLabel(0, 0)
		retVal.setLabel(label)

		retVal.setPublicFlag(F)
		retVal.setPresentationFlag(T)
		retVal.setShowLabel(F)
	}


	private def setConnectionSourceAndTargetName(Connector it, String sourceConnectable, String targetConnectable) {
		setSourceConnectableName(sourceConnectable)
		setTargetConnectableName(targetConnectable)
	}

}
