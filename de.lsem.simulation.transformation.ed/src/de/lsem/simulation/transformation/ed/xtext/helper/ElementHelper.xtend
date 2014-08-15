package de.lsem.simulation.transformation.ed.xtext.helper

import de.lsem.repository.model.simulation.DistributionFunction
import de.lsem.repository.model.simulation.IConstant
import de.lsem.repository.model.simulation.IDistribution
import de.lsem.repository.model.simulation.IDistributionFunction
import de.lsem.repository.model.simulation.ISimulationElement
import java.util.HashMap
import java.util.HashSet
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElementHelper {

	@Inject extension Logger
	@Inject extension DistributionFunctionHelper

	//	private final int STRETCH_FACTOR = 30
	val connections = new HashSet<Connection>
	val processed = new HashSet<Integer>
	val ids = new HashMap<Integer, Integer>
	val HashMap<Integer, Integer> chan1 = new HashMap<Integer, Integer>()
	val HashMap<Integer, Integer> chan2 = new HashMap<Integer, Integer>()
	var lastId = 500

	def getDistributionFor(IDistribution it, String objectString) {
		if (it instanceof IDistributionFunction) {
			distributionFunction(objectString)
		} else if (it instanceof IConstant) {
			constant(objectString)
		} else {
			defaultVal(objectString)
		}
	}

	private def distributionFunction(IDistribution it, String objectNumber) {
		var obj = it as DistributionFunction
		"SetExprAtt(" + objectNumber + ", [" + cDist(obj) + "]);"
	}

	private def constant(IDistribution it, String objectNumber) {
		val obj = it as IConstant
		var value = obj.value;
		if (value == 0.0)
			value = 0.1
		"SetExprAtt(" + objectNumber + ", [" + value + "]);"
	}

	private def defaultVal(String objectNumber) '''
		SetExprAtt(«objectNumber», [negexp(9)]);
	'''

	def addConnection(int from, int to, String condition, int percentage) {

		if (alreadyContains(from, to)) {
			return false
		}

		val connection = new Connection(from, to, condition, percentage)
		connections.add(connection)
		log(Level.INFO, connections.toString)
	}

	def getConnections() {
		return connections
	}

	/**
	 * ERROR: Called before connections have been generated.
	 */
	def getOutgoingConnections(ISimulationElement s) {
		connections.filter [
			ids.get(s.hashCode) == sourceID
		].toSet
	}

	def getId(ISimulationElement it) {
		if (it == null) {
			return -1
		}

		if (ids.containsKey(hashCode)) {
			return ids.get(hashCode)
		}
		lastId = lastId + 1
		ids.put(hashCode, lastId)
		lastId
	}

	/**
 * Used for generating ids for products. As they have no simulation-element as reference,
 * instead the unique source-name is used. 
 */
	def getId(String it) {
		if (it == null) {
			return -1
		}

		if (ids.containsKey(hashCode)) {
			return ids.get(hashCode)
		}
		lastId = lastId + 1
		ids.put(hashCode, lastId)
		lastId
	}

	private def alreadyContains(int sourceID, int targetID) {
		for (c : connections) {
			if (c.sourceID == sourceID && c.targetID == targetID) {
				return true
			}
		}
		return false
	}

	def isProcessed(int hashCode) {
		return processed.contains(hashCode)
	}

	def addProcessed(int hashCode) {
		if (!isProcessed(hashCode)) {
			processed.add(hashCode)
		}
	}

	def int outChanSource(int id) {
		var cnt = chan1.get(id)
		if (cnt == null) {
			cnt = 0
		}

		var con = connections.findFirst[sourceID == id]

		if (!con.condition.contains("product_to_source")) {
			cnt = cnt + 1;
		}

		chan1.put(id, cnt);
		return cnt;
	}

	def int incChanTarget(int id) {
		var cnt = chan2.get(id);
		if (cnt == null)
			cnt = 0;

		cnt = cnt + 1;
		chan2.put(id, cnt);
		return cnt;
	}

//	def recalcPosition(double d){
//		return d / STRETCH_FACTOR as int
//	}
}
