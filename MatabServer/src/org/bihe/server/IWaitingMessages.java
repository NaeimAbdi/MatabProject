package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;

/**
 * this interface provides : send messages and add waitingMessage to client
 * 
 */
public interface IWaitingMessages extends Remote {
	/**
	 * this method provides send message service to client, when client gives
	 * the ArrayList of message to this method it return true.
	 * 
	 * @param messages
	 *            is a parameter to send messages
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean sendMessages(ArrayList<WaitingMessages> messages)
			throws RemoteException;

	/**
	 * 
	 * this method provides add waiting message service to client, when client
	 * gives the object of waiting message to this method it return true.
	 * 
	 * @param waitingMessages
	 *            is a parameter to add waiting message
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addWaitingMessage(WaitingMessages waitingMessages)
			throws RemoteException;
}
