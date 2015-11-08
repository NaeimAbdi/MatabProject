package org.bihe.bean;

import java.io.Serializable;

/**
 * In this class we have Doctor object, it is one of our main entity
 * 
 */
public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String family;
	private String userID;
	private String phoneNumber;
	private String mobileNumber;

	public Doctor() {

	}

	public Doctor(String name, String family, String userID,
			String phoneNumber, String mobileNumber) {
		super();
		this.name = name;
		this.family = family;
		this.userID = userID;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	public Doctor(int id, String name, String family, String userID,
			String phoneNumber, String mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.family = family;
		this.userID = userID;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {

		return this.name + " " + this.family;
	}

}
