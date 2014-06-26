package de.lsem.simulation.features.create;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.SinkHelper;

public class CreateSinkFeature extends AbstractCreateFeature {

	public CreateSinkFeature(IFeatureProvider fp) {
		super(fp, "Sink", "Create Sink Element");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		ISink sink = (ISink)SimulationFactory.eINSTANCE.createSink();
		sink.setName("Sink_" + createInitialSinkNumber());
		getDiagram().eResource().getContents().add(sink);

		addGraphicalRepresentation(context, sink);
		return new Object[] { sink };
	}

	private int createInitialSinkNumber() {
		return SinkHelper.getISinksFromDiagram(getDiagram().eResource().getContents()).size() + 1;
	}

}
