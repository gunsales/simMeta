package de.lsem.simulation.transformation.mdb.xtend;

import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public interface ArenaTransformationConstants {
  public final static int GRAPHICAL_STRETCH_FACTOR = 8;
  
  public final static String PATH_TO_MDB_TEMPLATE = "/template/template_final.mdb";
  
  public final static String DIAGRAM_EXTENSION_CONSTANT = ".diagram";
  
  public final static String AUTHOR = "Mutke";
  
  public final static String MDB_EXTENSION_CONSTANT = ".mdb";
  
  public final static String MDB_TEMPLATE_FILE_CONSTANT = "template_final.mdb";
  
  public final static String VALUE_NWITH = "NWith";
  
  public final static String VALUE_WITH = "With";
  
  public final static String VALUE_IF = "If";
  
  public final static String VALUE_NIF = "NIf";
  
  public final static String VALUE_LABEL = "Label";
  
  public final static String VALUE_ENTITY_TYPE = "Entity Type";
  
  public final static String VALUE_EXPRESSION = "Expression";
  
  public final static String VALUE_N_NEXT_LABEL_YES = "N Next Label Yes";
  
  public final static String VALUE_NEXT_LABEL = "Next Label";
  
  public final static String VALUE_NEXT_LABEL_YES = "Next Label Yes";
  
  public final static String VALUE_NEXT_LABEL_NO = "Next Label No";
  
  public final static String SERVICE_TYPE_ARENA_TRANSPORT = "Tran";
  
  public final static String SERVICE_TYPE_ARENA_PICKING = "Other";
  
  public final static String SERVICE_TYPE_ARENA_HANDLING = "Other";
  
  public final static String SERVICE_TYPE_ARENA_STORAGE = "Wait";
  
  public final static String SERVICE_TYPE_ARENA_VALUE_ADDED = "VA";
  
  public final static String T_BP = "BasicProcess";
  
  public final static String T_BP_CREATE = "BasicProcess|Create";
  
  public final static String T_BP_DISPOSE = "BasicProcess|Dispose";
  
  public final static String T_BP_DECIDE = "BasicProcess|Decide";
  
  public final static String T_BP_DECIDE_COND = new Function0<String>() {
    public String apply() {
      String _plus = (ArenaTransformationConstants.T_BP_DECIDE + "|Conditions");
      return _plus;
    }
  }.apply();
  
  public final static String T_BP_ENTITY = "BasicProcess|Entity";
  
  public final static String T_BP_PROCESS = "BasicProcess|Process";
  
  public final static String T_BP_PROCESS_RES = new Function0<String>() {
    public String apply() {
      String _plus = (ArenaTransformationConstants.T_BP_PROCESS + "|Resources");
      return _plus;
    }
  }.apply();
  
  public final static String T_BP_RESOURCE = "BasicProcess|Resource";
  
  public final static String T_LIBRARY = "Library";
  
  public final static String T_CONNECTIONS = "Connections";
  
  public final static String T_MODEL_LEVELS = "ModelLevels";
  
  public final static String T_MODULE_TABLES = "ModuleTables";
  
  public final static String T_PROJECT_PARAMETERS = "ProjectParameters";
  
  public final static String T_REPL_PARA = "ReplicationParameters";
  
  public final static String T_REPORTS = "REPORTS";
  
  public final static String T_REP_GROUP_TABLES = "RepeatGroupTables";
  
  public final static String ARENA_TIME_HOUR = "Hours";
  
  public final static String ARENA_TIME_MINUTE = "Minutes";
  
  public final static String ARENA_TIME_DAY = "Days";
  
  public final static String ARENA_TIME_SECOND = "Seconds";
  
  public final static String ARENA_FUNCTION_CONSTANT = "Constant";
  
  public final static String ARENA_FUNCTION_EXPRESSION = "Expression";
}
