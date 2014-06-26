package de.lsem.simulation.features.custom;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.features.create.CreateActivityFeature;

public class CustomFeatureHelper {

	private static final int ACTIVITY_HEIGHT = 300;
	private static final int ACTIVITY_WIDTH = 120;
	// private static final int ACTIVITY_WIDTH_FOLDED = 50;
	private static final String MERGE_SEPARATOR = "+";
//	private static final int INITIAL_Y_COORDINATE_DISTANCE = 25;
	private static final String DEFAULT_ACTIVITY_NAME = "Activity";
	private static int createPosY = 1;
	private static int createPosX = 1;

	protected static IActivity createActivity(
			List<IActivity> newSubActivities,
			IFeatureProvider featureProvider) {

		List<PictogramElement> pictos = new ArrayList<PictogramElement>();
		for (IActivity a : newSubActivities) {
			PictogramElement pictogramElement = featureProvider.getPictogramElementForBusinessObject(a);
			pictos.add(pictogramElement);
		}
		// Y- / X-Coordinate for the activity to be generated on
		setCreatePosition(pictos);

		for (ICreateFeature createFeature : featureProvider.getCreateFeatures()) {
			if (createFeature instanceof CreateActivityFeature) {

				// Create new activity
				IActivity topActivity = createTopActivity(
						newSubActivities, createFeature);

				return topActivity;
			}
		}
		return null;
	}

	private static CreateContext createCreateContext(Diagram diagram) {
		CreateContext createContext = new CreateContext();
		createContext.setTargetContainer(diagram);
		createContext.setHeight(ACTIVITY_HEIGHT);
		createContext.setWidth(ACTIVITY_WIDTH);
		createContext.setLocation(createPosX, createPosY);
		return createContext;
	}

	private static IActivity createTopActivity(List<IActivity> subActivities,
			ICreateFeature c) {

		// init new top activity-context
		CreateContext createContext = createCreateContext(c
				.getFeatureProvider().getDiagramTypeProvider().getDiagram());

		// create new top activity
		IActivity topActivity = (IActivity) c.create(createContext)[0];

		// Add sub-activities
		topActivity.getSubActivities().addAll(subActivities);

		// create merged name
		topActivity.setName(createMergedActivityName(subActivities));
		return topActivity;
	}

	private static String createMergedActivityName(List<IActivity> elementList) {
		StringBuffer nameDummy = new StringBuffer();
		for (IActivity a : elementList) {
			String name = a.getName() == null ? DEFAULT_ACTIVITY_NAME : a
					.getName();
			nameDummy.append(name);
			nameDummy.append(MERGE_SEPARATOR);
		}
		if (nameDummy.toString().length() > 1)
			return nameDummy.toString().substring(0,
					nameDummy.toString().length() - 1);
		else
			return nameDummy.toString();
	}

	private static void setCreatePosition(List<PictogramElement> pictogramElements) {
		assert pictogramElements.size() > 0;
		createPosX = 0;
		createPosY = 0;
		for (PictogramElement p : pictogramElements) {
			createPosX += p.getGraphicsAlgorithm().getX();
			createPosY += p.getGraphicsAlgorithm().getY();
		}

		createPosX /= pictogramElements.size();
		createPosY /= pictogramElements.size();

	}

}
