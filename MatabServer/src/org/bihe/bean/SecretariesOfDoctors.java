package org.bihe.bean;

import java.io.Serializable;

/**
 * In this bean class secretaries and doctors can have relation
 */
public class SecretariesOfDoctors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int doctorID;
	private int secretaryID;

	public SecretariesOfDoctors() {
		super();
	}

	public SecretariesOfDoctors(int doctorID, int secretaryID) {
		super();
		this.doctorID = doctorID;
		this.secretaryID = secretaryID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getSecretaryID() {
		return secretaryID;
	}

	public void setSecretaryID(int secretaryID) {
		this.secretaryID = secretaryID;
	}

}
