package de.lsem.simulation.transformation.ed.elementHandler.helper

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISource
import java.util.ArrayList
import org.eclipse.emf.ecore.xmi.XMIResource

class Helper {

	static def lsemName(ISimulationElement it) {
		name.replaceUmlaute
	}

	static def replaceUmlaute(String s) {
		if (s == null)
			return "";
		return s.replaceAll("ä", "ae").replaceAll("Ä", "Ae").replaceAll("ö", "oe").replaceAll("Ö", "Oe").
			replaceAll("ü", "ue").replaceAll("Ü", "Ue").replaceAll("ß", "ss"); //.replace(" ", "");
	}

	static def int013(ISimulationElement e, XMIResource it) {
		val s = new StringBuffer
		var i = 1
		for (r : getIncomming(e)) {
			s.append("int013(" + i + ", 0, true, false, 0, 0, [], []);")
			i = i + 1
		}
		s.toString
	}

	static def int013_new(ISimulationElement e, XMIResource it) '''
		«var i = 1»
		«FOR r : getIncomming(e)»
			int013(«i», 0, true, false, 0, 0, [], []);
			«i = i + 1»
		«ENDFOR»
	'''

	static def getIncomming(XMIResource it, ISimulationElement e) {
		val retVal = new ArrayList<IRelation>
		simulationElements.forEach [ s |
			s.outgoing.forEach [ r |
				if (r.target.name.equals(e.name)) {
					retVal.add(r)
				}
			]
		]
		retVal
	}

	static def getNumberOfIncomingElements(XMIResource it, ISimulationElement e) {
		getIncomming(e).size
	}

	static def getOut(ISimulationElement it) {
		outgoing.size
	}

	static def getSimulationElements(XMIResource it) {
		allContents.filter(typeof(ISimulationElement))
	}

	static def getSources(XMIResource it) {
		simulationElements.filter(typeof(ISource))
	}

	static def getActivities(XMIResource it) {
		simulationElements.filter(typeof(IActivity))
	}

	static def getSimElementsWithoutSubActivities(XMIResource it) {
		val retVal = new ArrayList<ISimulationElement>(simulationElements.toList)

		activities.forEach [
			subActivities.forEach [
				retVal.remove(it)
			]
		]
		retVal
	}

}
