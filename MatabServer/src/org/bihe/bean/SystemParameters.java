package org.bihe.bean;

import java.io.Serializable;

/**
 * here we have our different parameters of our system
 * 
 */
public class SystemParameters implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String englishName;
	private String farsiName;
	private String value;
	private int doctorID;

	public SystemParameters() {
		super();
	}

	public SystemParameters(String englishName, String farsiName, String value) {
		super();
		this.englishName = englishName;
		this.farsiName = farsiName;
		this.value = value;
	}

	public SystemParameters(int id, String englishName, String farsiName,
			String value) {
		super();
		this.id = id;
		this.englishName = englishName;
		this.farsiName = farsiName;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFarsiName() {
		return farsiName;
	}

	public void setFarsiName(String farsiName) {
		this.farsiName = farsiName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

}
