package ua.nure.chernev.FinalTask.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.bean.NurseDoctorBean;
import ua.nure.chernev.FinalTask.db.bean.PatientPrescriptionBean;
import ua.nure.chernev.FinalTask.db.entity.Prescription;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

public class NurseDoctorBeanDao {
	
	private final String SQL_INSERT_NURSE_DOCTOR = "INSERT INTO `doctor_nurse_list` (`nurse_id`, `doctor_id`, `prescriptions_count`) VALUES (?, ?, '1')";
	
	private final String SQL_UPDATE_NURSE_DOCTOR = "UPDATE `doctor_nurse_list` SET `prescriptions_count`=? WHERE nurse_id = ?";
	
	/**
	 * Insert prescription.
	 * 
	 * @param prescription prescription to insert.
	 * @throws DBException
	 */
	public void insertNurseDoctor(NurseDoctorBean bean) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertNurseDoctor(con, bean);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_PRESCRIPTION, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);;
		}
	}

	/**
	 * Insert prescription.
	 * 
	 * @param prescription prescription to insert.
	 * @throws SQLException
	 */
	private void insertNurseDoctor(Connection con, NurseDoctorBean bean) throws SQLException {
		PreparedStatement pstmt = null;
//		"INSERT INTO `doctor_nurse_list` (`nurse_id`, `doctor_id`, `prescriptions_count`) VALUES (?, ?, '1')";
		
		try {
			pstmt = con.prepareStatement(SQL_INSERT_NURSE_DOCTOR);
			int k = 1;
			pstmt.setInt(k++, bean.getNurseId());
			pstmt.setInt(k, bean.getDoctorId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Update prescription.
	 *
	 * @param prescription prescription to update.
	 */
	public void updateNurseDoctor(Prescription prescription) {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			updatePrescription(con, prescription);
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods (for transactions)
	// //////////////////////////////////////////////////////////

	/**
	 * Update prescription.
	 *
	 * @param user user to update.
	 * @throws SQLException
	 */
	private void updatePrescription(Connection con, Prescription prescription) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_NURSE_DOCTOR);
		int k = 1;
		pstmt.setString(k++, prescription.getComment());
		pstmt.setInt(k++, prescription.getDiagnosisId());
		pstmt.setInt(k++, prescription.getStatusId());
		pstmt.setInt(k++, prescription.getTypeId());
		pstmt.setLong(k, prescription.getId());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	/**
	 * Extracts a prescription from the result set row.
	 */
	private static class NurseDoctorMapper implements EntityMapper<NurseDoctorBean> {

		@Override
		public NurseDoctorBean mapRow(ResultSet rs) {
			try {
				NurseDoctorBean bean = new NurseDoctorBean();
				bean.setDoctorId(rs.getInt(Fields.NURSE_DOCTOR_DOCTOR_ID));
				bean.setNurseId(rs.getInt(Fields.NURSE_DOCTOR_NURSE_ID));
				bean.setPrescriptionCount(rs.getInt(Fields.NURSE_DOCTOR_PRESCRIPTION_COUNT));
				return bean;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
