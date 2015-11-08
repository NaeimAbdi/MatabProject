package org.bihe.bean;

import java.io.Serializable;

public class FixHolidays implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String describtion;
	private int day;
	private int month;

	public FixHolidays() {
		super();
	}

	public FixHolidays(String describtion, int day, int month) {
		super();
		this.describtion = describtion;
		this.day = day;
		this.month = month;
	}

	public FixHolidays(int id, String describtion, int day, int month) {
		super();
		this.id = id;
		this.describtion = describtion;
		this.day = day;
		this.month = month;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
