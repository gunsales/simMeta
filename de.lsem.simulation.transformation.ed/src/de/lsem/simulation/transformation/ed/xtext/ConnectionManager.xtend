package de.lsem.simulation.transformation.ed.xtext

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IConditionalRelation
import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISink
import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.ed.xtext.helper.Connection
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import java.util.Set
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Singleton
import org.eclipse.graphiti.mm.pictograms.Diagram
import org.eclipse.graphiti.mm.pictograms.PictogramLink
import org.eclipse.ui.activities.IActivity

@Singleton
class ConnectionManager {

	@Inject
	extension ElementHelper
	@Inject
	extension Helper
	@Inject
	extension Logger

	public static final int POSITION_DIVISOR = 22

	//If just one outgoing connection, handle each connection as a usual connection
	// even if its a conditional relation
	def processConnections(ISimulationElement e) {
		if (e.outgoing.size == 1) {
			e.outgoing.forEach[createConnection(e, it as IRelation)]
		} else if (e.outgoing.size > 1) {
			e.outgoing.forEach [
				createConnection(e, it)
			]
		} else {
			log(Level.INFO, "ELSE CASE for Element:" + e.name)

		//
		}
	}

	// Ignore when Sink appears
	def dispatch void createConnection(ISink s, IRelation relation) {
	}

	def dispatch void createConnection(ISimulationElement e, IRelation relation) {
		var sourceId = getId(relation.source)
		var targetId = getId(relation.target)
		addConnection(sourceId, targetId, "", 50)
	}

	def dispatch void createConnection(ISimulationElement e, IConditionalRelation relation) {
		var sourceId = getId(relation.source)
		var targetId = getId(relation.target)
		addConnection(sourceId, targetId, relation.condition, relation.probability as int)
	}

	/**
	 * Creates a connection between a product and a source.
	 * The annotation in condition is needed in order to separate 
	 * connections between ISimulationElements and connections 
	 * between products and sources.
	 */
	def dispatch void createConnection(int sourceId, int targetId) {
		addConnection(sourceId, targetId, "product_to_source_relation", 50)
	}

	def getCoordinate(ISimulationElement e, boolean returnX) {
		while (resource.allContents.hasNext) {
			val next = resource.allContents.next

			if (next instanceof Diagram) {
				for (PictogramLink p : (next as Diagram).pictogramLinks) {
					if (p.businessObjects.contains(e)) {
						val ga = p.pictogramElement.graphicsAlgorithm
						if (returnX) {
							return recalcPosition(ga.x)
						} else {
							return recalcPosition(ga.y)
						}
					}
				}
			}
		}
		0
	}

	//	def getCoordinate(ISimulationElement e, Resource it, boolean returnX){
	//		allContents.filter(typeof(Diagram)).head.pictogramLinks.map[
	//			if(businessObjects.contains(e)){
	//				val ga = pictogramElement.graphicsAlgorithm
	//				if(returnX){
	//					recalcPosition(ga.x)
	//				} else {
	//					recalcPosition(ga.y)
	//				}
	//			}
	//		].head
	//	}
	def recalcPosition(int i) {
		i / POSITION_DIVISOR
	}

	def createTargetText(ISimulationElement it) {

		//Error when requesting outgoing relations, as "cons" is always empty
		val cons = outgoingConnections
		log(Level.INFO, ">>> cons: " + cons)
		if (cons.size() > 0) {
			if (cons.get(0).condition != null && !"".equals(cons.get(0).condition)) {
				"SetExprAtt(" + expressionPosition + ", [1]);"
			} else {
				val dummy = createBernoulli(cons, 0, 100)
				"SetExprAtt(" + expressionPosition + ", [{.~1|~" + dummy + "~.}" + dummy + "]);"
			}
		}
	}

	/**
	 * Positions of expressions differ depending on created object
	 * 1 is used in case of Source, 2 if Activity, default case is 2 by now.
	 * This value might change if new atoms are used.
	 */
	def getExpressionPosition(ISimulationElement it) {
		switch (it) {
			case it instanceof IActivity: 2
			case it instanceof ISource: 1
			default: 2
		}
	}

	def String createBernoulli(Set<Connection> cons, int consListStartIndex, double remainingPercentage) {

		if (consListStartIndex + 1 < cons.size() && cons.get(consListStartIndex).percentage != 0) {

			//Probability depending on rest
			val aktuelleWahrscheinlichkeit = ( (cons.get(consListStartIndex).percentage) / remainingPercentage * 100 ) as int

			//Write beginning elements with probability of this specific element
			"Bernoulli(" + aktuelleWahrscheinlichkeit + "," + (consListStartIndex + 1) + "," //recursive call
			+ createBernoulli( //ConnectionList
			cons, //new Index
			(consListStartIndex + 1),
				//Remaining percentage
				(remainingPercentage - cons.get(consListStartIndex).percentage)) //Append Rest
			+ ")"

		} else {

			//Append index+1 in case no following connection with probability is found
			(consListStartIndex + 1).toString
		}
	}

}
