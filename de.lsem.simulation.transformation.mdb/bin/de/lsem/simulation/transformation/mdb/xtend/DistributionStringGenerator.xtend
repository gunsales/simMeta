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

	def dispatch String getDistributionString(IWeibull it) '''
		Weib( «beta», «alpha» )
	'''

	def dispatch String getDistributionString(IBeta it) '''
		BETA( «beta», «alpha» )
	'''

	def dispatch String getDistributionString(INormal it) '''
		NORM( «mean», «stdDev» )
	'''

	def dispatch String getDistributionString(ITriangular it) '''
		TRIA( «min», «mode», «max» )
	'''

	def dispatch String getDistributionString(ILogNormal it) '''
		LOGN( «logMean», «logStd» )
	'''

	def dispatch String getDistributionString(IUniform it) '''
		UNIF( «min», «max» )
	'''

	def dispatch String getDistributionString(IPoisson it) '''
		POIS( «mean» )
	'''

	def dispatch String getDistributionString(INegExp it) '''
		EXPO( «mean» )
	'''

	def dispatch String getDistributionString(IErlang it) '''
		ERLA( «expMean», «k» )
	'''

	def dispatch String getDistributionString(IGamma it) '''
		GAMM( «beta», «alpha» )
	'''

	def dispatch String getDistributionString(IConstant it) '''
		«value»
	'''
	
	def dispatch String getDistributionString(Void it) ''''''
}
