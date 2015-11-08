package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;
import org.bihe.dao.MessagesDAO;

/**
 * This imple class that implement IWaitingMessages which is interface, make
 * relation between WaitingMessagesDAO and IWaitingMessagesservices. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class WaitingMessagesImple extends UnicastRemoteObject implements
		IWaitingMessages {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected WaitingMessagesImple() throws RemoteException {
		super();
	}

	public boolean sendMessages(ArrayList<WaitingMessages> messages)
			throws RemoteException {
		return MessagesDAO.insertMessages(messages);
	}

	public boolean addWaitingMessage(WaitingMessages waitingMessages)
			throws RemoteException {
		return MessagesDAO.insertMessage(waitingMessages);
	}
}
