package ua.nure.chernev.FinalTask.db;

import ua.nure.chernev.FinalTask.db.entity.User;

/**
 * Enumeration for user roles.
 * 
 * @author K. Chernev
 * 
 */
public enum Role {
	ADMIN, PATIENT, DOCTOR;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
