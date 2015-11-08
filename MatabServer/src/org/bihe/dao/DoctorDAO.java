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
 * This DAO class have insert query for add the objects of doctor to
 * database,select query for read the object of doctor from database. get all
 * doctor's system and update doctors.
 */
public class DoctorDAO {

	private static final String INSERT_DOCTOR = "INSERT INTO doctor(name,family,user_id,phone_number,mobile_number) VALUES(?,?,?,?,?)";
	private static final String UPDATE_DOCTOR = "UPDATE doctor set name = ? , family = ? , user_id = ? , phone_number = ? , mobile_number = ? WHERE id = ?";
	private static final String GET_DOCTOR = "SELECT * FROM doctor WHERE id = ? ";
	private static final String GET_DOCTOR_ID = "SELECT id FROM doctor WHERE name = ? AND family = ? AND user_id = ? AND phone_number = ? AND mobile_number = ? ";
	private static final String GET_DOCTOR_BY_USERNAME = "SELECT * FROM doctor WHERE user_id = ? ";
	private static final String FIND_SECRETARIES_OF_DOCTOR = "SELECT * FROM secretary s, doctor d, secretaries_of_doctors sd WHERE sd.doctor_id = d.id AND sd.secretary_id = s.id AND d.id = ?  ";

	public static ArrayList<Secretary> secretaryOfDoctor(Doctor doctor) {
		ArrayList<Secretary> retVal = new ArrayList<Secretary>();

		String sql = FIND_SECRETARIES_OF_DOCTOR;
		System.out.println(sql);
		Connection connection = DBManager.getConnection();
		//
		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			System.out.println(doctor.getId());
			ps.setInt(1, doctor.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Secretary secretary = new Secretary();
				secretary.setName(rs.getString("name"));
				secretary.setFamily(rs.getString("family"));
				secretary.setUserID(rs.getString("user_id"));
				secretary.setPhoneNumber(rs.getString("phone_number"));
				secretary.setMobileNumber(rs.getString("mobile_number"));
				secretary.setId(rs.getInt("id"));

				String name = doctor.getName();

				System.out.println(name);
				retVal.add(secretary);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(retVal);

		return retVal;
	}

	public static Doctor getDoctorByUsername(String username) {
		Doctor doctor = null;

		System.out.println("SERVICE START");
		Connection con = DBManager.getConnection();

		String sql = GET_DOCTOR_BY_USERNAME;
		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs.getMetaData().toString());
			if (rs.next()) {
				doctor = new Doctor();
				doctor.setName(rs.getString("name"));
				doctor.setFamily(rs.getString("family"));
				doctor.setUserID(rs.getString("user_id"));
				doctor.setPhoneNumber(rs.getString("phone_number"));
				doctor.setMobileNumber(rs.getString("mobile_number"));
				doctor.setId(rs.getInt("id"));
				System.out.println(rs.getInt("id"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return doctor;

	}

	public static boolean addDoctor(Doctor doctor) {

		boolean retVal = false;

		System.out.println("SERVICE START");
		Connection con = DBManager.getConnection();
		String sql = INSERT_DOCTOR;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//

			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getFamily());
			ps.setString(3, doctor.getUserID());
			ps.setString(4, doctor.getPhoneNumber());
			ps.setString(5, doctor.getMobileNumber());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("SERVICE OUT");
		return retVal;

	}

	public static boolean editDoctor(Doctor doctor) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_DOCTOR;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getFamily());
			ps.setString(3, doctor.getUserID());
			ps.setString(4, doctor.getPhoneNumber());
			ps.setString(5, doctor.getMobileNumber());
			ps.setInt(6, doctor.getId());
			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public static Doctor getDoctor(Doctor doctor) {

		System.out.println("SERVICE START");
		Connection con = DBManager.getConnection();

		String sql = GET_DOCTOR;
		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, getDoctorID(doctor));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				doctor.setName(rs.getString("name"));
				doctor.setFamily(rs.getString("family"));
				doctor.setUserID(rs.getString("user_id"));
				doctor.setPhoneNumber(rs.getString("phone_number"));
				doctor.setMobileNumber(rs.getString("mobile_number"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return doctor;
	}

	private static int getDoctorID(Doctor doctor) {

		Connection con = DBManager.getConnection();
		String sql = GET_DOCTOR_ID;
		int id = -1;
		try {
			System.out.println(doctor.getName());
			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getFamily());
			ps.setString(3, doctor.getUserID());
			ps.setString(4, doctor.getPhoneNumber());
			ps.setString(5, doctor.getMobileNumber());

			System.out.println(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

}
