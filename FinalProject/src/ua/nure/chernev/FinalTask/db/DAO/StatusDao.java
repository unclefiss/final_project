package ua.nure.chernev.FinalTask.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.Status;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;


/**
 * Data access object for User entity.
 */
public class StatusDao {
	
	private static final String SQL_FIND_ALL_PRESCRIPTION_STATUSES = "SELECT * FROM prescription_status";
	
	private static final String SQL_FIND_ALL_PATIENT_STATUSES = "SELECT * FROM patient_status";
	
	private static final String SQL_FIND_ALL_TYPES = "SELECT * FROM type";
	
	private static final String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";
	
	private static final String SQL_FIND_ALL_POSITIONS = "SELECT * FROM position";
	
	private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";


	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findPatientStatuses() throws DBException {
		List<Status> statuses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_PATIENT_STATUSES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				statuses.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return statuses;
	}
	
	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findTypes() throws DBException {
		List<Status> statuses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_TYPES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				statuses.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_TYPE, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return statuses;
	}
	

	


	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findPrescriptionStatuses() throws DBException {
		List<Status> statuses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_PRESCRIPTION_STATUSES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				statuses.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return statuses;
	}
	
	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findRoles() throws DBException {
		List<Status> statuses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_ROLES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				statuses.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return statuses;
	}
	
	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findPositions() throws DBException {
		List<Status> statuses = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_POSITIONS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				statuses.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return statuses;
	}
	
	/**
	 * Returns all categories
	 * 
	 * @param login User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public List<Status> findCategories() throws DBException {
		List<Status> categories = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			StatusMapper mapper = new StatusMapper();
			pstmt = con.prepareStatement(SQL_FIND_ALL_CATEGORIES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				categories.add(mapper.mapRow(rs));
			}
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_PATIENT_ID, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return categories;
	}


	/**
	 * Extracts a user from the result set row.
	 */
	private static class StatusMapper implements EntityMapper<Status> {

		@Override
		public Status mapRow(ResultSet rs) {
			try {
				Status status = new Status();
				status.setId(rs.getLong(Fields.ENTITY_ID));
				status.setName(rs.getString(Fields.CATEGORY_NAME));
				status.setNameRu(rs.getString(Fields.CATEGORY_NAME_RU));
				return status;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
