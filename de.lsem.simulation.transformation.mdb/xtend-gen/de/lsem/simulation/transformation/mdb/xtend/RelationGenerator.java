package de.lsem.simulation.transformation.mdb.xtend;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationConstants;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationHelper;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class RelationGenerator implements ArenaTransformationConstants {
  @Inject
  @Extension
  private ArenaTransformationHelper _arenaTransformationHelper;
  
  /**
   * TODO When creating decide-elements, each conditional relation generates a decide-element, which is bullshit
   */
  public void createConnectionsFromDecide(final ISimulationElement source, final int decideID) throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table connectionTable = _saveFile.getTable(ArenaTransformationConstants.T_CONNECTIONS);
    Database _saveFile_1 = this._arenaTransformationHelper.getSaveFile();
    Table decideConditionTable = _saveFile_1.getTable(ArenaTransformationConstants.T_BP_DECIDE_COND);
    int i = 1;
    EList<IRelation> _outgoing = source.getOutgoing();
    for (final IRelation e : _outgoing) {
      {
        String nextLabel = this.setNextLabelValue(source, i);
        double prob = 50.0;
        String cond = "";
        if ((e instanceof IConditionalRelation)) {
          double _probability = ((IConditionalRelation) e).getProbability();
          prob = _probability;
          String _condition = ((IConditionalRelation) e).getCondition();
          cond = _condition;
        }
        String _plus = ("Attribute " + Integer.valueOf(i));
        String _plus_1 = ("Variable " + Integer.valueOf(i));
        decideConditionTable.addRow(Integer.valueOf(decideID), Integer.valueOf(i), Double.valueOf(prob), cond, "Entitiy 1", Integer.valueOf(1), Integer.valueOf(1), _plus, _plus_1, Integer.valueOf(1), 
          ">=");
        int _generateID = this._arenaTransformationHelper.generateID();
        Map<ISimulationElement,Integer> _lookupTable = this._arenaTransformationHelper.getLookupTable();
        ISimulationElement _target = e.getTarget();
        Integer _get = _lookupTable.get(_target);
        connectionTable.addRow(Integer.valueOf(_generateID), Integer.valueOf(1), Integer.valueOf(decideID), nextLabel, Integer.valueOf(i), _get, ArenaTransformationConstants.VALUE_LABEL, Integer.valueOf(0));
        int _plus_2 = (i + 1);
        i = _plus_2;
      }
    }
    final int connectionSerialNumber = this._arenaTransformationHelper.generateID();
    Map<ISimulationElement,Integer> _lookupTable = this._arenaTransformationHelper.getLookupTable();
    Integer _get = _lookupTable.get(source);
    connectionTable.addRow(Integer.valueOf(connectionSerialNumber), Integer.valueOf(1), _get, ArenaTransformationConstants.VALUE_NEXT_LABEL, Integer.valueOf(0), Integer.valueOf(decideID), 
      ArenaTransformationConstants.VALUE_LABEL, Integer.valueOf(0));
  }
  
  public String setNextLabelValue(final ISimulationElement it, final int i) {
    String _xifexpression = null;
    EList<IRelation> _outgoing = it.getOutgoing();
    int _size = _outgoing.size();
    boolean _lessEqualsThan = (_size <= 2);
    if (_lessEqualsThan) {
      String _xifexpression_1 = null;
      boolean _lessThan = (i < 2);
      if (_lessThan) {
        _xifexpression_1 = ArenaTransformationConstants.VALUE_NEXT_LABEL_YES;
      } else {
        _xifexpression_1 = ArenaTransformationConstants.VALUE_NEXT_LABEL_NO;
      }
      _xifexpression = _xifexpression_1;
    } else {
      _xifexpression = ArenaTransformationConstants.VALUE_N_NEXT_LABEL_YES;
    }
    return _xifexpression;
  }
  
  public int createConnection(final IRelation relation) throws IOException {
    int idDummy = this._arenaTransformationHelper.generateID();
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table connectionTable = _saveFile.getTable(ArenaTransformationConstants.T_CONNECTIONS);
    ISimulationElement source = relation.getSource();
    ISimulationElement target = relation.getTarget();
    Map<ISimulationElement,Integer> _lookupTable = this._arenaTransformationHelper.getLookupTable();
    Integer _get = _lookupTable.get(source);
    Map<ISimulationElement,Integer> _lookupTable_1 = this._arenaTransformationHelper.getLookupTable();
    Integer _get_1 = _lookupTable_1.get(target);
    String _name = relation.getName();
    connectionTable.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), _get, ArenaTransformationConstants.VALUE_NEXT_LABEL, Integer.valueOf(0), _get_1, 
      ArenaTransformationConstants.VALUE_LABEL, Integer.valueOf(0), _name);
    return idDummy;
  }
  
  public int createConnection(final int sourceID, final int targetID, final String relationName) throws IOException {
    int idDummy = this._arenaTransformationHelper.generateID();
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table connectionTable = _saveFile.getTable(ArenaTransformationConstants.T_CONNECTIONS);
    connectionTable.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), Integer.valueOf(sourceID), ArenaTransformationConstants.VALUE_NEXT_LABEL, Integer.valueOf(0), Integer.valueOf(targetID), 
      ArenaTransformationConstants.VALUE_LABEL, Integer.valueOf(0), relationName);
    return idDummy;
  }
}
