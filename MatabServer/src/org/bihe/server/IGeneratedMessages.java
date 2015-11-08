package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.Doctor;

/**
 * This interface provides: doctor message runner, send message and automatic
 * send message
 * 
 */
public interface IGeneratedMessages extends Remote {
	/**
	 * this method run thread of message sending service
	 * 
	 * @param doctor
	 *            this is the parameters to run doctor message
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean doctorMessageRunner(Doctor doctor) throws RemoteException;

	/**
	 * This method get two String and then sending message to patients
	 * 
	 * @param text
	 *            this is the first parameter to send message
	 * @param number
	 *            this is the second parameter to send message
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean sendMessage(String text, String number)
			throws RemoteException;

	/**
	 * 
	 * @param on_off
	 *            is the parameter to power on and power off the automatic send
	 *            message
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean automaticSendMessage(boolean on_off, Doctor doctor)
			throws RemoteException;

	/**
	 * Sends messages to people that we want send theme sms.
	 * 
	 * @param doctor
	 * @return
	 * @throws RemoteException
	 */
	public boolean sendWaitingMessage(Doctor doctor) throws RemoteException;
}
