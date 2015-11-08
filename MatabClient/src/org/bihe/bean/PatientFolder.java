package org.bihe.bean;

import java.io.Serializable;

public class PatientFolder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String folderUniqueNumber;
	private String diseaseBriefDesc;
	private int patientID;
	private int doctorID;

	public PatientFolder() {
		super();
	}

	public PatientFolder(String folderUniqueNumber, String diseaseBriefDesc,
			int patientID, int doctorID) {
		super();
		this.folderUniqueNumber = folderUniqueNumber;
		this.diseaseBriefDesc = diseaseBriefDesc;
		this.patientID = patientID;
		this.doctorID = doctorID;
	}

	public PatientFolder(int id, String folderUniqueNumber,
			String diseaseBriefDesc, int patientID, int doctorID) {
		super();
		this.id = id;
		this.folderUniqueNumber = folderUniqueNumber;
		this.diseaseBriefDesc = diseaseBriefDesc;
		this.patientID = patientID;
		this.doctorID = doctorID;
	}
	
	public PatientFolder( String folderUniqueNumber, int doctorID) {
		super();
		this.folderUniqueNumber = folderUniqueNumber;
		this.doctorID = doctorID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolderUniqueNumber() {
		return folderUniqueNumber;
	}

	public void setFolderUniqueNumber(String folderUniqueNumber) {
		this.folderUniqueNumber = folderUniqueNumber;
	}

	public String getDiseaseBriefDesc() {
		return diseaseBriefDesc;
	}

	public void setDiseaseBriefDesc(String diseaseBriefDesc) {
		this.diseaseBriefDesc = diseaseBriefDesc;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

}
