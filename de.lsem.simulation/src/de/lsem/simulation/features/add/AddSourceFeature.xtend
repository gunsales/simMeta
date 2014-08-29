package de.lsem.simulation.features.add

import org.eclipse.graphiti.features.IFeatureProvider

class AddSourceFeature extends AddSinkSourceCommonFeature implements ISimulationColorConstants {

	static val LINE_WIDTH = 1

	new(IFeatureProvider fp) {
		super(fp, E_CLASS_SOURCE_OUTER_CIRCLE, E_CLASS_SOURCE_INNER_CIRCLE, E_CLASS_SOURCE_TEXT_FOREGROUND, LINE_WIDTH)
	}

}
