package de.lsem.simulation.features.add

import org.eclipse.graphiti.features.IFeatureProvider

class AddSinkFeature extends AddSinkSourceCommonFeature implements ISimulationColorConstants{
	
	static val LINE_WIDTH = 2
	
	new(IFeatureProvider fp) {
		super(fp, E_CLASS_SINK_OUTER_CIRCLE, E_CLASS_SINK_INNER_CIRCLE, E_CLASS_SINK_TEXT_FOREGROUND, LINE_WIDTH)
	}
	
}