package de.lsem.simulation.transformation.ed.elementHandler

import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper
import java.util.Iterator
import javax.inject.Inject

class ConnectionReferenceHandler {

	@Inject
	extension ElementHelper

	def writeConnectionReferences(Iterator<ISource> sources) '''
		«««writeInt014Sources
		«writeInt014Other»
		«writeInt006(sources)»
	'''

	//	int006 means creating an event with attributes and values:
	//	1=time 2=eventatom 3=EventCode 4=priority, 5=EventInvolved
	private def writeInt006(Iterator<ISource> sources) '''
		«FOR source : sources.toIterable»
			int006(10, «getId(source)», 1, 0, 0);
		«ENDFOR»
	'''

	//	Iterate through all connections and write its source and sink
	private def writeInt014Other() '''
		«FOR c : getConnections»
			int014( «outChanSource(c.getSourceID)», «c.getSourceID», «incChanTarget(c.getTargetID)», «c.getTargetID», 0);
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
