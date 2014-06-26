package de.lsem.simulation.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.repository.model.simulation.ISink;
import de.lsem.repository.model.simulation.ISource;

public class AddSinkSourceCommonFeature extends AbstractAddShapeFeature {

	private IColorConstant outerCircle;
	private IColorConstant innerCircle;
	private IColorConstant textForeground;
	private Integer lineWidth;

	public AddSinkSourceCommonFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		Object newObject = context.getNewObject();

		if (newObject instanceof ISink || newObject instanceof ISource) {
			return true;
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		assert (isValuesSet());
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		ContainerShape containerShape = peCreateService.createContainerShape(
				targetDiagram, true);

		int full_width = 70;
		int full_height = 50;

		int width = 30;
		int height = 30;

		IGaService gaCreateService = Graphiti.getGaService();
		ISimulationElement object = (ISimulationElement) context.getNewObject();

		
		// rectangle allowing text elements outside of the circle
		{
			Rectangle rectangle = gaCreateService
					.createRectangle(containerShape);
			
//			rectangle.setForeground(manageColor(IColorConstant.WHITE));
//			rectangle.setBackground(manageColor(IColorConstant.WHITE));
			rectangle.setLineWidth(0);
			rectangle.setLineStyle(LineStyle.DOT);
			rectangle.setLineVisible(true);
			rectangle.setFilled(false);

			gaCreateService.setLocationAndSize(rectangle, context.getX(),
					context.getY(), full_width, full_height);

//			Rectangle newRectangle = gaCreateService.createInvisibleRectangle(containerShape);
//			gaCreateService.setLocationAndSize(newRectangle, context.getX(),
//					context.getY(), full_width, full_height);
			
//			link(containerShape, object);
		}
		// Kreis
		{
			Shape shape = peCreateService.createShape(containerShape, false);

			Ellipse ellipse = gaCreateService.createEllipse(shape);
			ellipse.setForeground(manageColor(outerCircle));
			ellipse.setBackground(manageColor(innerCircle));
			ellipse.setLineWidth(lineWidth);
			gaCreateService.setLocationAndSize(
					ellipse, 
					(full_width / 2) - (width / 2), 
					(full_height / 2) - (height / 2), 
					width,
					height);

//			link(shape, object);
		}
		// Text nich im Kreis Junge
		{
			Shape shape = peCreateService.createShape(containerShape, false);

			Text text = gaCreateService.createText(shape, object.getName());
			text.setForeground(manageColor(textForeground));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setFont(gaCreateService.manageDefaultFont(getDiagram(), false, true));

			// OKTODO Check parameters! Default: text, 0, 0, width, 20
			gaCreateService.setLocationAndSize(text, 0, 35, full_width, 20);// text,
																		// text.getX(),
																		// text.getY(),
																		// text.getWidth(),
																		// text.getHeight());

//			link(shape, object);
		}

		// ANCHOR
		peCreateService.createChopboxAnchor(containerShape);
		layoutPictogramElement(containerShape);
		link(containerShape, object);

		return containerShape;
	}

	private boolean isValuesSet() {
		return (innerCircle != null && lineWidth != null && outerCircle != null && textForeground != null);
	}

	// protected PictogramElement specialEdd( ) {
	//
	// return this.add(context);
	// }

	protected void setValues(IColorConstant outerCircle,
			IColorConstant innerCircle, IColorConstant textForeground,
			Integer lineWidth) {
		this.outerCircle = outerCircle;
		this.innerCircle = innerCircle;
		this.textForeground = textForeground;
		this.lineWidth = lineWidth;
	}
}
