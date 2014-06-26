package de.lsem.simulation.transformation.sim.helper;

public interface ExtensionNameHelper {

	public static final String[] FILTER_EXTENSIONS = new String[] { "*.pnml; *.json", "*.*" };

	public static final String[] FILTER_NAMES = new String[] {
			"Petri-Net via Yasper (*.pnml) or Oryx BPMN-JSON file (*.json)",
			"All (*.*)" };

	static final String PNML_EXTENSION_STRING = "pnml";
	static final String EPML_EXTENSION_STRING = "epml";
	static final String BPMN_ECLIPSE_EDITOR_EXTENSION = "bpmn2";
	static final String BPMN_ORYX_EDITOR_EXTENSION = "json";
	static final String DIAGRAM_EXTENSION = ".diagram";
}
