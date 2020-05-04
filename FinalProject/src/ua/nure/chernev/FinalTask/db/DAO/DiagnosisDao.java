package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.Diagnosis;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Diagnosis entity.
 */
public class DiagnosisDao {

	private static final String SQL_FIND_DIAGNOSIS_BY_CARD_ID = "SELECT diagnosis.* FROM diagnosis"
			+ " WHERE card_id = ?";
	
	private static final String SQL_INSERT_INTERIM_DIAGNOSIS = "INSERT INTO `diagnosis` (`id`, `text`, `card_id`, `status_id`) VALUES (NULL, ?, ?, '1');";

	private static final String SQL_INSERT_FINAL_DIAGNOSIS = "INSERT INTO `diagnosis` (`id`, `text`, `card_id`, `status_id`) VALUES (NULL, ?, ?, '0');";
	
	private static final String SQL_FIND_ALL_DIAGNOSES = "SELECT diagnosis.* FROM diagnosis";
	
	private static final String SQL_FIND_FINAL_DIAGNOSIS = "SELECT diagnosis.* FROM diagnosis"
			+" WHERE card_id = ? AND diagnosis.status_id = 0";
	
	
	/**
	 * Insert interim diagnosis into card.
	 * 
	 * @param hospital card hospital_card to insert.
	 * @throws DBException
	 */
	public void insertInterimDiagnosis(Diagnosis diagnosis) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertInterimDiagnosis(con, diagnosis);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_PRESCRIPTION, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);;
		}
	}

	/**
	 * Insert an interim diagnosis.
	 * 
	 * @param hospital card hospital_cards to insert.
	 * @throws SQLException
	 */
	private void insertInterimDiagnosis(Connection con, Diagnosis diagnosis) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_INSERT_INTERIM_DIAGNOSIS);
			int k = 1;
			pstmt.setString(k++, diagnosis.getComment());
			pstmt.setInt(k, diagnosis.getCardId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	
	/**
	 * Insert final diagnosis into card.
	 * 
	 * @param hospital card hospital_card to insert.
	 * @throws DBException
	 */
	public void insertFinalDiagnosis(Diagnosis diagnosis) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertFinalDiagnosis(con, diagnosis);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_PRESCRIPTION, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);;
		}
	}

	/**
	 * Insert final diagnosis.
	 * 
	 * @param hospital card hospital_cards to insert.
	 * @throws SQLException
	 */
	private void insertFinalDiagnosis(Connection con, Diagnosis diagnosis) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_INSERT_FINAL_DIAGNOSIS);
			int k = 1;
			pstmt.setString(k++, diagnosis.getComment());
			pstmt.setInt(k, diagnosis.getCardId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Returns a last diagnosis.id
	 * 
	 * @return int diagnosis.id
	 * @throws DBException
	 */
	public int findLastDiagnosisId() throws DBException {
		int diagnosisId = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_DIAGNOSES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				diagnosisId = rs.getInt("id");
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return diagnosisId;
	}
	
	/**
	 * Returns a diagnosis with the given card.id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Diagnosis> findDiagnosisByCardId(HospitalCard card) throws DBException {
		List<Diagnosis> prescriptions = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DiagnosisMapper mapper = new DiagnosisMapper();
			pstmt = con.prepareStatement(SQL_FIND_DIAGNOSIS_BY_CARD_ID);
			pstmt.setLong(1, card.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prescriptions.add(mapper.mapRow(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DIAGNOSIS_BY_CARD_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return prescriptions;
	}
	
	/**
	 * Returns a final diagnosis.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public Diagnosis findFinalDiagnosis(HospitalCard card) throws DBException {
		Diagnosis diagnosis = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			DiagnosisMapper mapper = new DiagnosisMapper();
			pstmt = con.prepareStatement(SQL_FIND_FINAL_DIAGNOSIS);
			pstmt.setLong(1, card.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				diagnosis = mapper.mapRow(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DIAGNOSIS_BY_CARD_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return diagnosis;
	}

	/**
	 * Extracts a diagnosis from the result set row.
	 */
	private static class DiagnosisMapper implements EntityMapper<Diagnosis> {

		@Override
		public Diagnosis mapRow(ResultSet rs) {
			try {
				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setCardId(rs.getInt(Fields.DIAGNOSIS_CARD_ID));
				diagnosis.setComment(rs.getString(Fields.DIAGNOSIS_COMMENT));
				diagnosis.setStatusId(rs.getInt(Fields.DIAGNOSIS_STATUS_ID));
				return diagnosis;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
