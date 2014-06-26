package de.lsem.simulation.transformation.sim.xtext

import de.lsem.process.model.ProcessNode
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.ITime
import de.lsem.repository.model.simulation.SimulationFactory
import de.lsem.repository.model.simulation.UnitOfTime
import org.eclipse.bpmn2.FlowElement

class CommonTransformation {

	protected def create capa:SimulationFactory::eINSTANCE.createCapacity createCapacity(IActivity o) {
	}

	protected def create el:SimulationFactory::eINSTANCE.createGood createGood(ISource o) {
		el.description = ""
		el.name = ""
		el.type = ""
	}

	// Is not set as create-method-type because the same ISimulationParameter needs a newEntities- and a firstEntity-object
	protected def create el:SimulationFactory::eINSTANCE.createTime createTime(ISimulationElement o) {
		el.period = el.createTimePeriod
		el.unit = UnitOfTime::HOUR
	}

	protected def create el:SimulationFactory::eINSTANCE.createConstant createTimePeriod(ITime time) {
		el.value = 0.0
	}

	protected def void transferId(ProcessNode s, ISimulationElement t) {
		t.name = getTrimmedId(s.id, s.label)
	}

	protected def void transferId(FlowElement s, ISimulationElement t) {
		t.name = getTrimmedId(s.id, s.name)
	}

	private def getTrimmedId(String id, String label) {
		if (label != null && !label.empty) {
			label.replaceUmlaute.trim
		} else if (id != null && !id.empty) {
			id.replaceUmlaute.trim
		} else {
			""
		}
	}

	static def replaceUmlaute(String it) {
		replace("ü", "ue").replace("ö", "oe").replace("ä", "ae").replace("Ü", "Ue").replace("Ö", "Oe").replace("Ä",
			"Ae")
	}

}
