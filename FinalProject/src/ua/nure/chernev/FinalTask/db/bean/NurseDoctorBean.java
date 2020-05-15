package ua.nure.chernev.FinalTask.db.bean;

import ua.nure.chernev.FinalTask.db.entity.Entity;

public class NurseDoctorBean extends Entity {


	private static final long serialVersionUID = -3091034685984524727L;
	
	private int doctorId;
	
	private int nurseId;
	
	private int prescriptionCount;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public int getPrescriptionCount() {
		return prescriptionCount;
	}

	public void setPrescriptionCount(int prescriptionCount) {
		this.prescriptionCount = prescriptionCount;
	}
	

	@Override
	public String toString() {
		return "NurseDoctorBean [doctorId=" + doctorId + ", nurseId=" + nurseId + ", prescriptionCount="
				+ prescriptionCount + "]";
	}

}
