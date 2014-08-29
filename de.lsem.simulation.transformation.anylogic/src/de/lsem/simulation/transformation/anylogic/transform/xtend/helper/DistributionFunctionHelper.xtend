package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Constant
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

class DistributionFunctionHelper {
		
	private def className(IDistribution it){
		class.simpleName.toLowerCase
	}
			
	def dispatch String getDistributionFunctionFor(IWeibull it)'''
		«className»(«beta», «alpha»)'''
		 
	def dispatch String getDistributionFunctionFor(IBeta it) '''
		«className»(«beta», «alpha»)'''
		
	def dispatch String getDistributionFunctionFor(INormal it) '''
		«className»(«mean», «stdDev»)'''
	
	def dispatch String getDistributionFunctionFor(ITriangular it) '''
		«className»(«min», «mode», «max»)'''

	def dispatch String getDistributionFunctionFor(IUniform it) '''
		«className»(«min», «max»)'''
	
	def dispatch String getDistributionFunctionFor(ILogNormal it) '''
		«className»(«logMean», «logStd»)'''

	def dispatch String getDistributionFunctionFor(IPoisson it) '''
		«className»(«mean»)'''
	
	def dispatch String getDistributionFunctionFor(INegExp it) '''
		«className»(«mean»)'''
	
	def dispatch String getDistributionFunctionFor(IErlang it) '''
		«className»(«expMean», «k»)'''
	
	def dispatch String getDistributionFunctionFor(IGamma it) '''
		«className»(«beta», «alpha»)'''
	
	def dispatch String getDistributionFunctionFor(IConstant it) '''
		«Math.round(value).toString»'''
	
	def dispatch String getDistributionFunctionFor(Void it)'''
	'''

/**
 * Testcase
 */
	def static void main(String[] args) {
		
		val test1 = new Beta
		test1.setAlpha(0.1)
		test1.setBeta(0.2)
		
		val test2 = new Constant
		test2.setValue(0.3)
		
		val test3 = new Erlang
		test3.setExpMean(0.4)
		test3.setK(0.5)
		
		val test4 = new Gamma
		test4.setAlpha(0.6)
		test4.setBeta(0.7)
		
		val test5 = new LogNormal
		test5.setLogMean(0.8)
		test5.setLogStd(0.9)
			
		val test7 = new NegExp
		test7.setMean(1.0)
		
		val test8 = new Normal
		test8.setMean(1.1)
		test8.setStdDev(1.2)
		
		val test9 = new Poisson
		test9.setMean(1.3)
		
		val test10 = new Triangular
		test10.setMax(1.4)
		test10.setMin(1.5)
		test10.setMode(1.6)
		
		val test11 = new Uniform
		test11.setMax(1.7)
		test11.setMin(1.8)
		
		val test12 = new Weibull
		test12.setAlpha(1.9)
		test12.setBeta(2.0)
		
		
		val dfh = new DistributionFunctionHelper
		println(dfh.getDistributionFunctionFor(test1))
		println(dfh.getDistributionFunctionFor(test2))
		println(dfh.getDistributionFunctionFor(test3))
		println(dfh.getDistributionFunctionFor(test4))
		println(dfh.getDistributionFunctionFor(test5))
		println(dfh.getDistributionFunctionFor(test12))
		println(dfh.getDistributionFunctionFor(test7))
		println(dfh.getDistributionFunctionFor(test8))
		println(dfh.getDistributionFunctionFor(test9))
		println(dfh.getDistributionFunctionFor(test10))
		println(dfh.getDistributionFunctionFor(test11))
		println(dfh.getDistributionFunctionFor(test12))
	}
		
}