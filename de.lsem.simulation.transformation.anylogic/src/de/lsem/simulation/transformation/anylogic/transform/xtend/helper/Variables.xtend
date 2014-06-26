package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector
import java.util.Set
import javax.inject.Singleton
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.graphiti.mm.pictograms.Diagram

@Singleton
class Variables {

	Set<Connector> connectorSet = newHashSet
	XMIResource resource
	
	var connectorCounter = 0
	var outStringCounter = 0
	var selectOutputCounter = 0
	var sourceCounter = 0
	var sinkCounter = 0
	
	def getSourceCounter(){
		sourceCounter = sourceCounter + 1
	}
	
	def getSinkCounter(){
		sinkCounter = sinkCounter + 1 
	}
	
	def resetOutstringCounter(){
		outStringCounter = 0
	}		
	
	def getOutStringCounter(){
		outStringCounter
	}
	
	def setOutStringCounter(int _counter){
		this.outStringCounter = _counter
	}

	def getConnectorCounter() {
		connectorCounter = connectorCounter + 1
	}

	def getSelectOutputCounter() {
		selectOutputCounter = selectOutputCounter + 1
	}

	def setResource(XMIResource r){
		resource = r	
	}
	
	def getResource() {
		resource
	}

	def getConnectorSet() {
		connectorSet
	}

	def getDiagram() {
		resource.allContents.findFirst [
			it instanceof Diagram
		] as Diagram
	}

}
