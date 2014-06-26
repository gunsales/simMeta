package de.lsem.simulation.transformation.ed.xtext.helper;


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
	public static final String TIME_SECOND = "SECOND";
	public static final String TIME_MINUTE = "MINUTE";
	public static final String TIME_HOUR = "HOUR";
	public static final String TIME_DAY = "DAY";
	
	/*
	 * Conditional Relation Label Constants
	 */
	public static final String LABEL_BEDINGUNG = "Bedingung";
	public static final String LABEL_WAHRSCHEINLICHKEIT = "Wahrscheinlichkeit";
	public static final String LABEL_BEZEICHNUNG = "Bezeichnung";
	public static final String LABEL_GRUNDLAGE = "Grundlage";
//	public static final String LABEL_TYPE_MEAN_DEVIATION = "Type/Mean/Deviation:";
	
	/*
	 * Element Main Section
	 */
	public static final String LABEL_NAME = "Name:";
	public static final String LABEL_EMPTY = "";
	
	/*
	 * Service Section Labels
	 */
	public static final String ORIGIN_LABEL_STRING 		= "Herkunft:";
	public static final String DESTINATION_LABEL_STRING = "Ziel:";
	public static final String START_DATE_LABEL_STRING 	= "Startzeit:";
	public static final String END_DATE_LABEL_STRING 	= "Endzeit:";
	public static final String PERIOD_LABEL_STRING 		= "Periode:";

	public static final String SERVICE_TYPE 	= "Servicetyp:";
	public static final String QUEUETYPE_STRING = "Queue-Strategie:";
	public static final String DAUER_LABEL 		= "Dauer:";
	public static final String MENGE_LABEL 		= "Menge:";
	public static final String MAX_KAPAZITAET 	= "Max. Kapazität:";
	public static final String MIN_MAX_STRING 	= "Min/Max:";

	public static final String DEVIATION_TYPE 		= "Verteilungstyp:";
	public static final String ZEITEINHEIT_CONSTANT = "Zeiteinheit:";
	public static final String CONSTANT_CONSTANT 	= "Constant:";

//	public static final String[] FUNCTION_TYPE_ARRAY = {
//		PICKING, 
//		VALUE_ADDED, 
//		HANDLING, 
//		STORAGE, 
//		TRANSPORT,
//		DEFAULT_SERVICE};

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
