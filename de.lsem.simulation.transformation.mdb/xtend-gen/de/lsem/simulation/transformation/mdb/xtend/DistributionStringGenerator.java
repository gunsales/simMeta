package de.lsem.simulation.transformation.mdb.xtend;

import de.lsem.repository.model.simulation.IBeta;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IErlang;
import de.lsem.repository.model.simulation.IGamma;
import de.lsem.repository.model.simulation.ILogNormal;
import de.lsem.repository.model.simulation.INegExp;
import de.lsem.repository.model.simulation.INormal;
import de.lsem.repository.model.simulation.IPoisson;
import de.lsem.repository.model.simulation.ITriangular;
import de.lsem.repository.model.simulation.IUniform;
import de.lsem.repository.model.simulation.IWeibull;
import javax.inject.Singleton;
import org.eclipse.xtend2.lib.StringConcatenation;

@Singleton
@SuppressWarnings("all")
public class DistributionStringGenerator {
  public CharSequence createWeibull(final IWeibull it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Weib( ");
    double _beta = it.getBeta();
    _builder.append(_beta, "");
    _builder.append(", ");
    double _alpha = it.getAlpha();
    _builder.append(_alpha, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createBeta(final IBeta it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("BETA( ");
    double _beta = it.getBeta();
    _builder.append(_beta, "");
    _builder.append(", ");
    double _alpha = it.getAlpha();
    _builder.append(_alpha, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createNormal(final INormal it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("NORM( ");
    double _mean = it.getMean();
    _builder.append(_mean, "");
    _builder.append(", ");
    double _stdDev = it.getStdDev();
    _builder.append(_stdDev, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createTriangle(final ITriangular it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("TRIA( ");
    double _min = it.getMin();
    _builder.append(_min, "");
    _builder.append(", ");
    double _mode = it.getMode();
    _builder.append(_mode, "");
    _builder.append(", ");
    double _max = it.getMax();
    _builder.append(_max, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createLogN(final ILogNormal it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("LOGN( ");
    double _logMean = it.getLogMean();
    _builder.append(_logMean, "");
    _builder.append(", ");
    double _logStd = it.getLogStd();
    _builder.append(_logStd, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createUniform(final IUniform it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("UNIF( ");
    double _min = it.getMin();
    _builder.append(_min, "");
    _builder.append(", ");
    double _max = it.getMax();
    _builder.append(_max, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createPoisson(final IPoisson it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("POIS( ");
    double _mean = it.getMean();
    _builder.append(_mean, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createNegativeExpo(final INegExp it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("EXPO( ");
    double _mean = it.getMean();
    _builder.append(_mean, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createErlang(final IErlang it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ERLA( ");
    double _expMean = it.getExpMean();
    _builder.append(_expMean, "");
    _builder.append(", ");
    double _k = it.getK();
    _builder.append(_k, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createGamma(final IGamma it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("GAMM( ");
    double _beta = it.getBeta();
    _builder.append(_beta, "");
    _builder.append(", ");
    double _alpha = it.getAlpha();
    _builder.append(_alpha, "");
    _builder.append(" )");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence createConstant(final IConstant it) {
    StringConcatenation _builder = new StringConcatenation();
    double _value = it.getValue();
    _builder.append(_value, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
