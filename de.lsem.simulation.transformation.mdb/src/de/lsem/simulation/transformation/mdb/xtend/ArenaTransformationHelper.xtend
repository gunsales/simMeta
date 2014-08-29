package de.lsem.simulation.transformation.mdb.xtend

import com.healthmarketscience.jackcess.Database
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ServiceType
import de.lsem.repository.model.simulation.UnitOfTime
import de.lsem.simulation.transformation.mdb.commands.Messages
import java.util.ArrayList
import java.util.HashMap
import java.util.Iterator
import java.util.List
import java.util.Map
import javax.inject.Singleton
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.CommonPlugin
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramLink

import static de.lsem.repository.model.simulation.ServiceType.*
import static de.lsem.repository.model.simulation.UnitOfTime.*

@Singleton
class ArenaTransformationHelper implements ArenaTransformationConstants {

	int counter = 0
	Diagram diagram
	Database saveFile
	XMIResource resource
	String savePath

	// Map contains elements and their IDs
	Map<ISimulationElement, Integer> lookupTable

	def generateID() {
		return (counter = counter + 1)
	}

	def toARENAServiceType(ServiceType serviceType) {
		switch serviceType {
			case TRANSPORT:
				SERVICE_TYPE_ARENA_TRANSPORT
			case HANDLING:
				SERVICE_TYPE_ARENA_HANDLING
			case PICKING:
				SERVICE_TYPE_ARENA_PICKING
			case STORAGE:
				SERVICE_TYPE_ARENA_STORAGE
			case VALUE_ADDED:
				SERVICE_TYPE_ARENA_VALUE_ADDED
			default:
				SERVICE_TYPE_ARENA_HANDLING
		}
	}

	def stretch(int coordinate) {
		return coordinate * GRAPHICAL_STRETCH_FACTOR
	}

	def getX(ISimulationElement it) {
		getCoordinate(true)
	}

	def getY(ISimulationElement it) {
		getCoordinate(false)
	}

	def getCoordinate(ISimulationElement e, boolean returnX) {

		for (PictogramLink p : getDiagram().pictogramLinks) {
			if (p.businessObjects.contains(e)) {
				val ga = p.pictogramElement.graphicsAlgorithm
				if (returnX) {
					return ga.x
				} else {
					return ga.y
				}
			}
		}
		0
	}

	def getLookupTable() {
		if (lookupTable == null) {
			lookupTable = new HashMap<ISimulationElement, Integer>()
		}
		return lookupTable
	}

	def getDiagram() {
		if (diagram == null) {
			diagram = diagramFromResource
		}
		return diagram
	}

	private def getDiagramFromResource() {
		resource.allContents.filter(typeof(Diagram)).next
	}

	def getSaveFile() {
		return saveFile
	}

	def setSaveFile(Database _saveFile) {
		saveFile = _saveFile
	}

	def getResource() {
		return resource
	}

	def setResource(XMIResource _resource) {
		resource = _resource
	}

	def getSaveFileString() {
		if (savePath == null) {
			savePath = getSavePath
		}
		return savePath
	}

	private def String getSavePath() {

		//logger.log(Level.INFO, "Setting up save path ... ")
		val IFile sourceFile = getSourceFileFrom(resource)
		val IProject project = sourceFile.getProject
		val IFolder targetFolder = project.getFolder(Messages.TransformToArena_13)

		// get file name
		var String tempFileName = sourceFile.getName

		// change file-type
		tempFileName = tempFileName.replace(DIAGRAM_EXTENSION_CONSTANT, MDB_EXTENSION_CONSTANT)

		// change path to project-root-->arena-gen
		return targetFolder.getFile(tempFileName).getRawLocation().toOSString()
	}

	private def IFile getSourceFileFrom(XMIResource xmiResource) {
		val URI uri = xmiResource.getURI()
		val URI resolvedFile = CommonPlugin.resolve(uri)

		val String projectNameString = getProjectNameFor(uri)
		val IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectNameString)

		val Path path = new Path(resolvedFile.toFileString())
		project.getFile(path)
	}

	private def String getProjectNameFor(URI uri) {
		val String uriString = uri.toString()
		var String trimmedFirst = uriString.substring(19, uriString.length())
		trimmedFirst = trimmedFirst.replaceAll("%20", " ")
		trimmedFirst.substring(0, trimmedFirst.indexOf("/"))
	}

	/*
	 * Maps simulation-time to arena-time
	 */
	def String mapTimeUnit(UnitOfTime timeUnit) {
		switch timeUnit.value {
			case timeUnit.value == DAY_VALUE: ARENA_TIME_DAY
			case timeUnit.value == HOUR_VALUE: ARENA_TIME_HOUR
			case timeUnit.value == MINUTE_VALUE: ARENA_TIME_MINUTE
			case timeUnit.value == SECOND_VALUE: ARENA_TIME_SECOND
			default: ARENA_TIME_HOUR
		}
	}

	def String setConnectionNWayType(List<IConditionalRelation> allConnectionsFromOneSource) {

		// type is based on probability
		if (allConnectionsFromOneSource.get(0).condition.empty) {
			if (allConnectionsFromOneSource.size() > 2) {
				VALUE_NWITH
			} else {
				VALUE_WITH
			}
		}
		// type is based on condition
		else {
			if (allConnectionsFromOneSource.size() > 2) {
				VALUE_NIF
			} else {
				VALUE_IF
			}
		}
	}
	

	def filterSimulationElementsFromXMIResource() {
		resource.allContents.filter(typeof(ISimulationElement))
	}

	def filterSubActivities(Iterator<ISimulationElement> iterator) {
		val List<ISimulationElement> retList = new ArrayList<ISimulationElement>(iterator.toList)
		iterator.filter(typeof(IActivity)).forEach [ e1 |
			e1.subActivities.forEach [ e2 |
				retList.remove(e2)
			]
		]
		retList
	}

}
