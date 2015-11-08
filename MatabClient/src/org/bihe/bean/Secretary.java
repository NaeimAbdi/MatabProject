package org.bihe.bean;

import java.io.Serializable;

public class Secretary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String family;
	private String phoneNumber;
	private String userID;
	private String mobileNumber;

	public Secretary() {
		super();
	}

	public Secretary(String name, String family, String phoneNumber,
			String userID, String mobileNumber) {
		super();
		this.name = name;
		this.family = family;
		this.phoneNumber = phoneNumber;
		this.userID = userID;
		this.mobileNumber = mobileNumber;
	}

	public Secretary(int id, String name, String family, String phoneNumber,
			String userID, String mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.family = family;
		this.phoneNumber = phoneNumber;
		this.userID = userID;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
