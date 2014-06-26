package de.lsem.simulation.transformation.sim.xtext

import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ITime
import de.lsem.repository.model.simulation.SimulationFactory
import java.util.HashSet
import java.util.List
import java.util.Set
import javax.inject.Inject
import org.eclipse.bpmn2.EndEvent
import org.eclipse.bpmn2.FlowElement
import org.eclipse.bpmn2.FlowNode
import org.eclipse.bpmn2.Gateway
import org.eclipse.bpmn2.SequenceFlow
import org.eclipse.bpmn2.StartEvent
import org.eclipse.bpmn2.Task

class TransformBPMN2ToSimulation {

	/**
	 * Used for simMeta-subelement-generation
	 */
	@Inject extension CommonTransformation

	var relationCounter = 0;

	/**
 * Main-class for transformation from BPMN2-Process-Diagrams to simMeta-Diagrams.
 * The transformation includes the following flow-elements: Gateways, Start- and End-Events, 
 * Tasks and sequence-flows. All other BPMN2-Elements will be ignored.
 */
	new() {
	}

	/**
 * Entry point for transformation. The transformation searches for start-elements. 
 * Start-Element and CONNECTED successors will be transformed until all successors have been transformed.
 * Check for cycles in process before the transformation!
 * 
 * @return Transformed and connected simMeta-Elements
 * 
 */
	def Set<? extends ISimulationElement> startTransformation(List<FlowElement> it) {
		val Set<ISimulationElement> starts = filterValidElements.startElements.map [
			if (! (it instanceof Gateway))
				transform
		].toSet
		reachable(newHashSet, starts).filterNull.toSet
	}

	private def getStartElements(Set<FlowNode> it) {
		filter[incoming == null || incoming.size < 1]
	}
	
	private def filterValidElements(List<FlowElement> it){
		val retVal = new HashSet<FlowNode>();
		
		retVal.addAll(filter(typeof(StartEvent)))
		retVal.addAll(filter(typeof(EndEvent)))
		retVal.addAll(filter(typeof(Task)))
		retVal.addAll(filter(typeof(Gateway)))
		retVal
	}

	private def Set<ISimulationElement> reachable(Set<ISimulationElement> input, Set<ISimulationElement> output) {

		if (input.size == output.size) {
			output
		} else {
			val temp = new HashSet(output)
			output.map [
				if (it == null || outgoing == null) {
					new HashSet<IRelation>
				} else {
					outgoing
				} // OKTODO Make it NULL-SAFE!! Get outgoing relations
			].flatten.map [
				target // Get target of relations
			].forEach [
				temp.add(it) // Add it to targetList
			]
			reachable(output, temp)
		}
	}

	private def void transformFlow(SequenceFlow sf, FlowElement originalSource) {
		if (sf.targetRef instanceof Gateway) {
			sf.targetRef.outgoing.forEach [
				transformFlow(it, sf.sourceRef)
			]
		} else {
			var targ = sf.targetRef.transform
			var sour = targ

			if (sf.sourceRef instanceof Gateway)  // only split into conditional relations, 
			//			if there is more than one outgoing relation, else use usual relations
			{
				sour = originalSource.transform

				if (sf.sourceRef.outgoing.size > 1) {

					// Only generate a conditional relation, when it is not the source itself
					// Problem when multiple tasks are merged via one gateway. 
					// Instead of generating a conditional relation for each outgoing element, create usual relations.
					val conditionalRelation = SimulationFactory::eINSTANCE.createConditionalRelation

					// set condition/probability
					conditionalRelation.probability = 0.0
					conditionalRelation.condition = ""

					// set common values
					conditionalRelation.setCommonRelationValues(sour, targ)

					// Disallow self-connecting relations --> targ == sour ? do not create a relation
					// this happens if the source is a gateway, so the incomming source-element is used as source
					if (!sour.equals(targ)) {
						sour.outgoing.add(conditionalRelation)
					}

				} else { // If there is only one or zero outgoing relation
					val relation = SimulationFactory::eINSTANCE.createRelation
					relation.setCommonRelationValues(sour, targ)
				}
			} else { // if it is not a gateway ... 

				var relation = SimulationFactory::eINSTANCE.createRelation
				sour = sf.sourceRef.transform

				//set common values
				relation.setCommonRelationValues(sour, targ)
			}
		}
	}

	private def IRelation setCommonRelationValues(IRelation it, ISimulationElement sour, ISimulationElement targ) {
		source = sour
		target = targ

		name = "relation_" + relationCounter
		relationCounter = relationCounter + 1

		it
	}

	private def dispatch ISimulationElement transform(FlowElement it) {
	}

	private def dispatch ISimulationElement transform(EndEvent it) {
		createTarget
	}

	private def dispatch ISimulationElement transform(StartEvent it) {
		val so = createTarget
		so.setFirstEntity(so.createTime)
		so.firstEntity.setPeriod(createTimePeriod(so.firstEntity as ITime))
		so.setNewEntities(so.createTime)
		so.newEntities.setPeriod(createTimePeriod(so.newEntities as ITime))
		so.setProcessedObject(so.createGood)
		so
	}

	private def dispatch ISimulationElement transform(Task it) {
		val ac = createTarget
		ac.setCapacity(ac.createCapacity)
		ac.setTimePeriod(ac.createTime)
		ac.getTimePeriod().setPeriod(createTimePeriod(ac.timePeriod as ITime))
		ac
	}

	private def create start:SimulationFactory::eINSTANCE.createSource createTarget(StartEvent o) {
		transferId(o, start)
		o.outgoing.forEach [
			transformFlow(o)
		]
	}

	private def create el:SimulationFactory::eINSTANCE.createSink createTarget(EndEvent o) {
		transferId(o, el)
	}

	private def create el:SimulationFactory::eINSTANCE.createActivity createTarget(Task o) {
		transferId(o, el)
		o.outgoing.forEach [
			transformFlow(o)
		]
	}
}
