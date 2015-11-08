package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.Holidays;
import org.bihe.jdbc.DBManager;

/**
 * We can add doctor's holidays in database and get all doctor holidays from it
 * 
 */
public class HolidayDAO {

	private static final String INSERT_HOLIDAY = "INSERT INTO holidays(description , holiday_date) VALUES(? , ?)";
	private static final String GET_ALL_HOLIDAYS = "SELECT * FROM holidays";

	public static boolean addHoliday(Holidays holiday) {

		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_HOLIDAY;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, holiday.getDescription());
			ps.setString(2, holiday.getHolidayDate());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static ArrayList<Holidays> getAllHolidays() {

		ArrayList<Holidays> retVal = new ArrayList<Holidays>();
		//
		String sql = GET_ALL_HOLIDAYS;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while (res.next()) {

				Holidays holidays = new Holidays();
				//
				holidays.setId(res.getInt("id"));
				holidays.setDescription(res.getString("description"));
				holidays.setHolidayDate(res.getString("holiday_date"));

				//
				retVal.add(holidays);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

}
