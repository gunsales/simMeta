package de.lsem.simulation.transformation.ed.xtext

import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.ed.xtext.helper.ElementHelper
import de.lsem.simulation.transformation.ed.xtext.helper.Helper
import javax.inject.Inject

class SourceManager {
	
	@Inject
	extension Helper
	@Inject
	extension ConnectionManager
	@Inject
	extension ElementHelper
	@Inject
	extension ProductManager
	
	def startEvent(ISource it) '''
		«firstPart»
		SetChannels(1, «out»);
		SetChannelRanges(1, 1, 1, 255);
		int001(«id»);
		int013(1, 0, true, false, 0, 0, [], []);
		SetLoc(«getCoordinate(true)»,«getCoordinate(false)»,0);
		«secondPart»
		
		«writeProduct»
	'''
	
	private def getFirstPart(ISource it) '''
		
		{Atom: «lsemName»}	
		sets;
		AtomByName([Source], Main);
		if(not(AtomExists), Error([Cannot find mother atom 'Source'. Inheriting from BaseClass.]));
		CreateAtom(a, «embeddSinUp», [], 1, false);
		int023([«lsemName»], 7168771, 240);
		Set(Icon(a), 
		RegisterIcon(IconsDir([bmp\atoms\source.bmp]), [source]));
		AddModel3D(
		RegisterModel3D(Model3DDir([\source.wrl]), [source.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		AddModel3D(
		RegisterModel3D(Model3DDir([\source_Resized.wrl]), [Source_Resized.wrl], 0, 0, 0, 1, 1, -1, -90, 0, 0), a);
		SetMaterial(
		RegisterMaterial([Default], 8421504, 8421504, 3289650, 0, 0.100000001490116, 0, false, false, 1, 0), 1, a);
		Set(Version(a), 0);
		SetTreeIcon(pDir([Media\Icons\Source.ico]));
	'''

	private def getSecondPart(ISource it)'''	
		SetSize(5, 2, 2);
		LockPosition(false);
		LockSize(false);
		DisableIconRotation(false);
		SetProductCode([]);
«««		TODO Test Value
«««		SetExprAtt(2, [LogNormal(hr(1), hr(0.5))]);
		«createTargetText»
«««		time between products --> following entities
		«getDistributionFor(newEntities.period, "2")»
		SetExprAtt(3, [{.0|10. Do Nothing .}0]);
		SetExprAtt(4, 0);
«««		time till first product --> new entities
		«getDistributionFor(firstEntity.period, "5")»
		SetExprAtt(6, [{. -1 |1. Unlimited .} -1 ]);
		SetAtt(7, 4);
		int024;
		SetStatus(2);
		int018;
	'''

}