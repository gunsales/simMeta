package de.lsem.simulation.util

interface ElementConstants {

	static val LABEL_WIDTH = 130

	/*
	 * Types of service - moved to ServiceType-Enum
	 */
	//	static val PICKING 			= "Picking"
	//	static val VALUE_ADDED 		= "Value added"
	//	static val HANDLING 		= "Handling"
	//	static val STORAGE 			= "Storage"
	//	static val TRANSPORT 		= "Transport"
	//	static val DEFAULT_SERVICE	= "Default"
	/*
	 * Types of time measurement
	 */
	static val TIME_UNIFORM_CONSTANT = "Uniform"
	static val TIME_CONSTANT_CONSTANT = "Constant"
	static val TIME_DISTRIBUTION_CONSTANT = "DistributionFunction"

	/*
	 *  Time
	 */
	static val TIME_SECOND = "SECOND"
	static val TIME_MINUTE = "MINUTE"
	static val TIME_HOUR = "HOUR"
	static val TIME_DAY = "DAY"

	/*
	 * Conditional Relation Label Constants
	 */
	static val LABEL_CONDITION = "Condition:"
	static val LABEL_PROBABILITY = "Probability:"
	static val LABEL_DESCRIPTION = "Description:"
	static val LABEL_BASED_ON = "Based on:"
	static val LABEL_TYPE = "Type:"

	/*
	 * Element Main Section
	 */
	static val LABEL_NAME = "Name:"
	static val LABEL_EMPTY = ""
	static val LABEL_MAX_ENTITIES = "Max. Entities:"

	/*
	 * Service Section Labels
	 */
	static val ORIGIN_LABEL_STRING = "Origin:"
	static val DESTINATION_LABEL_STRING = "Target:"
	static val START_DATE_LABEL_STRING = "Start time:"
	static val END_DATE_LABEL_STRING = "End time:"
	static val PERIOD_LABEL_STRING = "Period:"

	static val SERVICE_TYPE = "Service-type:"
	static val QUEUETYPE_STRING = "Queue-Strategy:"
	static val DAUER_LABEL = "Duration:"
	static val MENGE_LABEL = "Amount:"
	static val MAX_CAPACITY = "Max. Capacity:"
	static val MIN_MAX_STRING = "Min/Max:"

	static val DEVIATION_TYPE = "Deviation-type:"
	static val ZEITEINHEIT_CONSTANT = "Time-unit:"
	static val CONSTANT_CONSTANT = "Constant value:"

	static val DISTRIBUTION_TYPE = #[
		TIME_UNIFORM_CONSTANT,
		TIME_CONSTANT_CONSTANT,
		TIME_DISTRIBUTION_CONSTANT
	]

	static val TIME_VALUES = #[
		TIME_SECOND,
		TIME_MINUTE,
		TIME_HOUR,
		TIME_DAY
	]
}
