package de.lsem.simulation.transformation.mdb.xtend;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import de.lsem.repository.model.simulation.Constant;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationConstants;
import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformationHelper;
import de.lsem.simulation.transformation.mdb.xtend.DistributionStringGenerator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class ElementCreator implements ArenaTransformationConstants {
  @Inject
  @Extension
  private ArenaTransformationHelper _arenaTransformationHelper;
  
  @Inject
  @Extension
  private DistributionStringGenerator _distributionStringGenerator;
  
  /**
   * @param e
   * @return generated ARENA-id
   * @throws IOException
   */
  protected int _createElement(final IActivity it) throws IOException {
    int idDummy = this._arenaTransformationHelper.generateID();
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_BP_PROCESS);
    String timeUnitArena = ArenaTransformationConstants.ARENA_TIME_HOUR;
    String delayTypeFunction = ArenaTransformationConstants.ARENA_FUNCTION_EXPRESSION;
    String functionType = "1";
    String value = "1";
    String max = "1.5";
    String min = "0.5";
    String stdDev = "0.2";
    String action = "D";
    ServiceType _serviceType = it.getServiceType();
    boolean _notEquals = (!Objects.equal(_serviceType, null));
    if (_notEquals) {
      ServiceType _serviceType_1 = it.getServiceType();
      boolean _equals = _serviceType_1.equals(ServiceType.TRANSPORT);
      if (_equals) {
        ITime _timePeriod = it.getTimePeriod();
        boolean _notEquals_1 = (!Objects.equal(_timePeriod, null));
        if (_notEquals_1) {
          ITime _timePeriod_1 = it.getTimePeriod();
          UnitOfTime _unit = _timePeriod_1.getUnit();
          String _mapTimeUnit = this._arenaTransformationHelper.mapTimeUnit(_unit);
          timeUnitArena = _mapTimeUnit;
          ITime _timePeriod_2 = it.getTimePeriod();
          IDistribution _period = _timePeriod_2.getPeriod();
          boolean _notEquals_2 = (!Objects.equal(_period, null));
          if (_notEquals_2) {
            delayTypeFunction = ArenaTransformationConstants.ARENA_FUNCTION_EXPRESSION;
            ITime _timePeriod_3 = it.getTimePeriod();
            IDistribution _period_1 = _timePeriod_3.getPeriod();
            String _distributionString = this._distributionStringGenerator.getDistributionString(_period_1);
            functionType = _distributionString;
          }
        }
      } else {
        ServiceType _serviceType_2 = it.getServiceType();
        boolean _equals_1 = _serviceType_2.equals(ServiceType.STORAGE);
        if (_equals_1) {
          action = "SDR";
          this.createResource(it);
          this.createResourceFailure(it);
          this.createProcessResource(it, idDummy);
          functionType = "0";
        }
      }
    }
    boolean _and = false;
    ITime _timePeriod_4 = it.getTimePeriod();
    IDistribution _period_2 = _timePeriod_4.getPeriod();
    boolean _notEquals_3 = (!Objects.equal(_period_2, null));
    if (!_notEquals_3) {
      _and = false;
    } else {
      ITime _timePeriod_5 = it.getTimePeriod();
      IDistribution _period_3 = _timePeriod_5.getPeriod();
      _and = (_notEquals_3 && (_period_3 instanceof IConstant));
    }
    if (_and) {
      delayTypeFunction = ArenaTransformationConstants.ARENA_FUNCTION_CONSTANT;
      ITime _timePeriod_6 = it.getTimePeriod();
      IDistribution _period_4 = _timePeriod_6.getPeriod();
      double _value = ((Constant) _period_4).getValue();
      String _valueOf = String.valueOf(_value);
      value = _valueOf;
    }
    int _x = this._arenaTransformationHelper.getX(it);
    int xCoorInt = this._arenaTransformationHelper.stretch(_x);
    int _y = this._arenaTransformationHelper.getY(it);
    int yCoorInt = this._arenaTransformationHelper.stretch(_y);
    ServiceType _serviceType_3 = it.getServiceType();
    String arenaServiceType = this._arenaTransformationHelper.toARENAServiceType(_serviceType_3);
    String _name = it.getName();
    String _name_1 = it.getName();
    String _plus = (_name_1 + "_");
    int _generateID = this._arenaTransformationHelper.generateID();
    String _plus_1 = (_plus + Integer.valueOf(_generateID));
    table.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), Integer.valueOf(xCoorInt), Integer.valueOf(yCoorInt), _name, _plus_1, "Yes", "Standard", action, arenaServiceType, delayTypeFunction, timeUnitArena, Integer.valueOf(2), functionType, stdDev, max, min, value);
    Database _saveFile_1 = this._arenaTransformationHelper.getSaveFile();
    Table _table = _saveFile_1.getTable(ArenaTransformationConstants.T_MODEL_LEVELS);
    table = _table;
    int _rowCount = table.getRowCount();
    int _plus_2 = (_rowCount + 1);
    String _plus_3 = ("Top." + Integer.valueOf(idDummy));
    table.addRow(Integer.valueOf(_plus_2), _plus_3);
    return idDummy;
  }
  
  public int createDecide(final IConditionalRelation it) {
    try {
      int _xblockexpression = (int) 0;
      {
        String type = ArenaTransformationConstants.VALUE_WITH;
        ISimulationElement _source = it.getSource();
        EList<IRelation> _outgoing = _source.getOutgoing();
        Iterable<IConditionalRelation> _filter = Iterables.<IConditionalRelation>filter(_outgoing, IConditionalRelation.class);
        List<IConditionalRelation> _list = IterableExtensions.<IConditionalRelation>toList(_filter);
        String _setConnectionNWayType = this._arenaTransformationHelper.setConnectionNWayType(_list);
        type = _setConnectionNWayType;
        ISimulationElement _source_1 = it.getSource();
        int _x = this._arenaTransformationHelper.getX(_source_1);
        int xCoorSource = this._arenaTransformationHelper.stretch(_x);
        ISimulationElement _source_2 = it.getSource();
        int _y = this._arenaTransformationHelper.getY(_source_2);
        int yCoorSource = this._arenaTransformationHelper.stretch(_y);
        ISimulationElement _target = it.getTarget();
        int _x_1 = this._arenaTransformationHelper.getX(_target);
        int xCoorTarget = this._arenaTransformationHelper.stretch(_x_1);
        ISimulationElement _target_1 = it.getTarget();
        int _y_1 = this._arenaTransformationHelper.getY(_target_1);
        int yCoorTarget = this._arenaTransformationHelper.stretch(_y_1);
        int _plus = (xCoorSource + xCoorTarget);
        int xCoor = (_plus / 2);
        int _plus_1 = (yCoorSource + yCoorTarget);
        int yCoor = (_plus_1 / 2);
        Database _saveFile = this._arenaTransformationHelper.getSaveFile();
        Table decideTable = _saveFile.getTable(ArenaTransformationConstants.T_BP_DECIDE);
        final int idDummy = this._arenaTransformationHelper.generateID();
        String _name = it.getName();
        String _plus_2 = (_name + "_");
        String _plus_3 = (_plus_2 + Integer.valueOf(idDummy));
        double _probability = it.getProbability();
        String _condition = it.getCondition();
        decideTable.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), Integer.valueOf(xCoor), Integer.valueOf(yCoor), "", _plus_3, type, Double.valueOf(_probability), "Expression", _condition, "Attribute 1", "Entity 1", Integer.valueOf(1), "Variable 1", Integer.valueOf(1), ">=");
        _xblockexpression = (idDummy);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected int _createElement(final ISource e) throws IOException {
    int _xblockexpression = (int) 0;
    {
      final int idDummy = this._arenaTransformationHelper.generateID();
      Database _saveFile = this._arenaTransformationHelper.getSaveFile();
      final Table table = _saveFile.getTable(ArenaTransformationConstants.T_BP_CREATE);
      final ITime firstEntity = e.getFirstEntity();
      final ITime newEntities = e.getNewEntities();
      final IGood good = e.getProcessedObject();
      final int maxBatches = e.getMaxNewEntities();
      String firstCreation = "0.0";
      String value = "1";
      String followingEntities = "";
      String timeUnitArena = ArenaTransformationConstants.ARENA_TIME_HOUR;
      String interarrival = ArenaTransformationConstants.ARENA_FUNCTION_EXPRESSION;
      boolean _notEquals = (!Objects.equal(newEntities, null));
      if (_notEquals) {
        UnitOfTime _unit = newEntities.getUnit();
        String _mapTimeUnit = this._arenaTransformationHelper.mapTimeUnit(_unit);
        timeUnitArena = _mapTimeUnit;
        final IDistribution timePeriod = newEntities.getPeriod();
        boolean _notEquals_1 = (!Objects.equal(timePeriod, null));
        if (_notEquals_1) {
          String _distributionString = this._distributionStringGenerator.getDistributionString(timePeriod);
          followingEntities = _distributionString;
          boolean _contains = followingEntities.contains("(");
          if (_contains) {
            interarrival = ArenaTransformationConstants.ARENA_FUNCTION_EXPRESSION;
          } else {
            interarrival = ArenaTransformationConstants.ARENA_FUNCTION_CONSTANT;
            value = followingEntities;
          }
        }
      }
      boolean _notEquals_2 = (!Objects.equal(firstEntity, null));
      if (_notEquals_2) {
        IDistribution timePeriod_1 = firstEntity.getPeriod();
        boolean _notEquals_3 = (!Objects.equal(timePeriod_1, null));
        if (_notEquals_3) {
          String _distributionString_1 = this._distributionStringGenerator.getDistributionString(timePeriod_1);
          firstCreation = _distributionString_1;
        }
      }
      String objectType = "Entity 1";
      boolean _notEquals_4 = (!Objects.equal(good, null));
      if (_notEquals_4) {
        String _type = good.getType();
        objectType = _type;
      }
      int _x = this._arenaTransformationHelper.getX(e);
      final int xCoorInt = this._arenaTransformationHelper.stretch(_x);
      int _y = this._arenaTransformationHelper.getY(e);
      final int yCoorInt = this._arenaTransformationHelper.stretch(_y);
      String _name = e.getName();
      String _name_1 = e.getName();
      table.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), Integer.valueOf(xCoorInt), Integer.valueOf(yCoorInt), _name, _name_1, Integer.valueOf(maxBatches), interarrival, Integer.valueOf(0), followingEntities, value, firstCreation, timeUnitArena, Integer.valueOf(1), objectType);
      _xblockexpression = (idDummy);
    }
    return _xblockexpression;
  }
  
  public int createResourceFailure(final IActivity resource) throws IOException {
    return 0;
  }
  
  protected int _createElement(final ISink e) throws IOException {
    int idDummy = this._arenaTransformationHelper.generateID();
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_BP_DISPOSE);
    int xCoor = this._arenaTransformationHelper.getX(e);
    int yCoor = this._arenaTransformationHelper.getY(e);
    int xCoorInt = this._arenaTransformationHelper.stretch(xCoor);
    int yCoorInt = this._arenaTransformationHelper.stretch(yCoor);
    String _name = e.getName();
    String _plus = (_name + "_");
    int _generateID = this._arenaTransformationHelper.generateID();
    String _plus_1 = (_plus + Integer.valueOf(_generateID));
    table.addRow(Integer.valueOf(idDummy), Integer.valueOf(1), Integer.valueOf(xCoorInt), Integer.valueOf(yCoorInt), "", _plus_1, 
      "Yes");
    return idDummy;
  }
  
  public int createProcessResource(final IActivity process, final int processID) throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_BP_PROCESS_RES);
    String moduleSerialNumber = "";
    String resourcesIndex = "1";
    String resourceType = "Resource";
    String selectionRule = "CYC";
    String saveAttribute = "";
    String inputAttribute = "1";
    String setName = "Set 1";
    String resourceName = "Resource 1";
    String quantity = "1";
    String _valueOf = String.valueOf(processID);
    moduleSerialNumber = _valueOf;
    table.addRow(moduleSerialNumber, resourcesIndex, resourceType, selectionRule, saveAttribute, inputAttribute, setName, resourceName, quantity);
    return processID;
  }
  
  public int createResource(final IActivity process) throws IOException {
    Database _saveFile = this._arenaTransformationHelper.getSaveFile();
    Table table = _saveFile.getTable(ArenaTransformationConstants.T_BP_RESOURCE);
    int serialNumber = 0;
    String modelLevelID = "1";
    int x = 0;
    int y = 0;
    String userDescription = "";
    String name = "Resource 1";
    String reportStatistics = "Yes";
    double usage = 0.0;
    double busy = 0.0;
    String type = "Capacity";
    double idle = 0.0;
    String scheduleRule = "Wait";
    int schedule = 1;
    int capacity = 1;
    String stateSetN = "";
    String initState = "";
    String fdmName = "";
    int fdmID = 0;
    String arenaImportName = "";
    double baseEfficiency = 0.0;
    String efficiencySchedule = "";
    int _generateID = this._arenaTransformationHelper.generateID();
    serialNumber = _generateID;
    table.addRow(Integer.valueOf(serialNumber), modelLevelID, Integer.valueOf(x), Integer.valueOf(y), userDescription, name, reportStatistics, Double.valueOf(usage), Double.valueOf(busy), type, Double.valueOf(idle), scheduleRule, Integer.valueOf(schedule), Integer.valueOf(capacity), stateSetN, initState, fdmName, Integer.valueOf(fdmID), arenaImportName, Double.valueOf(baseEfficiency), efficiencySchedule);
    return serialNumber;
  }
  
  public int createElement(final ISimulationElement it) throws IOException {
    if (it instanceof IActivity) {
      return _createElement((IActivity)it);
    } else if (it instanceof ISink) {
      return _createElement((ISink)it);
    } else if (it instanceof ISource) {
      return _createElement((ISource)it);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
