package ua.nure.chernev.FinalTask.db.entity;

/**
 * Hospital Card entity
 */
public class HospitalCard extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3258199714803626974L;

	private int statusId;

	private int patientId;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int diagnosis) {
		this.statusId = diagnosis;
	}

	@Override
	public String toString() {
		return "HospitalCard [patientId= " + patientId + ", statusId= " + statusId + "]";
	}

}
