package de.lsem.simulation.transformation.mdb.xtend

import com.healthmarketscience.jackcess.Database
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.mdb.Activator
import java.io.File
import java.io.IOException
import java.net.URL
import javax.inject.Inject
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactoryConfigurationError
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.xmi.XMIResource
import org.xml.sax.SAXException

class ArenaTransformer implements ArenaTransformationConstants {

	@Inject
	extension RelationGenerator
	@Inject
	extension ArenaTransformationHelper
	@Inject
	extension DefaultTables
	@Inject
	extension ElementCreator

	private def init(XMIResource _resource) {

		resource = _resource

		// Create SaveFile in template folder
		val File retVal = initTemplateDatabase()

		// Create defaults in save-file
		createDefaultTables()

		//logger.log(Level.INFO, "Initialization of files complete.")
		return retVal
	}

	private def File initTemplateDatabase() throws IOException {

		// Load template file
		val Database templateFile = loadTemplateDatabase

		// Set up save file
		val File tempCopyFile = new File(saveFileString)
		saveFile = Database.create(tempCopyFile)

		// Copy values from template file to final save file
		for (String name : templateFile.tableNames) {
			saveFile.createTable(name, templateFile.getTable(name).columns)
		}

		//logger.log(Level.CONFIG, "Database file created. Path: " + saveFile.file.absolutePath)
		return saveFile.file
	}

	private def Database loadTemplateDatabase() throws IOException {

		val File file = new File(templatePath)
		Database.open(file)

	}

	private def String getTemplatePath() throws IOException {
		var URL templateURL = FileLocator.find(Activator.getDefault().getBundle(), new Path(PATH_TO_MDB_TEMPLATE), null)
		templateURL = FileLocator.toFileURL(templateURL)

		//logger.log(Level.INFO, "Template path set to: " + fileUrlPath)
		templateURL.path
	}

	public def File start(XMIResource _resource) throws IOException, ParserConfigurationException,
			SAXException, TransformerFactoryConfigurationError,
			TransformerException {

		resource = _resource

		// Init fiels, lists with lsem-elements and relations
		val File retVal = init(resource)

		// iterate over connection- and element-list to create objects
		createArenaElements()

		// Connections
		createArenaConnections()

		//logger.log(Level.INFO, "Transformation complete.")
		retVal
	}

	private def void createArenaElements() throws IOException {

		// Relevant are only top-level-elements (Activity, Source, Sink)
		var int idDummy = 0
		for (ISimulationElement e : filterSimulationElementsFromXMIResource.toList.filterSubActivities) {
			if (e instanceof IActivity) {
				idDummy = createProcess(e as IActivity)
				lookupTable.put(e, idDummy)
			} else if (e instanceof ISource) {
				idDummy = createCreate(e as ISource)
				lookupTable.put(e, idDummy)
			} else if (e instanceof ISink) {
				idDummy = createDispose(e as ISink)
				lookupTable.put(e, idDummy)
			}
		}
	}

	private def void createArenaConnections() throws IOException {
		resource.contents.filter(typeof(ISimulationElement)).forEach [
			// OUTGOING contains only conditional relations, so generate
			// a decide-element and connect all relations to it.
			if (outgoing.size > 1 && areAllOutgoingRelationsConditional) {

				//Create decide-element
				val cRelation = outgoing.filter(IConditionalRelation).iterator.next as IConditionalRelation
				val decideID = createDecide(cRelation)

				//Create one usual relation from start-element to decide
				createConnection(lookupTable.get(it), decideID, cRelation.name)

				//Create connections from decide- to target-element
				createConnectionsFromDecide(decideID)

			} 
		
			// Not every outgoing relation is a conditional relation
			// or no outgoing relations
			// or the only outgoing relation is a conditional relation, which in that case is treated as a usual relation
			else {
				outgoing.forEach [ r |
					r.createConnection
				]
			}
		]
	}

	private def areAllOutgoingRelationsConditional(ISimulationElement e) {
		countOfConditionalRelations(e) == e.outgoing.size
	}

	private def countOfConditionalRelations(ISimulationElement e) {
		e.outgoing.filter(typeof(IConditionalRelation)).size
	}
}
