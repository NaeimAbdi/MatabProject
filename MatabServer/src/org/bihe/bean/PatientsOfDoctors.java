package org.bihe.bean;

import java.io.Serializable;

/**
 * In this bean class patients and doctors can have relation
 */
public class PatientsOfDoctors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int doctorID;
	private int patiendID;

	public PatientsOfDoctors() {
		super();
	}

	public PatientsOfDoctors(int doctorID, int patiendID) {
		super();
		this.doctorID = doctorID;
		this.patiendID = patiendID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatiendID() {
		return patiendID;
	}

	public void setPatiendID(int patiendID) {
		this.patiendID = patiendID;
	}

}
