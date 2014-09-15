package de.lsem.simulation.util

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

import static de.lsem.simulation.util.LSEMElementHelper.*

class NamingHelper {
		/**
 * Searches for the next free activity-name, while an activity name by default is named as \"<serviceType.literal>-Service\<freeActivityNumber\>\".
 * @returns unique-activity-name 
 */
	static def dispatch String createName(EList<EObject> it, IActivity a) {

		var n = 0
		var newName = createActivityName(a, String.valueOf(n))
		while (nameExists(it, newName)) {
			n = n + 1
			newName = createActivityName(a, String.valueOf(n))
		}
		newName
	}

	/**
 * Searches for the next free source-name, while an source-name by default is named as \"Source_\<freeSourceNumber\>\".
 * @returns unique-source-name 
 */
	static def dispatch String createName(EList<EObject> it, ISource s) {
		var n = 0
		var newName = createSourceName(s, String.valueOf(n))
		while (nameExists(it, newName)) {
			n = n + 1
			newName = createSourceName(s, String.valueOf(n))
		}
		newName
	}

	static def dispatch String createName(EList<EObject> it, Void v) ''''''

	/**
 * Searches for the next free sink-name, while an source-name by default is named as \"Sink_\<freeSinkNumber\>\".
 * @returns unique-sink-name 
 */
	static def dispatch String createName(EList<EObject> it, ISink s) {
		var n = 0
		var newName = createSinkName(s, String.valueOf(n))
		while (nameExists(it, newName)) {
			n = n + 1
			newName = createSinkName(s, String.valueOf(n))
		}
		newName
	}
	
	static def dispatch String createName(EList<EObject> it, IRelation r){
		var n = 0
		var newName = createRelationName(r, String.valueOf(n))
		while (nameExists(it, newName)) {
			n = n + 1
			newName = createRelationName(r, String.valueOf(n))
		}
		newName
	}
	
	private def static String createRelationName(IRelation relation, String string) '''
		Relation_«string»
	'''

	private static def String createActivityName(IActivity it, String activityNumber) '''
	«serviceType.literal»-Service_«activityNumber»'''

	private static def String createSourceName(ISource it, String sourceNumber) '''
	Source_«sourceNumber»'''

	private static def String createSinkName(ISink it, String sinkNumber) '''
	Sink_«sinkNumber»'''}