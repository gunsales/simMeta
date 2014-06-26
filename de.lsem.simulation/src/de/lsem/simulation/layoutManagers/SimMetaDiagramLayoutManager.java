package de.lsem.simulation.layoutManagers;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DiagramEditor;

import de.cau.cs.kieler.kiml.graphiti.GraphitiDiagramLayoutManager;

public class SimMetaDiagramLayoutManager extends GraphitiDiagramLayoutManager {

	@Override
	public boolean supports(Object object) {
		return object instanceof DiagramEditor
				|| object instanceof PictogramElement;
	}
//	
//	@Override
//	protected KNode createNode(final LayoutMapping<PictogramElement> mapping,
//			final KNode parentNode, final Shape shape) {
//		KNode node = KimlUtil.createInitializedNode();
//		node.setParent(parentNode);
//
//		setCurrentPositionAndSize(mapping, parentNode, node, shape);
//
//		mapping.getGraphMap().put(node, shape);
//
//		// gather all connections connected to Internal ports in the diagram.
//		// It is of no use to ActorRefs as they do-not possess direct
//		// connection(They have all connections via port).
//		for (Anchor anchor : shape.getAnchors()) {
//			mapping.getProperty(KimlGraphitiUtil.CONNECTIONS).addAll(
//					anchor.getOutgoingConnections());
//		}
//
//		return node;
//	}
//
//	protected void setCurrentPositionAndSize(
//			final LayoutMapping<PictogramElement> mapping,
//			final KNode parentNode, final KGraphElement kelem, final Shape shape) {
//
//		VolatileLayoutConfig staticConfig = mapping
//				.getProperty(KimlGraphitiUtil.STATIC_CONFIG);
//
//		KShapeLayout shapeLayout = kelem.getData(KShapeLayout.class);
//		GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
//
//		// Calculate and set the invisible insets
//		KInsets shapeInsets = KimlGraphitiUtil.calcInsets(ga);
//		shapeLayout
//				.setProperty(GraphitiLayoutCommand.INVIS_INSETS, shapeInsets);
//		staticConfig.setValue(GraphitiLayoutCommand.INVIS_INSETS, kelem,
//				LayoutContext.GRAPH_ELEM, shapeInsets);
//
//		// Get the parent insets
//		KInsets parentInsets = parentNode == null ? null : parentNode.getData(
//				KShapeLayout.class).getProperty(
//				GraphitiLayoutCommand.INVIS_INSETS);
//
//		// Set Position
//		if (parentInsets == null) {
//			shapeLayout.setPos(ga.getX() + shapeInsets.getLeft(), ga.getY()
//					+ shapeInsets.getTop());
//		} else {
//			shapeLayout.setPos(
//					ga.getX() + shapeInsets.getLeft() - parentInsets.getLeft(),
//					ga.getY() + shapeInsets.getTop() - parentInsets.getTop());
//		}
//
//		// Set Size
//		shapeLayout
//				.setSize(
//						shape.getContainer().getGraphicsAlgorithm().getWidth() / 2
//						- shapeInsets.getLeft()
//								- shapeInsets.getRight()
//						,
//						shape.getContainer().getGraphicsAlgorithm().getHeight() / 2 
//						- shapeInsets.getTop()
//								- shapeInsets.getBottom())
//						;
//
//		// the modification flag must initially be false
//		((KShapeLayoutImpl) shapeLayout).resetModificationFlag();
//
//	}


}
