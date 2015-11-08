package org.bihe.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bihe.util.GregorianJalaliCoverter;

/**
 * Secretary can add visit time, here secretary can set patients visit times
 * 
 */
public class VisitTimes implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String start;
	private String finish;
	private int patientID;
	private int doctorID;

	public VisitTimes() {
		super();
	}

	public VisitTimes(String start, String finish, int patientID, int doctorID) {
		super();
		this.start = start;
		this.finish = finish;
		this.patientID = patientID;
		this.doctorID = doctorID;
	}

	public VisitTimes(int id, String start, String finish, int patientID,
			int doctorID) {
		super();
		this.id = id;
		this.start = start;
		this.finish = finish;
		this.patientID = patientID;
		this.doctorID = doctorID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
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

	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		String jalaliDate = "";
		try {
			Date date = formatter.parse(this.getStart());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int min = cal.get(Calendar.MINUTE);

			GregorianJalaliCoverter gjc = new GregorianJalaliCoverter();
			gjc.GregorianToPersian(year, month + 1, day);

			jalaliDate = gjc.toString() + " " + hour + ":" + min;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jalaliDate;
	}
}
