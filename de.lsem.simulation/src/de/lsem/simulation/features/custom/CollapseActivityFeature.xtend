package de.lsem.simulation.features.custom

import de.lsem.repository.model.simulation.IActivity
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.ICustomContext
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext
import org.eclipse.graphiti.features.custom.AbstractCustomFeature
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.PictogramElement

import static de.lsem.simulation.util.LSEMElementHelper.*
import static org.eclipse.graphiti.services.Graphiti.*

class CollapseActivityFeature extends AbstractCustomFeature {

	public static val COLLAPSE = "Collapse"
	public static val EXPAND = "Expand"

	public static val IS_COLLAPSED = "isCollapsed"
	public static val INITIAL_WIDTH = "initial_width"
	public static val INITIAL_HEIGTH = "initial_height"

	new(IFeatureProvider fp) {
		super(fp)
	}

	override getName() {
		COLLAPSE
	}

	override canExecute(ICustomContext it) {
		if (pictogramElements != null && pictogramElements.length == 1) {
			val bo = getBusinessObjectForPictogramElement(pictogramElements.get(0))
			return bo instanceof IActivity
		}
		return false
	}

	override execute(ICustomContext it) {
		if (pictogramElements != null && pictogramElements.length == 1) {
			val picto = pictogramElements.get(0)
			val bo = getBusinessObjectForPictogramElement(picto)

			if (bo instanceof IActivity) {
				val activity = bo as IActivity
				if (!isSubActivity(contents, activity)) {
					createCollapsedPictogramElement(picto)
				}
			}
		}
	}

	def createCollapsedPictogramElement(PictogramElement it) {

		val width = graphicsAlgorithm.width
		val height = graphicsAlgorithm.height
		val cs = it as ContainerShape
		val activity = getBusinessObjectForPictogramElement as IActivity

		var changeWidth = 120
		var changeHeight = stretchCollapsedElementIfNeeded(activity) // Default: 210

		// If expand
		if (!activityCollapsed) {
			setPictoHeightAndWidth(it, width, height)
			peService.setPropertyValue(it, IS_COLLAPSED, String.valueOf(true))
		} 
		// If collapse
		else {
			changeWidth = it.initialWidth
			changeHeight = it.initialHeight

			peService.setPropertyValue(it, IS_COLLAPSED, String.valueOf(false))
		}

		val resizeContext = createResizeShapeContext(cs, changeWidth, changeHeight)
		executeResizeFeature(resizeContext)

		makeChildrenInvisible(cs, activityCollapsed)
	}

	private def void makeChildrenInvisible(ContainerShape it, boolean visible) {
		children.filter(typeof(ContainerShape)).forEach [ child |
			makeChildrenInvisible(child, visible)
			child.setVisible(visible)
			//Check whether the child-shape is visible or not
//			if (hasInOrOutAnchors) {
//				changeIncommingConnectionVisibility(firstAnchor, visible)
//				changeOutgoingConnectionVisibility(firstAnchor, visible)
//			}
		]
	}

//	private def changeOutgoingConnectionVisibility(Anchor it, boolean visible) {
//		outgoingConnections.forEach [ con |
//			con.visible = visible
//			setDecoratorVisibility(con, visible)
//		]
//	}
//
//	private def changeIncommingConnectionVisibility(Anchor it, boolean visible) {
//		incomingConnections.forEach [ con |
//			con.visible = visible
//			setDecoratorVisibility(con, visible)
//		]
//	}
//
//	private def setDecoratorVisibility(Connection con, boolean visible) {
//		con.connectionDecorators.forEach [ dec |
//			dec.visible = visible
//		]
//	}
//
//	private def getFirstAnchor(ContainerShape it) {
//		anchors.get(0)
//	}

//	private def hasInOrOutAnchors(ContainerShape shape) {
//		shape.anchors.size > 0
//	}

	//	private def isChildVisible(ContainerShape parent) {
	//		for(shape:parent.children){
	//			if(shape.graphicsAlgorithm instanceof Ellipse){
	//				return shape.visible
	//			}
	//		}
	//		return false
	////		parent.children.findFirst(e|e.graphicsAlgorithm instanceof Ellipse).visible
	//	}
	private def executeResizeFeature(ResizeShapeContext resizeContext) {
		featureProvider.getResizeShapeFeature(resizeContext).execute(resizeContext)
	}

	private def create it:new ResizeShapeContext(cs) createResizeShapeContext(ContainerShape cs, int changeWidth,
		int changeHeight) {
		setSize(changeWidth, changeHeight)
		setLocation(cs.graphicsAlgorithm.x, cs.graphicsAlgorithm.y)
	}

	private def setPictoHeightAndWidth(PictogramElement it, int width, int height) {
		peService.setPropertyValue(it, INITIAL_WIDTH, String.valueOf(width))
		peService.setPropertyValue(it, INITIAL_HEIGTH, String.valueOf(height))
	}

	private def getInitialHeight(PictogramElement it) {
		val initHeight = peService.getPropertyValue(it, INITIAL_HEIGTH)
		Integer.parseInt(initHeight)
	}

	private def getInitialWidth(PictogramElement it) {
		val initWidth = peService.getPropertyValue(it, INITIAL_WIDTH)
		Integer.parseInt(initWidth)
	}

	private def isActivityCollapsed(PictogramElement it) {
		val s = peService.getPropertyValue(it, IS_COLLAPSED)

		if (s == null || Boolean.parseBoolean(s).equals(false)) {
			return false
		}
		return true
	}

	private def stretchCollapsedElementIfNeeded(IActivity it) {
		if (amountOfSubActivities > 3) {
			return (amountOfSubActivities * 50 + 50)
		}
		return 210
	}

	private def getAmountOfSubActivities(IActivity activity) {
		activity.subActivities.size
	}

	private def getContents() {
		diagram.eResource.contents
	}

}
