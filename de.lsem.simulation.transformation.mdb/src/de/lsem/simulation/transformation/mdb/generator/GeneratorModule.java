package de.lsem.simulation.transformation.mdb.generator;

import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

public class GeneratorModule extends AbstractGenericResourceRuntimeModule {

	@Override
	protected String getLanguageName() {
		return "de.lsem.simulation";
	}

	@Override
	protected String getFileExtensions() {
		return "diagram";
	}
}
