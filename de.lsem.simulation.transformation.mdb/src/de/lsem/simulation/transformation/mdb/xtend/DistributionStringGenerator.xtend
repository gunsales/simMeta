package de.lsem.simulation.transformation.mdb.xtend

import de.lsem.repository.model.simulation.IBeta
import de.lsem.repository.model.simulation.IConstant
import de.lsem.repository.model.simulation.IErlang
import de.lsem.repository.model.simulation.IGamma
import de.lsem.repository.model.simulation.ILogNormal
import de.lsem.repository.model.simulation.INegExp
import de.lsem.repository.model.simulation.INormal
import de.lsem.repository.model.simulation.IPoisson
import de.lsem.repository.model.simulation.ITriangular
import de.lsem.repository.model.simulation.IUniform
import de.lsem.repository.model.simulation.IWeibull
import javax.inject.Singleton

@Singleton
class DistributionStringGenerator {

	def createWeibull(IWeibull it) '''
		Weib( «beta», «alpha» )
	'''

	def createBeta(IBeta it) '''
		BETA( «beta», «alpha» )
	'''

	def createNormal(INormal it) '''
		NORM( «mean», «stdDev» )
	'''

	def createTriangle(ITriangular it) '''
		TRIA( «min», «mode», «max» )
	'''

	def createLogN(ILogNormal it) '''
		LOGN( «logMean», «logStd» )
	'''

	def createUniform(IUniform it) '''
		UNIF( «min», «max» )
	'''

	def createPoisson(IPoisson it) '''
		POIS( «mean» )
	'''

	def createNegativeExpo(INegExp it) '''
		EXPO( «mean» )
	'''

	def createErlang(IErlang it) '''
		ERLA( «expMean», «k» )
	'''

	def createGamma(IGamma it) '''
		GAMM( «beta», «alpha» )
	'''

	def createConstant(IConstant it) '''
		«value»
	'''
}
