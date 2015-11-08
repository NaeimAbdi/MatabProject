package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.Doctor;

public interface GeneratedMessagesService extends Remote {

	public boolean doctorMessageRunner(Doctor doctor) throws RemoteException;

	public boolean sendMessage(String text, String number)
			throws RemoteException;

	public boolean automaticSendMessage(boolean on_off, Doctor doctor)
			throws RemoteException;

	public boolean sendWaitingMessage(Doctor doctor) throws RemoteException;
}
