package de.lsem.simulation.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.lsem.repository.model.simulation.ISink;

public class SinkHelper {

	public static List<ISink> getISinksFromDiagram(EList<EObject> contents) {
		List<ISink> retVal = new ArrayList<ISink>();
		
		for ( EObject e : contents ) {
			if ( e instanceof ISink )
				retVal.add((ISink) e);
		}
		
		return retVal;
	}
}
