package de.lsem.simulation.transformation.ed.xtext

import com.google.inject.Inject
import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.transformation.ed.xtext.helper.ActivityHelper
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import java.util.logging.Level
import java.util.logging.Logger

class ActivityTransportManager {
	@Inject
	extension ActivityHelper activityHelper
	@Inject
	extension ConnectionManager connectionManager
	@Inject
	extension Helper
	@Inject
	extension ElementHelper
	@Inject 
	extension Logger
	
	def String transportActivity(IActivity it)'''
		«log(Level::INFO, lsemName + ", " + activityServiceName)»
		
		{Atom: «lsemName»}	
		sets;
		AtomByName([«activityServiceName»], Main);
		if(not(AtomExists), Error([Cannot find mother atom '«activityServiceName»'. Inheriting from BaseClass.]));
		CreateAtom(a, «embeddSinUp», [], 1, false);
		int023([«lsemName»], «getInt023Param», 263408);
		Set(Icon(a), 
			RegisterIcon(IconsDir([bmp\atoms\server.bmp]), [server]));
		AddModel3D(
			RegisterModel3D(Model3DDir([\Server.wrl]), [Server.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\server2.wrl]), [server2.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\Server_Resized.wrl]), [Server_Resized.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
			RegisterModel3D(Model3DDir([\Server2_Resized.wrl]), [Server2_Resized.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		SetMaterial(
			RegisterMaterial([Default], 8421504, 8421504, 3289650, 0, 0.100000001490116, 0, false, false, 1, 0), 1, a);
		Set(Version(a), 0);
		SetTreeIcon(pDir([Media\Icons\Server.ico]));
		SetChannels(«getIn(resource, it)», «getOut»);
		SetChannelRanges(1, 255, 1, 255);
		int001(«id»);
		«int013(resource, it)»
		SetLoc(«getCoordinate(true)»,«getCoordinate(false)»,0);
		SetSize(5, 2, 2);
		LockPosition(false);
		LockSize(false);
		DisableIconRotation(false);
		SetProductCode([]);
		«getDistributionFor(timePeriod.period, "1")»
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
	'''}