package de.lsem.simulation.transformation.ed.elementHandler.helper

import de.lsem.repository.model.simulation.Beta
import de.lsem.repository.model.simulation.Erlang
import de.lsem.repository.model.simulation.Gamma
import de.lsem.repository.model.simulation.LogNormal
import de.lsem.repository.model.simulation.NegExp
import de.lsem.repository.model.simulation.Normal
import de.lsem.repository.model.simulation.Poisson
import de.lsem.repository.model.simulation.Triangular
import de.lsem.repository.model.simulation.Uniform
import de.lsem.repository.model.simulation.Weibull

class DistributionFunctionHelper {
		
	def dispatch cDist(Weibull it) {
		class.simpleName + "( " + beta + ", " + alpha + " )"
	}
	
	def dispatch cDist(Beta it) {
		class.simpleName + "( " + beta + ", " + alpha + ", 0.0 )"
	}
	
	def dispatch cDist(Normal it) {
		class.simpleName + "( " + mean + ", " + stdDev + " )"
	}
	
	def dispatch cDist(Triangular it) {
		class.simpleName + "( " + min + ", " + mode + ", " + max + " )"
	}
	
	def dispatch cDist(Uniform it) {
		class.simpleName + "( " + min + ", " + max + " )"
	}
	
	def dispatch cDist(LogNormal it) {
		class.simpleName + "( " + logMean + ", " + logStd + " )"
	}
	
	def dispatch cDist(Poisson it) {
		class.simpleName + "( " + mean +" )"
	}
	
	def dispatch cDist(NegExp it) {
		class.simpleName + "( " + mean +" )"
	}
	
	def dispatch cDist(Erlang it) {
		class.simpleName + "( " + expMean + ", " + k + " )"
	}
	
	def dispatch cDist(Gamma it) {
		class.simpleName + "( " + beta + ", " + alpha + " )"
	}
	
	def dispatch cDist(Void it) {
		''
//		class.simpleName + "( " + beta + ", " + alpha + " )"
	}
	
//	def getDistributionFunctionFor(IDistributionFunction it){
//
//		switch it {
//			Weibull		: 	cDist
//			Beta		: 	cDist			
//			Normal		: 	cDist
//			Triangular 	: 	cDist
//			Uniform 	: 	cDist
//			LogNormal	:	cDist
//			Poisson		:	cDist
//			NegExp		:	cDist
//			Erlang		:	cDist
//			Gamma		:	cDist
//		}	
//	}
	}