package de.lsem.simulation.transformation.anylogic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import com.google.inject.Injector;

import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.transformation.anylogic.Activator;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.transform.generator.GeneratorInjector;
import de.lsem.simulation.transformation.anylogic.transform.xtend.MainTransformer;
import de.lsem.simulation.validation.SimulationValidator;
import de.lsem.simulation.validation.exception.ValidationException;

public class TransformToAnylogicJob extends Job {

	private static final Logger log = Logger
			.getLogger(TransformToAnylogicJob.class.getSimpleName());

	private XMIResource xmiResource;
	private List<EmbeddedObject> generatedEmbeddedObjects;
	private Set<Connector> connections;

	public TransformToAnylogicJob(XMIResource xmiResource) {
		super("Transform to Anylogic");
		this.xmiResource = xmiResource;
		init();
	}

	private void init() {
		generatedEmbeddedObjects = new ArrayList<EmbeddedObject>();
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {

		Status status = new Status(Status.OK, Activator.PLUGIN_ID,
				"Transformation completed successfully.");

		// Filter business-objects
		// List<ISimulationElement> businessObjects =
		// filterBusinessObjects(xmiResource);
		// Pre-Check transform-conditions REPLACED BY plugin
		// de.simulation.validation
		// final Status preCheckStatus = preCheckConditions(businessObjects);
		// switch (preCheckStatus.getSeverity()) {
		// case Status.ERROR: {
		// return preCheckStatus;
		// }
		// case Status.WARNING: {
		// status = preCheckStatus;
		// }
		// default:
		// }

		preCheckBusinessObjects();

		// Init Transformation based on business-objects
		// Injector injector = Guice.createInjector(new GeneratorModule());
		// AnylogicTransformer anylogicObjectTransformer = injector
		// .getInstance(AnylogicTransformer.class);
		GeneratorInjector generator = new GeneratorInjector();
		Injector injector = generator.createInjectorAndDoEMFRegistration();
		MainTransformer anylogicTransformer = injector
				.getInstance(MainTransformer.class);

		// Execute Transformation
		try {
			generatedEmbeddedObjects = anylogicTransformer
					.transform(xmiResource);
			// Get Connections
			connections = anylogicTransformer.getConnectorSet();

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(Status.ERROR, Activator.PLUGIN_ID,
					"Error during transformation: " + e);
		}

		return status;
	}

	private void preCheckBusinessObjects() {

		SimulationValidator simulationValidator = new SimulationValidator(
				xmiResource, Activator.PLUGIN_ID);

		List<ValidationException> foundProblems = simulationValidator
				.validate();

		ILog iLog = Activator.getDefault().getLog();

		for (ValidationException e : foundProblems) {

			log.log(Level.WARNING,
					e.getLocalizedMessage() + "\n" + e.getMessage());

			iLog.log(e.getStatus());

		}

		if (foundProblems.size() > 0) {
			Display.getDefault().asyncExec(new Runnable() {
				
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
	}

	@Deprecated
	private Status preCheckConditions(List<ISimulationElement> businessObjects) {

		// Case 1 (Warning): If multiple outgoing connections and at least on of
		// them is not a conditional relation
		for (ISimulationElement s : businessObjects) {
			if (s.getOutgoing().size() > 1) {
				for (IRelation r : s.getOutgoing()) {
					if (!(r instanceof IConditionalRelation)) {
						return new Status(
								Status.WARNING,
								Activator.PLUGIN_ID,
								"Warning. Element \""
										+ s.getName()
										+ "\" has multiple outgoing relations. If you use more than one outgoing relation, please use conditional relations.");
					}
				}
			}
			// Case 2 (Warning): Only one conditional-relation is going out
			else if (s.getOutgoing().size() == 1) {
				if (s.getOutgoing().get(0) instanceof IConditionalRelation) {
					return new Status(
							Status.WARNING,
							Activator.PLUGIN_ID,
							"Warning. Element \""
									+ s.getName()
									+ "\" only has one outgoing conditional-relation. This relation will be treated as usual relation. Use relation instead.");
				}
			}
		}

		return new Status(Status.OK, Activator.PLUGIN_ID, "Pre-check ok.");
	}

	protected List<EmbeddedObject> getEmbeddedObjects() {
		return generatedEmbeddedObjects;
	}

	protected Set<Connector> getConnections() {
		return connections;
	}

}
