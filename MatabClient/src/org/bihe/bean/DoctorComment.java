package org.bihe.bean;

import java.io.Serializable;

public class DoctorComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String comment;
	private String commentDate;
	private String patientFolderID;

	public DoctorComment() {
		super();
	}

	public DoctorComment(String comment, String commentDate, String patientFolderID) {
		super();
		this.comment = comment;
		this.commentDate = commentDate;
		this.patientFolderID = patientFolderID;
	}

	public DoctorComment(int id, String comment, String commentDate,
			String patientFolderID) {
		super();
		this.id = id;
		this.comment = comment;
		this.commentDate = commentDate;
		this.patientFolderID = patientFolderID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getPatientFolderID() {
		return patientFolderID;
	}

	public void setPatientFolderID(String patientFolderID) {
		this.patientFolderID = patientFolderID;
	}

}
