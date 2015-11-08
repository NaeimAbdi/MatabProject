/**
 * 
 */
package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.jdbc.DBManager;

/**
 * We can insert secretaries into database, get each secretaries of system,
 * delete secretaries from database and update their information and we can find
 * doctor for each secretary
 */
public class SecretaryDAO {

	private static final String INSERT_SECRETARY = "INSERT INTO secretary(name,family,phone_number,user_id,mobile_number) VALUES(?,?,?,?,?)";

	private static final String GET_SECRETARY = "SELECT * FROM secretary WHERE user_id = ? ";
	private static final String DELETE_SECRETARY = "DELETE FROM secretary WHERE id = ? ";
	private static final String UPDATE_SECRETARY = "UPDATE secretary SET name = ? , family = ?, phone_number = ?, mobile_number = ? WHERE id = ?";
	private static final String FIND_DOCTORS_FOR_SECRETARY = "SELECT * FROM doctor d, secretary s, secretaries_of_doctors sd WHERE sd.doctor_id = d.id AND sd.secretary_id = s.id AND s.id = ? ";

	public static ArrayList<Doctor> findDoctorsForSecretary(Secretary secretary) {
		ArrayList<Doctor> retVal = new ArrayList<Doctor>();

		String sql = FIND_DOCTORS_FOR_SECRETARY;

		Connection connection = DBManager.getConnection();
		//
		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, secretary.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Doctor doctor = new Doctor();
				doctor.setName(rs.getString("name"));
				doctor.setFamily(rs.getString("family"));
				doctor.setUserID(rs.getString("user_id"));
				doctor.setPhoneNumber(rs.getString("phone_number"));
				doctor.setMobileNumber(rs.getString("mobile_number"));
				doctor.setId(rs.getInt("id"));

				retVal.add(doctor);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static boolean addSecretary(Secretary secretary) {

		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_SECRETARY;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//

			ps.setString(1, secretary.getName());
			ps.setString(2, secretary.getFamily());
			ps.setString(3, secretary.getPhoneNumber());
			ps.setString(4, secretary.getUserID());
			ps.setString(5, secretary.getMobileNumber());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return retVal;

	}

	public static Secretary getSecretary(String username) {
		Secretary secretary = null;

		Connection con = DBManager.getConnection();

		String sql = GET_SECRETARY;
		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				secretary = new Secretary();
				secretary.setName(rs.getString("name"));
				secretary.setFamily(rs.getString("family"));
				secretary.setPhoneNumber(rs.getString("phone_number"));
				secretary.setUserID(rs.getString("user_id"));
				secretary.setMobileNumber(rs.getString("mobile_number"));
				secretary.setId(rs.getInt("id"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return secretary;

	}

	public static boolean editSecretary(Secretary secretary) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_SECRETARY;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, secretary.getName());
			ps.setString(2, secretary.getFamily());
			ps.setString(3, secretary.getPhoneNumber());
			ps.setString(4, secretary.getMobileNumber());
			ps.setInt(5, secretary.getId());
			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;

	}

	public static boolean deleteSecretary(Secretary secretary) {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_SECRETARY;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, secretary.getId());

			int nRecord = ps.executeUpdate();

			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return retVal;

	}

}
