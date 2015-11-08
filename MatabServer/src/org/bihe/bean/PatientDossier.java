package org.bihe.bean;

import java.io.Serializable;

/**
 * here we make profile of patient entity
 */
public class PatientDossier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String recordDate;
	private String description;
	private String imagePath;
	private int patientFolderID;

	public PatientDossier() {
		super();
	}

	public PatientDossier(String recordDate, String description,
			String imagePath, int patientFolderID) {
		super();
		this.recordDate = recordDate;
		this.description = description;
		this.imagePath = imagePath;
		this.patientFolderID = patientFolderID;
	}

	public PatientDossier(int id, String recordDate, String description,
			String imagePath, int patientFolderID) {
		super();
		this.id = id;
		this.recordDate = recordDate;
		this.description = description;
		this.imagePath = imagePath;
		this.patientFolderID = patientFolderID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getPatientFolderID() {
		return patientFolderID;
	}

	public void setPatientFolderID(int patientFolderID) {
		this.patientFolderID = patientFolderID;
	}

}
