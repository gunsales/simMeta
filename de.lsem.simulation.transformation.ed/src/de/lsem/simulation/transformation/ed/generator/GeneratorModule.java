package de.lsem.simulation.transformation.ed.generator;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import de.lsem.simulation.transformation.ed.xtext.MainGenerator;

public class GeneratorModule extends AbstractGenericResourceRuntimeModule {

	public Class<? extends ResourceSet> bindResourceSet() {	
		return ResourceSetImpl.class;
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
		return MainGenerator.class;//null
	}
//public void bindBla(Binder b){
//	b.bind(MainGenerator.class).to(MainGenerator.class).in(Scopes.SINGLETON);
//}
	public org.eclipse.core.resources.IWorkspaceRoot bindIWorkspaceRootToInstance() {
		return org.eclipse.core.resources.ResourcesPlugin.getWorkspace()
				.getRoot();
	}
}