package de.lsem.simulation.transformation.mdb.xtend

import com.healthmarketscience.jackcess.Table
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import java.io.IOException
import javax.inject.Inject

class RelationGenerator implements ArenaTransformationConstants {

	@Inject
	extension ArenaTransformationHelper

	/**
 * TODO When creating decide-elements, each conditional relation generates a decide-element, which is bullshit 
 */
	def void createConnectionsFromDecide(ISimulationElement source, int decideID) throws IOException {

		var connectionTable = saveFile.getTable(T_CONNECTIONS)
		var decideConditionTable = saveFile.getTable(T_BP_DECIDE_COND)

		//logger.log(Level.INFO, "Decide-Element with id " + idDummy + " created.")
		// ModuleSerialNumber Conditions|Index N Percent True N If N TypeNamed N
		// Value N Column N ANamed N VNamed N Row N Is
		// 187 1 33 Entity Type Entity 1 1 1 Attribute 1 Variable 1 1 >=
		// 187 2 75 Entity Type Entity 1 1 1 Attribute 2 Variable 2 1 >=
		// 46 1 50 Entity Type Entity 1 1 1 Attribute 1 Variable 1 1 >=
		// 46 2 50 Entity Type Entity 1 1 1 Attribute 2 Variable 2 1 >=
		// 46 3 50 Entity Type Entity 1 1 1 Attribute 3 Variable 3 1 >=
		// ############# create outgoing connections #############
		// Case 1 : More then two outgoing connections
		// if (allConnectionsFromOneSource.size() > 2) {
		var int i = 1

		//		var isDecideCreated = false
		//		var savedDecideID = -1
		for (IRelation e : source.outgoing) {
			var nextLabel = setNextLabelValue(source, i)

			var prob = 50.0
			var cond = ""
			if (e instanceof IConditionalRelation) {
				prob = (e as IConditionalRelation).probability
				cond = (e as IConditionalRelation).condition
			}

			// ************** ERROR: Decide-Element is generated for each conditional-relation ********************
			// ************** Aggregate all conditional relation in a saved decide-element     ********************
			// ############################### NEW ###################################
			//			if (!isDecideCreated) {
			//				decideConditionTable.addRow(idDummy, i, prob, cond, "Entitiy 1", 1, 1, "Attribute " + i, "Variable " + i,
			//					1, ">=")
			//				savedDecideID = idDummy
			//			}
			//			// ############################### /NEW ###################################
			decideConditionTable.addRow(decideID, i, prob, cond, "Entitiy 1", 1, 1, "Attribute " + i, "Variable " + i, 1,
				">=")
			connectionTable.addRow(generateID, 1, decideID, nextLabel, i, lookupTable.get(e.target), VALUE_LABEL, 0)
			i = i + 1
		}

		// }
		// Case 2: Less then or exactly two outgoing connections
		// else {
		// connectionTable.addRow(generateID(), 1, idDummy, VALUE_NEXT_LABEL,
		// 0, lookupTable.get(target), VALUE_LABEL, 0)
		// }
		// SerialNumber ModelLevelID SourceSerialNumber SourceLabel
		// SourceRepeatIndex DestinationSerialNumber DestinationLabel
		// DestinationRepeatIndex UserDescription
		// 126 1 46 N Next Label Yes 1 92 Label 0
		// 127 1 46 N Next Label Yes 2 109 Label 0
		// 128 1 46 N Next Label Yes 3 125 Label 0
		// 129 1 92 Next Label 0 70 Label 0
		// 130 1 109 Next Label 0 70 Label 0
		// 131 1 125 Next Label 0 70 Label 0
		// 71 1 57 Next Label 0 46 Label 0
		// ############# create incomming connection #############
		val int connectionSerialNumber = generateID()
		connectionTable.addRow(connectionSerialNumber, 1, lookupTable.get(source), VALUE_NEXT_LABEL, 0, decideID,
			VALUE_LABEL, 0)

	//logger.log(Level.INFO,
	//"Conditional Relation(s) from start element with id=" + lookupTable.get(source) + " generated.")
	}

	def setNextLabelValue(ISimulationElement it, int i) {
		if (outgoing.size <= 2) {
			if (i < 2) {
				VALUE_NEXT_LABEL_YES
			} else {
				VALUE_NEXT_LABEL_NO
			}
		} else {
			VALUE_N_NEXT_LABEL_YES
		}
	}

	def createConnection(IRelation relation) throws IOException {
		var int idDummy = generateID
		var Table connectionTable = saveFile.getTable(T_CONNECTIONS)

		var ISimulationElement source = relation.getSource()
		var ISimulationElement target = relation.getTarget()

		connectionTable.addRow(idDummy, 1, lookupTable.get(source), VALUE_NEXT_LABEL, 0, lookupTable.get(target),
			VALUE_LABEL, 0, relation.getName())
		return idDummy
	}

	def createConnection(int sourceID, int targetID, String relationName) throws IOException {
		var int idDummy = generateID
		var Table connectionTable = saveFile.getTable(T_CONNECTIONS)


		connectionTable.addRow(idDummy, 1, sourceID, VALUE_NEXT_LABEL, 0, targetID,
			VALUE_LABEL, 0, relationName)
		return idDummy
	}

}
