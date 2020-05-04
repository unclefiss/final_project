package ua.nure.chernev.FinalTask.db.entity;


/**
 * Prescription entity
 * 
 * @author K. Chernev
 * 
 */
public class Prescription extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306539590969925680L;

	private String comment;
	
	private int statusId;
	
	private int typeId;
	
	private int diagnosisId;
	
	private String diagnosisComment;

	public String getDiagnosisComment() {
		return diagnosisComment;
	}

	public void setDiagnosisComment(String diagnosisComment) {
		this.diagnosisComment = diagnosisComment;
	}

	public int getDiagnosisId() {
		
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Appointment [comment=" + comment + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}

	
	
}
