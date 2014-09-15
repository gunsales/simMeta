package de.lsem.simulation.features.add

import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.features.custom.CollapseActivityFeature
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.context.impl.CustomContext
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle
import org.eclipse.graphiti.mm.algorithms.styles.Orientation
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.util.ColorConstant
import org.eclipse.graphiti.util.IColorConstant

import static de.lsem.simulation.util.LSEMElementHelper.*
import static org.eclipse.graphiti.services.Graphiti.*

class AddActivityFeature extends AbstractAddShapeFeature {

	public static val IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK
	public static val IColorConstant E_CLASS_FOREGROUND = new ColorConstant(105, 105, 105)
	public static val IColorConstant E_CLASS_BACKGROUND = new ColorConstant(229, 229, 229)

	val LINE_WIDTH = 1
	static val INITIAL_DISTANCE_TO_TOP = 30
	static val logger = Logger.getLogger(typeof(AddActivityFeature).simpleName)

	new(IFeatureProvider fp) {
		super(fp)
	}

	override add(IAddContext context) {
		val picto = createActivityGraphicalRepresentation(context)

		peCreateService.createChopboxAnchor(picto as ContainerShape)
		layoutPictogramElement(picto)

		picto
	}

	override canAdd(IAddContext it) {
		if (targetContainer instanceof ContainerShape) {
			val oldActivity = getBusinessObjectForPictogramElement(targetContainer)
			if (oldActivity instanceof IActivity && isSubActivity(diagram.eResource.contents, oldActivity as IActivity)) {
				return false
			}
		}

		newObject instanceof IActivity
	}

	private def createActivityGraphicalRepresentation(IAddContext it) {
		val width = 100
		val height = 50
		val colapsed_width = 120
		val newActivity = newObject as IActivity

		// Overall container				
		val containerShape = peCreateService.createContainerShape(activityTargetContainer, true)

		// Surrounding rectangle
		containerShape.setRoundedRectangle(it, width, height)

		// line underlining the name of element
		containerShape.setPictogramMiddleLine(colapsed_width)

		// element-name
		containerShape.setPictogramText(newActivity, width)

		logger.log(Level.INFO, "Fully added activity " + newActivity)

		// set as collapsed element
		peService.setPropertyValue(containerShape, CollapseActivityFeature.IS_COLLAPSED, String.valueOf(Boolean.FALSE))
		link(containerShape, newActivity)
		containerShape
	}

	private def setRoundedRectangle(ContainerShape containerShape, IAddContext it, int width, int height) {
		val roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5) => [
			foreground = manageColor(E_CLASS_FOREGROUND)
			background = manageColor(E_CLASS_BACKGROUND)
			lineWidth = LINE_WIDTH
		]

		// in case of drop on activity, recalculate position 
		val dropOnActivity = !(targetContainer instanceof Diagram)
		if (dropOnActivity) {
			positionOnActivity(it, roundedRectangle, width, height)
		} else {
			gaService.setLocationAndSize(roundedRectangle, x, y, width, height)
		}
	}

	private def setPictogramMiddleLine(ContainerShape containerShape, int colapsed_width) {
		val shapeForLine = peCreateService.createShape(containerShape, false)
		gaService.createPolyline(shapeForLine, #[0, 20, colapsed_width, 20]) => [
			foreground = manageColor(E_CLASS_FOREGROUND)
			lineWidth = LINE_WIDTH
		]
	}

	private def setPictogramText(ContainerShape containerShape, IActivity newActivity, int width) {
		val nameShape = peCreateService.createShape(containerShape, false)
		val text = gaService.createText(nameShape, newActivity.name ?: newActivity.serviceType.literal) => [
			foreground = manageColor(E_CLASS_TEXT_FOREGROUND)
			horizontalAlignment = Orientation.ALIGNMENT_CENTER
			font = gaService.manageDefaultFont(diagram, false, true)
		]
		gaService.setLocationAndSize(text, 0, 0, width, 20)
	}

	private def positionOnActivity(IAddContext it, RoundedRectangle roundedRectangle, int width, int height) {
		val topActivity = getBusinessObjectForPictogramElement(targetContainer) as IActivity

		var y = 30
		if (topActivity != null) {
			y = calculateYCoordinate(newObject as IActivity, topActivity)
		}

		gaService.setLocationAndSize(roundedRectangle, 10, y, width, height)

	}

	private def calculateYCoordinate(IActivity newActivity, IActivity topActivity) {

		// Drop from palate
		if (topActivity.getSubActivities().contains(newActivity)) {

			// Drop from activity existing in editor
			// Calculate position depending on the index as sub-activity
			return (topActivity.getSubActivities().indexOf(newActivity)) * 50 + INITIAL_DISTANCE_TO_TOP
		} else {

			// Append new sub-activity as last sub-activity
			return (topActivity.getSubActivities().size()) * 50 + INITIAL_DISTANCE_TO_TOP;
		}
	}

	private def getActivityTargetContainer(IAddContext it) {
		if (targetContainer instanceof Diagram) {
			return targetContainer as Diagram
		}
		collapseTopActivity(targetContainer)
		targetContainer
	}

	private def collapseTopActivity(ContainerShape shape) {
		val cc = new CustomContext
		cc.innerPictogramElement = shape
		cc.setPictogramElements(#[shape])
		val customFeatures = featureProvider.getCustomFeatures(cc)
		val collapseFeature = customFeatures.findFirst[it instanceof CollapseActivityFeature]

		val collapsed = peService.getPropertyValue(shape, CollapseActivityFeature.IS_COLLAPSED)
		if (collapsed != null) {
			val isCollapsed = Boolean.parseBoolean(collapsed)

			if (!isCollapsed && collapseFeature.canExecute(cc)) {
				collapseFeature.execute(cc)
			}
		}
	}

}
