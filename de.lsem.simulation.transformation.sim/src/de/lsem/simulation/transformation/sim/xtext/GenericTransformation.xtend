package de.lsem.simulation.transformation.sim.xtext

import de.lsem.process.model.ProcessEdge
import de.lsem.process.model.ProcessNode
import de.lsem.repository.model.metamodel.ILSEMElement
import de.lsem.repository.model.metamodel.LSEMElement
import de.lsem.repository.model.metamodel.MetamodelFactory
import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.Capacity
import de.lsem.repository.model.simulation.Constant
import de.lsem.repository.model.simulation.Good
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import de.lsem.repository.model.simulation.Time
import de.lsem.repository.model.simulation.UnitOfTime
import java.util.Collection
import java.util.HashSet
import java.util.List
import java.util.Set

class GenericTransformation {

	Collection<ProcessNode> nodes
	Set<ProcessEdge> edges

	new(Collection<ProcessNode> nodes, Set<ProcessEdge> edges) {
		this.nodes = nodes
		this.edges = edges
		println(nodes)
		println(edges)
	}

	def List<ILSEMElement> transform() {

		val starts = nodes.startElements.map [
			transform(it)
		].toList
		val retVal = reachable(newHashSet(), starts.toSet).toList
		retVal.addStartAndEndElement

	}

	def List<ILSEMElement> addStartAndEndElement(List<ILSEMElement> transformedList) {
		startElements(nodes).forEach [
			transformedList.add(createSource)
		]

		endElements(nodes).forEach [
			transformedList.add(createSink)
		]

		//		println(">> after transformation:" + transformedList)
		transformedList
	}

	def Set<ProcessNode> startElements(Collection<ProcessNode> nodes) {
		val retVal = newHashSet()
		nodes.forEach [
			//Wenn keine eingehenden Kanten --> StartElement
			println(">> incomming: " + incomming)
			if (incomming.empty)
				retVal.add(it)
		]
		retVal
	}

	def Set<ProcessEdge> getIncomming(ProcessNode p) {
		val retVal = newHashSet()
		edges.forEach [
			if (target.equals(p))
				retVal.add(it)
		]
		retVal
	}

	def Set<ProcessNode> endElements(Collection<ProcessNode> nodes) {
		val retVal = newHashSet()
		nodes.forEach [
			//Wenn keine ausgehenden Kanten --> EndElement
			println("outgoing" + outgoing)
			if (outgoing.empty)
				retVal.add(it)
		]
		retVal
	}

	def Set<ProcessEdge> getOutgoing(ProcessNode p) {
		val retVal = newHashSet()
		edges.forEach [
			if (source.equals(p))
				retVal.add(it)
		]
		retVal
	}

	def Set<ILSEMElement> reachable(Set<ILSEMElement> input, Set<ILSEMElement> output) {

		if (input.size == output.size) {
			output
		} else {
			val temp = new HashSet(output)
			output.map[relations].flatten.map[target].forEach [
				//				if (it instanceof Activity) {
				addAllActivityElementsToList(it, temp)
			//				} else if (it instanceof Source) {
			//					val so = it as Source
			//					temp.add(so.firstEntity)
			//					temp.add(so.firstEntity.period)
			//					temp.add(so.newEntities)
			//					temp.add(so.newEntities.period)
			//					temp.add(so.processedObject)
			//				}
			]
			reachable(output, temp)
		}
	}

	def addAllActivityElementsToList(ILSEMElement it, HashSet<ILSEMElement> temp) {
		val ac = it as Activity
		temp.add(it)
		temp.add(ac.capacity)
		temp.add(ac.serviceType)
		temp.add(ac.timePeriod)
//		temp.add(ac.timePeriod.period)
		temp
	}

	def void transformFlow(ProcessEdge it) {

		var targ = target.transform
		var sour = source.transform

		var relation = MetamodelFactory::eINSTANCE.createRelation

		relation.source = sour
		relation.target = targ

		sour.relations.add(relation)

	}

	def ILSEMElement transform(ProcessNode it) {

		//		switch type {
		//			case ProcessNode::ACTIVITY: {
		createActivity

		//			}
		//			case ProcessNode::START_EVENT: {
		//				createSource
		//			}
		//			case ProcessNode::END_EVENT: {
		//				createSink
		//			}
		//		}
		}

		def create el:new Activity() createActivity(ProcessNode o) {
			transferId(o, el)
			edges.forEach [
				if (source.equals(o)) {
					transformFlow
				}
			]
		}

		def create start:new Source() createSource(ProcessNode o) {
			transferId(o, start)
			edges.forEach [
				if (source.equals(o)) {
					transformFlow
				}
			]
		}

		def create el:new Sink() createSink(ProcessNode o) {
			transferId(o, el)
		}

		// |				|
		// | helper methods |
		// | 				|
		// V				V
		def create el:MetamodelFactory::eINSTANCE.createService createService(Activity activity) {
			el.name = "Default"
		}

		def create el:new Capacity() createCapacity(Activity o) {
		}

		def create el:new Good() createGood(Source o) {
		}

		def create el:new Time() createTime(LSEMElement o) {
			el.unit = UnitOfTime::HOUR
		}

		def create el:new Constant() createTimePeriod(Time time) {
		}

		def void transferId(ProcessNode s, ILSEMElement t) {
			t.name = s.id
		}
	}
	