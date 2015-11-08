package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bihe.bean.PatientsOfDoctors;
import org.bihe.jdbc.DBManager;

/**
 * here We can connect the data between two entity patients and doctors Add
 * patient of each doctor, delete them. And get patient of each doctor
 */
public class PatientOfDoctorsDAO {

	private static final String INSERT_PATIENT_OF_DOCTORS = "INSERT INTO patients_of_doctors(doctor_id,patient_id) VALUES(?,?)";
	private static final String DELETE_PATIENT_OF_DOCTORS = "DEELTE FROM patients_of_doctors WHERE patient_id = ? ";
	private static final String GET_PATIENT_OF_DOCTOR = "SELECT * FROM patients_of_doctors WHERE patient_id = ? AND doctor_id = ?  ";

	public static boolean addPatientOfDoctors(
			PatientsOfDoctors patientsOfDoctors) {

		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_PATIENT_OF_DOCTORS;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//

			ps.setInt(1, patientsOfDoctors.getDoctorID());
			ps.setInt(2, patientsOfDoctors.getPatiendID());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean deletePatientOfDoctors(
			PatientsOfDoctors patientsOfDoctors) {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_PATIENT_OF_DOCTORS;

		PreparedStatement ps;
		try {

			ps = connection.prepareStatement(sql);
			ps.setInt(1, patientsOfDoctors.getPatiendID());

			int nRecord = ps.executeUpdate();

			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean getPatientOfDoctors(
			PatientsOfDoctors patientsOfDoctors) {

		boolean retVal = false;

		Connection con = DBManager.getConnection();

		String sql = GET_PATIENT_OF_DOCTOR;
		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, patientsOfDoctors.getPatiendID());
			ps.setInt(2, patientsOfDoctors.getDoctorID());

			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return retVal;

	}

}
