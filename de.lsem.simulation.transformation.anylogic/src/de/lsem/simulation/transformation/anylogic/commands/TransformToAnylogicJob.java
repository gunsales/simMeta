package de.lsem.simulation.transformation.anylogic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.xmi.XMIResource;

import com.google.inject.Injector;

import de.lsem.simulation.transformation.anylogic.Activator;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.transform.generator.GeneratorInjector;
import de.lsem.simulation.transformation.anylogic.transform.xtend.MainTransformer;
import de.lsem.simulation.validation.SimulationValidator;

public class TransformToAnylogicJob extends Job {

	// private static final Logger log = Logger
	// .getLogger(TransformToAnylogicJob.class.getSimpleName());

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

		if (!preCheckBusinessObjects()) {
			return new Status(
					Status.ERROR,
					Activator.PLUGIN_ID,
					"Transformation can not be started. Please check the error-log for more information.");
		}

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

	private boolean preCheckBusinessObjects() {

		ILog iLog = Activator.getDefault().getLog();

		SimulationValidator simulationValidator = new SimulationValidator(
				xmiResource, iLog, Activator.PLUGIN_ID);

		return simulationValidator.validate();
	}

	// @Deprecated
	// private Status preCheckConditions(List<ISimulationElement>
	// businessObjects) {
	//
	// // Case 1 (Warning): If multiple outgoing connections and at least on of
	// // them is not a conditional relation
	// for (ISimulationElement s : businessObjects) {
	// if (s.getOutgoing().size() > 1) {
	// for (IRelation r : s.getOutgoing()) {
	// if (!(r instanceof IConditionalRelation)) {
	// return new Status(
	// Status.WARNING,
	// Activator.PLUGIN_ID,
	// "Warning. Element \""
	// + s.getName()
	// +
	// "\" has multiple outgoing relations. If you use more than one outgoing relation, please use conditional relations.");
	// }
	// }
	// }
	// // Case 2 (Warning): Only one conditional-relation is going out
	// else if (s.getOutgoing().size() == 1) {
	// if (s.getOutgoing().get(0) instanceof IConditionalRelation) {
	// return new Status(
	// Status.WARNING,
	// Activator.PLUGIN_ID,
	// "Warning. Element \""
	// + s.getName()
	// +
	// "\" only has one outgoing conditional-relation. This relation will be treated as usual relation. Use relation instead.");
	// }
	// }
	// }
	//
	// return new Status(Status.OK, Activator.PLUGIN_ID, "Pre-check ok.");
	// }

	protected List<EmbeddedObject> getEmbeddedObjects() {
		return generatedEmbeddedObjects;
	}

	protected Set<Connector> getConnections() {
		return connections;
	}

}
