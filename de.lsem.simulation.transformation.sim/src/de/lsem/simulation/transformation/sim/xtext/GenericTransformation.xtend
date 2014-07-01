package de.lsem.simulation.transformation.sim.xtext

import de.lsem.process.model.ProcessEdge
import de.lsem.process.model.ProcessNode
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ServiceType
import de.lsem.repository.model.simulation.SimulationFactory
import java.util.HashSet
import java.util.Set
import javax.inject.Inject

/**
 * Transformation only creates activities, since events are not recognized by Klinkes transformation.
 * Start- and end-event are automatically added after transformation.
 */
class GenericTransformation {

	@Inject
	extension CommonTransformation

	Set<ProcessNode> nodes = newHashSet
	Set<ProcessEdge> edges = newHashSet

	//Used to set unique id for connection-names
	var relationCounter = 0;

	@Inject
	new() {
		SimulationFactory::init
	}

	private def void filterNodesAndEdges(Set<ProcessNode> nodes, Set<ProcessEdge> edges) {

		this.edges = edges
		this.nodes = nodes

		//Transform only activities and gateways
		this.nodes = nodes.filter [
			(activity || gateway) // only gateways and activities
		].toSet

		nodes.filter[gateway].filter[incoming.size < 1 || outgoing.size < 1].forEach [
			edges.removeAll(incoming)
			edges.removeAll(outgoing)
		]

		//Remove all gateways without incoming or outgoing relations
		this.nodes.removeAll(
			nodes.filter[gateway].filter[incoming.size < 1 || outgoing.size < 1]
		)
	}

	public def Set<ISimulationElement> transform(Set<ProcessNode> nodes, Set<ProcessEdge> edges) {
		filterNodesAndEdges(nodes, edges)
		val starts = startElements.map [
			transform
		].toSet
		val retVal = reachable(newHashSet, starts)
		retVal.addStartAndEndElement
	}

	private def isGateway(ProcessNode p) {

		//		println("" + (p.isOfType(ProcessNode::GATEWAY) || p.isOfType(ProcessNode::EXCLUSIVE_GATEWAY) ||
		//			p.isOfType(ProcessNode::PARALLEL_GATEWAY)) + " name: " + p.label + " id:"+ p.id)
		return ( p.isOfType(ProcessNode::GATEWAY) || p.isOfType(ProcessNode::EXCLUSIVE_GATEWAY) ||
			p.isOfType(ProcessNode::PARALLEL_GATEWAY) )

	}

	private def Set<ISimulationElement> addStartAndEndElement(Set<ISimulationElement> transformedSet) {
		endElements.forEach [
			if (activity)
				transformedSet.add(createSink)
		]
		startElements.forEach [
			transformedSet.add(createSource)
		]

		transformedSet
	}

	private def startElements() {
		nodes.filter[incoming.empty].toSet
	}

	private def getIncoming(ProcessNode p) {
		edges.filter[target.id.equals(p.id) && ( target.activity || target.gateway )].toSet
	}

	/*
 	* A correct EPC ends with an event. This means, that the last function of the
 	* EPC has outgoing edges. In that case, the method "connectedToEndElements" returns
 	* those elements, that have outgoing relations, but are connected to an element with the label "end"
 	*/
	private def endElements() {
		var retVal = nodes.filter [
			outgoing.empty
		].toSet

		retVal.addAll(connectedToEndElements)

		retVal
	}

	private def getConnectedToEndElements() {
		nodes.filter [
			outgoing.filter [
				target.label.equals("end")
			].size > 0
		].toSet
	}

	private def getOutgoing(ProcessNode p) {
		edges.filter [
			source.id.equals(p.id) && ( source.activity || source.gateway )
		].toSet
	}

	private def Set<ISimulationElement> reachable(Set<ISimulationElement> input, Set<ISimulationElement> output) {

		if (input.size == output.size) {
			output
		} else {
			val temp = new HashSet(output)
			output.map[outgoing].flatten.map[target].forEach [
				temp.add(it as IActivity)
			]
			reachable(output, temp)
		}
	}

	private def void transformFlow(ProcessEdge it) {

		if (target.gateway) {
			val sour = source.transform

			// BUGFIX if there is only one outgoing relation and target is a gateway, create a usual relation
			if (source.outgoing.size < 2) {
				sour.outgoing.add(createRelation(sour, target.transform))
			} 
			// Create conditional relations for each outgoing edge
			else {
				target.outgoing.forEach [ t |
					sour.outgoing.add(createConditionalRelation(sour, target.transform))
				]
			}
		} else {
			var sour = source.transform
			var targ = target.transform

			if (source.outgoing.size < 2) {
				createRelation(sour, targ)
			} else {
				createConditionalRelation(sour, targ)
			}

		//			relation.source = sour
		//			relation.target = targ
		//			println("Creating relation between: " + relation.source + " and " + relation.target)
		//			sour.outgoing.add(relation)
		}
	}

	private def transformFlow(ISimulationElement startElement, ISimulationElement endElement) {

		var relation = createRelation(startElement, endElement)

		relation.source = startElement
		relation.target = endElement

		startElement.outgoing.add(relation)
		relation
	}

	private def ISimulationElement transform(ProcessNode node) {
		node.createActivity
	}

	private def create condRel:SimulationFactory::eINSTANCE.createConditionalRelation createConditionalRelation(
		ISimulationElement source, ISimulationElement target) {
		condRel.condition = ""
		condRel.probability = 50f
		condRel.name = "relation_" + relationCounter

		condRel.source = source
		condRel.target = target

		relationCounter = relationCounter + 1
		source.outgoing.add(condRel)
	}

	private def create rel:SimulationFactory::eINSTANCE.createRelation createRelation(ISimulationElement source,
		ISimulationElement target) {
		rel.source = source
		rel.target = target
		rel.name = "relation_" + relationCounter
		relationCounter = relationCounter + 1
		source.outgoing.add(rel)
	}

	private def create el:SimulationFactory::eINSTANCE.createActivity createActivity(ProcessNode sourceNode) {

		//		println(sourceNode.id + ", " + sourceNode.label)
		transferId(sourceNode, el)

		el.capacity = el.createCapacity
		el.serviceType = ServiceType::DEFAULT
		el.timePeriod = el.createTime
		el.subActivities

		//		el.outgoing
		sourceNode.transformOutgoingFlows
	}

	private def void transformOutgoingFlows(ProcessNode sourceNode) {
		edges.filter[source.id.equals(sourceNode.id)].forEach [
			transformFlow
		]
	}

	var int startCounter = 0

	private def create start:SimulationFactory::eINSTANCE.createSource createSource(ProcessNode o) {

		start.name = "Start_" + startCounter
		startCounter = startCounter + 1
		start.processedObject = start.createGood
		start.firstEntity = start.createTime
		start.newEntities = start.createTime
		start.maxNewEntities = 0
		start.outgoing
		val relation = transformFlow(start, o.transform)
		start.outgoing.add(relation)
	}

	var int sinkCounter = 0;

	private def create sink:SimulationFactory::eINSTANCE.createSink createSink(ProcessNode o) {

		sink.name = "Sink_" + sinkCounter
		sinkCounter = sinkCounter + 1
		transformFlow(o.transform, sink)
	}

}
