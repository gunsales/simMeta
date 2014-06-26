package de.lsem.simulation.transformation.ed.xtext

import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.ServiceType
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import de.lsem.simulation.transformation.ed.xtext.helper.ElementConstants
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import java.util.List
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.xtext.util.StringInputStream

class MainGenerator implements ElementConstants {

	private static final Logger logger = Logger::getLogger("MainGenerator")

	@Inject
	extension ConnectionManager
	@Inject
	extension ElementHelper
	@Inject
	extension SourceManager
	@Inject
	extension HeaderManager
	@Inject
	extension ActivityStorageManager
	@Inject
	extension ActivityTransportManager
	@Inject
	extension SinkManager
	@Inject
	extension Helper
	@Inject
	extension ConnectionReferenceManager

	int sourceCounter

	def getStream(XMIResource resource) {
		setResource(resource)
		sourceCounter = 0
		new StringInputStream(compile.toString)
	}

	//	Main method: iterate over all elements and transform its followers
	private def compile() '''
		«printHeader»
		
		«««		Ignore sub-activities, as recursion is not supported in ED
		«FOR s : getSimElementsWithoutSubActivities»
			«s.processConnections»
			«s.writeElement»
			«s.outgoing.processOutgoingElements»
			«logger.log(Level::INFO, s.toString)»
		«ENDFOR»
		«««		########### Product ################
		«writeConnectionReferences»
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
			addProcessed(hashCode)
			switch (class) {
				case Activity: next(it as Activity)
				case Sink: next(it as Sink)
				case Source: next(it as Source)
				default: ""
			}
		}
	}

	private def processOutgoingElements(List<IRelation> it) '''
		«map[
			target.writeElement
		].join»
	'''

	private def next(ISource it) '''
		«outgoing.processOutgoingElements»
		«processConnections»
		«startEvent»
	'''

	private def next(IActivity it) '''
		«outgoing.processOutgoingElements»
		«processConnections»
		«switch serviceType {
			case ServiceType::STORAGE: storageActivity
			default: transportActivity
		}»
	'''

	private def next(ISink it) '''
		«writeSink»
	'''
}
