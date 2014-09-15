package de.lsem.simulation.layoutManagers

import de.cau.cs.kieler.kiml.graphiti.GraphitiDiagramLayoutManager
import org.eclipse.graphiti.ui.editor.DiagramEditor
import org.eclipse.graphiti.mm.pictograms.PictogramElement

class SimMetaDiagramLayoutManager extends GraphitiDiagramLayoutManager {
	
	override supports(Object object) {
		object instanceof DiagramEditor || object instanceof PictogramElement
	}
	
}