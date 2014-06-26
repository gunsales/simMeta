package de.lsem.simulation.transformation.anylogic.transform.helper;

import java.util.List;
import java.util.Set;

import de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClass;
import de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses;
import de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.AnyLogicWorkspace;
import de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.ClientAreaTopLeft;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connectors;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Control;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Controls;
import de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments;
import de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Font;
import de.lsem.simulation.transformation.anylogic.generator.persistant.FontExtendedProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Frame;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Label;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Model;
import de.lsem.simulation.transformation.anylogic.generator.persistant.ModelTimeProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties;
import de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes;
import de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Text;
import de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty;

/**
 * This class contains default values for Anylogic-specific objects. It allows
 * the generation of an Anylogic-Workspace-Object, which will recursively build
 * sub-elements.
 * 
 * @author Lewin
 * 
 */
public class ClassGenerator implements Costants {

	public static AnyLogicWorkspace createAnylogicWorkspace(String projectName,
			List<EmbeddedObject> objects, Set<Connector> connections) {
		AnyLogicWorkspace anyLogicWorkspace = new AnyLogicWorkspace();
		anyLogicWorkspace.setAlpVersion("6.6.0");
		anyLogicWorkspace.setAnyLogicVersion("6.9.0.201310021509");
		anyLogicWorkspace.setWorkspaceVersion(1.9f);
		Model model = createModel(createValidProjectName(projectName), objects);
		anyLogicWorkspace.setModel(model);

		ActiveObjectClass activeObjectClass = model.getActiveObjectClasses()
				.getActiveObjectClass();
		Connectors c = new Connectors();
		c.setConnector(connections.toArray(new Connector[connections.size()]));
		activeObjectClass.setConnectors(c);
		Experiments experiments = createExperiments(activeObjectClass,
				model.getId());
		model.setExperiments(experiments);

		return anyLogicWorkspace;
	}

	private static String createValidProjectName(String projectName) {
		return projectName.replaceAll(" ", "_").replace("-", "");
	}

	private static ActiveObjectClass createActiveObjectClass(
			List<EmbeddedObject> objects) {
		ActiveObjectClass activeObjectClass = new ActiveObjectClass();
		activeObjectClass.setId(IDGenerator.generateID());
		activeObjectClass.setName("<![CDATA[Main]]>");
		ClientAreaTopLeft clientAreaTopLeft = createClientAreaTopLeft();
		activeObjectClass.setClientAreaTopLeft(clientAreaTopLeft);
		activeObjectClass.setPresentationTopGroupPersistent(T);
		activeObjectClass.setIconTopGroupPersistent(T);
		activeObjectClass.setGeneric(F);
		activeObjectClass.setGenericParameters("<![CDATA[T]]>");
		activeObjectClass
				.setGenericParametersLabel("<![CDATA[Generic parameters:]]>");
		activeObjectClass.setSamplesToKeep(100);
		activeObjectClass.setLimitNumberOfArrayElements(F);
		activeObjectClass.setElementsLimitValue(100);
		activeObjectClass.setMakeDefaultViewArea(T);
		activeObjectClass.setSceneGridColor("");
		activeObjectClass.setSceneBackgroundColor("");

		AgentProperties agentProperties = createAgentProperties();
		activeObjectClass.setAgentProperties(agentProperties);

		DatasetsCreationProperties datasetsCreationProperties = createDatasetsCreationProperties();
		activeObjectClass
				.setDatasetsCreationProperties(datasetsCreationProperties);

		EmbeddedObject[] objArray = new EmbeddedObject[objects.size()];
		for (int i = 0; i < objArray.length; i++) {
			EmbeddedObject dummy = enrichEmbeddedObject(objects.get(i));
			objArray[i] = dummy;
		}
		EmbeddedObjects embeddedObjects = new EmbeddedObjects();
		embeddedObjects.setEmbeddedObject(objArray);
		activeObjectClass.setEmbeddedObjects(embeddedObjects);

		return activeObjectClass;
	}

	private static DatasetsCreationProperties createDatasetsCreationProperties() {
		DatasetsCreationProperties datasetsCreationProperties = new DatasetsCreationProperties();
		datasetsCreationProperties.setAutoCreate(T);
		datasetsCreationProperties.setRecurrenceCode("<![CDATA[1]]>");
		return datasetsCreationProperties;
	}

	private static Model createModel(String projectName,
			List<EmbeddedObject> objects) {
		Model model = new Model();

		model.setId(IDGenerator.generateID());
		model.setName("<![CDATA[" + projectName + "]]>");
		model.setEngineVersion(6);
		model.setJavaPackageName("<![CDATA[" + projectName + "]]>");
		model.setModelTimeUnit("<![CDATA[Minute]]>");

		ActiveObjectClasses activeObjectClasses = createActiveObjectClasses(objects);
		model.setActiveObjectClasses(activeObjectClasses);
		model.setRequiredLibraryReference(createRequiredLibraryReference());
		return model;
	}

	private static RequiredLibraryReference createRequiredLibraryReference() {
		RequiredLibraryReference requiredLibraryReference = new RequiredLibraryReference();
		requiredLibraryReference
				.setLibraryName("<![CDATA[com.xj.anylogic.libraries.enterprise]]>");
		requiredLibraryReference.setVersionMajor(6);
		requiredLibraryReference.setVersionMinor(0);
		requiredLibraryReference.setVersionBuild(3);
		return requiredLibraryReference;
	}

	private static ActiveObjectClasses createActiveObjectClasses(
			List<EmbeddedObject> objects) {
		ActiveObjectClasses activeObjectClasses = new ActiveObjectClasses();
		ActiveObjectClass activeObjectClass = createActiveObjectClass(objects);
		activeObjectClasses.setActiveObjectClass(activeObjectClass);
		return activeObjectClasses;
	}

	private static AgentProperties createAgentProperties() {
		AgentProperties agentProperties = new AgentProperties();
		agentProperties.setSpaceType("CONTINUOUS");
		agentProperties.setEnvironmentDefinesInitialLocation(T);
		return agentProperties;
	}

	private static ClientAreaTopLeft createClientAreaTopLeft() {
		ClientAreaTopLeft clientAreaTopLeft = new ClientAreaTopLeft();
		clientAreaTopLeft.setX(0);
		clientAreaTopLeft.setY(0);
		return clientAreaTopLeft;
	}

	private static Experiments createExperiments(
			ActiveObjectClass activeObjectClass, long modelID) {
		Experiments experiments = new Experiments();
		SimulationExperiment simulationExperiment = createSimulationExperiment(
				activeObjectClass, modelID);
		experiments.setSimulationExperiment(simulationExperiment);
		return experiments;
	}

	private static SimulationExperiment createSimulationExperiment(
			ActiveObjectClass activeObjectClass, long modelID) {
		SimulationExperiment experiment = new SimulationExperiment();
		experiment.setActiveObjectClassId(activeObjectClass.getId());
		experiment.setId(IDGenerator.generateID());
		experiment.setName("<![CDATA[Simulation]]>");
		experiment.setClientAreaTopLeft(createClientAreaTopLeft());
		experiment.setPresentationTopGroupPersistent(T);
		experiment.setIconTopGroupPersistent(T);
		experiment.setFrame(createFrame());
		experiment.setCommandLineArguments("<![CDATA[]]>");
		experiment.setMaximumMemory(64);
		experiment.setRandomNumberGenerationType("randomSeed");
		experiment.setCustomGeneratorCode("new Random()");
		experiment.setSeedValue(1);
		experiment.setRandomSelectionModeForSimultaneousEvents(F);
		experiment.setVmArgs("<![CDATA[]]>");
		experiment.setAbsoluteAccuracy(1.0E-5f);
		experiment.setRelativeAccuracy(1.0E-5f);
		experiment.setTimeAccuracy(1.0E-5f);
		experiment.setFixedTimeStep(0.0010f);
		experiment.setLoadRootFromSnapshot(F);
		experiment.setSnapshotFile("");
		experiment.setDiffEquationsNumericalMethod("EULER");
		experiment.setMixedEquationsNumericalMethod("RK45_NEWTON");
		experiment.setAlgebraicEquationsNumericalMethod("MODIFIED_NEWTON");

		experiment.setShapes(createShapes());
		experiment.setControls(createControls());
		experiment.setParameters("");
		experiment.setPresentationProperties(createPresentationProperties());
		experiment.setModelTimeProperties(createModelTimeProperties(modelID));

		return experiment;
	}

	private static ModelTimeProperties createModelTimeProperties(long modelID) {
		ModelTimeProperties modelTimeProperties = new ModelTimeProperties();

		modelTimeProperties.setUseCalendar(F);
		modelTimeProperties.setStopOption("<![CDATA[Never]]>");
		modelTimeProperties.setInitialDate("<![CDATA[" + modelID + "]]>");
		modelTimeProperties.setInitialTime("<![CDATA[0.0]]>");
		modelTimeProperties.setFinalDate("<![CDATA[" + modelID + "]]>");
		modelTimeProperties.setFinalTime("<![CDATA[100.0]]>");

		return modelTimeProperties;
	}

	private static PresentationProperties createPresentationProperties() {
		PresentationProperties pp = new PresentationProperties();

		pp.setEnableAdaptiveFrameManagement(T);
		pp.setEnableAntiAliasing(T);
		pp.setEnableEnhancedModelElementsAnimation(T);
		pp.setEnablePanning(T);
		pp.setToolbarCustomizableAtRuntime(T);
		pp.setEnableZoom(T);

		pp.setExecutionMode("<![CDATA[realTimeScaled]]>");
		pp.setCpuRatio("<![CDATA[ratio_1_2]]>");
		pp.setTitle("<![CDATA[Model : Simulation]]>");
		pp.setFramesPerSecond("<![CDATA[20.0]]>");
		pp.setRealTimeScale(1.0f);

		pp.addUIProperty(createUIProperty("Experiment Progress", F));
		pp.addUIProperty(createUIProperty("Simulation Progress", T));
		pp.addUIProperty(createUIProperty("Statusbar Events Per Second", F));
		pp.addUIProperty(createUIProperty("Statusbar Frames Per Second", F));
		pp.addUIProperty(createUIProperty("Statusbar Memory", T));
		pp.addUIProperty(createUIProperty("Statusbar Model Date", F));
		pp.addUIProperty(createUIProperty("Statusbar Model Step", F));
		pp.addUIProperty(createUIProperty("Statusbar Model Time", T));
		pp.addUIProperty(createUIProperty("Statusbar Real Time Of Simulation",
				F));
		pp.addUIProperty(createUIProperty("Statusbar Status", T));
		pp.addUIProperty(createUIProperty("Toolbar Animation setup", F));
		pp.addUIProperty(createUIProperty("Toolbar Execution control", T));
		pp.addUIProperty(createUIProperty("Toolbar File", F));
		pp.addUIProperty(createUIProperty("Toolbar Model navigation", T));
		pp.addUIProperty(createUIProperty("Toolbar Time scale setup", T));
		pp.addUIProperty(createUIProperty("Toolbar View", F));

		return pp;
	}

	private static UIProperty createUIProperty(String name, String value) {
		UIProperty uiProperty = new UIProperty();
		uiProperty.setName(name.replace("-", ""));
		uiProperty.setValue(value);
		return uiProperty;
	}

	private static Controls createControls() {
		Controls controls = new Controls();
		Control control = createControl();
		controls.setControl(control);
		return controls;
	}

	private static Control createControl() {
		Control control = new Control();
		control.setType("Button");
		control.setEmbeddedIcon(F);
		control.setId(IDGenerator.generateID());
		control.setName("<![CDATA[button]]>");
		control.setX(40);
		control.setY(120);
		control.setLabel(createLabel(10, 0));
		control.setPublicFlag(T);
		control.setPresentationFlag(T);
		control.setShowLabel(F);
		control.setBasicProperties(createBasicProperties(345, 30));
		control.setExtendedProperties(createExtendedProperties());

		return control;
	}

	private static ExtendedProperties createExtendedProperties() {
		ExtendedProperties extendedProperties = new ExtendedProperties();

		extendedProperties.setFont(createExtendedFont("Dialog", 11, 0));
		extendedProperties
				.setLabelText("<![CDATA[Fuehren Sie das Modell aus und wechseln Sie zur Hauptansicht]]>");
		extendedProperties
				.setLabelCode("<![CDATA[getState() == IDLE ? \"Fuehren Sie das Modell aus und wechseln Sie zur Hauptansicht\" : \"Umschaltung zur Hauptansicht\"]]>");
		return extendedProperties;
	}

	private static FontExtendedProperties createExtendedFont(String string,
			int i, int j) {
		FontExtendedProperties fep = new FontExtendedProperties();
		fep.setName(string);
		fep.setSize(i);
		fep.setStyle(j);
		return fep;
	}

	private static BasicProperties createBasicProperties(int width, int height) {
		BasicProperties basicProperties = new BasicProperties();
		basicProperties.setWidth(width);
		basicProperties.setHeight(height);
		basicProperties.setAsObject(T);
		basicProperties.setEmbeddedIcon(F);
		basicProperties
				.setActionCode("<![CDATA[if ( getState() == IDLE ) run(); getPresentation().setPresentable( getEngine().getRoot() );]]>");
		basicProperties.setFillColor("");
		basicProperties.setTextColor("-16777216");
		return basicProperties;
	}

	private static Shapes createShapes() {
		Shapes shapes = new Shapes();

		Text textModel = createText("text", "Model", 10, 0);
		Text textEinrichtungsseite = createText("text1",
				"Einrichtungsseite Experiment", 40, 63);

		shapes.addText(textModel);
		shapes.addText(textEinrichtungsseite);
		return shapes;
	}

	private static Text createText(String name, String textString, int x, int y) {
		Text text = new Text();
		text.setId(IDGenerator.generateID());
		text.setName("<![CDATA[" + name.replace("-", "") + "]]>");
		text.setX(x);
		text.setY(y);
		text.setLabel(createLabel(10, 0));
		text.setPublicFlag(T);
		text.setPresentationFlag(T);
		text.setShowLabel(F);
		text.setAsObject(T);
		text.setEmbeddedIcon(F);
		text.setLock(F);
		text.setShowIn3D(F);
		text.setZ(0);
		text.setRotation(0.0f);
		text.setColor(-16777216);
		text.setText("<![CDATA[" + textString + "]]>");
		text.setFont(createFont("Serif", 28, 1));
		text.setAlignment("LEFT");
		return text;
	}

	private static Font createFont(String name, int size, int style) {
		Font font = new Font();
		font.setName(name.replace("-", ""));
		font.setSize(size);
		font.setStyle(style);
		return font;
	}

	private static Label createLabel(int x, int y) {
		Label label = new Label();
		label.setX(x);
		label.setY(y);
		return label;
	}

	private static Frame createFrame() {
		Frame frame = new Frame();
		frame.setX(0);
		frame.setY(0);
		frame.setWidth(800);
		frame.setHeight(600);
		frame.setMaximized(F);
		frame.setCloseConfirmation(F);
		return frame;
	}

	private static EmbeddedObject enrichEmbeddedObject(
			EmbeddedObject embeddedObject) {

		embeddedObject.setCollectionType("ARRAY_LIST_BASED");
		embeddedObject.setGenericParametersSubstitute("<![CDATA[Entity]]>");
		embeddedObject.setPresentationFlag(T);
		embeddedObject.setPublicFlag(F);
		embeddedObject.setShowLabel(T);

		return embeddedObject;
	}

}
