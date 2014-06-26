package de.lsem.simulation.transformation.anylogic.transform.xtend.generator

import de.lsem.repository.model.simulation.Activity
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.Sink
import de.lsem.repository.model.simulation.Source
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameters
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.ActiveObjectClassInnerImpl
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.CreateHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.DistributionFunctionHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.PositionHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables
import javax.inject.Inject
import javax.inject.Singleton

import static de.lsem.simulation.transformation.anylogic.transform.helper.IDGenerator.*
import static de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric.*
import de.lsem.repository.model.simulation.ServiceType

@Singleton
class ObjectTransformer {
	
	@Inject extension CreateHelper
	@Inject extension NamingHelper
	@Inject extension PositionHelper
	@Inject extension Variables
	@Inject extension DistributionFunctionHelper
		
	def transformGeneralObject(ISimulationElement it) {

		var eo = dispatchForTransformation

		eo.name = name.makeValidForAnyLogic
		eo.x = x
		eo.y = y

		eo.activeObjectClassInner = new ActiveObjectClassInnerImpl(class.createEntityTypeDeclaration)

		eo.label = createLabel(-2, -11)

		eo
	}
	
	private def dispatchForTransformation(ISimulationElement it) {
		switch it {
			Activity: transformObject(it as IActivity)
			Source: transformObject(it as ISource)
			Sink: transformObject(it as ISink)
			default: transformObject
		}
	}
	
	private def create retVal:new EmbeddedObjectGeneric transformObject(IActivity it) {
		val Parameters p = new Parameters()

		retVal.setId(generateID)
		p.addParameter(createEmptyParameter("quantity"))
		/* Add DistributionFunction/Constant-Value */
		p.addParameter(createParameter("delayTime", getDistributionFunctionFor(timePeriod.period)))
		p.addParameter(createEmptyParameter("resourcePool"))
		p.addParameter(createEmptyParameter("onEnter"))
		p.addParameter(createEmptyParameter("onEnterDelay"))
		p.addParameter(createEmptyParameter("onExit"))
//		p.addParameter(createEmptyParameter("queueCapacity"))
		p.addParameter(queueCapacity)
		p.addParameter(createEmptyParameter("maximumCapacity"))
		p.addParameter(createEmptyParameter("enableTimeout"))
		p.addParameter(createEmptyParameter("timeout"))
		p.addParameter(createEmptyParameter("onExitTimeout"))
		p.addParameter(createEmptyParameter("enablePreemption"))
		p.addParameter(createEmptyParameter("priority"))
		p.addParameter(createEmptyParameter("onExitPreempted"))
		p.addParameter(createEmptyParameter("animationGuideQueue"))
		p.addParameter(createEmptyParameter("animationTypeQueue"))
		p.addParameter(createEmptyParameter("animationForwardQueue"))
		p.addParameter(createEmptyParameter("animationGuideDelay"))
		p.addParameter(createEmptyParameter("animationTypeDelay"))
		p.addParameter(createEmptyParameter("animationForwardDelay"))
		p.addParameter(createEmptyParameter("enableStats"))

		retVal.setParameters(p)
	}
	
	def queueCapacity(IActivity it) {
		
		if ( serviceType.value == ServiceType.STORAGE_VALUE ){
			createParameter("queueCapacity", String.valueOf(capacity.maxCapacity)) 
		} else {
			createEmptyParameter("queueCapacity")
		}
	}

	private def create retVal:new EmbeddedObjectGeneric transformObject(ISink it) {
		val Parameters p = new Parameters()
		p.addParameter(createEmptyParameter("onEnter"))

		retVal.setParameters(p)
		retVal.setId(generateID)
	}

	private def create retVal:new EmbeddedObjectGeneric transformObject(ISimulationElement it) {
		retVal.setId(generateID)
	}
	
	private def create retVal:new EmbeddedObjectGeneric transformObject(ISource it) {
		val Parameters p = new Parameters()

		retVal.setId(generateID)
		p.addParameter(createEmptyParameter("arrivalType"))
		p.addParameter(createEmptyParameter("rate"))
		p.addParameter(createEmptyParameter("interarrivalTime"))
		p.addParameter(createEmptyParameter("rateSchedule"))
		p.addParameter(createEmptyParameter("rateTable"))
		p.addParameter(createEmptyParameter("arrivalSchedule"))
		p.addParameter(createEmptyParameter("arrivalTable"))
		p.addParameter(createEmptyParameter("modifyRate"))
		p.addParameter(createEmptyParameter("rateExpression"))
		p.addParameter(createParameter("entitiesPerArrival", newEntities.period.distributionFunctionFor))
		
		setLimitAndMaxArrivals(p)
		
		p.addParameter(createEmptyParameter("newEntity"))
		p.addParameter(createEmptyParameter("onExit"))
		p.addParameter(createEmptyParameter("entityShape"))
		p.addParameter(createEmptyParameter("uniqueShape"))
		p.addParameter(createEmptyParameter("enableRotation"))
		p.addParameter(createEmptyParameter("enableVerticalRotation"))

		retVal.setParameters(p)
		
		retVal.name = "SourceA" + sourceCounter
	}
	
	def setLimitAndMaxArrivals(ISource it, Parameters p) {
		
		if(maxNewEntities != 0){
			p.addParameter(createParameter("limitArrivals", String.valueOf(Boolean.TRUE)))
			p.addParameter(createParameter("maxArrivals", String.valueOf(maxNewEntities)))
		} else {
			p.addParameter(createEmptyParameter("limitArrivals"))
			p.addParameter(createEmptyParameter("maxArrivals"))
		}

	}

	
	
}