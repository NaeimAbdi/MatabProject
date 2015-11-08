package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bihe.bean.PatientFolder;
import org.bihe.jdbc.DBManager;

/**
 * this class can add patient folder to database, get all profiles, delete theme
 * .
 */
public class PatientFolderDAO {

	private static final String INSERT_PATIENT_FOLDER = "INSERT INTO patient_folder(folder_unique_number,disease_brief_desc,patient_id,doctor_id) VALUES(?,?,?,?) ";
	private static final String GET_PATIENT_FOLDER_ID = "SELECT id FROM patient_folder WHERE folder_unique_number = ? AND disease_brief_desc = ? AND patient_id = ? AND doctor_id = ? ";

	private static final String UPDATE_PATIENT_FOLDER = "UPDATE patient_foler set folder_unique_number = ? , disease_brief_desc = ? , patient_id = ? , doctor_id = ? WHERE id = ?";

	private static final String DELETE_PATIENT_FOLDER = "DELETE FROM patient_folder WHERE id = ?";

	private static final String GET_PATIENT_FOLDER = "SELECT * FROM patient_folder WHERE patient_id = ? AND doctor_id = ?";

	public static PatientFolder getPatientFolder(PatientFolder patientFolder) {
		PatientFolder retVal = null;

		Connection connection = DBManager.getConnection();
		String sql = GET_PATIENT_FOLDER;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, patientFolder.getPatientID());
			ps.setInt(2, patientFolder.getDoctorID());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				patientFolder = new PatientFolder();
				patientFolder.setFolderUniqueNumber(rs
						.getString("folder_unique_number"));
				patientFolder.setDiseaseBriefDesc(rs
						.getString("disease_brief_desc"));
				patientFolder.setPatientID(rs.getInt("patient_id"));
				patientFolder.setDoctorID(rs.getInt("doctor_id"));
				patientFolder.setId(rs.getInt("id"));
				retVal = patientFolder;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean deletePatientFolder(PatientFolder patientFolder) {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_PATIENT_FOLDER;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, patientFolder.getFolderUniqueNumber());

			int nRecord = ps.executeUpdate();

			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static boolean updatePatientFolder(PatientFolder patientFolder) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_PATIENT_FOLDER;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, patientFolder.getFolderUniqueNumber());
			ps.setString(2, patientFolder.getDiseaseBriefDesc());
			ps.setInt(3, patientFolder.getPatientID());
			ps.setInt(4, patientFolder.getDoctorID());
			ps.setInt(5, patientFolder.getId());

			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean addPatientFolder(PatientFolder patientFolder) {
		Connection con = DBManager.getConnection();
		String sql = INSERT_PATIENT_FOLDER;
		int d = 0;
		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, patientFolder.getFolderUniqueNumber());
			ps.setString(2, patientFolder.getDiseaseBriefDesc());
			ps.setInt(3, patientFolder.getPatientID());
			ps.setInt(4, patientFolder.getDoctorID());
			d = ps.executeUpdate();

			//

			// patientFolder.setId(getPatientFolderID(patientFolder));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d == 1;

	}

	@SuppressWarnings("unused")
	private static int getPatientFolderID(PatientFolder patientFolder) {

		Connection con = DBManager.getConnection();
		String sql = GET_PATIENT_FOLDER_ID;
		int id = -1;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, patientFolder.getFolderUniqueNumber());
			ps.setString(2, patientFolder.getDiseaseBriefDesc());
			ps.setInt(3, patientFolder.getPatientID());
			ps.setInt(4, patientFolder.getDoctorID());

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
