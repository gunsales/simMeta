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
import de.lsem.repository.model.simulation.Triangular
import de.lsem.repository.model.simulation.Uniform
import de.lsem.repository.model.simulation.Weibull

import static de.lsem.repository.model.simulation.SimulationFactory.*

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

	def getDistributionFunctionForLSEMElement(String text){
		if(text == null){
			return null
		}
		
		val attributeArray = generateAttributeValueArray(text)
		
		switch(text){
			case text.startsWith(typeof(Weibull).simpleName) && attributeArray.length == 2:{
				sim.createWeibull => [
					beta = attributeArray.get(0)
					alpha = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Beta).simpleName) && attributeArray.length == 2:{
				sim.createBeta => [
					beta = attributeArray.get(0)
					alpha = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Normal).simpleName) && attributeArray.length == 2:{
				sim.createNormal => [
					mean = attributeArray.get(0)
					stdDev = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Triangular).simpleName) && attributeArray.length == 3:{
				return sim.createTriangular => [
					min = attributeArray.get(0)
					mode = attributeArray.get(1)
					max = attributeArray.get(2)
				]
			}
			case text.startsWith(typeof(LogNormal).simpleName) && attributeArray.length == 2:{
				sim.createLogNormal => [
					logMean = attributeArray.get(0)
					logStd = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Uniform).simpleName) && attributeArray.length == 2:{
				sim.createUniform => [
					min = attributeArray.get(0)
					max = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Poisson).simpleName) && attributeArray.length == 1:{
				return sim.createPoisson => [
					mean = attributeArray.get(0)
				]
			}
			case text.startsWith(typeof(NegExp).simpleName) && attributeArray.length == 1:{
				sim.createNegExp => [
					mean = attributeArray.get(0)
				]
			}
			case text.startsWith(typeof(Erlang).simpleName) && attributeArray.length == 2:{
				sim.createErlang => [
					expMean = attributeArray.get(0)
					k = attributeArray.get(1)
				]
			}
			case text.startsWith(typeof(Gamma).simpleName) && attributeArray.length == 2:{
				sim.createGamma => [
					beta = attributeArray.get(0)
					alpha = attributeArray.get(1)
				]
			}
			default: return null
		}
	}
	
	private def getSim(){
		eINSTANCE
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
