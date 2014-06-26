package de.lsem.simulation.transformation.ed.xtext

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.transformation.ed.xtext.helper.ActivityHelper
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import java.util.logging.Level
import java.util.logging.Logger
import de.lsem.repository.model.simulation.QueuingStrategy

class ActivityStorageManager {
	
	@Inject
	extension ActivityHelper
	@Inject
	extension ConnectionManager
	@Inject
	extension Helper
	@Inject
	extension ElementHelper
	@Inject 
	extension Logger
	
	def String storageActivity(IActivity it)'''
	«log(Level::INFO, lsemName + " --> " + activityServiceName)»
	
	{Atom: «lsemName»}

	sets;
	AtomByName([«activityServiceName»], Main);
	if(not(AtomExists), Error([Cannot find mother atom '«activityServiceName»'. Inheriting from BaseClass.]));
	CreateAtom(a, «embeddSinUp», [], 1, false);
	int023([«lsemName»], 128, 1264);
	Set(Icon(a), 
		RegisterIcon(IconsDir([bmp\atoms\queue.bmp]), [queue]));
	SetMaterial(
		RegisterMaterial([Default], 8421504, 8421504, 3289650, 0, 0.100000001490116, 0, false, false, 1, 0), 1, a);
	Set(Version(a), 0);
	SetTreeIcon(pDir([Media\Icons\Queue.ico]));
	SetChannels(«getIn(resource, it)», «getOut»);
	SetChannelRanges(1, 255, 1, 255);
	int001(«id»);
	«int013(resource, it)»
	SetLoc(«getCoordinate(true)»,«getCoordinate(false)»,0);
	SetSize(5, 2, 0);
	LockPosition(false);
	LockSize(false);
	DisableIconRotation(false);
	SetProductCode([]);
	
	«createTargetText»	
	SetAtt(2, «capacity.maxCapacity»);
	SetAtt(3, 0);
	SetAtt(4, 0);
	SetExprAtt(5, [{.content(c)|«queuingStrategie» .}content(c)]);
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