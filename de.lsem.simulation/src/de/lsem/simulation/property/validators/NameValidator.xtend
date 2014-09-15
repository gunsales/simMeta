package de.lsem.simulation.property.validators

import com.google.inject.Inject
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.dialogs.IInputValidator

import static de.lsem.simulation.util.LSEMElementHelper.*

class NameValidator implements IInputValidator {
	val EObject object
	val EList<EObject> contents

	@Inject
	new(EObject object, EList<EObject> contents) {
		this.object = object
		this.contents = contents
	}

	override isValid(String newText) {
		if (nameExists(contents, newText)) {
			return "Name already exists"
		}

		return null
	}

}
