package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;

public interface WaitingMessagesService extends Remote {

	public boolean sendMessages(ArrayList<WaitingMessages> messages)
			throws RemoteException;

	public boolean addWaitingMessage(WaitingMessages waitingMessages)
			throws RemoteException;
}
