package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.jdbc.DBManager;

/**
 * Here we can add all parameters of the system to database, we can get all
 * parameters and update them.
 */
public class SystemParametersDAO {


	private static final String INSERT_SYSTEM_PARAMETERS = "INSERT INTO system_parameters(english_name,farsi_name,value,doctor_id) VALUES(?,?,?,?)";
	private static final String UPDATE_PARAMETERS = "UPDATE system_parameters SET value = ? WHERE english_name = ? ";
	private static final String SELECT = "SELECT * FROM system_parameters  WHERE english_name = ? AND doctor_id = ?";
	private static final String GET_ALL_PARAMETERS = "SELECT * FROM system_parameters WHERE doctor_id = ?";

	public static boolean addSystemParameters(SystemParameters systemParameters) {
		boolean retVal = false;
		//
		Connection con = DBManager.getConnection();
		String sql = INSERT_SYSTEM_PARAMETERS;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, systemParameters.getEnglishName());
			ps.setString(2, systemParameters.getFarsiName());
			ps.setString(3, systemParameters.getValue());
			ps.setInt(4, systemParameters.getDoctorID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//
		return retVal;

	}

	public static ArrayList<SystemParameters> getAllParameters(Doctor d) {
		ArrayList<SystemParameters> retVal = new ArrayList<SystemParameters>();
		//
		String sql = GET_ALL_PARAMETERS;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement st = connection.prepareStatement(sql);
			if (d.getId() != 0)
				st.setInt(1, d.getId());
			else
				st.setString(1, "");
			ResultSet res = st.executeQuery();
			while (res.next()) {

				SystemParameters systemOfParameters = new SystemParameters();
				//
				systemOfParameters.setId(res.getInt("id"));
				systemOfParameters
						.setEnglishName(res.getString("english_name"));
				systemOfParameters.setFarsiName(res.getString("farsi_name"));
				systemOfParameters.setValue(res.getString("value"));

				//
				retVal.add(systemOfParameters);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static SystemParameters select(String englishName, Doctor d) {
		SystemParameters retVal = null;
		//
		String sql = SELECT;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, englishName);
			st.setInt(2, d.getId());
			ResultSet res = st.executeQuery();
			if (res.next()) {
				retVal = new SystemParameters();
				SystemParameters systemParameters = new SystemParameters();
				//
				systemParameters.setId(res.getInt("id"));
				systemParameters.setEnglishName(res.getString("english_name"));
				systemParameters.setFarsiName(res.getString("farsi_name"));
				systemParameters.setValue(res.getString("value"));

				//
				retVal = systemParameters;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean updateParameters(SystemParameters systemParameters) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_PARAMETERS;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, systemParameters.getValue());

			ps.setString(2, systemParameters.getEnglishName());

			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
