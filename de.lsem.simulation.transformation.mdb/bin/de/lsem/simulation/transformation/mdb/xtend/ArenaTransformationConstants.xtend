package de.lsem.simulation.transformation.mdb.xtend

interface ArenaTransformationConstants {
	
	val GRAPHICAL_STRETCH_FACTOR = 8
	
	val PATH_TO_MDB_TEMPLATE = "/template/template_final.mdb"
	val DIAGRAM_EXTENSION_CONSTANT = ".diagram"

	// ############## ARENA Specific #############
	//Meta-Data const
	val AUTHOR = "Mutke"

	//Referencing elements
	val MDB_EXTENSION_CONSTANT = ".mdb"
	val MDB_TEMPLATE_FILE_CONSTANT = "template_final.mdb"

	//Values
	val VALUE_NWITH = "NWith"
	val VALUE_WITH = "With"
	val VALUE_IF = "If"
	val VALUE_NIF = "NIf"
	val VALUE_LABEL = "Label"
	val VALUE_ENTITY_TYPE = "Entity Type"
	val VALUE_EXPRESSION = "Expression"
	val VALUE_N_NEXT_LABEL_YES = "N Next Label Yes"
	val VALUE_NEXT_LABEL = "Next Label"
	val VALUE_NEXT_LABEL_YES = "Next Label Yes"
	val VALUE_NEXT_LABEL_NO = "Next Label No"

	val SERVICE_TYPE_ARENA_TRANSPORT = "Tran"
	val SERVICE_TYPE_ARENA_PICKING = "Other"
	val SERVICE_TYPE_ARENA_HANDLING = "Other"
	val SERVICE_TYPE_ARENA_STORAGE = "Wait"
	val SERVICE_TYPE_ARENA_VALUE_ADDED = "VA"

	//Database specific const
	val T_BP = "BasicProcess"
	val T_BP_CREATE = "BasicProcess|Create"
	val T_BP_DISPOSE = "BasicProcess|Dispose"
	val T_BP_DECIDE = "BasicProcess|Decide"
	val T_BP_DECIDE_COND = T_BP_DECIDE + "|Conditions"
	val T_BP_ENTITY = "BasicProcess|Entity"
	val T_BP_PROCESS = "BasicProcess|Process"
	val T_BP_PROCESS_RES = T_BP_PROCESS + "|Resources"
	val T_BP_RESOURCE = "BasicProcess|Resource"

	val T_LIBRARY = "Library"
	val T_CONNECTIONS = "Connections"
	val T_MODEL_LEVELS = "ModelLevels"
	val T_MODULE_TABLES = "ModuleTables"
	val T_PROJECT_PARAMETERS = "ProjectParameters"
	val T_REPL_PARA = "ReplicationParameters"
	val T_REPORTS = "REPORTS"
	val T_REP_GROUP_TABLES = "RepeatGroupTables"

	//ARENA Time Values
	val ARENA_TIME_HOUR = "Hours"
	val ARENA_TIME_MINUTE = "Minutes"
	val ARENA_TIME_DAY = "Days"
	val ARENA_TIME_SECOND = "Seconds"

	//ARENA Distribution-Function-Type-Values
	//	val ARENA_FUNCTION_NORMAL 		= "Normal"
	val ARENA_FUNCTION_CONSTANT = "Constant"

	//	val ARENA_FUNCTION_TRIANGULAR 	= "Triangular"
	//	val ARENA_FUNCTION_UNIFORM 		= "Uniform"
	val ARENA_FUNCTION_EXPRESSION = "Expression"

}
