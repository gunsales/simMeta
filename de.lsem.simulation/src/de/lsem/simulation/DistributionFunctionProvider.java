package de.lsem.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.lsem.repository.model.simulation.IDistributionFunction;


public enum DistributionFunctionProvider {
	INSTANCE;

	private static final Logger log = Logger.getLogger(DistributionFunctionProvider.class.getSimpleName());

	private List<IDistributionFunction> distributionFunctionList;



	private DistributionFunctionProvider(){
		distributionFunctionList = new ArrayList<IDistributionFunction>();
//		loadClasses();
	}

//	private void loadClasses() {
//		Collection<? extends IDistributionFunction> loader = Lookup.lookupAll(
//				IDistributionFunction.class);
//		distributionFunctionList = new ArrayList<IDistributionFunction>();
//		for(IDistributionFunction d : loader){
//			distributionFunctionList.add(d);
//			log.log(Level.INFO, "Added class " + d.getClass().getSimpleName() + " to IDistributionFunctions.");
//		}
//	}

	public List<IDistributionFunction> getDistributionFunctions(){
		Collection<? extends IDistributionFunction> loader = Lookup.lookupAll(
				IDistributionFunction.class);

		distributionFunctionList = new ArrayList<IDistributionFunction>();
		for(IDistributionFunction d : loader){
			distributionFunctionList.add(d);
			log.log(Level.INFO, "Added class " + 
			d.getClass().getSimpleName() + " to IDistributionFunctions. " + d.toString());
		}
		
		return distributionFunctionList;
	}
}
