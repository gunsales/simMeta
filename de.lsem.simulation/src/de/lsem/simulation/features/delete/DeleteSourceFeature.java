package de.lsem.simulation.features.delete;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import de.lsem.repository.model.simulation.ISource;

public class DeleteSourceFeature extends DefaultDeleteFeature {

	public DeleteSourceFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void delete(IDeleteContext context) {
		PictogramElement pe = context.getPictogramElement();
		ISource source = (ISource) getBusinessObjectForPictogramElement(pe);
		try {
			getDiagram().eResource().getContents()
					.remove(source.getFirstEntity().getPeriod());
		} catch (Exception e) {
		}
		try {
			getDiagram().eResource().getContents()
					.remove(source.getFirstEntity());
		} catch (Exception e) {
		}
		try {
			getDiagram().eResource().getContents()
					.remove(source.getNewEntities().getPeriod());
		} catch (Exception e) {
		}
		try {
			getDiagram().eResource().getContents()
					.remove(source.getNewEntities());
		} catch (Exception e) {
		}
		try {
			getDiagram().eResource().getContents()
					.remove(source.getProcessedObject());
		} catch (Exception e) {
		}
		super.delete(context);
	}
}
