package de.lsem.simulation.transformation.anylogic.transform.xtend

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject
import de.lsem.simulation.transformation.anylogic.transform.helper.Costants
import de.lsem.simulation.transformation.anylogic.transform.xtend.generator.ObjectTransformer
import de.lsem.simulation.transformation.anylogic.transform.xtend.generator.SelectOutputGenerator
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables
import java.util.ArrayList
import javax.inject.Inject
import org.eclipse.emf.ecore.xmi.XMIResource

class MainTransformer implements Costants {

	@Inject extension ObjectTransformer
	@Inject extension SelectOutputGenerator
	@Inject extension Variables v

	/**
	 * @param r - xmiresource that holds the graphical and object-based information to be transformed. The resource must be unique in injection-scope, meaning only one transformation allowed per injection.
	 */
	def transform(XMIResource r) throws NullPointerException {
		val retVal = new ArrayList<EmbeddedObject>
		if (r == null) {
			throw new NullPointerException("Error. Resource must not be null.")
		} else if (resource != null) {
			throw new Exception(
				"Transformation can not be started, as the xmi-resource is not unique. Please use a new injector.")
		}

		// Only one instance can be run per generated injector!
		resource = r

		simulationElementsWithoutSubActivities.forEach [
			retVal.add(transformGeneralObject)
			dispatchOut.forEach[retVal.add(it)]
		]
		retVal
	}

	def getConnectorSet() {
		v.connectorSet
	}

	//	private def isSubactivity(ISimulationElement ac) {
	//		if (!( ac instanceof IActivity ))
	//			return false
	//		while (activities.hasNext) {
	//			if (getParentActivity(activities.next) != null)
	//				return true
	//		}
	//		return false
	//	}
	//
	//	private def getParentActivity(IActivity subActivity) {
	//		while (activities.hasNext) {
	//			for (aDummy : activities.next.subActivities) {
	//				if (aDummy == subActivity)
	//					return next
	//			}
	//		}
	//	}
	private def activities() {
		resource.allContents.filter(typeof(IActivity)).toList
	}

	private def getSubActivities() {
		val retVal = newArrayList
		for (aDummy : activities) {
			for (aDummy2 : aDummy.subActivities) {
				retVal.add(aDummy2)
			}
		}
		retVal
	}

	private def simulationElementsWithoutSubActivities() {
		val retVal = resource.allContents.filter(typeof(ISimulationElement)).toList
		retVal.removeAll(subActivities)
		retVal
	}
}
