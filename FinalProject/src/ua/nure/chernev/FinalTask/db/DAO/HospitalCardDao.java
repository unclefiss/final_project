package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.HospitalCard;
import ua.nure.chernev.FinalTask.db.entity.Patient;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for Hospital card entity.
 */
public class HospitalCardDao {

	private static final String SQL_INSERT_CARD = "INSERT INTO `hospital_cards` (`id`, `status_id`, `patient_Id`) VALUES (NULL, '0', ?)";

	private static final String SQL_FIND_CARD_BY_PATIENT_ID = "SELECT hospital_cards.* FROM hospital_cards"
			+ " WHERE patient_id = ?";

	/**
	 * Insert hospital card.
	 * 
	 * @param hospital card hospital_card to insert.
	 * @throws DBException
	 */
	public void insertCard(HospitalCard card) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertCard(con, card);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_CARD, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Insert hospital card.
	 * 
	 * @param hospital card card to insert.
	 * @throws SQLException
	 */
	private void insertCard(Connection con, HospitalCard card) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_INSERT_CARD);
			pstmt.setInt(1, card.getPatientId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns a card with the given patient.id.
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public HospitalCard findCardByPatientId(Patient patient) throws DBException {
		HospitalCard card = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			CardMapper mapper = new CardMapper();
			pstmt = con.prepareStatement(SQL_FIND_CARD_BY_PATIENT_ID);
			pstmt.setLong(1, patient.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				card = mapper.mapRow(rs);
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return card;
	}

	/**
	 * Extracts a card from the result set row.
	 */
	private static class CardMapper implements EntityMapper<HospitalCard> {

		@Override
		public HospitalCard mapRow(ResultSet rs) {
			try {
				HospitalCard card = new HospitalCard();
				card.setId(rs.getLong(Fields.ENTITY_ID));
				card.setPatientId(rs.getInt(Fields.HOSPITAL_CARD_PATIENT_ID));
				card.setStatusId(rs.getInt(Fields.HOSPITAL_CARD_STATUS_ID));
				return card;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
