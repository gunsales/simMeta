package de.lsem.simulation.transformation.mdb.xtend

import com.healthmarketscience.jackcess.Table
import java.io.IOException
import java.util.Calendar
import javax.inject.Inject

class DefaultTables implements ArenaTransformationConstants {

	@Inject extension ArenaTransformationHelper

	def void createDefaultTables() throws IOException {
		createDefaultModelLevels
		createDefaultModuleTables
		createDefaultEntity
		createDefaultProjectParameters
		createDefaultReplicationParameters
		createDefaultReport
		createDefaultRepeatGroupTables

	//logger.log(Level.INFO, "Default table-entries in database created.")
	}

	/*
	 * Default entity is not needed and generated a bug because ID is fixed and used by IDGenerator too
	 */
	private def void createDefaultEntity() throws IOException {
//		var Table table = saveFile.getTable(T_BP_ENTITY)
//		table.addRow(12, 1, 0, 0, "", "Entity 1", "Yes", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "Picture.Report")

	// table = saveFile.getTable(T_LIBRARY)
	// table.addRow(12, 24, 3, "Top", "Module")
	}

	private def void createDefaultModuleTables() throws IOException {
		var Table table = saveFile.getTable(T_MODULE_TABLES)
		table.addRow(T_BP, "Create", T_BP_CREATE)
		table.addRow(T_BP, "Decide", T_BP_DECIDE)
		table.addRow(T_BP, "Dispose", T_BP_DISPOSE)
		table.addRow(T_BP, "Entity", T_BP_ENTITY)
		table.addRow(T_BP, "Process", T_BP_PROCESS)
		table.addRow(T_BP, "Resource", T_BP_RESOURCE)
	}

	private def void createDefaultModelLevels() throws IOException {
		var Table table = saveFile.getTable(T_MODEL_LEVELS)
		table.addRow(table.getRowCount() + 1, "Top")
	}

	private def void createDefaultRepeatGroupTables() throws IOException {
		var Table table = saveFile.getTable(T_REP_GROUP_TABLES)

		// table.addRow(
		// T_BP, "Assign", "Assignment", T_BP_ASSIGN_ASSIGNM)
		table.addRow(T_BP, "Decide", "Conditions", T_BP_DECIDE_COND)
		table.addRow(T_BP, "Process", "Resources", T_BP_PROCESS_RES)

		// TODO TEST!! BasicProcess|Resource|Failures does not exist yet!
		table.addRow(T_BP, "Resource", "Failures", "BasicProcess|Resource|Failures")

	// table.addRow(
	// T_BP, "Separate", "Attributes", T_BP_SEPARATE_ATT)
	}

	private def void createDefaultReport() throws IOException {
		var Table table = saveFile.getTable(T_REPORTS)
		table.addRow(2, "Category Overview", 0)
	}

	private def void createDefaultReplicationParameters() throws IOException {
		var Table table = saveFile.getTable(T_REPL_PARA)
		table.addRow(1, -1, -1, Calendar.getInstance().getTime(), 0.0, 1, "Infinite", 1, 24, 1, "")
	}

	private def void createDefaultProjectParameters() throws IOException {
		var Table table = saveFile.getTable(T_PROJECT_PARAMETERS)
		table.addRow(
		// ProjectTitle
		saveFile.getFile().getName().replace(".mdb", ""),
				// AnalystName
		AUTHOR, "", 0, -1, 0, -1, 0, 0, -1, 0, 0, 0)
	}

}
