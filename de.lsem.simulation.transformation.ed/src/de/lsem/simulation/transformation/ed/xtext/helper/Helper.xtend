package de.lsem.simulation.transformation.ed.xtext.helper

import de.lsem.repository.model.simulation.IRelation
import de.lsem.repository.model.simulation.ISimulationElement
import de.lsem.repository.model.simulation.ISource
import java.util.ArrayList
import javax.inject.Singleton
import org.eclipse.emf.ecore.xmi.XMIResource
import de.lsem.repository.model.simulation.IActivity

@Singleton
class Helper {

	XMIResource resource
	boolean sSet = false
	
	def setResource(XMIResource resource){
		this.resource = resource
	}
	
	def getResource(){
		resource
	}
	
	def embeddSinUp() {
		if(!sSet) {
			sSet = true
			"s"
		} else {
			"Up(s)"		
		}	
	}
	
	def lsemName(ISimulationElement it){
		name.trimString
	}
	
	
	def trimString(String s) {
		if(s == null)
			return "";
		return s.replaceAll("ä", "ae").replaceAll("Ä", "Ae")
				.replaceAll("ö", "oe").replaceAll("Ö", "Oe")
				.replaceAll("ü", "ue").replaceAll("Ü", "Ue")
				.replaceAll("ß", "ss");//.replace(" ", "");
	}
	
	def int013(XMIResource it,ISimulationElement e){
		val s = new StringBuffer
		var i = 1
		for ( r : getIncomming(e) ){
			s.append("int013(" + i + ", 0, true, false, 0, 0, [], []);")
			i = i + 1 
		}
		s.toString
	}
	
	def create retVal:new ArrayList<IRelation> getIncomming(XMIResource it, ISimulationElement e){
		simulationElements.forEach[
			outgoing.forEach[
				if ( target.name.equals(e.name)){
					retVal.add(it)
				}
			]
		]
	}
	
	def getIn(XMIResource it, ISimulationElement e){
		getIncomming(e).size
	}
	
	def getOut(ISimulationElement it){
		outgoing.size
	}
	
	def getSimulationElements(){
		resource.allContents.filter(typeof(ISimulationElement))
	}
	
	def getSources(){
		simulationElements.filter(typeof(ISource))
	}
	
	def getActivities(){
		simulationElements.filter(typeof(IActivity))
	}
	
	def getSimElementsWithoutSubActivities() {
		val retVal = new ArrayList<ISimulationElement>(simulationElements.toList)

		activities.forEach [
			subActivities.forEach [
				retVal.remove(it)
			]
		]
		retVal
	}
	

}