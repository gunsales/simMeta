package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import javax.inject.Inject

class NamingHelper {

	@Inject extension Variables

	def getSelectOutputName() {
		("SelectOutput" + selectOutputCounter).wrapCDATA
	}

	def createEntityTypeDeclaration(Class<? extends ISimulationElement> _class) {
		switch _class {
			case Activity: "Service"
			case Source: "Source"
			case Sink: "Sink"
			default: ""
		}
	}

	def wrapCDATA(String stringToWrap) {
		"<![CDATA[" + stringToWrap + "]]>"
	}

	def makeValidForAnyLogic(String name) {

		var retVal = ""

		if (name == null) {
			retVal
		} else {

			// Anylogic does not allow empty spaces in names,
			// because it uses those names as Class-Names.
			// --> return camel-cased words without special-chars
			val words = name.split(" ");

			for (String s : words) {
				var k = s;
				k.stripFirstInteger
				k.replaceUmlaute
				retVal = retVal + k.toFirstUpper.replace(" ", "").replaceAll("[^a-zA-Z0-9]", "").trim;
			}
			
			
			retVal
		}
	}
	
	private def replaceUmlaute(String k) {
		k.replace("ü", "ue").replace("ö", "oe").replace("ä", "ae").replace("Ü", "Ue").replace("Ö", "Oe").replace("Ä", "Ae")
	}
	
	private def stripFirstInteger(String k) {
		try {
			Integer.parseInt(k.charAt(0).toString)
			return k.substring(1, k.length)
		} catch (Exception e) {
		}
		k
	}
}
