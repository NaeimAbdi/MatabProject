package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;
import org.bihe.jdbc.DBManager;

/**
 * This class save fix holidays into database and read fix holidays from
 * database. The holidays which are fix through the year
 */
public class FixHolidaysDAO {

	private static final String INSERT_FIX_HOLIDAY = "INSERT INTO fix_holidays(description , day , month) VALUES(? , ? , ?)";
	private static final String GET_ALL_HOLIDAY = "SELECT * FROM fix_holidays";

	public static ArrayList<FixHolidays> getAllHolidays() {
		ArrayList<FixHolidays> retVal = new ArrayList<FixHolidays>();
		//
		String sql = GET_ALL_HOLIDAY;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet res = pst.executeQuery();
			while (res.next()) {

				FixHolidays fixHolidays = new FixHolidays();
				//
				fixHolidays.setId(res.getInt("id"));
				fixHolidays.setDescribtion(res.getString("description"));
				fixHolidays.setDay(res.getInt("day"));
				fixHolidays.setMonth(res.getInt("month"));

				//
				retVal.add(fixHolidays);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean insertHoliday(FixHolidays fixHoliday) {
		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_FIX_HOLIDAY;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, fixHoliday.getDescribtion());
			ps.setInt(2, fixHoliday.getDay());
			ps.setInt(3, fixHoliday.getMonth());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

}
