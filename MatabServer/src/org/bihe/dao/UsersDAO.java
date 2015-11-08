package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bihe.bean.Users;
import org.bihe.jdbc.DBManager;

/**
 * this system have two roles. here doctor can add the user of this
 * system,delete it ,and update users
 */
public class UsersDAO {

	public static boolean isCorrect = false;

	private static final String INSERT_USER = "INSERT INTO users(username,password) VALUES(?,?)";

	private static final String CHECK_USERNAME = "SELECT * FROM users where username = ?";

	private static final String UPDATE_USERNAME_AND_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";

	private static final String DELETE_USERS = "DELETE FROM users WHERE username = ?";

	private static final String GET_USERNAME_AND_PASSWORD_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

	public static Users getUserNameAndPassword(String username) {
		Users user = new Users();
		Connection con = DBManager.getConnection();
		String sql = GET_USERNAME_AND_PASSWORD_BY_USERNAME;

		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet res = ps.executeQuery();

			if (res.next()) {

				user.setUsername(res.getString("username"));
				user.setPassword(res.getString("password"));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return user;

	}

	public static boolean updateUsersID(Users users) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_USERNAME_AND_PASSWORD;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, users.getPassword());
			ps.setString(2, users.getUsername());

			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;
	}

	public static boolean deleteUser(Users user) {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_USERS;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUsername());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return retVal;
	}

	public static boolean insertUsers(Users users) {
		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_USER;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			//
			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static boolean isAuthenticate(String userName, String password) {

		boolean result = false;

		String sql = CHECK_USERNAME;
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet res = pst.executeQuery();
			if (res.next()) {

				if (password.equals(res.getString("password"))) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;

	}

}
