package de.lsem.simulation.transformation.ed.elementHandler.helper

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
import de.lsem.repository.model.simulation.UnitOfTime

/**
 * Class transforms business-object-related distribution-functions into readable output for enterprise-dynamics.
 */
class DistributionFunctionHelper {

	private def wrapTime(UnitOfTime it){
		switch(value){
			case UnitOfTime.DAY_VALUE:"DaysInYear"
			case UnitOfTime.HOUR_VALUE:"hr"
			case UnitOfTime.MINUTE_VALUE:"Mins"
			default:"" //Seconds case, default --> no transformation or value needed
		}
	}

	def dispatch cDist(IWeibull it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«beta»), «unit.wrapTime»(«alpha») )
	'''

	def dispatch cDist(IBeta it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«beta»), «unit.wrapTime»(«alpha»), 0.1 )
	'''

	def dispatch cDist(INormal it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«mean»), «unit.wrapTime»(«stdDev») )
	'''

	def dispatch cDist(ITriangular it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«min»), «unit.wrapTime»(«mode»), «unit.wrapTime»(«max») )
	'''

	def dispatch cDist(IUniform it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«min»), «unit.wrapTime»(«max») )
	'''

	def dispatch cDist(ILogNormal it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«logMean»), «unit.wrapTime»(«logStd») )
	'''

	def dispatch cDist(IPoisson it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«mean») )
	'''

	def dispatch cDist(INegExp it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«mean») )
	'''

	def dispatch cDist(IErlang it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«expMean»), «unit.wrapTime»(«k») )
	'''

	def dispatch cDist(IGamma it, UnitOfTime unit) '''
		«class.simpleName»( «unit.wrapTime»(«beta»), «unit.wrapTime»(«alpha») )
	'''

	def dispatch cDist(IConstant it, UnitOfTime unit) '''
		«unit.wrapTime»(«value»)
	'''

	/**
	 * Default case / null case
	 */
	def dispatch cDist(Void it, UnitOfTime unit) ''''''

}
