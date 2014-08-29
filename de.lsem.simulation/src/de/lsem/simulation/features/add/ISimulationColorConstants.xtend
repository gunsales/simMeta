package de.lsem.simulation.features.add

import org.eclipse.graphiti.util.ColorConstant
import org.eclipse.graphiti.util.IColorConstant

interface ISimulationColorConstants {

	// ************ SOURCE ********
	static val IColorConstant E_CLASS_SOURCE_TEXT_FOREGROUND = IColorConstant.BLACK;
	static val IColorConstant E_CLASS_SOURCE_OUTER_CIRCLE = new ColorConstant(105, 105, 105);
	static val IColorConstant E_CLASS_SOURCE_INNER_CIRCLE = new ColorConstant(60, 179, 113);
	
	// ********** SINK ********
	static val IColorConstant E_CLASS_SINK_TEXT_FOREGROUND = IColorConstant.BLACK;
	static val IColorConstant E_CLASS_SINK_OUTER_CIRCLE = new ColorConstant(105, 105, 105);
	static val IColorConstant E_CLASS_SINK_INNER_CIRCLE = new ColorConstant(238, 50, 50);
}
