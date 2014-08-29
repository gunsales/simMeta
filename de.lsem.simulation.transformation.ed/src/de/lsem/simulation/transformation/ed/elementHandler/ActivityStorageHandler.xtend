package de.lsem.simulation.transformation.ed.elementHandler

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IActivity
import de.lsem.repository.model.simulation.QueuingStrategy
import de.lsem.simulation.transformation.ed.elementHandler.helper.ActivityHelper
import java.util.logging.Level
import java.util.logging.Logger
import static de.lsem.simulation.transformation.ed.elementHandler.helper.Helper.*

class ActivityStorageHandler {

	@Inject
	extension ActivityHelper
	@Inject
	extension ConnectionHandler
	@Inject
	extension Logger
	@Inject
	extension ActivityCommonHandler

	def String storageActivity(IActivity it, int x, int y, int numberOfIncomingElements, String int013,
		String embeddSinUp) '''
		«log(Level::INFO, lsemName(it) + " --> " + activityServiceName)»
		
		«commonParamters(x, y, numberOfIncomingElements, int013, embeddSinUp)»
		
		«createTargetText»
		SetAtt(2, «capacity.maxCapacity»);
		SetAtt(3, 0);
		SetAtt(4, 0);
		SetExprAtt(5, [{.content(c)|«getQueuingStrategie» .}content(c)]);
		SetAtt(6, 1);
		SetExprAtt(7, [{.openallic(c)|Any inputchannel .}openallic(c)]);
		SetAtt(8, 2);
		int024;
		SetStatus(13);
		int018;
	'''

	private def getQueuingStrategie(IActivity it) {

		switch capacity.queueStrategy {
			case QueuingStrategy.FIFO: "Fifo (First In First Out)"
			case QueuingStrategy.LIFO: "Lifo (Last In First Out)"
			case QueuingStrategy.RANDOM: "Random"
			default: "Sort by Label Ascending: The atoms with the lowest value of the label named LabelName are put in front"
		}
	}

}
