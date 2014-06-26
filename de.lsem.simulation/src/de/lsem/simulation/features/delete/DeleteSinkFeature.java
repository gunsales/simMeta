package de.lsem.simulation.features.delete;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import de.lsem.repository.model.simulation.ISink;
import de.lsem.simulation.util.DeleteHelper;

public class DeleteSinkFeature extends DefaultDeleteFeature {

	public DeleteSinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		ISink sink = (ISink) getBusinessObjectForPictogramElement(context
				.getPictogramElement());

		if (sink != null) {
			DeleteHelper.deleteIncommingConnections(getDiagram().eResource()
					.getContents(), sink);
		}

		super.delete(context);
	}
}
