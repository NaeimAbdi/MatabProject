package org.bihe.bean;

import java.io.Serializable;

public class Messages implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int messagesID;

	public int getMessagesID() {
		return messagesID;
	}

	public void setMessagesID(int messagesID) {
		this.messagesID = messagesID;
	}

	private String message;
	private String telephoneNumber;

	public Messages() {

	}

	public Messages(String message, String patientID) {
		super();
		this.message = message;
		this.telephoneNumber = patientID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTelePhoneNumber() {
		return telephoneNumber;
	}

	public void setTelePhoneNumber(String telePhoneNumber) {
		this.telephoneNumber = telePhoneNumber;
	}

}
