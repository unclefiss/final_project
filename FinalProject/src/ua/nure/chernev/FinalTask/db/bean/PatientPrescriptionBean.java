package ua.nure.chernev.FinalTask.db.bean;

import ua.nure.chernev.FinalTask.db.entity.Entity;

public class PatientPrescriptionBean extends Entity {

	private static final long serialVersionUID = 2611028156843743099L;

	private String patientFirstName;

	private String patientLastName;

	private int prescriptionId;

	private int statusId;

	private int typeId;

	private String diagnosisComment;

	private String prescriptionComment;
	
	private int doctorId;

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
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

	public String getDiagnosisComment() {
		return diagnosisComment;
	}

	public void setDiagnosisComment(String diagnosisComment) {
		this.diagnosisComment = diagnosisComment;
	}

	public String getPrescriptionComment() {
		return prescriptionComment;
	}

	public void setPrescriptionComment(String prescriptionComment) {
		this.prescriptionComment = prescriptionComment;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public String toString() {
		return "PateintPrescriptionBean [patientFirstName=" + patientFirstName + ", patientLastName=" + patientLastName
				+ ", prescriptionId=" + prescriptionId + ", statusId=" + statusId + ", typeId=" + typeId
				+ ", diagnosisComment=" + diagnosisComment + ", prescriptionComment=" + prescriptionComment + "]";
	}

}
