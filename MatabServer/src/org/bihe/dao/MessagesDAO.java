package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;
import org.bihe.jdbc.DBManager;

/**
 * By this class we can Insert different messages into database and see all
 * messages from database and if we have to delete them, we can delete it.
 * 
 */
public class MessagesDAO {
	private static final String INSERT_MESSAGE = "INSERT INTO messages(message,telephone_number) VALUES(?,?)";
	private static final String SHOW_ALL_MESSAGES = "SELECT * FROM messages";
	private static final String DELETE_MESSAGE_BY_ID = "DELETE FROM messages";

	public static boolean insertMessages(ArrayList<WaitingMessages> messages) {
		boolean retVal = true;

		Connection con = DBManager.getConnection();
		String sql = INSERT_MESSAGE;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			for (int j = 0; j < messages.size(); j++) {

				ps.setString(1, messages.get(j).getMessage());
				ps.setString(2, messages.get(j).getTelephoneNumber());
				int nRecord = ps.executeUpdate();
				if (nRecord != 1) {
					return false;
				}
			}
			//

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static boolean insertMessage(WaitingMessages message) {
		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_MESSAGE;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, message.getMessage());
			ps.setString(2, message.getTelephoneNumber());

			//
			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static ArrayList<WaitingMessages> getAllMessages() {
		ArrayList<WaitingMessages> retVal = new ArrayList<WaitingMessages>();
		//
		String sql = SHOW_ALL_MESSAGES;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while (res.next()) {

				WaitingMessages message = new WaitingMessages();
				//
				message.setMessage(res.getString("message"));
				message.setTelephoneNumber(res.getString("telephone_number"));

				//
				retVal.add(message);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean deleteAllMessages() {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_MESSAGE_BY_ID;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			int nRecord = ps.executeUpdate();
			retVal = nRecord >= 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retVal;
	}

}
