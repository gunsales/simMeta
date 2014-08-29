package de.lsem.simulation.transformation.ed.elementHandler

import de.lsem.repository.model.simulation.IActivity
import de.lsem.simulation.transformation.ed.elementHandler.helper.ActivityHelper
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper
import javax.inject.Inject
import static de.lsem.simulation.transformation.ed.elementHandler.helper.Helper.*

class ActivityCommonHandler {	
	@Inject
	extension ActivityHelper
	@Inject
	extension ElementHelper
		
	def commonParamters(IActivity it, int x, int y, int numberOfIncomingElements, String int013, String embeddSinUp)'''
		{Atom: «lsemName(it)»}
		sets;
		AtomByName([«activityServiceName»], Main);
		if(not(AtomExists), Error([Cannot find mother atom '«activityServiceName»'. Inheriting from BaseClass.]));
		CreateAtom(a, «embeddSinUp», [], 1, false);
		int023([«lsemName(it)»], «int023Param», 263408);
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
		SetChannels(«numberOfIncomingElements», «getOut(it)»);
		SetChannelRanges(1, 255, 1, 255);
		int001(«getId»);
		«int013»
		SetLoc(«x»,«y»,0);
		SetSize(5, 2, 2);
		LockPosition(false);
		LockSize(false);
		DisableIconRotation(false);
		SetProductCode([]);
	'''}