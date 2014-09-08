package de.lsem.simulation.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;

public class LSEMElementHelper {

	public static boolean isSubActivity(EList<EObject> contents, IActivity activity) {
		for (Iterator<IActivity> iter = getActivitiesFromDiagram(contents)
				.iterator(); iter.hasNext();) {
			IActivity e = iter.next();
			if (e.getSubActivities().contains(activity))
				return true;
		}
		return false;
	}

	public static List<IActivity> getActivitiesFromDiagram(EList<EObject> contents) {
		List<IActivity> retVal = new ArrayList<IActivity>();

		for (EObject e : contents) {
			if (e instanceof IActivity) {
				retVal.add((IActivity) e);
			}
		}

		return retVal;
	}

	public static List<ISimulationElement> getElementsFromDiagram(
			EList<EObject> contents) {
		List<ISimulationElement> retVal = new ArrayList<ISimulationElement>();

		for (EObject e : contents) {
			if (e instanceof ISimulationElement) {
				retVal.add((ISimulationElement) e);
			}
		}

		return retVal;

	}

	public static List<IRelation> getIncommingConnections(EList<EObject> contents,
			IActivity activity) {

		List<IRelation> retVal = new ArrayList<IRelation>();

		for (Iterator<ISimulationElement> iter = getElementsFromDiagram(
				contents).iterator(); iter.hasNext();) {
			ISimulationElement dummy = iter.next();
			for (IRelation r : dummy.getOutgoing()) {
				if (r.getTarget().equals(activity)) {
					retVal.add(r);
				}
			}
		}
		return retVal;
	}

	public static List<ISimulationElement> getSourcesForIncommingConnections(
			EList<EObject> contents, ISimulationElement element) {

		List<ISimulationElement> retVal = new ArrayList<ISimulationElement>();

		for (Iterator<ISimulationElement> iter = getElementsFromDiagram(
				contents).iterator(); iter.hasNext();) {
			ISimulationElement source = iter.next();
			for (Iterator<IRelation> iter2 = source.getOutgoing().iterator(); iter2
					.hasNext();) {
				IRelation r = iter2.next();
				if (r.getTarget().equals(element)) {
					retVal.add(source);
				}
			}
		}
		return retVal;
	}

	public static boolean removeFromIncommingRelations(EList<EObject> contents,
			ISimulationElement element) {

		boolean retVal = false;
		for (Iterator<ISimulationElement> iter = getSourcesForIncommingConnections(
				contents, element).iterator(); iter.hasNext();) {
			ISimulationElement e = iter.next();
			for (IRelation r : e.getOutgoing()) {
				if (r.getTarget().equals(element))
					retVal = e.getOutgoing().remove(r);
			}
		}

		return retVal;
	}

	public static boolean hasSubActivities(IActivity topActivity) {

		if (topActivity.getSubActivities() == null
				|| topActivity.getSubActivities().isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean checkIfSubActivityOfEachother(IActivity source,
			IActivity target) {
		return (source.getSubActivities().contains(target) || target
				.getSubActivities().contains(source));
	}

	public static IActivity getTopActivityFor(IActivity activity,
			EList<EObject> contentsOfDiagram) {
		for (Iterator<IActivity> iter = getActivitiesFromDiagram(
				contentsOfDiagram).iterator(); iter.hasNext();) {
			IActivity dummy = iter.next();
			if (dummy.getSubActivities().contains(activity)) {
				return dummy;
			}
		}
		return null;
	}

	
	public static List<IRelation> getRelationsFromDiagram(EList<EObject> contents) {
		List<IRelation> retVal = new ArrayList<IRelation>();

		for (ISimulationElement e : getElementsFromDiagram(contents)) {
			for (IRelation r : e.getOutgoing()) {
				retVal.add(r);
			}
		}

		return retVal;
	}
}
