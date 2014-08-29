package de.lsem.simulation.validation;

public enum ValidationStatus {
	STATUS_OK, STATUS_PROBLEM, STATUS_ERROR, VALIDATION_IMPOSSIBLE;
	
	public boolean equals(ValidationStatus validationStatus, ValidationStatus compareStatus){
		int compareTo = validationStatus.compareTo(compareStatus);
			
		return compareTo == 0;
	}
}
