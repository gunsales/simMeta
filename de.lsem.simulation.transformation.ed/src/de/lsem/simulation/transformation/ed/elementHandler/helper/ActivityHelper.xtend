package de.lsem.simulation.transformation.ed.elementHandler.helper

import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ServiceType

class ActivityHelper {


	def String activityServiceName(IActivity a) {
		a.serviceType.literal + "_Service"
	}

	def getInt023Param(IActivity it) {
		var param = switch serviceType {
			case ServiceType.HANDLING: "8388608"
			case ServiceType.TRANSPORT: "32768"
			case ServiceType.VALUE_ADDED: "8388736"
			case ServiceType.STORAGE: "128"
			case ServiceType.PICKING: "32896"
			default: "16711680"
		}

		return param
	}

}
