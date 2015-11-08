package org.bihe.bean;

import java.io.Serializable;

public class WaitingMessages implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String telephoneNumber;

	public WaitingMessages() {
		super();
	}

	public WaitingMessages(String message, String telephoneNumber) {
		super();
		this.message = message;
		this.telephoneNumber = telephoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}
