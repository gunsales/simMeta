package de.lsem.simulation.transformation.ed.elementHandler

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.transformation.ed.elementHandler.helper.ActivityHelper
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper
import java.util.logging.Level
import java.util.logging.Logger
import static de.lsem.simulation.transformation.ed.elementHandler.helper.Helper.*

class ActivityTransportHandler {
	@Inject
	extension ActivityHelper
	@Inject
	extension ConnectionHandler
	@Inject
	extension ElementHelper
	@Inject
	extension Logger
	@Inject
	extension ActivityCommonHandler

	def String transportActivity(IActivity it, int x, int y, int numberOfIncommingElements, String int013,
		String embeddSinUp) '''
		«log(Level::INFO, lsemName(it) + ", " + activityServiceName)»
		
		«commonParamters(x, y, numberOfIncommingElements, int013, embeddSinUp)»
		«getDistributionFor(timePeriod, "1")»
		«createTargetText»
		SetExprAtt(3, [1]);
		SetAtt(4, 1);
		SetAtt(5, 0);
		SetAtt(6, 0);
		SetExprAtt(7, [{.~1|~0~ .}0]);
		SetAtt(8, 0);
		SetAtt(9, 0);
		SetAtt(10, 0);
		SetAtt(11, 0);
		SetAtt(12, 0);
		SetExprAtt(13, [If(
		 Time > 0,
		 Concat( [Util: ], String ( *( 100, /( -( TotalBusy, Max( 0, EndBusy - Time)), Time)), 0, 1), [ %]),
		 [Util: 0 %]
		)]);
		SetAtt(14, 0);
		SetAtt(15, 0);
		SetAtt(16, 1E40);
		SetAtt(17, 0);
		SetExprAtt(18, [{.openallic(c)|Any inputchannel .}openallic(c)]);
		SetAtt(19, 7);
		SetAtt(20, 0);
		SetAtt(21, 0);
		SetAtt(22, 0);
		SetAtt(23, 0);
		SetAtt(24, 0);
		SetAtt(25, 0);
		SetAtt(26, 0);
		SetAtt(27, 0);
		SetAtt(28, 0);
		SetAtt(29, 677797);
		SetExprAtt(30, [mttf]);
		SetAtt(31, 0);
		SetAtt(32, 0);
		SetAtt(33, 0);
		SetAtt(34, 0);
		int024;
		SetStatus(1);
		int018;
	'''
}
