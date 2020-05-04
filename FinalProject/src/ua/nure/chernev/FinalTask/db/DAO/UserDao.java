package ua.nure.chernev.FinalTask.db.DAO;

import ua.nure.chernev.FinalTask.db.DBUtil;
import ua.nure.chernev.FinalTask.db.EntityMapper;
import ua.nure.chernev.FinalTask.db.Fields;
import ua.nure.chernev.FinalTask.db.entity.User;
import ua.nure.chernev.FinalTask.exception.DBException;
import ua.nure.chernev.FinalTask.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for User entity.
 */
public class UserDao {

	private static final String SQL__FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL__FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, password=?, first_name=?, last_name=?, locale_name=?"
			+ "	WHERE id=?";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_INSERT_USER = "INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `last_name`, `role_id`, locale_name)"
			+ " VALUES (NULL, ?, ?, ?, ?, ?,'ru')";

	private static final String SQL_DELETE_USER = "DELETE FROM `users` WHERE `users`.`id` = ?";
	
	/**
	 * Returns a user with the given identifier.
	 *
	 * @param id User identifier.
	 * @return User entity.
	 */
	public User findUser(Long id) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			UserMapper mapper = new UserMapper();
			pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return user;
	}

	/**
	 * Returns a user with the given login.
	 *
	 * @param login User login.
	 * @return User entity.
	 */
	public User findUserByLogin(String login) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			UserMapper mapper = new UserMapper();
			pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = mapper.mapRow(rs);
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			ex.printStackTrace();
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user user to update.
	 */
	public void updateUser(User user) {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			updateUser(con, user);
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
	 * Update user.
	 *
	 * @param user user to update.
	 * @throws SQLException
	 */
	public void updateUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
		int k = 1;
		pstmt.setString(k++, user.getLogin());
		pstmt.setString(k++, user.getPassword());
		pstmt.setString(k++, user.getFirstName());
		pstmt.setString(k++, user.getLastName());
		pstmt.setString(k++, user.getLocaleName());
		pstmt.setLong(k, user.getId());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	/**
	 * Delete user.
	 * 
	 * @param user user to delete.
	 * @throws DBException
	 */
	public void deleteUser(User user) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			deleteUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_DOCTOR, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Delete user.
	 * 
	 * @param user user to delete.
	 * @throws SQLException
	 */
	private void deleteUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_DELETE_USER);
			int k = 1;
			pstmt.setLong(k, user.getId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Returns a last user.id
	 * 
	 * @return int user.id.
	 * @throws DBException
	 */
	public int findLastUserId() throws DBException {
		int userId = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_USERS);
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
	 * Returns exist login in DB or not.
	 * 
	 * @param login User login.
	 * @return boolean existLogin.
	 * @throws DBException
	 */
	public boolean findExistLogin(String login) throws DBException {
		boolean existLogin = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (login.equals(rs.getString("login"))) {
					existLogin = true;
				}
			}
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
		return existLogin;
	}

	/**
	 * Insert user.
	 * 
	 * @param user user to insert.
	 * @throws DBException
	 */
	public void insertUser(User user) throws DBException {
		Connection con = null;
		try {
			con = DBUtil.getInstance().getConnection();
			insertUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			DBUtil.getInstance().rollbackAndClose(con);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			DBUtil.getInstance().commitAndClose(con);
		}
	}

	/**
	 * Insert user.
	 * 
	 * @param user user to insert.
	 * @throws SQLException
	 */
	private void insertUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_INSERT_USER);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setLong(k, user.getRoleId());
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
	}

	/**
	 * Extracts a user from the result set row.
	 */
	private static class UserMapper implements EntityMapper<User> {

		@Override
		public User mapRow(ResultSet rs) {
			try {
				User user = new User();
				user.setId(rs.getLong(Fields.ENTITY_ID));
				user.setLogin(rs.getString(Fields.USER_LOGIN));
				user.setPassword(rs.getString(Fields.USER_PASSWORD));
				user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
				user.setLastName(rs.getString(Fields.USER_LAST_NAME));
				user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
				user.setLocaleName(rs.getString(Fields.USER_LOCALE_NAME));
				return user;
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
