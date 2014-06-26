package de.lsem.simulation.transformation.ed.xtext.helper

class Connection {

	int sourceID
	int targetID
	int percentage;
	
	String condition;

	/**
		 * Default values will be set
		 * 
		 * @param from
		 *            hash-value of source
		 * @param to
		 *            hash-value of target
		 * @param condition
		 *            Condition for entering this route
		 * @param percentage
		 *            Percentage possible for entering this route. Must be
		 *            between 0 and 100.
		 */
	new(int sourceID, int targetID, String condition, int percentage) {

		this.sourceID = sourceID
		this.targetID = targetID
		this.percentage = percentage
		if (condition != null) {
			this.condition = condition
		} else {
			this.condition = ""
		}

	// logger.log(Level.INFO, "Connection created... " + toString() );
	}

	def getCondition(){
		return condition
	}
	
	def getPercentage(){
		return percentage
	}
	
	def getSourceID(){
		return sourceID
	}
	
	def getTargetID(){
		return targetID
	}

	override String toString() {
		return "Connection [from=" + sourceID + ", to=" + targetID + ", condition=" + condition + ", percentage=" +
			percentage + "]";
	}
}
