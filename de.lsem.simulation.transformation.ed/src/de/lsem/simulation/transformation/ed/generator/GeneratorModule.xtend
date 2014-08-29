package de.lsem.simulation.transformation.ed.generator

import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule

class GeneratorModule extends AbstractGenericResourceRuntimeModule {
	
	override protected getFileExtensions() {
		"diagram"
	}
	
	override protected getLanguageName() {
		"de.lsem.simulation"
	}
	
}