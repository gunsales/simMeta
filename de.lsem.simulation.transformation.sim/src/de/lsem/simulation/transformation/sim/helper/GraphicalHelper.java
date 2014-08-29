package de.lsem.simulation.transformation.sim.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;

import de.lsem.process.model.ProcessNode;
import de.lsem.simulation.transformation.sim.xtend.CommonTransformation;

public class GraphicalHelper {

	public static final double STRETCH_FACTOR = 1.9;

	public static Map<String, Position> copyGraphicalCoordinates(
			BPMNDiagram bpmnDiagram) {

		BPMNPlane plane = bpmnDiagram.getPlane();
		Map<String, Position> retVal = new HashMap<String, Position>();

		for (DiagramElement e : plane.getPlaneElement()) {
			if (e instanceof BPMNShape) {
				BPMNShape shape = (BPMNShape) e;

				int x = (int) Math.round(shape.getBounds().getX()
						* STRETCH_FACTOR);
				int y = (int) Math.round(shape.getBounds().getY()
						* STRETCH_FACTOR);
				Position position = new Position(x, y);

				if (shape.getBpmnElement() instanceof FlowElement) {

					FlowElement f = (FlowElement) shape.getBpmnElement();
					String key = (f.getName() == null || f.getName().isEmpty()) ? f
							.getId() : f.getName();
					key = CommonTransformation.replaceUmlaute(key);
					retVal.put(key, position);
				}
			}
		}
		return retVal;

	}

	public static Map<String, Position> copyGraphicalCoordinates(
			Collection<ProcessNode> nodes) {

		Map<String, Position> retVal = new HashMap<String, Position>();

		for (ProcessNode e : nodes) {

			Position position = new Position(0, 0);
			if (e.getGraphicalInformation() != null) {
				int x = (int) e.getGraphicalInformation().getX();
				int y = (int) e.getGraphicalInformation().getY();

				position.setX(x);
				position.setY(y);

			}

			String key = e.getLabel() == null ? e.getId() : e.getLabel();
			if (key == null) {
				key = "";
			}
			// System.out.println(key);
			retVal.put(key, position);

		}

		if (!retVal.containsKey("Sink"))
			retVal.put("Sink", createPositionForSink(nodes));

		if (!retVal.containsKey("Start"))
			retVal.put("Start", createPositionForSource(nodes));

		return retVal;

	}

	private static Position createPositionForSink(Collection<ProcessNode> nodes) {

		int x = Integer.MIN_VALUE;
		int y = Integer.MIN_VALUE;

		for (ProcessNode p : nodes) {
			if (p.getGraphicalInformation() != null) {
				if (p.getGraphicalInformation().getX() > x)
					x = (int) p.getGraphicalInformation().getX();
				if (p.getGraphicalInformation().getY() > y)
					y = (int) p.getGraphicalInformation().getY();
			}
		}

		x += 100;
		y += 100;
		// System.out.println("Sink placed at: (" + x + ", " + y + ")");

		return new Position(x, y);

	}

	private static Position createPositionForSource(
			Collection<ProcessNode> nodes) {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;

		for (ProcessNode p : nodes) {
			if (p.getGraphicalInformation() != null) {
				if (p.getGraphicalInformation().getX() < x)
					x = (int) p.getGraphicalInformation().getX();
				if (p.getGraphicalInformation().getY() < y)
					y = (int) p.getGraphicalInformation().getY();
			}
		}

		x -= 100;
		y -= 100;

		// System.out.println("Source placed at: (" + x + ", " + y + ")");

		return new Position(x, y);
	}
}
