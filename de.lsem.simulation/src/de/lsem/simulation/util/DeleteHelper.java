package de.lsem.simulation.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;

public final class DeleteHelper {
	/*
	 * Method iterates through pictogram-elements and removes incoming
	 * connections. Incoming connections are not directly linked to the activity
	 * that has to be removed.
	 */
	public static void deleteIncommingConnections(List<EObject> contents,
			ISimulationElement element) {

		// Get sources from removing object
		List<ISimulationElement> sources = getSources(contents, element);

		// delete connections from sources
		for (ISimulationElement source : sources) {
			deleteConnectionsFromSourceToTarget(source, element);
		}
	}

	public static void deleteConnectionsFromSourceToTarget(
			ISimulationElement source, ISimulationElement target) {
		for (Iterator<IRelation> it = source.getOutgoing().iterator(); it
				.hasNext();) {
			IRelation r = it.next();
			if (r.getTarget() != null && r.getTarget().equals(target)) {
				it.remove();
			}
		}

	}

	public static List<ISimulationElement> getSources(List<EObject> contents,
			ISimulationElement object) {
		List<ISimulationElement> retList = new ArrayList<ISimulationElement>();

		for (EObject e : contents) {
			if (e instanceof ISimulationElement) {
				ISimulationElement lsem = (ISimulationElement) e;
				for (IRelation r : lsem.getOutgoing()) {
					if (r.getTarget() != null && r.getTarget().equals(object))
						retList.add(lsem);
				}
			}
		}

		return retList;
	}

}
