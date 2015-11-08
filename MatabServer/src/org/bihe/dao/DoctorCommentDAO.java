package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;
import org.bihe.jdbc.DBManager;

/**
 * This DAO class have insert query for add the objects of comment of doctor to
 * database,select query for read object of comments of doctor from database.
 */
public class DoctorCommentDAO {

	private static final String INSERT_DOCTOR_COMMENT = "INSERT INTO doctor_comments (comment,date_of_comment,patient_folder_id) VALUES(?,?,?) ";
	private static final String GET_DOCTOR_COMMENT_ID = "SELECT id FROM doctor_comments WHERE comment = ? AND date_of_comment = ? AND patient_folder_id = ?";

	private static final String GET_DOCTOR_COMMENT = "SELECT * FROM doctor_comments WHERE patient_folder_id = ?";

	public static DoctorComment addDoctorComment(DoctorComment doctorComment) {

		Connection con = DBManager.getConnection();
		String sql = INSERT_DOCTOR_COMMENT;

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, doctorComment.getComment());
			ps.setString(2, doctorComment.getCommentDate());
			ps.setInt(3, Integer.parseInt(doctorComment.getPatientFolderID()));

			//

			if (ps.executeUpdate() == 1) {

				doctorComment.setId(getDoctorCommentID(doctorComment));
			} else {
				doctorComment = null;
			}

			if (doctorComment != null && doctorComment.getId() == -1) {
				doctorComment = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorComment;

	}

	private static int getDoctorCommentID(DoctorComment doctorComment) {

		Connection con = DBManager.getConnection();
		String sql = GET_DOCTOR_COMMENT_ID;
		int id = -1;
		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, doctorComment.getComment());
			ps.setString(2, doctorComment.getCommentDate());
			ps.setString(3, doctorComment.getPatientFolderID());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	public static ArrayList<DoctorComment> getDoctorComment(
			DoctorComment doctorComment) {

		ArrayList<DoctorComment> retVal = new ArrayList<>();

		Connection connection = DBManager.getConnection();
		String sql = GET_DOCTOR_COMMENT;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, doctorComment.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				DoctorComment dComment = new DoctorComment();
				dComment.setId(rs.getInt("id"));
				dComment.setComment(rs.getString("comment"));
				dComment.setCommentDate(rs.getString("date_of_comment"));
				dComment.setId(rs.getInt("patient_folder_id"));

				retVal.add(dComment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

}
