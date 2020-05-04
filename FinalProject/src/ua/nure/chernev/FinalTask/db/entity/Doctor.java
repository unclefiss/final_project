package ua.nure.chernev.FinalTask.db.entity;


/**
 * Doctor entity
 * 
 */
public class Doctor extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5890895843622270583L;

	private int positionId;
	
	private int categoryId;
	
	private int patientCount;
	
	private int userId;

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Doctor [firstName= "+ this.getFirstName()+ ", lastName= "+ this.getLastName() +", positionId= " + positionId +
				", categoryId= " + categoryId +
				", patientCount= " + patientCount
				+ ", userId= " + userId + "]";
	}
	
	
}
