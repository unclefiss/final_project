package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.bean.PatientPrescriptionBean;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Patient Prescriptin bean.
 */
public class PatientPrescriptionBeanDao {

	private static final String SQL_FIND_ALL_PATIENT_PRESCRIPTION_BEAN_FOR_NURSE = "SELECT users.first_name, users.last_name, prescriptions.*, diagnosis.text"
	+" FROM patients, users, prescriptions, hospital_cards, diagnosis"
			+" WHERE users.id = patients.user_id AND hospital_cards.patient_Id = patients.id"
	+" AND diagnosis.card_id = hospital_cards.id AND prescriptions.diagnosis_id = diagnosis.id"
			+" AND (prescriptions.type_id = 0 OR prescriptions.type_id = 1)";
	
	private static final String SQL_FIND_PATIENT_PRESCRIPTION_BEAN_FOR_DOCTOR = "SELECT users.first_name, users.last_name, prescriptions.*, diagnosis.text"
			+" FROM patients, users, prescriptions, hospital_cards, diagnosis, patients_list"
			+" WHERE users.id = patients.user_id AND hospital_cards.patient_Id = patients.id"
			+" AND diagnosis.card_id = hospital_cards.id AND prescriptions.diagnosis_id = diagnosis.id"
			+" AND patients_list.doctor_id = ? AND patients_list.patient_id = patients.id";
	
	
	/**
	 * Returns a prescription for nurse.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<PatientPrescriptionBean> findPatientPrescriptionBeansForNurse() throws DBException {
		List<PatientPrescriptionBean> prescriptions = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PrescriptionMapper mapper = new PrescriptionMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_PATIENT_PRESCRIPTION_BEAN_FOR_NURSE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prescriptions.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PRESCROPTION_FOR_NURSE, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return prescriptions;
	}
	
	/**
	 * Returns a prescription for doctor.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<PatientPrescriptionBean> findPatientPrescriptionBeansForDoctor(Doctor doctor) throws DBException {
		List<PatientPrescriptionBean> prescriptions = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PrescriptionMapper mapper = new PrescriptionMapper();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_PRESCRIPTION_BEAN_FOR_DOCTOR);
			pstmt.setLong(1, doctor.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prescriptions.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PRESCROPTION_FOR_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return prescriptions;
	}
	
	
	/**
	 * Extracts a prescription from the result set row.
	 */
	private static class PrescriptionMapper implements EntityMapper<PatientPrescriptionBean> {

		@Override
		public PatientPrescriptionBean mapRow(ResultSet rs) {
			try {
				PatientPrescriptionBean prescription = new PatientPrescriptionBean();
				prescription.setPrescriptionComment(rs.getString(Fields.PRESCRIPTION_COMMENT));
				prescription.setStatusId(rs.getInt(Fields.PRESCRIPTION_STATUS_ID));
				prescription.setTypeId(rs.getInt(Fields.PRESCRIPTION_TYPE_ID));
				prescription.setDiagnosisComment(rs.getString(Fields.DIAGNOSIS_COMMENT));
				prescription.setPatientFirstName(rs.getString(Fields.USER_FIRST_NAME));
				prescription.setPatientLastName(rs.getString(Fields.USER_LAST_NAME));
				prescription.setPrescriptionId(rs.getInt(Fields.ENTITY_ID));
				return prescription;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
