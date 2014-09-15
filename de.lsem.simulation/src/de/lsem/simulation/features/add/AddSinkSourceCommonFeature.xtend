package de.lsem.simulation.features.add

import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import org.eclipse.graphiti.features.IFeatureProvider
import org.eclipse.graphiti.features.context.IAddContext
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle
import org.eclipse.graphiti.mm.algorithms.styles.Orientation
import org.eclipse.graphiti.mm.pictograms.ContainerShape
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.util.IColorConstant

import static org.eclipse.graphiti.services.Graphiti.*

class AddSinkSourceCommonFeature extends AbstractAddShapeFeature {

	var IColorConstant outerCircle
	var IColorConstant innerCircle
	var IColorConstant textForeground
	var Integer lineWidth

	static val fullWidth = 70
	static val fullHeight = 50
	static val width = 30
	static val height = 30

	new(IFeatureProvider fp, IColorConstant _outerCircle, IColorConstant _innerCircle, IColorConstant _textForeground,
		int _lineWidth) {
		super(fp)
		outerCircle = _outerCircle
		innerCircle = _innerCircle
		textForeground = _textForeground
		lineWidth = _lineWidth
	}

	override add(IAddContext it) {

		val targetDiagram = targetContainer as Diagram
		val conShape = peCreateService.createContainerShape(targetDiagram, true)
		val object = newObject as ISimulationElement

		createRectangle(conShape, it, fullWidth, fullHeight)
		createCircle(conShape, fullWidth, width, fullHeight, height)
		createDecoratorText(conShape, object, fullWidth)

		peCreateService.createChopboxAnchor(conShape)
		layoutPictogramElement(conShape)
		link(conShape, object)
		conShape
	}

	private def createDecoratorText(ContainerShape conShape, ISimulationElement object, int full_width) {
		val shape = peCreateService.createShape(conShape, false)
		val text = gaCreateService.createText(shape, object.name) => [
			foreground = manageColor(textForeground)
			horizontalAlignment = Orientation.ALIGNMENT_CENTER
			font = gaService.manageDefaultFont(diagram, false, true)
		]
		gaService.setLocationAndSize(text, 0, 35, full_width, 20)
	}

	private def createCircle(ContainerShape conShape, int full_width, int width, int full_height, int height) {
		val shape = peCreateService.createShape(conShape, false)
		val ellipse = gaService.createEllipse(shape) => [
			foreground = manageColor(outerCircle)
			background = manageColor(innerCircle)
			lineWidth = lineWidth
		]
		val startX = (full_width / 2) - (width / 2)
		val startY = (full_height / 2) - (height / 2)
		gaService.setLocationAndSize(ellipse, startX, startY, width, height)
	}

	private def createRectangle(ContainerShape conShape, IAddContext it, int full_width, int full_height) {
		val rect = gaCreateService.createRectangle(conShape) => [
			lineWidth = 0
			lineStyle = LineStyle.DOT
			lineVisible = true
			filled = false
		]

		gaService.setLocationAndSize(rect, x, y, full_width, full_height)
	}

	override canAdd(IAddContext it) {
		// OKTODO Test valuesSet!!
		(newObject instanceof ISource || newObject instanceof ISink) && targetContainer instanceof Diagram && valuesSet
	}

	private def valuesSet() {
		return (innerCircle != null && lineWidth != null && outerCircle != null && textForeground != null);
	}

	protected def setValues(IColorConstant outerCircle, IColorConstant innerCircle, IColorConstant textForeground,
		Integer lineWidth) {
		this.outerCircle = outerCircle
		this.innerCircle = innerCircle
		this.textForeground = textForeground
		this.lineWidth = lineWidth
	}

}
