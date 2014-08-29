package de.lsem.simulation.property.xtend

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Erlang
import de.lsem.repository.model.simulation.Gamma
import de.lsem.repository.model.simulation.IBeta
import de.lsem.repository.model.simulation.IConstant
import de.lsem.repository.model.simulation.IDistribution
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

	private def className(IDistribution it) {
		class.simpleName
	}

	def dispatch String getDistributionFunctionFor(IWeibull it) '''
	«className» ( «beta», «alpha» )'''

	def dispatch String getDistributionFunctionFor(IBeta it) '''
	«className» ( «beta», «alpha» )'''

	def dispatch String getDistributionFunctionFor(INormal it) '''
	«className» ( «mean», «stdDev» )'''

	def dispatch String getDistributionFunctionFor(ITriangular it) '''
	«className» ( «min», «mode», «max» )'''

	def dispatch String getDistributionFunctionFor(IUniform it) '''
	«className»( «min», «max» )'''

	def dispatch String getDistributionFunctionFor(ILogNormal it) '''
	«className» ( «logMean», «logStd» )'''

	def dispatch String getDistributionFunctionFor(IPoisson it) '''
	«className» ( «mean» )'''

	def dispatch String getDistributionFunctionFor(INegExp it) '''
	«className» ( «mean» )'''

	def dispatch String getDistributionFunctionFor(IErlang it) '''
	«className» ( «expMean», «k» )'''

	def dispatch String getDistributionFunctionFor(IGamma it) '''
	«className» ( «beta», «alpha» )'''

	def dispatch String getDistributionFunctionFor(IConstant it) '''
	«Math.round(value).toString»'''

	def dispatch String getDistributionFunctionFor(Void it) ''''''

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
				default:
					SimulationFactory.eINSTANCE.createBeta
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
