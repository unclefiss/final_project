package ua.nure.chernev.FinalTask.db;

import ua.nure.chernev.FinalTask.db.entity.Doctor;

/**
 * Enumeration for doctor positions.
 * 
 * @author K. Chernev
 * 
 */
public enum Position {
	DOCTOR, NURSE;
	
	public static Position getPosition(Doctor doctor) {
		int positionId = doctor.getPositionId();
		return Position.values()[positionId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
