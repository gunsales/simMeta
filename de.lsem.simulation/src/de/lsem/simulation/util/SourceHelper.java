package de.lsem.simulation.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.lsem.repository.model.simulation.ISource;

public class SourceHelper {

	public static List<ISource> getISourcesFromDiagram(EList<EObject> contents) {
		List<ISource> retVal = new ArrayList<ISource>();
		
		for ( EObject e : contents ) {
			if ( e instanceof ISource )
				retVal.add((ISource) e);
		}
		
		return retVal;
	}
}
