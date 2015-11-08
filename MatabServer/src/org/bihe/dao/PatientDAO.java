package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.jdbc.DBManager;

/**
 * In this class we can add,delete,update and get patient from database and we
 * can get each patient id which is unique for each patient
 * 
 */
public class PatientDAO {

	private static final String INSERT_PATIENT = "INSERT INTO patient(name,family,mobile_number,telephone_number,birthday,city) VALUES(?,?,?,?,?,?)";

	private static final String DELETE_PATIENT = "DELETE FROM patient WHERE id = ?";

	private static final String UPDATE_PATIENT = "UPDATE patient set name = ? , family = ? , mobile_number = ? , telephone_number = ? , birthday = ? , city = ? WHERE id = ?";

	private static final String SELECT_PATIENT = "SELECT * FROM patient p, patient_folder pf WHERE 1=1 ";

	private static final String GET_PATIENT_ID = "SELECT id FROM patient WHERE name = ? AND family = ? AND mobile_number = ? AND telephone_number = ?  AND city = ? ";

	private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id= ? ";

	public static boolean editPatientByID(Patient patient) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_PATIENT;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, patient.getName());
			ps.setString(2, patient.getFamily());
			ps.setString(3, patient.getMobileNumber());
			ps.setString(4, patient.getTelephoneNumber());
			ps.setString(5, patient.getBirthday());
			ps.setString(6, patient.getCity());
			ps.setInt(7, patient.getId());
			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean deletePatient(Patient patient) {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_PATIENT;

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, patient.getId());

			int nRecord = ps.executeUpdate();

			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static Patient addPatient(Patient patient) {

		Connection con = DBManager.getConnection();
		String sql = INSERT_PATIENT;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getFamily());
			ps.setString(3, patient.getMobileNumber());
			ps.setString(4, patient.getTelephoneNumber());
			ps.setString(5, patient.getBirthday());
			ps.setString(6, patient.getCity());
			ps.executeUpdate();
			patient.setId(getPatientID(patient));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;

	}

	private static int getPatientID(Patient patient) {

		Connection con = DBManager.getConnection();
		String sql = GET_PATIENT_ID;
		int id = -1;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, patient.getName());
			ps.setString(2, patient.getFamily());
			ps.setString(3, patient.getMobileNumber());
			ps.setString(4, patient.getTelephoneNumber());
			ps.setString(5, patient.getCity());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	public static Patient getPatientByID(Patient patient) {

		Connection con = DBManager.getConnection();
		String sql = SELECT_PATIENT_BY_ID;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setInt(1, patient.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				patient.setId(rs.getInt("id"));
				patient.setName(rs.getString("name"));
				patient.setFamily(rs.getString("family"));
				patient.setMobileNumber(rs.getString("mobile_number"));
				patient.setTelephoneNumber(rs.getString("telephone_number"));
				patient.setBirthday(rs.getDate("birthday").toString());
				patient.setCity(rs.getString("city"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;

	}

	public static ArrayList<Patient> searchPatientBy(Patient p, PatientFolder pf) {

		ArrayList<Patient> retVal = new ArrayList<Patient>();
		//
		String sql = SELECT_PATIENT + multiFieldsearchString(p, pf);

		Connection connection = DBManager.getConnection();
		//
		try {

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				Patient patient = new Patient();

				patient.setId(rs.getInt("id"));
				patient.setName(rs.getString("name"));
				patient.setFamily(rs.getString("family"));
				patient.setMobileNumber(rs.getString("mobile_number"));
				patient.setTelephoneNumber(rs.getString("telephone_number"));
				if (rs.getDate("birthday") != null) {
					patient.setBirthday(rs.getDate("birthday").toString());
				}
				patient.setBirthday("");
				patient.setCity(rs.getString("city"));

				retVal.add(patient);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	private static String multiFieldsearchString(Patient p, PatientFolder pf) {
		String search = "";

		if (p.getId() != 0) {
			search = "AND p.id LIKE '" + p.getId() + "%' ";
		}
		if (p.getName() != null && !p.getName().isEmpty()) {
			search += "AND p.name LIKE'" + p.getName() + "%' ";
		}
		if (p.getFamily() != null && !p.getFamily().isEmpty()) {
			search += "AND p.family LIKE '" + p.getFamily() + "%' ";
		}
		if (p.getMobileNumber() != null && !p.getMobileNumber().isEmpty()) {
			search += "AND p.mobile_number LIKE '" + p.getMobileNumber()
					+ "%' ";

		}
		if (p.getTelephoneNumber() != null && !p.getTelephoneNumber().isEmpty()) {

			search += "AND p.telephone_number LIKE '" + p.getTelephoneNumber()
					+ "%' ";
		}
		if (p.getCity() != null && !p.getCity().isEmpty()) {
			search += "AND p.city LIKE '" + p.getCity() + "%' ";
		}
		if (pf.getFolderUniqueNumber() != null
				&& !pf.getFolderUniqueNumber().isEmpty()) {
			search += " AND pf.folder_unique_number LIKE '"
					+ pf.getFolderUniqueNumber() + "%'";
		}

		search += " AND pf.doctor_id=" + pf.getDoctorID();
		search += " AND p.id = pf.patient_id";
		return search;

	}

	public static ArrayList<Patient> powerfullSearch(String search) {
		return null;

	}

}
