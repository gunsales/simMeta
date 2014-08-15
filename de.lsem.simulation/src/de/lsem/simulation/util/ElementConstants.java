package de.lsem.simulation.util;




public interface ElementConstants {


	public static final int LABEL_WIDTH = 130;
	
	/*
	 * Types of service
	 */
//	public static final String PICKING 			= "Picking";
//	public static final String VALUE_ADDED 		= "Value added";
//	public static final String HANDLING 		= "Handling";
//	public static final String STORAGE 			= "Storage";
//	public static final String TRANSPORT 		= "Transport";
//	public static final String DEFAULT_SERVICE	= "Default";
	
	
	/*
	 * Types of time measurement
	 */
	public static final String TIME_UNIFORM_CONSTANT 		= "Uniform";
	public static final String TIME_CONSTANT_CONSTANT 		= "Constant";
	public static final String TIME_DISTRIBUTION_CONSTANT 	= "DistributionFunction";

	
	/*
	 *  Time
	 */
	public static final String TIME_SECOND 	= "SECOND";
	public static final String TIME_MINUTE 	= "MINUTE";
	public static final String TIME_HOUR 	= "HOUR";
	public static final String TIME_DAY 	= "DAY";
	
	/*
	 * Conditional Relation Label Constants
	 */
	public static final String LABEL_CONDITION 			= "Condition:";
	public static final String LABEL_PROBABILITY 		= "Probability:";
	public static final String LABEL_DESCRIPTION 		= "Description:";
	public static final String LABEL_BASED_ON 			= "Based on:";
	public static final String LABEL_TYPE 				= "Type:";
	
	/*
	 * Element Main Section
	 */
	public static final String LABEL_NAME 			= "Name:";
	public static final String LABEL_EMPTY 			= "";
	public static final String LABEL_MAX_ENTITIES 	= "Max. Entities:";
	
	/*
	 * Service Section Labels
	 */
	public static final String ORIGIN_LABEL_STRING 		= "Origin:";
	public static final String DESTINATION_LABEL_STRING = "Target:";
	public static final String START_DATE_LABEL_STRING 	= "Start time:";
	public static final String END_DATE_LABEL_STRING 	= "End time:";
	public static final String PERIOD_LABEL_STRING 		= "Period:";

	public static final String SERVICE_TYPE 	= "Service-type:";
	public static final String QUEUETYPE_STRING = "Queue-Strategy:";
	public static final String DAUER_LABEL 		= "Duration:";
	public static final String MENGE_LABEL 		= "Amount:";
	public static final String MAX_CAPACITY 	= "Max. Capacity:";
	public static final String MIN_MAX_STRING 	= "Min/Max:";

	public static final String DEVIATION_TYPE 		= "Deviation-type:";
	public static final String ZEITEINHEIT_CONSTANT = "Time-unit:";
	public static final String CONSTANT_CONSTANT 	= "Constant value:";

	public static final String[] DISTRIBUTION_TYPE = {
		TIME_UNIFORM_CONSTANT,
		TIME_CONSTANT_CONSTANT,
		TIME_DISTRIBUTION_CONSTANT
	};
	
	public static final String[] TIME_VALUES = {
		TIME_SECOND,
		TIME_MINUTE,
		TIME_HOUR,
		TIME_DAY
	};
}
