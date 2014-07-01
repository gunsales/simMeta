package de.lsem.simulation.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.validation.exception.ProcessStructureException;
import de.lsem.simulation.validation.exception.ValidationException;
import de.lsem.simulation.validation.validators.ActivityValidator;
import de.lsem.simulation.validation.validators.SinkValidator;
import de.lsem.simulation.validation.validators.SourceValidator;

public class SimulationValidator {

	private List<ISimulationElement> elements;
	public static List<String> elementNames;
	private static String pluginId;

	public SimulationValidator(List<ISimulationElement> transformedElements,
			String pluginId_) {
		this.elements = transformedElements;
		init();
		if (pluginId_ != null && !"".equals(pluginId_))
			pluginId = pluginId_;
	}

	public SimulationValidator(XMIResource resource, String pluginId_) {
		elements = filterBusinessObjects(resource);
		init();
		if (pluginId_ != null && !"".equals(pluginId_))
			pluginId = pluginId_;
	}

	private void init() {
		elementNames = new ArrayList<String>();
		pluginId = new String();
	}

	public List<ValidationException> validate() {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		retVal = checkElements(retVal);
		retVal = checkProcessStructure(retVal);
		return retVal;
	}

	private List<ValidationException> checkProcessStructure(
			List<ValidationException> retVal) {

		boolean hasSource = false;
		boolean hasSink = false;
		for (ISimulationElement element : elements) {
			// Process needs at least one source
			if (element instanceof ISource) {
				hasSource = true;
			}
			// Process needs at least one sink
			else if (element instanceof ISink) {
				hasSink = true;
			}
		}

		if (!hasSource || !hasSink) {
			retVal.add(new ProcessStructureException(null,
					"Process has no source or sink."));
		}

		return retVal;

	}

	private List<ValidationException> checkElements(
			List<ValidationException> retVal) {
		for (ISimulationElement e : elements) {
			// Check Activities
			if (e instanceof IActivity) {
				ActivityValidator activityValidator = new ActivityValidator();
				retVal.addAll(activityValidator.validate(e));
			}
			// Check Source
			else if (e instanceof ISource) {
				SourceValidator sVal = new SourceValidator();
				retVal.addAll(sVal.validate(e));
			}
			// Check Sink
			else if (e instanceof ISink) {
				SinkValidator sinkValidator = new SinkValidator();
				retVal.addAll(sinkValidator.validate(e));
			}
		}

		return retVal;
	}

	private List<ISimulationElement> filterBusinessObjects(
			XMIResource xmiResource) {

		List<ISimulationElement> retVal = new ArrayList<ISimulationElement>();

		for (EObject e : xmiResource.getContents()) {
			if (e instanceof ISimulationElement) {
				retVal.add((ISimulationElement) e);
			}
		}

		return retVal;
	}

	public static String getPluginId() {
		return pluginId;
	}

}
