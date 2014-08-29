package de.lsem.simulation.transformation.ed.elementHandler

import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.transformation.ed.elementHandler.helper.ElementHelper
import javax.inject.Inject
import static de.lsem.simulation.transformation.ed.elementHandler.helper.Helper.*

class SourceHandler {
	
	@Inject
	extension ConnectionHandler
	@Inject
	extension ElementHelper
	@Inject
	extension ProductHandler
	
	def startEvent(ISource it, int x, int y, String embeddSinUp) '''
		«getFirstPart(embeddSinUp)»
		SetChannels(1, «getOut(it)»);
		SetChannelRanges(1, 1, 1, 255);
		int001(«getId»);
		int013(1, 0, true, false, 0, 0, [], []);
		SetLoc(«x»,«y»,0);
		«getSecondPart»
		
		«writeProduct(x, y)»
	'''
	
	private def getFirstPart(ISource it, String embeddSinUp) '''
		
		{Atom: «lsemName(it)»}	
		sets;
		AtomByName([Source], Main);
		if(not(AtomExists), Error([Cannot find mother atom 'Source'. Inheriting from BaseClass.]));
		CreateAtom(a, «embeddSinUp», [], 1, false);
		int023([«lsemName(it)»], 7168771, 240);
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
«««time till first product --> new entities
		«getDistributionFor(firstEntity.period, "5")»
		SetExprAtt(6, [{. -1 |1. Unlimited .} -1 ]);
		SetAtt(7, 4);
		int024;
		SetStatus(2);
		int018;
	'''

}