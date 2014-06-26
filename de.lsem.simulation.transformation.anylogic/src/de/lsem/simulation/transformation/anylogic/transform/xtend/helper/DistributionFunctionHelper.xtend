package de.lsem.simulation.transformation.anylogic.transform.xtend.helper

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Constant
import de.lsem.repository.model.simulation.Erlang
import de.lsem.repository.model.simulation.Gamma
import de.lsem.repository.model.simulation.IDistribution
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
			
	private def dispatch cDist(Weibull it) {
		className + "(" + beta + ", " + alpha + ")"
	}
	
	private def dispatch cDist(Beta it) {
		className + "(" + beta + ", " + alpha + ")"
	}
	
	private def dispatch cDist(Normal it) {
		className + "(" + mean + ", " + stdDev + ")"
	}
	
	private def dispatch cDist(Triangular it) {
		className + "(" + min + ", " + mode + ", " + max + ")"
	}
	
	private def dispatch cDist(Uniform it) {
		className + "(" + min + ", " + max + ")"
	}
	
	private def dispatch cDist(LogNormal it) {
		className + "(" + logMean + ", " + logStd + ")"
	}
	
	private def dispatch cDist(Poisson it) {
		className + "(" + mean +")"
	}
	
	private def dispatch cDist(NegExp it) {
		className + "(" + mean +" )"
	}
	
	private def dispatch cDist(Erlang it) {
		className + "(" + expMean + ", " + k + ")"
	}
	
	private def dispatch cDist(Gamma it) {
		className + "(" + beta + ", " + alpha + ")"
	}
	
	private def dispatch cDist(Constant it) {
		Math.round(value).toString
//		value.toString
	}
	
	def getDistributionFunctionFor(IDistribution it){

		switch it {
			Weibull		: 	cDist
			Beta		: 	cDist			
			Normal		: 	cDist
			Triangular 	: 	cDist
			Uniform 	: 	cDist
			LogNormal	:	cDist
			Poisson		:	cDist
			NegExp		:	cDist
			Erlang		:	cDist
			Gamma		:	cDist
			Constant	:	cDist
		}	
	}

/**
 * Testcase
 */
	def static void main(String[] args) {
		val dfh = new DistributionFunctionHelper
		
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