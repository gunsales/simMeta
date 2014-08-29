package de.lsem.simulation.transformation.ed.elementHandler

class HeaderHandler {
	
	def printHeader() '''
		{Enterprise Dynamics startup information}
			
		if(StartingED, SoftStartED([]));
		
		
		{Model information}
		
		AddLayer([Main], 1, 1, 0);
		
		{Load required atoms}
		
		int011;
		int035([Logistics_Services], pDir([atoms\Logistics_Services.atm]));
		int035([Sink], pDir([Atoms\Sink.atm]));
«««		int035([Server], pDir([Atoms\Server.atm]));
		int035([Source], pDir([Atoms\Source.atm]));
«««		int035([StatusMonitor], pDir([Atoms\StatusMonitor.atm]));
		int012;
		ChannelsVisible(1);	
	'''
}