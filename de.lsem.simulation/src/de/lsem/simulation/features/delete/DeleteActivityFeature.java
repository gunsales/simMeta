package de.lsem.simulation.features.delete;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.util.DeleteHelper;
import de.lsem.simulation.util.LSEMElementHelper;

/**
 * Remove the graphical representation and business-object
 * 
 * @author Lewin
 * 
 */
public class DeleteActivityFeature extends DefaultDeleteFeature {

	public DeleteActivityFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {

		PictogramElement pictogramElement = context.getPictogramElement();
		Object businessObjectForPictogramElement = getBusinessObjectForPictogramElement(pictogramElement);

		return businessObjectForPictogramElement instanceof IActivity;
	}

	@Override
	public void delete(IDeleteContext context) {

		if ( !getUserDecision() )
			return;

		try {
			IActivity activity = getActivityFrom(context);
			
			DeleteHelper.deleteIncommingConnections(getDiagram().eResource()
					.getContents(), activity);
			deleteActivityFromDiagram(activity);
			deleteFromTopActivity(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.delete(context);
	}

	private boolean deleteFromTopActivity(IActivity activity) {
		IActivity topActivity = LSEMElementHelper.getTopActivityFor(activity,
				getContentsOfDiagram());
		if (topActivity != null) {
			PictogramElement pictogramElement = getFeatureProvider()
					.getPictogramElementForBusinessObject(topActivity);

			topActivity.getSubActivities().remove(activity);
			
			UpdateContext uc = new UpdateContext(pictogramElement);
			getFeatureProvider().getUpdateFeature(uc).update(uc);
		}
		return false;
	}

	private IActivity getActivityFrom(IDeleteContext context) {
		PictogramElement pe = context.getPictogramElement();
		return (IActivity) getBusinessObjectForPictogramElement(pe);
	}

	private void deleteActivityFromDiagram(IActivity activity) throws Exception {

		deleteFromDiagram(activity.getCapacity());
		deleteFromDiagram(activity.getTimePeriod());
		deleteFromDiagram(activity.getTimePeriod().getPeriod());
	}

	private EList<EObject> getContentsOfDiagram() {
		return getDiagram().eResource().getContents();
	}

	private void deleteFromDiagram(final EObject e) {
		getContentsOfDiagram().remove(e);
	}

}
