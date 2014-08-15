package de.lsem.simulation.features;

import de.lsem.repository.model.simulation.IConditionalRelation;

@SuppressWarnings("all")
public class ConditionalRelationLabelHelper {
  public static String createConditionalLabel(final IConditionalRelation conRel) {
    String _xblockexpression = null;
    {
      String _condition = conRel.getCondition();
      final int length = _condition.length();
      String _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        boolean _lessThan = (length < 5);
        if (_lessThan) {
          _matched=true;
          String _condition_1 = conRel.getCondition();
          _switchResult = _condition_1;
        }
      }
      if (!_matched) {
        boolean _greaterEqualsThan = (length >= 5);
        if (_greaterEqualsThan) {
          _matched=true;
          String _condition_2 = conRel.getCondition();
          String _substring = _condition_2.substring(0, 5);
          String _plus = (_substring + "...");
          _switchResult = _plus;
        }
      }
      if (!_matched) {
        _switchResult = "";
      }
      String _plus_1 = ("(Cond): " + _switchResult);
      _xblockexpression = (_plus_1);
    }
    return _xblockexpression;
  }
  
  public static String createProbabilityLabel(final IConditionalRelation conRel) {
    String _xblockexpression = null;
    {
      double prob = conRel.getProbability();
      double _multiply = (prob * 100);
      long _round = Math.round(_multiply);
      long _divide = (_round / 100);
      prob = _divide;
      String _plus = (Double.valueOf(prob) + "%");
      _xblockexpression = (_plus);
    }
    return _xblockexpression;
  }
}
