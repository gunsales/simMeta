package de.lsem.simulation.util;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.dialogs.IInputValidator;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;

public class NameValidator implements IInputValidator {

	private EObject object;
	private Diagram diagram;

	public NameValidator(EObject object, Diagram diagram) {
		this.object = object;
		this.diagram = diagram;
	}
	
	@Override
	public String isValid(String newText) {

		EList<EObject> contents = diagram.eResource().getContents();
		List<ISimulationElement> filteredSimulationElements = LSEMElementHelper
				.getElementsFromDiagram(contents);

		for (ISimulationElement e : filteredSimulationElements) {
			if (object != e && e.getName().equals(newText)) {
				return "Name already exists";
			}
		}

		List<IRelation> filteredRelations = LSEMElementHelper
				.getRelationsFromDiagram(contents);

		for (IRelation e : filteredRelations) {
			if (object != e && e.getName().equals(newText)) {
				return "Name already exists";
			}
		}

		return null;
	}



}
