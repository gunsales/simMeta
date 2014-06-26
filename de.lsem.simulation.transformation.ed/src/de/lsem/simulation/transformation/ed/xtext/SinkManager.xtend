package de.lsem.simulation.transformation.ed.xtext

import com.google.inject.Inject
import de.lsem.repository.model.simulation.ISink
import de.lsem.simulation.transformation.ed.xtext.helper.ElementConstants
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper

class SinkManager implements ElementConstants{
	
	@Inject
	extension ElementHelper
	@Inject
	extension ConnectionManager
	@Inject
	extension Helper
	
	def writeSink(ISink it) '''
	{Atom: «name»}
	
	sets;
	AtomByName([Sink], Main);
	if(not(AtomExists), Error([Cannot find mother atom 'Sink'. Inheriting from BaseClass.]));
	CreateAtom(a, «embeddSinUp», [], 1, false);
	int023([«name»], 7168771, 240);
	Set(Icon(a), 
		RegisterIcon(pDir([Media\Images\Atoms\sink.bmp]), [sink]));
	AddModel3D(
		RegisterModel3D(Model3DDir([\sink.wrl]), [sink.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
	AddModel3D(
		RegisterModel3D(Model3DDir([\sink_Resized.wrl]), [sink_Resized.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
	SetMaterial(
		RegisterMaterial([Default], 8421504, 8421504, 3289650, 0, 0.100000001490116, 0, false, false, 1, 0), 1, a);
	Set(Version(a), 0);
	SetTreeIcon(pDir([Media\Icons\Sink.ico]));
	SetChannels(«getIn(resource, it)», 0);
	SetChannelRanges(1, 255, 0, 0);
	int001(«id»);
	«int013(resource, it)»
	SetLoc(«getCoordinate(true)»,«getCoordinate(false)»,0);
	SetSize(5, 2, 2);
	LockPosition(false);
	LockSize(false);
	DisableIconRotation(false);
	SetProductCode([]);
	SetAtt(1, 0);
	SetAtt(2, 4);
	int024;
	SetStatus(1);
	int018;
	
	'''
}