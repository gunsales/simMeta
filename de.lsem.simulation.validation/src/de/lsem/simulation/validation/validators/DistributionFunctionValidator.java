package de.lsem.simulation.validation.validators;

import java.util.ArrayList;
import java.util.List;

import de.lsem.repository.model.simulation.IBeta;
import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.repository.model.simulation.IErlang;
import de.lsem.repository.model.simulation.IGamma;
import de.lsem.repository.model.simulation.ILogNormal;
import de.lsem.repository.model.simulation.INegExp;
import de.lsem.repository.model.simulation.INormal;
import de.lsem.repository.model.simulation.IPoisson;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISimulationObject;
import de.lsem.repository.model.simulation.ITriangular;
import de.lsem.repository.model.simulation.IUniform;
import de.lsem.repository.model.simulation.IWeibull;
import de.lsem.simulation.validation.exception.DistributionFunctionException;
import de.lsem.simulation.validation.exception.ValidationException;

public class DistributionFunctionValidator implements IValidator {

	private ISimulationElement superElement;

	public DistributionFunctionValidator(ISimulationElement superElement) {
		this.superElement = superElement;
	}

	@Override
	public List<ValidationException> validate(ISimulationObject object) {

		List<ValidationException> retVal = new ArrayList<ValidationException>();

		if (!(object instanceof IDistributionFunction)) {
			return retVal;
		}

		IDistributionFunction distFunc = (IDistributionFunction) object;

		// Check all functions
		if (distFunc instanceof ILogNormal) {
			 ILogNormal logNorm = (ILogNormal) distFunc;
			 double logMean = logNorm.getLogMean();
			 double logStd = logNorm.getLogStd();
			 
			 checkValuesLarger0(logMean, logStd);
		} else if (distFunc instanceof IUniform) {
			IUniform uniform = (IUniform) distFunc;
			double max = uniform.getMax();
			double min = uniform.getMin();

			if (max < min) {
				retVal.add(new DistributionFunctionException(superElement,
						"Max < min"));
			}
			retVal.addAll(checkValuesLarger0(min, max));

		} else if (distFunc instanceof ITriangular) {
			ITriangular triangular = (ITriangular) distFunc;
			double max = triangular.getMax();
			double min = triangular.getMin();
			double mode = triangular.getMode();
			if (max < min) {
				retVal.add(new DistributionFunctionException(superElement,
						"Max < min"));
			}
			retVal.addAll(checkValuesLarger0(min, max, mode));
		} else if (distFunc instanceof INormal) {
			INormal normal = (INormal) distFunc;
			double mean = normal.getMean();
			double stdDev = normal.getStdDev();
			retVal.addAll(checkValuesLarger0(mean, stdDev));
		} else if (distFunc instanceof IPoisson) {
			IPoisson poisson = (IPoisson) distFunc;
			double mean = poisson.getMean();
			retVal.addAll(checkValuesLarger0(mean));
		} else if (distFunc instanceof INegExp) {
			INegExp negExp = (INegExp) distFunc;
			double mean = negExp.getMean();
			retVal.addAll(checkValuesLarger0(mean));
		} else if (distFunc instanceof IGamma) {
			IGamma gamma = (IGamma) distFunc;
			double alpha = gamma.getAlpha();
			double beta = gamma.getBeta();

			retVal.addAll(checkValuesLarger0(alpha, beta));

		} else if (distFunc instanceof IBeta) {
			IBeta beta = (IBeta) distFunc;
			double alpha = beta.getAlpha();
			double betaValue = beta.getBeta();

			retVal.addAll(checkValuesLarger0(alpha, betaValue));
		} else if (distFunc instanceof IErlang) {
			IErlang erlang = (IErlang) distFunc;
			double expMean = erlang.getExpMean();
			double k = erlang.getK();
			retVal.addAll(checkValuesLarger0(expMean, k));

		} else if (distFunc instanceof IWeibull) {
			IWeibull weibull = (IWeibull) distFunc;
			double alpha = weibull.getAlpha();
			double beta = weibull.getBeta();

			retVal.addAll(checkValuesLarger0(alpha, beta));
		}
		return retVal;
	}

	private List<ValidationException> checkValuesLarger0(double... values) {
		List<ValidationException> retVal = new ArrayList<ValidationException>();

		for (double value : values) {
			if (value <= 0.0) {
				retVal.add(new DistributionFunctionException(superElement,
						"A value is <= 0.0"));
			}
		}

		return retVal;
	}
}
