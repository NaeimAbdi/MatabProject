package org.bihe.bean;

import java.io.Serializable;

/**
 * Patient is one of our important entity , here we make it
 * 
 */
public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String family;
	private String mobileNumber;
	private String telephoneNumber;
	private String birthday;
	private String city;

	public Patient() {
		super();
	}

	public Patient(String name, String family, String mobileNumber,
			String telephoneNumber, String birthday, String city) {
		super();
		this.name = name;
		this.family = family;
		this.mobileNumber = mobileNumber;
		this.telephoneNumber = telephoneNumber;
		this.birthday = birthday;
		this.city = city;
	}

	public Patient(int id, String name, String family, String mobileNumber,
			String telephoneNumber, String birthday, String city) {
		super();
		this.id = id;
		this.name = name;
		this.family = family;
		this.mobileNumber = mobileNumber;
		this.telephoneNumber = telephoneNumber;
		this.birthday = birthday;
		this.city = city;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getId() + this.getName();
	}
}
