package de.lsem.simulation.transformation.sim.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.examples.common.ExamplesCommonPlugin;
import org.eclipse.graphiti.examples.common.FileService;
import org.eclipse.graphiti.examples.common.Messages;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.lsem.process.model.ProcessModel;
import de.lsem.process.model.ProcessNode;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.transformation.sim.Activator;
import de.lsem.simulation.transformation.sim.generator.GeneratorModule;
import de.lsem.simulation.transformation.sim.helper.GraphicalHelper;
import de.lsem.simulation.transformation.sim.helper.Position;
import de.lsem.simulation.transformation.sim.xtext.GenericTransformation;
import de.lsem.simulation.transformation.sim.xtext.TransformBPMN2ToSimulation;
import de.lsem.simulation.validation.SimulationValidator;

public class TransformToSimulationJob extends Job {

	private static final String SIMULATION_DIAGRAM_TYPE_ID = "de.lsem.simulation";
//	private static final Logger log = Logger
//			.getLogger(TransformToSimulationJob.class.getSimpleName());

	private List<FlowElement> bpmnElementList;
	private IFile saveFile;
	private IWorkbenchPage page;
	private BPMNDiagram bpmnDiagram;
	private ProcessModel model;
	private boolean startable = true;

	public TransformToSimulationJob(BPMNDiagram diagram, IFile saveFile,
			IWorkbenchPage page) {
		this(saveFile, page);
		init(diagram);
	}

	public TransformToSimulationJob(ProcessModel model, IFile saveFile,
			IWorkbenchPage page) {
		this(saveFile, page);
		init(model);
	}

	private TransformToSimulationJob(IFile saveFile, IWorkbenchPage page) {
		super("Transform to simulation-format");

		this.saveFile = saveFile;
		this.page = page;

	}

	private void init(ProcessModel model) {
		// assert (model != null);
		if (model != null) {
			this.model = model;
		}
	}

	private void init(BPMNDiagram diagram) {

		// Get BPMN-Process
		Process process = (Process) diagram.getPlane().getBpmnElement();

		// BUG - If a new BPMN-editor is opened with a new bpmn-process from the
		// wizard,
		// process will be null
		// assert (process != null);

		if (process != null) {

			// Set up flow elements to be transformed
			bpmnElementList = new ArrayList<FlowElement>();
			for (FlowElement e : process.getFlowElements()) {
				if (e instanceof FlowNode) {
					bpmnElementList.add((FlowNode) e);
				}
			}

			this.bpmnDiagram = diagram;
			startable = true;
		} else {
			startable = false;
			// createErrorMessage();
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {

		if (!startable) {
			return new Status(
					Status.ERROR,
					Activator.PLUGIN_ID,
					"Transformation not possible. This is a bug: BPMN2-Editor not correctly initialized. Please close and reopen the editor.");
		}
		monitor.beginTask("Starting transformation", IProgressMonitor.UNKNOWN);
		// Transform! Get simulation elements from bpmn-elements
		Set<? extends ISimulationElement> transformationSet = startTransformation(monitor);

		// Oh-Pen diagram in editor
		monitor.beginTask("Opening and initializing editor...",
				IProgressMonitor.UNKNOWN);
		openEditor(transformationSet, monitor);

		monitor.done();

		return new Status(IStatus.OK, Activator.PLUGIN_ID,
				"Transformation done.");
	}

	private boolean preCheckBusinessObjects(IEditorPart editor) {
		if (editor instanceof IDiagramContainer) {
			IDiagramContainer container = (IDiagramContainer) editor;
			EList<Resource> resources = container.getDiagramBehavior()
					.getEditingDomain().getResourceSet().getResources();
			for (Resource r : resources) {
				if (r instanceof XMIResource) {
					XMIResource xmiResource = (XMIResource) r;
					ILog iLog = Activator.getDefault().getLog();

					SimulationValidator simulationValidator = new SimulationValidator(
							xmiResource, iLog, Activator.PLUGIN_ID);

					return simulationValidator.validate();
				}
			}
		}
		// Allow transformation even if pre-check is not possible.
		return true;
	}

	private void addSimulationElementsToResource(final Resource resource,
			TransactionalEditingDomain domain,
			final Set<? extends ISimulationElement> transformationSet) {
		domain.getCommandStack().execute(new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				for (ISimulationElement e : transformationSet) {
					if (e instanceof ISource) {
						ISource source = (ISource) e;
						resource.getContents().add(source);
						resource.getContents().add(source.getFirstEntity());
						resource.getContents().add(
								source.getFirstEntity().getPeriod());
						resource.getContents().add(source.getNewEntities());
						resource.getContents().add(
								source.getNewEntities().getPeriod());
						resource.getContents().add(source.getProcessedObject());
					} else if (e instanceof IActivity) {
						IActivity activity = (IActivity) e;
						resource.getContents().add(activity);
						resource.getContents().add(activity.getCapacity());
						resource.getContents().add(activity.getTimePeriod());
						resource.getContents().add(
								activity.getTimePeriod().getPeriod());
					} else if (e instanceof ISink) {
						resource.getContents().add((ISink) e);
					}
				}

				// IValidator validator =
				// ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
				// IStatus results = validator.validate(resource.getContents());
				//
				// if(!results.isOK()){
				// ErrorDialog.openError(null, "Validation",
				// "Validation failed", results);
				// }
				try {
					resource.save(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	private Set<? extends ISimulationElement> startTransformation(
			IProgressMonitor monitor) {
		// Create simulation elements
		monitor.subTask("Performing jpmmt-to-sim-meta-transformation");
		monitor.beginTask("Creating simulation elements ... ",
				IProgressMonitor.UNKNOWN);

		Set<? extends ISimulationElement> transformationSet = new HashSet<ISimulationElement>();

		// Transform generic jpmmt
		if (model != null) {
			monitor.subTask("Performing jpmmt-to-sim-meta-transformation");
			monitor.worked(IProgressMonitor.UNKNOWN);

			Injector inj = Guice.createInjector(new GeneratorModule());
			GenericTransformation gt = inj
					.getInstance(GenericTransformation.class);
			HashSet<ProcessNode> nodes = new HashSet<ProcessNode>(
					model.getNodes());

			transformationSet = gt.transform(nodes, model.getEdges());
			// Set<ISimulationElement> asd = gt.transform2(nodes,
			// model.getEdges());

			monitor.done();
		}
		// Transform bpmn
		else if (bpmnElementList != null) {
			monitor.subTask("Performing BPMN2-to-sim-meta-transformation");
			monitor.worked(IProgressMonitor.UNKNOWN);

			// TransformBPMNToSimulation transformation = new
			// TransformBPMNToSimulation(
			// bpmnElementList);
			// transformationSet = transformation.transform();

			Injector inj = Guice.createInjector(new GeneratorModule());
			TransformBPMN2ToSimulation gt = inj
					.getInstance(TransformBPMN2ToSimulation.class);
			transformationSet = gt.startTransformation(bpmnElementList);
			monitor.done();
		}
		return transformationSet;
	}

	private void openEditor(
			final Set<? extends ISimulationElement> transformedObjects,
			final IProgressMonitor monitor) {
		// Open Editor
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				try {
					monitor.beginTask("Creating diagram ... ",
							IProgressMonitor.UNKNOWN);
					Diagram diagram = Graphiti.getPeService().createDiagram(
							SIMULATION_DIAGRAM_TYPE_ID, saveFile.getName(),
							false);
					URI uri = URI.createPlatformResourceURI(saveFile
							.getFullPath().toOSString(), true);
					FileService.createEmfFileForDiagram(uri, diagram);

					// provider id = diagramTypeID + ".SimulationDiagram"
					final String providerId = GraphitiUi.getExtensionManager()
							.getDiagramTypeProviderId(
									SIMULATION_DIAGRAM_TYPE_ID);

					// Create lightweight input instance
					final DiagramEditorInput editorInput = DiagramEditorInput
							.createEditorInput(diagram, providerId);
					// Create editor based on diagram and providerId off of the
					// editorInput
					DiagramEditor editor = (DiagramEditor) page.openEditor(
							editorInput, DiagramEditor.DIAGRAM_EDITOR_ID);

					// Create transactional editing domain
					TransactionalEditingDomain editingDomain = editor
							.getEditingDomain();

					monitor.beginTask("Adding elements to resource ... ",
							IProgressMonitor.UNKNOWN);

					// Create resource-set
					Resource resource = editingDomain.getResourceSet()
							.getResources().get(0);
					addSimulationElementsToResource(resource, editingDomain,
							transformedObjects);

					// Add graphical representation for business objects
					monitor.beginTask("Adding elements to editor ... ",
							IProgressMonitor.UNKNOWN);
					addBusinessObjectsToEditor(transformedObjects, editor);

					// Save editor
					editor.doSave(new NullProgressMonitor());

					// Check process-structure
					monitor.beginTask("Validating elements...",
							IProgressMonitor.UNKNOWN);
					preCheckBusinessObjects(editor);

					monitor.done();
				} catch (PartInitException e) {
					e.printStackTrace();
					createErrorMessage(e);
				}
			}

			private void addBusinessObjectsToEditor(
					Set<? extends ISimulationElement> transformedObjects,
					DiagramEditor editor) {

				IFeatureProvider fituarProwaider = editor
						.getDiagramTypeProvider().getFeatureProvider();

				// Add business objects graphically to diagram
				Set<PictogramElement> pictoElements = addGraphicalRepresentation(
						transformedObjects, editor);

				// Add connections graphically
				addConnections(fituarProwaider, pictoElements);
			}

			private void createErrorMessage(PartInitException e) {
				String error = Messages.CreateDiagramWizard_OpeningEditorError;
				IStatus status = new Status(IStatus.ERROR, ExamplesCommonPlugin
						.getID(), error, e);
				ErrorDialog.openError(new Shell(),
						Messages.CreateDiagramWizard_ErrorOccuredTitle, null,
						status);
			}

			private Set<PictogramElement> addGraphicalRepresentation(
					Set<? extends ISimulationElement> transformedObjects,
					DiagramEditor editor) {

				Set<PictogramElement> retVal = new HashSet<PictogramElement>();

				Map<String, Position> graphicalRepresentations = new HashMap<String, Position>();
				if (bpmnDiagram != null)
					graphicalRepresentations = GraphicalHelper
							.copyGraphicalCoordinates(bpmnDiagram);
				else if (model != null) {
					graphicalRepresentations = GraphicalHelper
							.copyGraphicalCoordinates(model.getNodes());
				}

				Diagram diagram2 = editor.getDiagramTypeProvider().getDiagram();
				IFeatureProvider featureProvider = editor
						.getDiagramTypeProvider().getFeatureProvider();
				int x = 0;
				String key = "";
				for (ISimulationElement s : transformedObjects) {
					AddContext addContext = initAddContext(diagram2, s);

					// When transforming, key special chars are replaced by
					// usual chars (e.g. ä --> ae)
					key = s.getName();
					// System.out.println(key);
					// Add x-/y-coordinates
					// Save graphical information
					Position pos = new Position(x, 0);
					if (graphicalRepresentations.containsKey(key)) {
						pos = graphicalRepresentations.get(key);
						x = pos.getX() + 50;
					}

					addContext.setX(pos.getX());
					addContext.setY(pos.getY());
					PictogramElement pic = featureProvider
							.addIfPossible(addContext);
					retVal.add(pic);
				}

				return retVal;
			}

			private AddContext initAddContext(Diagram diagram,
					ISimulationElement o) {
				AddContext addContext = new AddContext();
				addContext.setNewObject(o);
				addContext.setTargetContainer(diagram);
				return addContext;
			}

			private void addConnections(IFeatureProvider fituarProwaider,
					Set<PictogramElement> pictoElements) {
				for (PictogramElement p : pictoElements) {
					ContainerShape sourceContainer = (ContainerShape) p;
					ISimulationElement eObject = (ISimulationElement) p
							.getLink().getBusinessObjects().get(0);
					// ISimulationElement source = (ISimulationElement) Graphiti
					// .getLinkService()
					// .getBusinessObjectForLinkedPictogramElement(p);
					if (!(eObject instanceof ISink)) {
						for (IRelation r : eObject.getOutgoing()) {
							PictogramElement target = getTargetPictogramElement(
									pictoElements, r);
							ContainerShape targetContainer = (ContainerShape) target;

							if (targetContainer != null) {
								Anchor targetAnchor = targetContainer
										.getAnchors().get(0);

								AddConnectionContext acc = new AddConnectionContext(
										sourceContainer.getAnchors().get(0),
										targetAnchor);
								acc.setNewObject(r);

								fituarProwaider.addIfPossible(acc);
							}
						}
					}
				}
			}

			private PictogramElement getTargetPictogramElement(
					Set<PictogramElement> pictoElements, IRelation r) {
				for (PictogramElement p : pictoElements) {
					ISimulationElement sim = (ISimulationElement) p.getLink()
							.getBusinessObjects().get(0);

					if (sim.equals(r.getTarget()))
						return p;
					// if (Graphiti.getLinkService()
					// .getBusinessObjectForLinkedPictogramElement(p)
					// .equals(r.getTarget()))
					// return p;
				}
				return null;
			}
		});
	}

}
