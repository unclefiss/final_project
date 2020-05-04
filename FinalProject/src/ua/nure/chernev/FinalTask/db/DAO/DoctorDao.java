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
 * Data access object for Doctor entity.
 */
public class DoctorDao {

	private static final String SQL_INSERT_DOCTOR = "INSERT INTO `doctors` (`id`, `position_id`, `category_id`, `patients_count`, `user_id`)"
			+ " VALUES (NULL, '0', ?, '0', ?)";

	private static final String SQL_APPOINT_DOCTOR = "INSERT INTO `patients_list` (`doctor_id`, `patient_id`) VALUES (?, ?);";

	private static final String SQL_DELETE_APPOINT_DOCTOR = "DELETE FROM patients_list WHERE patients_list.patient_id = ?";

	private static final String SQL_UPDATE_DOCTOR = "UPDATE doctors SET position_id=?, category_id=?, patients_count=?, user_id=?"
			+ "	WHERE id=?";

	private static final String SQL_FIND_DOCTOR_BY_USER_ID = "SELECT doctors.*, users.* FROM doctors, users"
			+ " WHERE user_id = ? AND users.id = doctors.user_id";

	private static final String SQL_FIND_DOCTOR_BY_ID = "SELECT doctors.*, users.* FROM doctors, users"
			+ " WHERE doctors.id = ? AND users.id = doctors.user_id";

	private static final String SQL_FIND_DOCTOR_BY_CATEGORY_ID = "SELECT doctors.*, users.* FROM doctors, users"
			+ " WHERE category_id = ? AND users.id = doctors.user_id";

	private static final String SQL_FIND_ALL_DOCTORS = "SELECT doctors.*, users.first_name, users.last_name FROM doctors, users"
			+ " WHERE users.id = doctors.user_id AND doctors.position_id = 0";

	private static final String SQL_INSERT_NURSE = "INSERT INTO `doctors` (`id`, `position_id`, `category_id`, `patients_count`, `user_id`)"
			+ " VALUES (NULL, '1', NULL, '0', ?)";

	private static final String SQL_FIND_ALL_NURSES = "SELECT doctors.*, users.first_name, users.last_name FROM doctors, users"
			+ " WHERE users.id = doctors.user_id AND doctors.position_id = 1";

	private static final String SQL_FIND_DOCTOR_FOR_PATIENT = "SELECT doctors.*, users.first_name, users.last_name FROM doctors, users, patients_list"
			+ " WHERE users.id = doctors.user_id AND doctors.position_id = 0 AND patients_list.patient_id = ?"
			+ " AND patients_list.doctor_id = doctors.id";
	
	private static final String SQL_DELETE_DOCTOR = "DELETE FROM `doctors` WHERE `doctors`.`id` = ?";
	

	/**
	 * Update doctor.
	 * 
	 * @param user user to update.
	 * @throws DBException
	 */
	public void updateDoctor(Doctor doctor) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			updateDoctor(con, doctor);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Update doctor.
	 * 
	 * @param user user to update.
	 * @throws SQLException
	 */
	private void updateDoctor(Connection con, Doctor doctor) throws SQLException {
		PreparedStatement pstmt = null;
//		private static final String SQL_UPDATE_DOCTOR = "UPDATE doctors SET position_id=?, category_id=?, patients_count=?, user_id=?"
//				+ "	WHERE id=?";
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_DOCTOR);
			int k = 1;
			pstmt.setInt(k++, doctor.getPositionId());
			pstmt.setInt(k++, doctor.getCategoryId());
			pstmt.setInt(k++, doctor.getPatientCount());
			pstmt.setInt(k++, doctor.getUserId());
			pstmt.setLong(k, doctor.getId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Delete doctor.
	 * 
	 * @throws DBException
	 */
	public void deleteDoctor(Doctor doctor) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			deleteDoctor(con, doctor);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Delete doctor.
	 * 
	 * @throws SQLException
	 */
	private void deleteDoctor(Connection con, Doctor doctor) throws SQLException {
		PreparedStatement pstmt = null;
//		private static final String SQL_UPDATE_DOCTOR = "UPDATE doctors SET position_id=?, category_id=?, patients_count=?, user_id=?"
//				+ "	WHERE id=?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE_DOCTOR);
			int k = 1;
			pstmt.setLong(k, doctor.getId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Delete appointment doctor.
	 * 
	 * @param patient patient to update.
	 * @throws DBException
	 */
	public void deleteAppointDoctor(Patient patient) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			deleteAppointDoctor(con, patient);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_APPOINT_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Delete appointment doctor.
	 * 
	 * @param user user to update.
	 * @throws SQLException
	 */
	private void deleteAppointDoctor(Connection con, Patient patient) throws SQLException {
		PreparedStatement pstmt = null;
//		private static final String SQL_UPDATE_DOCTOR = "UPDATE doctors SET position_id=?, category_id=?, patients_count=?, user_id=?"
//				+ "	WHERE id=?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE_APPOINT_DOCTOR);
			int k = 1;
			pstmt.setLong(k++, patient.getId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Insert doctor.
	 * 
	 * @param doctor doctor to insert.
	 * @throws DBException
	 */
	public void insertDoctor(Doctor doctor) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertDoctor(con, doctor);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Insert doctor.
	 * 
	 * @param doctor doctors to insert.
	 * @throws SQLException
	 */
	private void insertDoctor(Connection con, Doctor doctor) throws SQLException {
		PreparedStatement pstmt = null;
//		INSERT INTO `doctors` (`id`, `position_id`, `category_id`, `patients_count`, `user_id`) VALUES (NULL, '1', '0', '0', '8');
		try {
			pstmt = con.prepareStatement(SQL_INSERT_DOCTOR);
			int k = 1;
			pstmt.setInt(k++, doctor.getCategoryId());
			pstmt.setInt(k, doctor.getUserId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns all doctors.
	 * 
	 */
	public List<Doctor> findDoctors() throws DBException {
		List<Doctor> doctorsList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_DOCTORS);
			while (rs.next()) {
				doctorsList.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTORS, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctorsList;
	}

	/**
	 * Returns doctor for patient.
	 * 
	 */
	public Doctor findDoctorForPatient(Patient patient) throws DBException {
		Doctor doctor = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			pstmt = con.prepareStatement(SQL_FIND_DOCTOR_FOR_PATIENT);
			pstmt.setLong(1, patient.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				doctor = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTORS, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctor;
	}

	/**
	 * Appoint doctor.
	 * 
	 * @param doctorId for doctorId, patientId for patientId.
	 * @throws SQLException
	 * @throws DBException
	 */
	public void appointDoctor(int doctorId, int patientId) throws DBException {
		Connection con = null;
//		private static final String SQL_INSERT_PATIENT = "INSERT INTO `patients` (`id`, `birthday`, `phonenumber`, `user_id`)"
//				+" VALUES (NULL, ?, ?, ?);";
		try {
			con = DBUtil.getInstance().getConnection();
			appointDoctor(con, doctorId, patientId);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_APPOINT_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Appoint doctor.
	 * 
	 * @param doctorId for doctorId, patientId for patientId.
	 * @throws SQLException
	 */
	private void appointDoctor(Connection con, int doctorId, int patientId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_APPOINT_DOCTOR);
			int k = 1;
			pstmt.setInt(k++, doctorId);
			pstmt.setInt(k, patientId);
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns a doctor with the given user.id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public Doctor findDoctorByUserId(User user) throws DBException {
		Doctor doctor = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			pstmt = con.prepareStatement(SQL_FIND_DOCTOR_BY_USER_ID);
			pstmt.setLong(1, user.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				doctor = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_BY_USER_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctor;
	}

	/**
	 * Returns a doctor with the given id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public Doctor findDoctorById(int id) throws DBException {
		Doctor doctor = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			pstmt = con.prepareStatement(SQL_FIND_DOCTOR_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				doctor = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_BY_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctor;
	}

	/**
	 * Returns a doctors with the given categoryId.
	 * 
	 * @param categoryId categoryId.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Doctor> findDoctorsByCategoryId(int categoryId) throws DBException {
		List<Doctor> doctors = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			pstmt = con.prepareStatement(SQL_FIND_DOCTOR_BY_CATEGORY_ID);
			pstmt.setLong(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				doctors.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DOCTOR_BY_CATEGORY_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return doctors;
	}

	/**
	 * Insert nurse.
	 * 
	 * @param nurse nurse to insert.
	 * @throws DBException
	 */
	public void insertNurse(Doctor nurse) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertNurse(con, nurse);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_NURSE, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Insert nurse.
	 * 
	 * @param nurse nurse to insert.
	 * @throws SQLException
	 */
	private void insertNurse(Connection con, Doctor nurse) throws SQLException {
		PreparedStatement pstmt = null;
//		private static final String SQL_INSERT_NURSE = "INSERT INTO `doctors` (`id`, `position_id`, `category_id`, `patients_count`, `user_id`)"
//				+ " VALUES (NULL, '1', NULL, '0', ?)";
		try {
			pstmt = con.prepareStatement(SQL_INSERT_NURSE);
			pstmt.setInt(1, nurse.getUserId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns all nurses.
	 * 
	 * @return List of category entities.
	 */
	public List<Doctor> findNurses() throws DBException {
		List<Doctor> nursesList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DoctorMapper mapper = new DoctorMapper();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_NURSES);
			while (rs.next()) {
				nursesList.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_NURSES, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return nursesList;
	}

	/**
	 * Extracts a user from the result set row.
	 */
	private static class DoctorMapper implements EntityMapper<User> {

		@Override
		public Doctor mapRow(ResultSet rs) {
			try {
				Doctor doctor = new Doctor();
				doctor.setId(rs.getLong(Fields.ENTITY_ID));
				doctor.setCategoryId(rs.getInt(Fields.DOCTOR_CATEGORY_ID));
				doctor.setPatientCount(rs.getInt(Fields.DOCTOR_PATIENT_COUNT));
				doctor.setPositionId(rs.getInt(Fields.DOCTOR_POSITION_ID));
				doctor.setUserId(rs.getInt(Fields.DOCTOR_USER_ID));
				doctor.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
				doctor.setLastName(rs.getString(Fields.USER_LAST_NAME));
				return doctor;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
