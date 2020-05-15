package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Prescription;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Prescription entity.
 */
public class PrescriptionDao {

	private static final String SQL_INSERT_PRESCRIPTION = "INSERT INTO `prescriptions` (`id`, `comment`, `diagnosis_id`, `status_id`, `type_id`, `doctor_id`) VALUES (NULL, ?, ?, '1', ?, ?)";
//														   INSERT INTO `prescriptions` (`id`, `comment`, `diagnosis_id`, `status_id`, `type_id`, `doctor_id`) VALUES (NULL, 'pills', '28', '1', '0', '36');

	private static final String SQL_FIND_PRESCRIPTION_BY_CARD_ID = "SELECT prescriptions.*, diagnosis.* FROM prescriptions, diagnosis"
			+ " WHERE diagnosis.card_id = ? AND diagnosis.id = prescriptions.diagnosis_id";
	
	private static final String SQL_UPDATE_PRESCRIPTION = "UPDATE `prescriptions` SET `comment` = ?, diagnosis_id = ? , `status_id` = ?, `type_id` = ? WHERE `prescriptions`.`id` = ?"; 
	
	private static final String SQL_FIND_PRESCRIPTION_BY_ID = "SELECT prescriptions.*, diagnosis.text FROM prescriptions, diagnosis WHERE prescriptions.id = ?";
	
	/**
	 * Insert prescription.
	 * 
	 * @param prescription prescription to insert.
	 * @throws DBException
	 */
	public void insertPrescription(Prescription prescription) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertPrescription(con, prescription);
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
	private void insertPrescription(Connection con, Prescription prescription) throws SQLException {
		PreparedStatement pstmt = null;
//		INSERT INTO `prescriptions` (`id`, `comment`, `card_id`, `status_id`, `type_id`) VALUES (NULL, ?, ?, '1', ?);";

		try {
			pstmt = con.prepareStatement(SQL_INSERT_PRESCRIPTION);
			int k = 1;
			pstmt.setString(k++, prescription.getComment());
			pstmt.setInt(k++, prescription.getDiagnosisId());
			pstmt.setInt(k++, prescription.getTypeId());
			pstmt.setInt(k, prescription.getDoctorId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	
	/**
	 * Returns a prescription with the given id.
	 * 
	 * @param id int id.
	 * @throws DBException
	 */
	public Prescription findPrescriptionsById(int id) throws DBException {
		Prescription prescription = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PrescriptionMapper mapper = new PrescriptionMapper();
			pstmt = con.prepareStatement(SQL_FIND_PRESCRIPTION_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				prescription = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PRESCROPTION_BY_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return prescription;
	}
	
	/**
	 * Returns a prescription with the given card.id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Prescription> findPrescriptionsByCardId(HospitalCard card) throws DBException {
		List<Prescription> prescriptions = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			PrescriptionMapper mapper = new PrescriptionMapper();
			pstmt = con.prepareStatement(SQL_FIND_PRESCRIPTION_BY_CARD_ID);
			pstmt.setLong(1, card.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prescriptions.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PRESCROPTION_BY_CARD_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return prescriptions;
	}
	
	/**
	 * Update prescription.
	 *
	 * @param prescription prescription to update.
	 */
	public void updatePrescription(Prescription prescription) {
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
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PRESCRIPTION);
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
	private static class PrescriptionMapper implements EntityMapper<Prescription> {

		@Override
		public Prescription mapRow(ResultSet rs) {
			try {
				Prescription prescription = new Prescription();
				prescription.setId(rs.getLong(Fields.ENTITY_ID));
				prescription.setComment(rs.getString(Fields.PRESCRIPTION_COMMENT));
				prescription.setStatusId(rs.getInt(Fields.PRESCRIPTION_STATUS_ID));
				prescription.setTypeId(rs.getInt(Fields.PRESCRIPTION_TYPE_ID));
				prescription.setDiagnosisId(rs.getInt(Fields.PRESCRIPTION_DIAGNOSIS_ID));
				prescription.setDiagnosisComment(rs.getString(Fields.DIAGNOSIS_COMMENT));
				prescription.setDoctorId(rs.getInt(Fields.PRESCRIPTION_DOCTOR_ID));
				return prescription;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}