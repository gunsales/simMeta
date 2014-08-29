package de.lsem.simulation.transformation.ed.generator

import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.ServiceType
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import de.lsem.simulation.transformation.ed.elementHandler.ActivityStorageHandler
import de.lsem.simulation.transformation.ed.elementHandler.ActivityTransportHandler
import de.lsem.simulation.transformation.ed.elementHandler.ConnectionHandler
import de.lsem.simulation.transformation.ed.elementHandler.ConnectionReferenceHandler
import de.lsem.simulation.transformation.ed.elementHandler.HeaderHandler
import de.lsem.simulation.transformation.ed.elementHandler.SinkHandler
import de.lsem.simulation.transformation.ed.elementHandler.SourceHandler
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper
import java.util.List
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramLink
import org.eclipse.xtext.util.StringInputStream

import static de.lsem.simulation.transformation.ed.elementHandler.helper.Helper.*

class MainGenerator {
 
	static val Logger logger = Logger::getLogger(typeof(MainGenerator).simpleName)

	var sSet = false
	
	@Inject
	extension ConnectionHandler
	@Inject
	extension ElementHelper
	@Inject
	extension SourceHandler
	@Inject
	extension HeaderHandler
	@Inject
	extension ActivityStorageHandler
	@Inject
	extension ActivityTransportHandler
	@Inject
	extension SinkHandler
	@Inject
	extension ConnectionReferenceHandler


	int sourceCounter
	XMIResource resource

	def getStream(XMIResource resource) {
		this.resource = resource
		sourceCounter = 0
		new StringInputStream(compile)
	}
	
	//	Main method: iterate over all elements and transform its followers
	private def String compile() '''
		«printHeader»
		«««	Ignore sub-activities, as recursion is not supported in ED	
		«FOR s: getSimElementsWithoutSubActivities(resource)»
			«processConnections(s)»
			«s.writeElement»
			«s.outgoing.processOutgoingElements»
			«logger.log(Level.INFO, s.toString)»
		«ENDFOR»
		«««	########### Product ################
		«writeConnectionReferences(getSources(resource))»
		«experimentSettings»
		«logger.log(Level.INFO, connections.toString)»
	'''
	
	private def experimentSettings()'''
		{Experiment settings}	
		int034;
		int007;
	'''

	private def CharSequence writeElement(ISimulationElement it) {
		if (hashCode.processed) {
			''
		} else {
			hashCode.addProcessed
			switch (it){
				Activity: next(it as Activity)
				Sink: next(it as Sink)
				Source: next(it as Source)
				default: ""
			}
		}
	}

	private def processOutgoingElements(List<IRelation> it) '''
		«map[
			target.writeElement
		].join»
	'''

	private def dispatch next(ISource it) '''
		«outgoing.processOutgoingElements»
		«processConnections»
		«startEvent(getCoordinate(true), getCoordinate(false), embeddSinUp)»
	'''

	private def dispatch next(IActivity it) '''
		«outgoing.processOutgoingElements»
		«processConnections»
		«switch serviceType {
			case ServiceType::STORAGE: storageActivity(x, y, n, int013(it, resource), embeddSinUp)
			default: transportActivity(x, y, n, int013(it, resource), embeddSinUp)
		}»
	'''
	
	private def getN(ISimulationElement it){
		getNumberOfIncomingElements(resource, it)
	}
	
	private def getX(ISimulationElement it){
		getCoordinate(true)
	}
	
	private def getY(ISimulationElement it){
		getCoordinate(false)
	}

	private def dispatch next(ISink it) '''
		«writeSink(int013(it, resource), n, x, y, embeddSinUp)»
	'''
	
	private def dispatch next(Void it) ''''''
	
	private def getCoordinate(ISimulationElement e, boolean returnX) {
		while (resource.allContents.hasNext) {
			val next = resource.allContents.next

			if (next instanceof Diagram) {
				for (PictogramLink p : (next as Diagram).pictogramLinks) {
					if (p.businessObjects.contains(e)) {
						val ga = p.pictogramElement.graphicsAlgorithm
						if (returnX) {
							return recalcPosition(ga.x)
						} else {
							return recalcPosition(ga.y)
						}
					}
				}
			}
		}
		0
	}

	private def embeddSinUp() {
		if (!sSet) {
			sSet = true
			"s"
		} else {
			"Up(s)"
		}
	}
	
	
}
