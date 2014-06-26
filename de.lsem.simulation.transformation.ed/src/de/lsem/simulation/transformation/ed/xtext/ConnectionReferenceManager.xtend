package de.lsem.simulation.transformation.ed.xtext

import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import javax.inject.Inject

class ConnectionReferenceManager {
	
	@Inject
	extension ElementHelper
	@Inject
	extension Helper
	
	def writeConnectionReferences()'''
«««		«writeInt014Sources»
		«writeInt014Other»
		«writeInt006»
	'''
	
	//	int006 means creating an event with attributes and values:
	//	1=time 2=eventatom 3=EventCode 4=priority, 5=EventInvolved
	private def writeInt006() '''
	«FOR source : sources.toIterable»
		int006(10, «getId(source)», 1, 0, 0);
	«ENDFOR»
	'''

	//	Iterate through all connections and write its source and sink
	private def writeInt014Other() '''
	«FOR c : connections»
		int014( «outChanSource(c.sourceID)», «c.sourceID», «incChanTarget(c.targetID)», «c.targetID», 0);
	«ENDFOR»
	'''

	//	connect sources to itself
	//	int014 means the connection-id with attributes 1=AtomOut 2=ChOut 3=AtomIn 4=ChIn
	// Maybe a source of error. Should be replaced with connection between product and source
//	private def writeInt014Sources() '''
//	«FOR lsemElement : sources.toIterable»
//		int014(0, «getId(lsemElement)» , 1, «getId(lsemElement)», 0);
//	«ENDFOR»
//	'''
}