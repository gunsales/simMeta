package de.lsem.simulation.transformation.anylogic.transform.generator;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import com.google.inject.Binder;

import de.lsem.simulation.transformation.anylogic.transform.xtend.MainTransformer;

public class GeneratorModule extends AbstractGenericResourceRuntimeModule {

	public Class<? extends ResourceSet> bindResourceSet() {	
		return ResourceSetImpl.class;
	}
	
	@Override
	public void configure(Binder binder) {
		super.configure(binder);
	}
	
	@Override
	protected String getLanguageName() {
		return "de.lsem.simulation";
	}

	@Override
	protected String getFileExtensions() {
		return "diagram";
	}

	public Class<? extends Object> bindIGenerator() {
		return MainTransformer.class;
	}
//
//	public IWorkspaceRoot bindIWorkspaceRootToInstance() {
//		return ResourcesPlugin.getWorkspace()
//				.getRoot();
//	}
}
