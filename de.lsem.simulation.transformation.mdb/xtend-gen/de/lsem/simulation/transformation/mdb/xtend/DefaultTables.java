package de.lsem.simulation.transformation.mdb.xtend;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationConstants;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationHelper;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class DefaultTables implements ArenaTransformationConstants {
  @Inject
  @Extension
  private ArenaTransformationHelper _arenaTransformationHelper;
  
  public void createDefaultTables() throws IOException {
    this.createDefaultModelLevels();
    this.createDefaultModuleTables();
    this.createDefaultEntity();
    this.createDefaultProjectParameters();
    this.createDefaultReplicationParameters();
    this.createDefaultReport();
    this.createDefaultRepeatGroupTables();
  }
  
  /**
   * Default entity is not needed and generated a bug because ID is fixed and used by IDGenerator too
   */
  private void createDefaultEntity() throws IOException {
  }
  
  private void createDefaultModuleTables() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_MODULE_TABLES);
    table.addRow(ArenaTransformationConstants.T_BP, "Create", ArenaTransformationConstants.T_BP_CREATE);
    table.addRow(ArenaTransformationConstants.T_BP, "Decide", ArenaTransformationConstants.T_BP_DECIDE);
    table.addRow(ArenaTransformationConstants.T_BP, "Dispose", ArenaTransformationConstants.T_BP_DISPOSE);
    table.addRow(ArenaTransformationConstants.T_BP, "Entity", ArenaTransformationConstants.T_BP_ENTITY);
    table.addRow(ArenaTransformationConstants.T_BP, "Process", ArenaTransformationConstants.T_BP_PROCESS);
    table.addRow(ArenaTransformationConstants.T_BP, "Resource", ArenaTransformationConstants.T_BP_RESOURCE);
  }
  
  private void createDefaultModelLevels() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_MODEL_LEVELS);
    int _rowCount = table.getRowCount();
    int _plus = (_rowCount + 1);
    table.addRow(Integer.valueOf(_plus), "Top");
  }
  
  private void createDefaultRepeatGroupTables() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_REP_GROUP_TABLES);
    table.addRow(ArenaTransformationConstants.T_BP, "Decide", "Conditions", ArenaTransformationConstants.T_BP_DECIDE_COND);
    table.addRow(ArenaTransformationConstants.T_BP, "Process", "Resources", ArenaTransformationConstants.T_BP_PROCESS_RES);
    table.addRow(ArenaTransformationConstants.T_BP, "Resource", "Failures", "BasicProcess|Resource|Failures");
  }
  
  private void createDefaultReport() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_REPORTS);
    table.addRow(Integer.valueOf(2), "Category Overview", Integer.valueOf(0));
  }
  
  private void createDefaultReplicationParameters() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_REPL_PARA);
    int _minus = (-1);
    int _minus_1 = (-1);
    Calendar _instance = Calendar.getInstance();
    Date _time = _instance.getTime();
    table.addRow(Integer.valueOf(1), Integer.valueOf(_minus), Integer.valueOf(_minus_1), _time, Double.valueOf(0.0), Integer.valueOf(1), "Infinite", Integer.valueOf(1), Integer.valueOf(24), Integer.valueOf(1), "");
  }
  
  private void createDefaultProjectParameters() throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_PROJECT_PARAMETERS);
    Database _saveFile_1 = this._arenaTransformationHelper.getSaveFile();
    File _file = _saveFile_1.getFile();
    String _name = _file.getName();
    String _replace = _name.replace(".mdb", "");
    int _minus = (-1);
    int _minus_1 = (-1);
    int _minus_2 = (-1);
    table.addRow(_replace, 
      ArenaTransformationConstants.AUTHOR, "", Integer.valueOf(0), Integer.valueOf(_minus), Integer.valueOf(0), Integer.valueOf(_minus_1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(_minus_2), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
  }
}
