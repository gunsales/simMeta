package de.lsem.simulation.transformation.anylogic.transform.generator;


import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GeneratorInjector implements ISetup{

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		return Guice.createInjector(new GeneratorModule());
	}
}
