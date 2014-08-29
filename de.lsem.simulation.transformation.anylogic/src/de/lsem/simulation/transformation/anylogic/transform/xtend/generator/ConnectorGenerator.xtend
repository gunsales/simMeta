package de.lsem.simulation.transformation.anylogic.transform.xtend.generator

import de.lsem.repository.model.simulation.IRelation
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject
import de.lsem.simulation.transformation.anylogic.generator.persistant.Label
import de.lsem.simulation.transformation.anylogic.transform.helper.Costants
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.CreateHelper
import static de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper.*
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.PositionHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables
import javax.inject.Inject

import static de.lsem.simulation.transformation.anylogic.transform.helper.IDGenerator.*
import static java.lang.Math.*

class ConnectorGenerator implements Costants {

	@Inject extension CreateHelper
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

	def create it:new Connector createConnector(EmbeddedObject source, EmbeddedObject target,
		String sourceConnectableName, String targetConnectableName) {
		setCommonConnectorValues(source, target, source.x, source.y, it)
		setSourceConnectableName(sourceConnectableName)
		setTargetConnectableName(targetConnectableName)
	}

	private def setCommonConnectorValues(EmbeddedObject source, EmbeddedObject target, int xValue, int yValue,
		Connector it) {

		id = generateID
		name = createConnectorNameCleared

		x = xValue
		y = yValue

		val startPoint = point

		// points graphical information - target
		val targetPoint = point(correctedTargetX(target, xValue), correctedTargetY(target, yValue))

		// Wrapper for graphical-point-items
		val points = createPoints(startPoint, targetPoint)
		setPoints(points)

		// set source and target depending on connection starting from source
		targetEmbeddedObject = target.id
		sourceEmbeddedObject = source.id

		val Label label = createLabel(0, 0)
		setLabel(label)

		setPublicFlag(F)
		setPresentationFlag(T)
		setShowLabel(F)
	}
	
	private def correctedTargetY(EmbeddedObject target, int yValue) {
		abs(target.y - yValue)
	}
	
	private def correctedTargetX(EmbeddedObject target, int xValue) {
		abs(target.x - xValue)
	}

	private def createConnectorNameCleared() {
		wrapCDATA(createConnectorName)
	}

	private def String createConnectorName() '''connector«connectorCounter»'''

	private def setConnectionSourceAndTargetName(Connector it, String sourceConnectable, String targetConnectable) {
		setSourceConnectableName(sourceConnectable)
		setTargetConnectableName(targetConnectable)
	}

}
