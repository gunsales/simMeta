package de.lsem.simulation.transformation.anylogic.transform.xtend.generator

import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameters
import de.lsem.simulation.transformation.anylogic.transform.helper.Costants
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.ActiveObjectClassInnerImpl
import de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.CreateHelper
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables
import java.util.ArrayList
import java.util.List
import javax.inject.Inject

import static de.lsem.simulation.transformation.anylogic.transform.helper.IDGenerator.*
import static de.lsem.simulation.transformation.anylogic.transform.preparedObjects.EmbeddedObjectGeneric.*
import static de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper.*

class SelectOutputGenerator implements Costants {

	@Inject extension Variables
	@Inject extension ConnectorGenerator
	@Inject extension CreateHelper
	@Inject extension ObjectTransformer

	def dispatchOut(ISimulationElement it) {
		dispatchOut(transformGeneralObject, outgoing, OUT_STRING)
	}

	/**
	 * embedded-object is the transformed source, 
	 * outgoing are the relations connected to the source
	 */
	def ArrayList<EmbeddedObjectGeneric> dispatchOut(EmbeddedObject it, List<IRelation> outgoing, String outString) {
		val retVal = newArrayList //<EmbeddedObjectGeneric>
		switch outgoing.size {
			/* One outgoing relation --> simply create relation */
			case 1: {

				// Problem when exactly 5 relations exist in outgoing in the second iteration step, 
				// relation will be set between root object and target and not between selectOutput5 and target
				val rel = outgoing.get(0)
				val tTarget = rel.target.transformGeneralObject
//				val tSource = rel.source.transformGeneralObject

				connectorSet.add(rel.transformRelation(it, tTarget, outString))

			}
			/* two outgoing relations --> create selectOutput only if both are conditional*/
			case 2:{
				if(bothOutgoingRelationsConditional(outgoing)){
					retVal.add(createSelectOutput(outgoing, outString))
				} else {
					val rel1 = outgoing.get(0)
					val rel2 = outgoing.get(1)
					val rel1Targ = rel1.target.transformGeneralObject
					val rel2Targ = rel2.target.transformGeneralObject
					
					connectorSet.add(rel1.transformRelation(it, rel1Targ, outString))
					connectorSet.add(rel2.transformRelation(it, rel2Targ, outString))
					
				}			
			}
			/* more than two, but less or equal 5 outgoing relations */
			case outgoing.size > 2 && outgoing.size < 5: {
				val a = createSelectOutput5(outgoing)

				// Create connector between transformed source and select-output
				val sourceToSelectOutput5Connector = createConnector(a, outString, IN_STRING)
				connectorSet.add(sourceToSelectOutput5Connector)
				retVal.add(a)
			}
			/* more than five outgoing relations - split to multiple selectOutputs */
			case outgoing.size >= 5: {
				val work = outgoing.subList(0, 4)
				val selectOutput = createSelectOutput5(work)

				// Create connector between transformed source and select-output
				val sourceToSelectOutput5Connector = createConnector(selectOutput, outString, IN_STRING)
				connectorSet.add(sourceToSelectOutput5Connector)

				retVal.add(selectOutput)
				val newWork = outgoing.subList(4, outgoing.size)
				dispatchOut(selectOutput, newWork, OUT_4_STRING).forEach [
					retVal.add(it)
				]
			}
		}
		retVal
	}

	/*
 * Creates a selectOutput5-Object, including all connected elements referred in the list 
 */
	private def create selectOutput5:new EmbeddedObjectGeneric createSelectOutput5(EmbeddedObject source,
		List<IRelation> it) {

		selectOutput5.setId(generateID)

		// enrich select-output-object
		preSelectOutput5Parameter(selectOutput5)

		selectOutput5.x = source.x + 100
		selectOutput5.y = source.y

		forEach [
			val target = target.transformGeneralObject
			val con = createConnector(selectOutput5, target, switchOutStringCaseSelectOutput5, IN_STRING)
			connectorSet.add(con)
		]
		outStringCounter = 0

	}

	/*
 * Creates a selectOutput-Object, including the following elements and connections to these elements
 */
	private def create selectOutput:new EmbeddedObjectGeneric createSelectOutput(EmbeddedObject sim,
		List<IRelation> it, String outString) {

		preSelectOutputParameter(selectOutput)

		// transform source of conditional relation
		//		val sourceTransformed = sim.transformGeneralObject
		selectOutput.x = sim.x + 100
		selectOutput.y = sim.y

		// transform targets of outgoings->targets and create connectors between select-output and targets
		forEach [
			val targetTransformed = target.transformGeneralObject
			connectorSet.add(
				createConnector(selectOutput, targetTransformed, switchOutStringCaseSelectOutput, IN_STRING))
		]
		outStringCounter = 0

		// Create connector between transformed source and select-output
		connectorSet.add(createConnector(sim, selectOutput, outString, IN_STRING))

	}

	private def preSelectOutputParameter(List<IRelation> it, EmbeddedObjectGeneric selectOutput) {
		if (bothOutgoingRelationsConditional) {
			if (get(0).conditionalRelationConditionIsEmpty) {
				val prob = "" + (get(0) as IConditionalRelation).probability / 100

				// enrich select-output-object
				selectOutput.setSelectOutputParameter(prob, "", "", "", true)
			} else {
				val conditionTrue = (get(0) as IConditionalRelation).condition
				val conditionFalse = (get(1) as IConditionalRelation).condition
				selectOutput.setSelectOutputParameter("", "", conditionTrue, conditionFalse, false)
			}
		} else {
			selectOutput.setSelectOutputParameter("", "", "", "", true)
		}
	}

	private def conditionalRelationConditionIsEmpty(IRelation it) {
		(it as IConditionalRelation).condition.empty
	}

	private def bothOutgoingRelationsConditional(List<IRelation> it) {
		get(0) instanceof IConditionalRelation && get(1) instanceof IConditionalRelation
	}

	private def getProbability(IRelation r) {
		((r as IConditionalRelation).probability)
	}

	private def preSelectOutput5Parameter(List<IRelation> it, EmbeddedObjectGeneric selectOutput) {

		if (checkAllRelationsAreConditional) {
			selectOutput.allRelationsAreConditionalCase(it)
		} else {
			selectOutput.setSelectOutput5Parameters(null, null)
		}
	}

	private def allRelationsAreConditionalCase(EmbeddedObjectGeneric selectOutput, List<IRelation> it) {
		if (conditionalRelationConditionIsNull) {
			useProbabilitiesForOutgoingConnections(selectOutput)
		} else {
			useConditionsForOutgoingConnections(selectOutput)
		}
	}

	private def conditionalRelationConditionIsNull(List<IRelation> relations) {
		(relations.get(0) as IConditionalRelation).condition == null
	}

	private def useConditionsForOutgoingConnections(List<IRelation> relations, EmbeddedObjectGeneric selectOutput) {
		var conditions = newArrayList

		for (IRelation r : relations) {
			conditions.add(r.condition)
		}

		selectOutput.setSelectOutput5Parameters(null, conditions)
	}

	private def String getCondition(IRelation relation) {
		(relation as IConditionalRelation).condition
	}

	private def useProbabilitiesForOutgoingConnections(List<IRelation> relations, EmbeddedObjectGeneric selectOutput) {
		var probabilities = newArrayList

		for (IRelation r : relations) {
			probabilities.add(r.probability)
		}

		// enrich select-output-object
		selectOutput.setSelectOutput5Parameters(probabilities, null)
	}

	private def checkAllRelationsAreConditional(List<IRelation> relations) {

		for (IRelation r : relations) {
			if (!(r instanceof IConditionalRelation)) {
				return false
			}
		}

		return true
	}

	private def setSelectOutput5Parameters(EmbeddedObjectGeneric retVal, List<Double> probabilities,
		List<String> conditions) {

		retVal.setCommonSelectOutputParameter

		retVal.activeObjectClassInner = new ActiveObjectClassInnerImpl("SelectOutput5")

		val Parameters p = new Parameters()
		p.apEmpty("onEnter")
		if (probabilities != null) {
			p.setSelectOutput5ProbabilitiesParamter(probabilities)
		} else if (conditions != null) {
			p.setSelectOutput5ConditionsParameter(conditions)
		} else {
			p.setSelectOutput5EmptyParameter;
		}
		p.setSelectOutput5OnExitParameter;

		retVal.setParameters(p)
		retVal
	}

	private def switchOutStringCaseSelectOutput5() {
		val retVal = switch outStringCounter {
			case 0: OUT_0_STRING
			case 1: OUT_1_STRING
			case 2: OUT_2_STRING
			case 3: OUT_3_STRING
			case 4: OUT_4_STRING
			default: ""
		}
		outStringCounter = outStringCounter + 1

		retVal
	}

	private def switchOutStringCaseSelectOutput() {
		val retVal = switch outStringCounter {
			case 0: OUT_TRUE_STRING
			case 1: OUT_FALSE_STRING
			default: ""
		}
		outStringCounter = outStringCounter + 1

		retVal
	}

	private def setSelectOutput5ConditionsParameter(Parameters it, List<String> conditions) {
		var i = 0
		var j = 0
		addParameter(createParameter("useConditions", T));

		// Add conditions
		while ((i = i + 1) <= conditions.size) {
			j = i - 1
			addParameter(createParameter("condition" + j, conditions.get(j)))
			apEmpty("probability" + j)
		}

		// Add remaining conditions
		while ((i = i + 1) < 5) {
			j = i - 1
			apEmpty("condition" + j)
			apEmpty("probability" + j)
		}
	}

	private def setSelectOutput5ProbabilitiesParamter(Parameters it, List<Double> probabilities) {
		var i = 0
		var j = 0

		addParameter(createParameter("useConditions", F));

		// Add probabilities
		while ((i = i + 1) <= probabilities.size) {
			j = i - 1
			apEmpty("condition" + j)
			addParameter(createParameter("probability" + j, String.valueOf(probabilities.get(j))))
		}

		// Add remaining probabilities
		while ((i = i + 1) < 5) {
			j = i - 1
			apEmpty("condition" + j)
			apEmpty("probability" + j)
		}
	}

	private def setSelectOutput5EmptyParameter(Parameters it) {
		apEmpty("useConditions")
		apEmpty("condition0")
		apEmpty("probability0")
		apEmpty("condition1")
		apEmpty("probability1")
		apEmpty("condition2")
		apEmpty("probability2")
		apEmpty("condition3")
		apEmpty("probability3")
		apEmpty("condition4")
		apEmpty("probability4")
	}

	private def apEmpty(Parameters it, String para) {
		addParameter(para.cEP)
	}

	private def cEP(String string) {
		createEmptyParameter(string)
	}

	private def setSelectOutput5OnExitParameter(Parameters it) {
		var k = 0
		var l = 0
		while ((k = k + 1) <= 5) {
			l = k - 1
			apEmpty("onExit" + l)
		}
		apEmpty("onExitDefault")
	}

	private def setSelectOutputParameter(EmbeddedObjectGeneric retVal, String probability, String condition,
		String conditionTrue, String conditionFalse, boolean conditionIsProbablistic) {

		retVal.setCommonSelectOutputParameter

		retVal.activeObjectClassInner = new ActiveObjectClassInnerImpl("SelectOutput")

		val Parameters p = new Parameters()
		if (conditionIsProbablistic) {
			p.apEmpty("conditionIsProbabilistic")
		} else {
			p.addParameter(createParameter("conditionIsProbabilistic", "false"));
		}
		p.addParameter(createParameter("condition", condition))
		p.addParameter(createParameter("probability", probability))
		p.apEmpty("onEnter")
		p.addParameter(createParameter("onExitTrue", conditionTrue))
		p.addParameter(createParameter("onExitFalse", conditionFalse))
		retVal.setParameters(p)

		retVal
	}

	private def setCommonSelectOutputParameter(EmbeddedObjectGeneric retVal) {

		retVal.setId(generateID)
		retVal.name = getSelectOutputName(selectOutputCounter)
		retVal.label = createLabel(-2, -11)
		retVal.publicFlag = F
		retVal.presentationFlag = T
		retVal.showLabel = T
		retVal.genericParametersSubstitute = wrapCDATA("Entity")
		retVal.collectionType = ARRAY_LIST_BASED
		retVal
	}

}
