package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bihe.bean.SecretariesOfDoctors;
import org.bihe.jdbc.DBManager;

/**
 * here We can connect the data between two entity of secretaries and doctors
 * Add secretaries of each doctor, delete them. And get secretaries of each
 * doctor
 */
public class SecretariesOfDoctorDAO {

	private static final String INSERT_SECRETAIRES_OF_DOCTOR = "INSERT INTO secretaries_of_doctors(doctor_id,secretary_id) VALUES(?,?)";
	private static final String GET_SECRETARIES_OF_DOCTOR = "SELECT * FROM secretaries_of_doctors WHERE doctor_id";

	public static boolean addSecretariesOfDoctor(
			SecretariesOfDoctors secretariesOfDoctors) {

		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_SECRETAIRES_OF_DOCTOR;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setInt(1, secretariesOfDoctors.getDoctorID());
			ps.setInt(2, secretariesOfDoctors.getSecretaryID());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static SecretariesOfDoctors getSecretaryOfDoctorByDoctor(
			SecretariesOfDoctors secretariesOfDoctors) {

		Connection con = DBManager.getConnection();
		String sql = GET_SECRETARIES_OF_DOCTOR;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setInt(1, secretariesOfDoctors.getDoctorID());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				secretariesOfDoctors.setDoctorID(rs.getInt("doctor_id"));
				secretariesOfDoctors.setSecretaryID(rs.getInt("secretary_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return secretariesOfDoctors;

	}
}
