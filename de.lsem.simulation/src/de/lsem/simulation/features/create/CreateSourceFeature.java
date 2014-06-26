package de.lsem.simulation.features.create;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.SourceHelper;


public class CreateSourceFeature extends AbstractCreateFeature {

	public CreateSourceFeature(IFeatureProvider fp) {
		super(fp, "Source", "Create Source Element");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		ISource source = createFullSource(context);
		return new Object[] { source };
	}

	private ISource createFullSource(ICreateContext context){
		ISource source = SimulationFactory.eINSTANCE.createSource();
		source.setName("Source_" + createInitialSourceNumber());

		ITime first = SimulationFactory.eINSTANCE.createTime();
		ITime following = SimulationFactory.eINSTANCE.createTime();
		IGood good = SimulationFactory.eINSTANCE.createGood();

		source.setFirstEntity(first);
		source.setNewEntities(following);
		source.setProcessedObject(good);
		
		getDiagram().eResource().getContents().add(source);
		getDiagram().eResource().getContents().add(good);
		getDiagram().eResource().getContents().add(first);
		getDiagram().eResource().getContents().add(following);


		addGraphicalRepresentation(context, source);
		
		return source;		
	}

	private String createInitialSourceNumber() {
		EList<EObject> contents = getDiagram().eResource().getContents();
		int size = SourceHelper.getISourcesFromDiagram(contents).size();
		
		return String.valueOf(size+1);
	}

}
