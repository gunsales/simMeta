package de.lsem.simulation.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.lsem.repository.model.simulation.ISink;


public class AddSinkFeature extends AddSinkSourceCommonFeature {

	private static final IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;

	private static final IColorConstant E_CLASS_OUTER_CIRCLE = new ColorConstant(
			105, 105, 105);

	private static final IColorConstant E_CLASS_INNER_CIRCLE = new ColorConstant(
			238, 50, 50);

	private static final Integer LINE_WIDTH = 2;

	public AddSinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof ISink
				&& context.getTargetContainer() instanceof Diagram)
			return true;
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		setValues(E_CLASS_OUTER_CIRCLE, E_CLASS_INNER_CIRCLE, E_CLASS_TEXT_FOREGROUND, LINE_WIDTH);
		return super.add(context);
	}

}
