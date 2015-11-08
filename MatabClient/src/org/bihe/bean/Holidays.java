package org.bihe.bean;

import java.io.Serializable;

public class Holidays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String holidayDate;

	public Holidays() {
		super();
	}

	public Holidays(String description, String holidayDate) {
		super();
		this.description = description;
		this.holidayDate = holidayDate;
	}

	public Holidays(int id, String description, String holidayDate) {
		super();
		this.id = id;
		this.description = description;
		this.holidayDate = holidayDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

}
