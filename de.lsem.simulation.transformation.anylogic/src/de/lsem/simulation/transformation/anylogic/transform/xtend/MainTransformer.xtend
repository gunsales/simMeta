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

	def create retVal:new ArrayList<EmbeddedObject>() transform(XMIResource r) throws NullPointerException {
		if (r == null) {
			throw new NullPointerException("Error. Resource must not be null.")
		}

		resource = r

		simulationElementsWithoutSubActivities.forEach [
			retVal.add(transformGeneralObject)
			dispatchOut.forEach[retVal.add(it)]
		]
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
	
	private def getSubActivities(){
		val retVal = newArrayList
		for ( aDummy : activities ) {
			for (aDummy2 : aDummy.subActivities){
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
