package de.lsem.simulation.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

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
	private ILog log;
	public static List<String> elementNames;
	private static String pluginId;
	private Logger logger;

	@Deprecated
	public SimulationValidator(List<ISimulationElement> transformedElements,
			String pluginId_) {
		init();
		this.elements = transformedElements;
		setPluginid(pluginId_);
		initLogger();
	}

	@Deprecated
	public SimulationValidator(XMIResource resource, String pluginId_) {
		init();
		elements = filterBusinessObjects(resource);
		setPluginid(pluginId_);
		initLogger();
	}

	public SimulationValidator(XMIResource resource, ILog log, String pluginId_) {
		init();
		this.log = log;
		elements = filterBusinessObjects(resource);
		setPluginid(pluginId_);
		initLogger();
	}

	private void initLogger() {
		logger = Logger.getLogger(pluginId);
	}

	private void setPluginid(String pluginId_) {
		if (pluginId_ != null && !"".equals(pluginId_)) {
			pluginId = pluginId_;
		} else {
			pluginId = Activator.PLUGIN_ID;
		}
	}

	private void init() {
		elementNames = new ArrayList<String>();
		pluginId = new String();
	}

	/**
	 * Validates a process if attributes are set and if the process-structure is
	 * valid. A valid process-structure contains at least >=1 Source, >=1
	 * Activity and >=1 Sink connected to each other.
	 * 
	 * The user will be informed via a pop-up-message and the warnings will be
	 * displayed in error-view.
	 * 
	 * @return Returns whether a model is transformable or not. If Status.Error
	 *         is thrown, validation fails and method returns false. If only
	 *         Status.Warning/Everything fine, method returns true.
	 */
	public ValidationStatus validate() {
		List<ValidationException> retVal = new ArrayList<ValidationException>();
		retVal = checkElements(retVal);
		retVal = checkProcessStructure(retVal);

		return setLogAndDisplayProblemMessage(retVal);
	}

	private ValidationStatus setLogAndDisplayProblemMessage(
			List<ValidationException> foundProblems) {
		ValidationStatus hasNoError = ValidationStatus.STATUS_OK;

		if (log == null) {
			return ValidationStatus.VALIDATION_IMPOSSIBLE;
		}

		for (ValidationException e : foundProblems) {
			log.log(e.getStatus());
			if (e.getStatus().getSeverity() == Status.ERROR) {
				hasNoError = ValidationStatus.STATUS_ERROR;
			}
			logger.log(Level.WARNING, e.getMessage());
		}

		// Errors should aboard transformation, so that the containing job gives
		// the user information about aborting the transformation. Problems lead
		// to a message box.
		if (foundProblems.size() > 0
				&& (hasNoError.equals(ValidationStatus.STATUS_OK) || hasNoError
						.equals(ValidationStatus.STATUS_PROBLEM))) {
			displayProblemsExistMessage();
		}
		return hasNoError;
	}

	private void displayProblemsExistMessage() {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				MessageBox messageBox = new MessageBox(Display.getDefault()
						.getActiveShell(), SWT.ICON_INFORMATION);
				messageBox
						.setMessage("Your model contains errors or warnings. Please check the error log.");
				messageBox.setText("Errors found in model");
				messageBox.open();
			}
		});
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
