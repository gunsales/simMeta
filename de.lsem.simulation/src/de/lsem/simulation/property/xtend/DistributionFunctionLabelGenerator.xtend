package de.lsem.simulation.property.xtend

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Erlang
import de.lsem.repository.model.simulation.Gamma
import de.lsem.repository.model.simulation.IBeta
import de.lsem.repository.model.simulation.IDistributionFunction
import de.lsem.repository.model.simulation.IErlang
import de.lsem.repository.model.simulation.IGamma
import de.lsem.repository.model.simulation.ILogNormal
import de.lsem.repository.model.simulation.INegExp
import de.lsem.repository.model.simulation.INormal
import de.lsem.repository.model.simulation.IPoisson
import de.lsem.repository.model.simulation.ITriangular
import de.lsem.repository.model.simulation.IUniform
import de.lsem.repository.model.simulation.IWeibull
import de.lsem.repository.model.simulation.LogNormal
import de.lsem.repository.model.simulation.NegExp
import de.lsem.repository.model.simulation.Normal
import de.lsem.repository.model.simulation.Poisson
import de.lsem.repository.model.simulation.SimulationFactory
import de.lsem.repository.model.simulation.Triangular
import de.lsem.repository.model.simulation.Uniform
import de.lsem.repository.model.simulation.Weibull

class DistributionFunctionLabelGenerator {

	def String getDistributionFunctionLabelForComboViewer(IDistributionFunction it) '''
		
		«IF it instanceof IWeibull»
			«val w = it as IWeibull»
			«class.getSimpleName()» ( «w.beta», «w.alpha» )
		«ELSEIF it instanceof IBeta»
			«val w = it as IBeta»
			«class.getSimpleName()» ( «w.beta», «w.alpha» )
		«ELSEIF it instanceof INormal»
			«val w = it as INormal»
			«class.getSimpleName()» ( «w.mean», «w.stdDev» )
		«ELSEIF it instanceof ITriangular»
			«val w = it as ITriangular»
			«class.getSimpleName()» ( «w.min», «w.mode», «w.max» )
		«ELSEIF it instanceof ILogNormal»
			«val w = it as ILogNormal»
			«class.getSimpleName()» ( «w.logMean», «w.logStd» )
		«ELSEIF it instanceof IUniform»
			«val w = it as IUniform»
			«class.getSimpleName()» ( «w.min», «w.max» )
		«ELSEIF it instanceof IPoisson»
			«val w = it as IPoisson»
			«class.getSimpleName()» ( «w.mean» )
		«ELSEIF it instanceof INegExp»
			«val w = it as INegExp»
			«class.getSimpleName()» ( «w.mean» )
		«ELSEIF it instanceof IErlang»
			«val w = it as IErlang»
			«class.getSimpleName()» ( «w.expMean», «w.k» )
		«ELSEIF it instanceof IGamma»
			«val w = it as IGamma»
			«class.getSimpleName()» ( «w.beta», «w.alpha» )
		«ENDIF»	
	'''

	def getDistributionFunctionForLSEMElement(String text) {
		try {

			val attributeArray = generateAttributeValueArray(text);

			switch (text) {
				case null:
					SimulationFactory.eINSTANCE.createBeta
				case text.startsWith(typeof(Weibull).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createWeibull
					fct.beta = attributeArray.get(0)
					fct.alpha = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Beta).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createBeta
					fct.beta = attributeArray.get(0)
					fct.alpha = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Normal).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createNormal
					fct.mean = attributeArray.get(0)
					fct.stdDev = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Triangular).simpleName) && attributeArray.length == 3: {
					val fct = SimulationFactory.eINSTANCE.createTriangular
					fct.min = attributeArray.get(0)
					fct.mode = attributeArray.get(1)
					fct.max = attributeArray.get(2)
					fct
				}
				case text.startsWith(typeof(LogNormal).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createLogNormal
					fct.logMean = attributeArray.get(0)
					fct.logStd = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Uniform).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createUniform
					fct.min = attributeArray.get(0)
					fct.max = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Poisson).simpleName) && attributeArray.length == 1: {
					val fct = SimulationFactory.eINSTANCE.createPoisson
					fct.mean = attributeArray.get(0)
					fct
				}
				case text.startsWith(typeof(NegExp).simpleName) && attributeArray.length == 1: {
					val fct = SimulationFactory.eINSTANCE.createNegExp
					fct.mean = attributeArray.get(0)
					fct
				}
				case text.startsWith(typeof(Erlang).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createErlang
					fct.expMean = attributeArray.get(0)
					fct.k = attributeArray.get(1)
					fct
				}
				case text.startsWith(typeof(Gamma).simpleName) && attributeArray.length == 2: {
					val fct = SimulationFactory.eINSTANCE.createGamma
					fct.beta = attributeArray.get(0)
					fct.alpha = attributeArray.get(1)
					fct
				}
				default: SimulationFactory.eINSTANCE.createBeta
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace
			SimulationFactory.eINSTANCE.createBeta
		} catch (Exception e) {
			e.printStackTrace
			SimulationFactory.eINSTANCE.createBeta
		}

	}

	/**
	 * Generates a cleared attribute-array only containing values, so that:
	 * 
	 * <function-name> ([<attribute-name1>" "<attribute-value1>,
	 * <attribute-name2>" "<attribute-value2>, <...> ]) becomes
	 * [<attribute-value1>, <attribute-value2>, ... , <attribute-valueN>]
	 * 
	 * @param key
	 *            The displayed function in form <function-name>
	 *            ([<attribute-name1>" "<attribute-value1>,
	 *            <attribute-name2>" "<attribute-value2>, <...> ])
	 * @return string array in form [<attribute-value1>, <attribute-value2>, ...
	 *         , <attribute-valueN>]
	 */
	private def Double[] generateAttributeValueArray(String key) {

		if (key == null) {
			return newArrayOfSize(0)
		}

		// Take only attributes in brackets
		val attributes = key.split("\\(");
		var cleanedAttributes = attributes.get(1)

		// Clear last bracket + white spaces
		cleanedAttributes = cleanedAttributes.replace(")", "").replace(" ", "");

		// Get attribute-value-pairs
		val attributeArray = cleanedAttributes.split("\\,");

		var retVal = newArrayList()

		for (s : attributeArray) {
			val b = Double.valueOf(s.trim);
			retVal.add(b)
		}

		return retVal;
	}
}
