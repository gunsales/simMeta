package de.lsem.simulation.transformation.mdb.generator;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

import de.lsem.simulation.transformation.mdb.xtend.ArenaTransformer;

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
		return ArenaTransformer.class;// null
	}

	public IWorkspaceRoot bindIWorkspaceRootToInstance() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
}
