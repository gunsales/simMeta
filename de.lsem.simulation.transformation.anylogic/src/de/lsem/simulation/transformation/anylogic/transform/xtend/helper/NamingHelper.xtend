package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource

class NamingHelper {

	static private def String getSelectOutputNameInternal(int selectOutputCounter) '''SelectOutput«selectOutputCounter»'''

	static def getSelectOutputName(int selectOutputCounter) {
		getSelectOutputNameInternal(selectOutputCounter).wrapCDATA
	}

	static def dispatch String createEntityTypeDeclaration(IActivity it) '''Service'''

	static def dispatch String createEntityTypeDeclaration(ISource it) '''Source'''

	static def dispatch String createEntityTypeDeclaration(ISink it) '''Sink'''

	static def dispatch String createEntityTypeDeclaration(Void it) ''''''

	static def String wrapCDATA(String stringToWrap) '''<![CDATA[«stringToWrap»]]>'''

	static def dispatch String makeValidForAnylogic(String name) '''«FOR s : name.split(" ")»«s.stripFirstInteger.
		replaceUmlaute.toFirstUpper.replace(" ", "").replaceAll("[^a-zA-Z0-9]", "").trim»«ENDFOR»'''

	static def dispatch String makeValidForAnylogic(Void it) ''''''

	static private def replaceUmlaute(String k) {
		k.replace("ü", "ue").replace("ö", "oe").replace("ä", "ae").replace("Ü", "Ue").replace("Ö", "Oe").replace("Ä",
			"Ae")
	}

	static private def stripFirstInteger(String k) {
		try {
			Integer.parseInt(k.charAt(0).toString)
			return k.substring(1, k.length)
		} catch (Exception e) {
		}
		k
	}
}
