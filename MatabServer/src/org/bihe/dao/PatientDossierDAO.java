package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;
import org.bihe.jdbc.DBManager;

/**
 * We can insert and get patient's dossier
 */
public class PatientDossierDAO {

	private static final String INSERT_PATIENT_DOSSIER = "INSERT INTO patient_dossier (record_date,description,image_path,patient_folder_id) VALUES(?,?,?,?)";
	private static final String GET_PATIENT_DOSSIER_ID = "SELECT id FROM patient_dossier WHERE record_date = ? AND description = ? AND image_path = ? AND patient_folder_id = ? ";
	private static final String GET_PATIENT_DOSSIER = "SELECT * FROM patient_dossier WHERE patient_folder_id = ?";

	public static ArrayList<PatientDossier> getPatientDossier(
			PatientFolder patientFolder) {
		ArrayList<PatientDossier> pd = new ArrayList<>();
		Connection connection = DBManager.getConnection();
		String sql = GET_PATIENT_DOSSIER;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, patientFolder.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PatientDossier p = new PatientDossier();
				p.setDescription(rs.getString("description"));
				p.setRecordDate(rs.getString("record_date"));
				p.setImagePath(rs.getString("image_path"));
				p.setId(rs.getInt("id"));

				pd.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pd;

	}

	public static boolean addPatientDossier(PatientDossier patientDossier) {
		Connection con = DBManager.getConnection();
		String sql = INSERT_PATIENT_DOSSIER;

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, patientDossier.getRecordDate());
			ps.setString(2, patientDossier.getDescription());
			ps.setString(3, patientDossier.getImagePath());
			ps.setInt(4, patientDossier.getPatientFolderID());

			return ps.executeUpdate() == 1;
			//

			// patientDossier.setId(getPatientDossierID(patientDossier));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@SuppressWarnings("unused")
	private static int getPatientDossierID(PatientDossier patientDossier) {

		Connection con = DBManager.getConnection();
		String sql = GET_PATIENT_DOSSIER_ID;
		int id = -1;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//

			ps.setString(1, patientDossier.getRecordDate());
			ps.setString(2, patientDossier.getDescription());
			ps.setString(3, patientDossier.getImagePath());
			ps.setInt(4, patientDossier.getPatientFolderID());

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
