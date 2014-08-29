package de.lsem.simulation.transformation.mdb.xtend

import com.healthmarketscience.jackcess.Table
import de.lsem.repository.model.simulation.Constant
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.IConstant
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.repository.model.simulation.ServiceType
import java.io.IOException
import javax.inject.Inject

class ElementCreator implements ArenaTransformationConstants {

	@Inject
	extension ArenaTransformationHelper
	@Inject
	extension DistributionStringGenerator

	/**
	 * 
	 * @param e
	 * @return generated ARENA-id
	 * @throws IOException
	 */
	def dispatch int createElement(IActivity it) throws IOException {
		var int idDummy = generateID()
		var Table table = saveFile.getTable(T_BP_PROCESS)

		var String timeUnitArena = ARENA_TIME_HOUR
		var String delayTypeFunction = ARENA_FUNCTION_EXPRESSION
		var String functionType = "1"

		// TODO What value is "value"?
		var String value = "1"
		var String max = "1.5"
		var String min = "0.5"
		var String stdDev = "0.2"
		var String action = "D"

		if (serviceType != null) {
			if (serviceType.equals(ServiceType.TRANSPORT)) {

				// Time + TimePeriod
				if (timePeriod != null) {
					timeUnitArena = mapTimeUnit(timePeriod.unit)

					if (timePeriod.period != null) {
						delayTypeFunction = ARENA_FUNCTION_EXPRESSION
						functionType = getDistributionString(timePeriod.period)

					// value = timePeriod.getClass()
					}
				}
			} else if (serviceType.equals(ServiceType.STORAGE)) {
				action = "SDR"
				createResource
				createResourceFailure
				createProcessResource(idDummy)
				functionType = "0"
			}
		}

		// TODO TEST - Generate DistributionFunction Attribute
		// in case of constant isEmpty and distributionFunction is selected
		if (timePeriod.period != null && timePeriod.period instanceof IConstant) {
			delayTypeFunction = ARENA_FUNCTION_CONSTANT
			value = String.valueOf((timePeriod.period as Constant).value)
		}

		var int xCoorInt = x.stretch
		var int yCoorInt = y.stretch

		var String arenaServiceType = toARENAServiceType(serviceType)

		// NON-VALUE-ADDED of ARENA does not exist @ simMeta
		table.addRow(idDummy, 1, xCoorInt, yCoorInt, name, name + "_" + generateID(), "Yes", "Standard", action,
			arenaServiceType, delayTypeFunction, timeUnitArena, 2, functionType, stdDev, max, min, value)

		table = saveFile.getTable(T_MODEL_LEVELS)
		table.addRow(table.getRowCount() + 1, "Top." + idDummy)

		//logger.log(Level.INFO, "Process with id " + idDummy + " and name " + name + " generated.")
		return idDummy
	}

	// SerialNumber ModelLevelID X Y UserDescription Name Type Percent True
	// If Value ANamed TypeNamed Column VNamed Row Is
	def int createDecide(IConditionalRelation it) {
		
		var type = VALUE_WITH

		// ############# Differentiate type between 2-Way / n-Way #############
		// type is based on chance if condition is empty
		type = setConnectionNWayType(source.outgoing.filter(typeof(IConditionalRelation)).toList)

		// --------- Setting up source ----------
		var xCoorSource = source.getX.stretch
		var yCoorSource = source.getY.stretch

		// --------- Setting up target ----------
		var xCoorTarget = target.getX.stretch
		var yCoorTarget = target.getY.stretch

		// --------- Setting up final positions ----------
		var int xCoor = (xCoorSource + xCoorTarget) / 2
		var int yCoor = (yCoorSource + yCoorTarget) / 2
		var decideTable = saveFile.getTable(T_BP_DECIDE)
		val idDummy = generateID

		decideTable.addRow(idDummy, 1, xCoor, yCoor, "", name + "_" + idDummy, type, probability, "Expression",
			condition, "Attribute 1", "Entity 1", 1, "Variable 1", 1, ">=")

		idDummy

	}

	def dispatch int createElement(ISource e) throws IOException {
		val idDummy = generateID
		val table = saveFile.getTable(T_BP_CREATE)

		val firstEntity = e.firstEntity
		val newEntities = e.newEntities
		val good = e.processedObject
		val maxBatches = e.maxNewEntities

		var firstCreation = "0.0"
		var value = "1"
		var followingEntities = ""
		var timeUnitArena = ARENA_TIME_HOUR
		var interarrival = ARENA_FUNCTION_EXPRESSION

		if (newEntities != null) {

			timeUnitArena = mapTimeUnit(newEntities.unit)
			val timePeriod = newEntities.period

			if (timePeriod != null) {
				followingEntities = getDistributionString(timePeriod)

				// Switch between constant and distribution function @
				// delayFunctionType
				if (followingEntities.contains("(")) {
					interarrival = ARENA_FUNCTION_EXPRESSION
				} else {
					interarrival = ARENA_FUNCTION_CONSTANT
					value = followingEntities
				}
			}
		}

		// Take only the "First creation"-Value of firstEntity
		if (firstEntity != null) {
			var timePeriod = firstEntity.period
			if (timePeriod != null) {
				firstCreation = getDistributionString(timePeriod)
			}
		}
		var objectType = "Entity 1"

		// String objectName = ""
		// String objectDescription = ""
		if (good != null) {
			objectType = good.type

		// objectName = e.getAttribute(ATTRIBUTE_NAME)
		// objectDescription = e.getAttribute(ATTRIBUTE_DESCRIPTION)
		}

		val xCoorInt = stretch(getX(e))
		val yCoorInt = stretch(getY(e))

		table.addRow(
				// Source ID and model level
		idDummy, 1, xCoorInt, yCoorInt,
				// user-description
		e.name,
				// name
		e.name,
			// Max-Batches//Interarrival//Schedule//Expression//Value//First
			// creation//Units//Batch Size//Entity Type
			maxBatches, interarrival, 0, followingEntities, value, firstCreation,// "infinite",
			timeUnitArena, 1, objectType)

		//logger.log(Level.INFO, "Create with id " + idDummy + " and name " + e.name + " generated.")
		idDummy
	}

	def int createResourceFailure(IActivity resource) throws IOException {
		return 0
	}

	def dispatch int createElement(ISink e) throws IOException {
		var int idDummy = generateID()
		var Table table = saveFile.getTable(T_BP_DISPOSE)
		var xCoor = getX(e)
		var yCoor = getY(e)

		var int xCoorInt = stretch(xCoor)
		var int yCoorInt = stretch(yCoor)
		table.addRow(idDummy, 1, xCoorInt, yCoorInt, "", e.name + "_" + generateID(),
				// EntStats
		"Yes")

		//logger.log(Level.INFO, "Dispose with id " + idDummy + " and name " + e.name + " generated.")
		return idDummy
	}

	// Table structure
	// ModuleSerialNumber Resources|Index Resource Type Selection Rule Save
	// Attribute Input Attribute Set Name Resource Name Quantity
	// ModuleSerialNumber equals referenced process-id
	def int createProcessResource(IActivity process, int processID) throws IOException {

		// int idDummy = generateID() NOT NEEDED, AS THE ID IS TAKEN FROM
		// PROCESS
		var Table table = saveFile.getTable(T_BP_PROCESS_RES)

		// Init defaults
		var moduleSerialNumber = ""
		var resourcesIndex = "1"
		var resourceType = "Resource"
		var selectionRule = "CYC"
		var saveAttribute = "" // Empty
		var inputAttribute = "1"
		var setName = "Set 1"
		var resourceName = "Resource 1"
		var quantity = "1"

		// Set up values
		moduleSerialNumber = String.valueOf(processID)

		// Add to table
		table.addRow(moduleSerialNumber, resourcesIndex, resourceType, selectionRule, saveAttribute, inputAttribute,
			setName, resourceName, quantity)

		//logger.log(Level.INFO, "Resource created for process with id: " + moduleSerialNumber)
		return processID //Integer.parseInt(process.getAttribute(ATTRIBUTE_COUNTER))
	}

	// SerialNumber ModelLevelID X Y UserDescription Name ReportStatistics Usage
	// Busy Type Idle ScheduleRule Schedule Capacity StateSetN InitState FDM
	// Name FDM Id Arena Imported Name Base Efficiency Efficiency Schedule
	// 84 1 0 0 Resource 1 Yes 0.0 0.0 Capacity 0.0 Wait 1 1 0 1.0
	def int createResource(IActivity process) throws IOException {
		var Table table = saveFile.getTable(T_BP_RESOURCE)

		// Init defaults
		var serialNumber = 0
		var modelLevelID = "1"
		var x = 0
		var y = 0
		var userDescription = ""
		var name = "Resource 1"
		var reportStatistics = "Yes"
		var usage = 0.0
		var busy = 0.0
		var type = "Capacity"
		var idle = 0.0
		var scheduleRule = "Wait"
		var schedule = 1
		var capacity = 1
		var stateSetN = ""
		var initState = ""
		var fdmName = ""
		var fdmID = 0
		var arenaImportName = ""
		var baseEfficiency = 0.0
		var efficiencySchedule = ""

		// Set dynamic values
		serialNumber = generateID()

		// add to table
		table.addRow(serialNumber, modelLevelID, x, y, userDescription, name, reportStatistics, usage, busy, type, idle,
			scheduleRule, schedule, capacity, stateSetN, initState, fdmName, fdmID, arenaImportName, baseEfficiency,
			efficiencySchedule)

		//logger.log(Level.INFO, "Resource with id: " + serialNumber + " created.")
		return serialNumber
	}

}
