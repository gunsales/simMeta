package de.lsem.simulation.transformation.ed.elementHandler.helper

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Constant
import de.lsem.repository.model.simulation.Erlang
import de.lsem.repository.model.simulation.Gamma
import de.lsem.repository.model.simulation.IDistributionFunction
import de.lsem.repository.model.simulation.LogNormal
import de.lsem.repository.model.simulation.NegExp
import de.lsem.repository.model.simulation.Normal
import de.lsem.repository.model.simulation.Poisson
import de.lsem.repository.model.simulation.Triangular
import de.lsem.repository.model.simulation.Uniform
import de.lsem.repository.model.simulation.Weibull

/**
 * Class transforms business-object-related distribution-functions into readable output for enterprise-dynamics.
 */
class DistributionFunctionHelper {

	private def simpleName(Class<? extends IDistributionFunction> it) '''
		«simpleName»
	'''

	def dispatch cDist(Weibull it) '''
		«simpleName(class)»( «beta», «alpha» )
	'''

	def dispatch cDist(Beta it) '''
		«simpleName(class)»( «beta», «alpha», 0.0 )
	'''

	def dispatch cDist(Normal it) '''
		«simpleName(class)»( «mean», «stdDev» )
	'''

	def dispatch cDist(Triangular it) '''
		«simpleName(class)»( «min», «mode», «max» )
	'''

	def dispatch cDist(Uniform it) '''
		«simpleName(class)»( «min», «max» )
	'''

	def dispatch cDist(LogNormal it) '''
		«simpleName(class)»( «logMean», «logStd» )
	'''

	def dispatch cDist(Poisson it) '''
		«simpleName(class)»( «mean» )
	'''

	def dispatch cDist(NegExp it) '''
		«simpleName(class)»( «mean» )
	'''

	def dispatch cDist(Erlang it) '''
		«simpleName(class)»( «expMean», «k» )
	'''

	def dispatch cDist(Gamma it) '''
		«simpleName(class)»( «beta», «alpha» )
	'''

	def dispatch cDist(Constant it) '''
		«value»
	'''

	/**
	 * Default case / null case
	 */
	def dispatch cDist(Void it) ''''''

}
