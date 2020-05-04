package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.Doctor;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Patient entity.
 */
public class PatientDao {

	private static final String SQL_INSERT_PATIENT = "INSERT INTO `patients` (`id`, `birthday`, `phonenumber`, `user_id`, `status_id`, `date_registration`, `date_discharge`)"
			+ " VALUES (NULL, ?, ?, ?, '0', ?, '2000-01-01')";

	private static final String SQL_FIND_PATIENT_BY_USER_ID = "SELECT patients.*, users.* FROM patients, users"
			+ " WHERE user_id = ? AND users.id = patients.user_id";

	private static final String SQL_FIND_PATIENT_BY_ID = "SELECT patients.*, users.* FROM patients, users"
			+ " WHERE patients.id = ? AND users.id = patients.user_id";

	private static final String SQL_FIND_ALL_PATIENTS = "SELECT patients.*, users.first_name, users.last_name FROM patients, users"
			+ " WHERE users.id = patients.user_id";

	private static final String SQL_FIND_NO_DOCTOR_PATIENTS = "SELECT patients.*, users.first_name, users.last_name FROM patients, users"
			+ " WHERE users.id = patients.user_id AND patients.status_id = 0";

	private static final String SQL_UPDATE_PATIENT = "UPDATE patients SET birthday=?, phonenumber=?, status_id=?, user_id=?, date_discharge = ?"
			+ "	WHERE id=?";

	private static final String SQL_FIND_PATIENTS_FOR_DOCTOR = "SELECT patients.*, users.first_name, users.last_name FROM patients, users, patients_list"
			+ " WHERE users.id = patients.user_id AND patients_list.doctor_id = ? AND patients_list.patient_id = patients.id";

	private static final String SQL_FIND_PATIENT_WITH_DOCTOR = "SELECT patients.*, users.first_name, users.last_name FROM patients, users, patients_list"
			+ " WHERE users.id = patients.user_id AND patients.id = ? AND patients.id = patients_list.patient_id";


	/**
	 * Insert patient.
	 * 
	 * @param patient patient to insert.
	 * @throws DBException
	 */
	public void insertPatient(Patient patient) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertPatient(con, patient);
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_PATIENT, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Insert patient.
	 * 
	 * @param patient patients to insert.
	 * @throws SQLException
	 */
	private void insertPatient(Connection con, Patient patient) throws SQLException {
		PreparedStatement pstmt = null;
//		"INSERT INTO `patients` (`id`, `birthday`, `phonenumber`, `user_id`, `status_id`, `date_registration`, `date_discharge`)"
//		+ " VALUES (NULL, ?, ?, ?, 0, ?, ?)";
		try {
			pstmt = con.prepareStatement(SQL_INSERT_PATIENT);
			int k = 1;
			pstmt.setString(k++, patient.getBirthday());
			pstmt.setString(k++, patient.getPhonenumber());
			pstmt.setInt(k++, patient.getUserId());
			pstmt.setString(k, patient.getDateRegistration());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Update patient.
	 * 
	 * @param user user to update.
	 * @throws DBException
	 */
	public void updatePatient(Patient patient) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			updatePatient(con, patient);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_PATIENT, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Update patient.
	 * 
	 * @param user user to update.
	 * @throws SQLException
	 */
	private void updatePatient(Connection con, Patient patient) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_PATIENT);
			int k = 1;
			pstmt.setString(k++, patient.getBirthday());
			pstmt.setString(k++, patient.getPhonenumber());
			pstmt.setInt(k++, patient.getStatusId());
			pstmt.setInt(k++, patient.getUserId());
			pstmt.setString(k++, patient.getDateDischarge());
			pstmt.setLong(k, patient.getId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns all patients.
	 * 
	 * @return List of category entities.
	 */
	public List<Patient> findPatients() throws DBException {
		List<Patient> patientsList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PatientMapper mapper = new PatientMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PATIENTS);
			while (rs.next()) {
				patientsList.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENTS, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return patientsList;
	}

	/**
	 * Returns patients which naven't doctor.
	 * 
	 * @return List of category entities.
	 */
	public List<Patient> findNoDoctorPatients() throws DBException {
		List<Patient> patientsList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PatientMapper mapper = new PatientMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_NO_DOCTOR_PATIENTS);
			while (rs.next()) {
				patientsList.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENTS, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return patientsList;
	}

	/**
	 * Returns does patient have a doctor.
	 * 
	 * @return List of category entities.
	 */
	public boolean findPatientWithDoctor(Patient patient) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		boolean doctor = false;
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_WITH_DOCTOR);
			pstmt.setLong(1, patient.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				doctor = true;
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENTS, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctor;
	}

	/**
	 * Returns patients for doctor.
	 * 
	 * @return List of category entities.
	 */
	public List<Patient> findPatientsForDoctor(Doctor doctor) throws DBException {
		List<Patient> patientsList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PatientMapper mapper = new PatientMapper();
			pstmt = con.prepareStatement(SQL_FIND_PATIENTS_FOR_DOCTOR);
			pstmt.setLong(1, doctor.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				patientsList.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENTS_FOR_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return patientsList;
	}

	/**
	 * Returns a patient with the given user.id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public Patient findPatientByUserId(User user) throws DBException {
		Patient patient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PatientMapper mapper = new PatientMapper();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_BY_USER_ID);
			pstmt.setLong(1, user.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				patient = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENT_BY_USER_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return patient;
	}

	/**
	 * Returns a patient with the given id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public Patient findPatientById(int id) throws DBException {
		Patient patient = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PatientMapper mapper = new PatientMapper();
			pstmt = con.prepareStatement(SQL_FIND_PATIENT_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				patient = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PATIENT_BY_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return patient;
	}

	/**
	 * Returns a last patient.id
	 * 
	 * @return int patient.id
	 * @throws DBException
	 */
	public int findLastPatientId() throws DBException {
		int userId = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_PATIENTS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userId = rs.getInt("id");
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return userId;
	}

	/**
	 * Extracts a patient from the result set row.
	 */
	private static class PatientMapper implements EntityMapper<User> {

		@Override
		public Patient mapRow(ResultSet rs) {
			try {
				Patient patient = new Patient();
				patient.setId(rs.getLong(Fields.ENTITY_ID));
				patient.setBirthday(rs.getString(Fields.PATIENT_BIRTHDAY));
				patient.setPhonenumber(rs.getString(Fields.PATIENT_PHONENUMBER));
				patient.setUserId(rs.getInt(Fields.PATIENT_USER_ID));
				patient.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
				patient.setLastName(rs.getString(Fields.USER_LAST_NAME));
				patient.setStatusId(rs.getInt(Fields.PATIENT_STATUS_ID));
				patient.setDateRegistration(rs.getString(Fields.PATIENT_DATE_REGISTRATION));
				patient.setDateDischarge(rs.getString(Fields.PATIENT_DATE_DISCHARGE));
				return patient;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
