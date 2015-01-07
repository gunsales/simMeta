package de.lsem.simulation.transformation.sim.commands

import de.lsem.process.model.ProcessModel
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.sim.Activator
import de.lsem.simulation.transformation.sim.generator.GeneratorModule
import de.lsem.simulation.transformation.sim.helper.GraphicalHelper
import de.lsem.simulation.transformation.sim.helper.Position
import de.lsem.simulation.transformation.sim.xtend.GenericTransformation
import de.lsem.simulation.transformation.sim.xtend.TransformBPMN2ToSimulation
import de.lsem.simulation.validation.SimulationValidator
import de.lsem.simulation.validation.ValidationStatus
import java.io.IOException
import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.bpmn2.FlowElement
import org.eclipse.bpmn2.FlowNode
import org.eclipse.bpmn2.Process
import org.eclipse.bpmn2.di.BPMNDiagram
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.TransactionalEditingDomain
import org.eclipse.graphiti.examples.common.FileService
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.impl.AddConnectionContext
import org.eclipse.graphiti.features.context.impl.AddContext
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramElement
import org.eclipse.graphiti.platform.IDiagramContainer
import org.eclipse.graphiti.ui.editor.DiagramEditor
import org.eclipse.graphiti.ui.editor.DiagramEditorInput
import org.eclipse.graphiti.ui.services.GraphitiUi
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.IEditorPart
import org.eclipse.ui.IWorkbenchPage

import static com.google.inject.Guice.*
import static de.lsem.simulation.transformation.sim.commands.TransformToSimulationJob.*
import static org.eclipse.graphiti.services.Graphiti.*

class TransformToSimulationJob extends Job {

	static val SIMULATION_DIAGRAM_TYPE_ID = "de.lsem.simulation"

	val IFile saveFile
	val IWorkbenchPage page

	var ProcessModel model

	/* Startable only affects bpmnDiagrams and reacts on a bug created by bpmn2-project */
	var startable = false
	var BPMNDiagram bpmnDiagram
	var List<FlowElement> bpmnElementList

	new(BPMNDiagram diagram, IFile file, IWorkbenchPage page) {
		this(file, page)
		diagram.init

	}

	new(ProcessModel model, IFile file, IWorkbenchPage page) {
		this(file, page)
		if (model != null) {
			this.model = model
		}

		startable = true
	}

	new(IFile file, IWorkbenchPage page) {
		super("Transform to simulation-format")

		this.saveFile = file
		this.page = page
	}

	private def init(BPMNDiagram it) {

		// Get BPMN-Process
		val bpmnProcess = plane.bpmnElement as Process;

		// BUG - If a new BPMN-editor is opened with a new bpmn-process from the
		// wizard,
		// process will be null
		// assert (process != null);
		if (bpmnProcess != null) {

			// Set up flow elements to be transformed
			bpmnElementList = new ArrayList<FlowElement>();

			bpmnProcess.flowElements.filter(typeof(FlowNode)).forEach [ e |
				bpmnElementList.add(e)
			]

			bpmnDiagram = it;
			startable = true;
		} else {
			startable = false;

		// createErrorMessage();
		}
	}

	override protected run(IProgressMonitor monitor) {
		if (!startable) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID,
				"Transformation not possible. This is a bug: BPMN2-Editor is not correctly initialized. Please close and reopen the editor")
		}

		monitor.beginTask("Starting Transformation", IProgressMonitor.UNKNOWN)

		val transformationSet = monitor.startTransformation

		monitor.beginTask("Opening and initializing simMeta-Editor", IProgressMonitor.UNKNOWN)

		openEditor(transformationSet, monitor)

		monitor.done

		return new Status(IStatus.OK, Activator.PLUGIN_ID, "Transformation finished.")
	}

	private def openEditor(Set<? extends ISimulationElement> transformedObjects, IProgressMonitor monitor) {
		Display.getDefault.syncExec(
			new Runnable() {

				override run() {
					monitor.beginTask("Creating diagram...", IProgressMonitor.UNKNOWN)
					val diagram = peService.createDiagram(SIMULATION_DIAGRAM_TYPE_ID, saveFile.name, false)

					val uri = URI.createPlatformResourceURI(saveFile.fullPath.toOSString, true)

					FileService.createEmfFileForDiagram(uri, diagram)

					val providerId = GraphitiUi.getExtensionManager().getDiagramTypeProviderId(
						SIMULATION_DIAGRAM_TYPE_ID)

					// Create lightweight input instance
					val editorInput = DiagramEditorInput.createEditorInput(diagram, providerId)

					// Create editor based on diagram and providerId off of the
					// editorInput
					val editor = page.openEditor(editorInput, DiagramEditor.DIAGRAM_EDITOR_ID) as DiagramEditor

					// Create transactional editing domain
					val editingDomain = editor.editingDomain

					monitor.beginTask("Adding elements to resource ... ", IProgressMonitor.UNKNOWN);

					// Create resource-set
					val resource = editingDomain.resourceSet.resources.head;
					addSimulationElementsToResource(resource, editingDomain, transformedObjects);

					// Add graphical representation for business objects
					monitor.beginTask("Adding elements to editor ... ", IProgressMonitor.UNKNOWN);

					addBusinessObjectsToEditor(transformedObjects, editor);

					// Save editor
					editor.doSave(new NullProgressMonitor());

					// Check process-structure
					monitor.beginTask("Validating elements...", IProgressMonitor.UNKNOWN);

					val validationStatus = preCheckBusinessObjects(editor);

					if (validationStatus.compareTo(ValidationStatus.STATUS_ERROR) == 0 || validationStatus.
						compareTo(ValidationStatus.VALIDATION_IMPOSSIBLE) == 0) {
						// Blablubb
					}

					monitor.done();
				}

				private def addBusinessObjectsToEditor(Set<? extends ISimulationElement> transformedObjects,
					DiagramEditor editor) {
					val fp = editor.diagramTypeProvider.featureProvider

					val pictoElements = addGraphicalRepresentation(transformedObjects, editor) as Set<PictogramElement>

					addConnections(fp, pictoElements)
				}

				private def addConnections(IFeatureProvider fp, Set<PictogramElement> pictoElements) {

					pictoElements.filter(typeof(ContainerShape)).forEach [ p |
						val bo = p.link.businessObjects.head as ISimulationElement
						if (!(bo instanceof ISink)) {
							bo.outgoing.forEach [ r |
								val targetContainer = getTargetPictogramElement(r, pictoElements) as ContainerShape
								if (targetContainer != null) {
									val targetAnchor = targetContainer.anchors.head

									val acc = new AddConnectionContext(p.anchors.head, targetAnchor)
									acc.newObject = r

									fp.addIfPossible(acc)
								}
							]
						}
					]
				}

				private def getTargetPictogramElement(IRelation r, Set<PictogramElement> elements) {
					elements.filter [ e |
						val head = e.link.businessObjects.head
						head.equals(r.target)
					].head

				}

				private def addGraphicalRepresentation(Set<? extends ISimulationElement> transformedObjects,
					DiagramEditor editor) {

					val retVal = newHashSet

					val diagram2 = editor.diagramTypeProvider.diagram
					val fp = editor.diagramTypeProvider.featureProvider

					transformedObjects.forEach [
						val addContext = initAddContext(diagram2)
						var x = 0
						var pos = new Position(x, 0)
						if (graphicalRepresentations.containsKey(name)) {
							pos = graphicalRepresentations.get(name) as Position
							x = pos.x + 50
						}
						addContext.x = pos.x
						addContext.y = pos.y
						val pic = fp.addIfPossible(addContext)
						retVal.add(pic)
					]

					retVal

				}

				def getGraphicalRepresentations() {
					var Map<String, Position> retVal = null

					if (bpmnDiagram != null) {
						retVal = GraphicalHelper.copyGraphicalCoordinates(bpmnDiagram)
					} else if (model != null) {
						retVal = GraphicalHelper.copyGraphicalCoordinates(model.nodes)
					}

					retVal
				}

				private def initAddContext(ISimulationElement o, Diagram diagram) {
					new AddContext => [
						newObject = o
						targetContainer = diagram
					]
				}
			})
	}

	private def startTransformation(IProgressMonitor it) {
		subTask("Performing jpmmt-to-sim-meta-transformation")
		beginTask("Creating simulation-elements... ", IProgressMonitor.UNKNOWN)

		var transformationSet = newHashSet as Set<? extends ISimulationElement>

		//tranform generic jpmmt
		if (model != null) {
			subTask("Performing jpmmt-to-sim-meta-transformation")
			worked(IProgressMonitor.UNKNOWN)

			//			val nodes = model.nodes
			val nodes = newHashSet
			model.nodes.forEach [
				nodes.add(it)
			]

			val edges = model.edges

			val inj = createInjector(new GeneratorModule)
			transformationSet = inj.getInstance(typeof(GenericTransformation)).transform(nodes, edges)
			done
		}
		//Tranform bpmn-element-list
		else if (bpmnElementList != null) {
			subTask("Performing bpmn2-to-sim-meta-transformation")
			worked(IProgressMonitor.UNKNOWN)

			val inj = createInjector(new GeneratorModule)
			transformationSet = inj.getInstance(typeof(TransformBPMN2ToSimulation)).startTransformation(bpmnElementList)
			done
		}

		transformationSet
	}

	private def preCheckBusinessObjects(IEditorPart it) {
		if (it instanceof IDiagramContainer) {
			val container = it as IDiagramContainer
			val resources = container.diagramBehavior.editingDomain.resourceSet.resources
			val iLog = Activator.getDefault.log

			val filtered = resources.filter(typeof(XMIResource)).head
			val simulationValidator = new SimulationValidator(filtered, iLog, Activator.PLUGIN_ID)
			return simulationValidator.validate
		}
		return ValidationStatus.VALIDATION_IMPOSSIBLE
	}

	private def addSimulationElementsToResource(Resource r, TransactionalEditingDomain domain,
		Set<? extends ISimulationElement> transformationSet) {

		domain.commandStack.execute(
			new RecordingCommand(domain) {

				override protected doExecute() {
					addSources(transformationSet.sources)
					addActivities(transformationSet.activities)
					addSinks(transformationSet.sinks)
					try {
						r.save(null)
					} catch (IOException e) {
						e.printStackTrace
					}
				}

				private def addSinks(Iterable<ISink> it) {
					forEach [ s |
						r.contents.add(s)
					]
				}

				private def addActivities(Iterable<IActivity> it) {
					forEach [ a |
						r.contents => [
							add(a)
							add(a.capacity)
							add(a.timePeriod)
							add(a.timePeriod.period)
						]
					]
				}

				private def addSources(Iterable<ISource> it) {
					forEach [ s |
						r.contents => [
							add(s)
							add(s.firstEntity)
							add(s.firstEntity.period)
							add(s.newEntities)
							add(s.newEntities.period)
							add(s.processedObject)
						]
					]
				}

			})
	}

	private def getSources(Set<? extends ISimulationElement> it) {
		filter(typeof(ISource))
	}

	private def getActivities(Set<? extends ISimulationElement> it) {
		filter(typeof(IActivity))
	}

	private def getSinks(Set<? extends ISimulationElement> it) {
		filter(typeof(ISink))
	}

}
