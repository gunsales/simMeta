package de.lsem.simulation.util

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

			val it = generateAttributeValueArray(text);

			switch length {
				case 1: {
					return oneAttributeCases(text)
				}
				case 2: {
					return twoAttributeCases(text)
				}
				case 3: {
					return threeAttributeCases(text)
				}
				default: {
					createDefaultBeta
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace
		} catch (Exception e) {
			e.printStackTrace
		}
		createDefaultBeta

	}

	private def threeAttributeCases(Double[] aa, String text) {
		switch text {
			case text.startsWith(typeof(Triangular).simpleName): {
				SimulationFactory.eINSTANCE.createTriangular => [
					min = aa.get(0)
					mode = aa.get(1)
					max = aa.get(2)
				]
			}
			default:
				createDefaultBeta
		}
	}

	private def twoAttributeCases(Double[] aa, String it) {

		switch it {
			case startsWith(typeof(Weibull).simpleName): {
				SimulationFactory.eINSTANCE.createWeibull => [
					beta = aa.get(0)
					alpha = aa.get(1)
				]
			}
			case startsWith(typeof(Beta).simpleName): {
				SimulationFactory.eINSTANCE.createBeta => [
					beta = aa.get(0)
					alpha = aa.get(1)
				]
			}
			case startsWith(typeof(Normal).simpleName): {
				SimulationFactory.eINSTANCE.createNormal => [
					mean = aa.get(0)
					stdDev = aa.get(1)
				]
			}
			case startsWith(typeof(LogNormal).simpleName): {
				SimulationFactory.eINSTANCE.createLogNormal => [
					logMean = aa.get(0)
					logStd = aa.get(1)
				]
			}
			case startsWith(typeof(Uniform).simpleName): {
				SimulationFactory.eINSTANCE.createUniform => [
					min = aa.get(0)
					max = aa.get(1)
				]
			}
			case startsWith(typeof(Erlang).simpleName): {
				SimulationFactory.eINSTANCE.createErlang => [
					expMean = aa.get(0)
					k = aa.get(1)
				]
			}
			case startsWith(typeof(Gamma).simpleName): {
				SimulationFactory.eINSTANCE.createGamma => [
					beta = aa.get(0)
					alpha = aa.get(1)
				]
			}
			default: {
				createDefaultBeta
			}
		}
	}

	private def oneAttributeCases(Double[] aa, String it) {
		switch it {
			case startsWith(typeof(Poisson).simpleName): {
				SimulationFactory.eINSTANCE.createPoisson => [
					mean = aa.get(0)
				]
			}
			case startsWith(typeof(NegExp).simpleName): {
				SimulationFactory.eINSTANCE.createNegExp => [
					mean = aa.get(0)
				]
			}
			default: createDefaultBeta
		}
	}

	private def createDefaultBeta() {
		SimulationFactory.eINSTANCE.createBeta => [
			beta = 0
			alpha = 0
		]
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
