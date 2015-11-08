package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;

public interface IWaitingMessages extends Remote {

	public boolean sendMessages(ArrayList<WaitingMessages> messages)
			throws RemoteException;

	public boolean addWaitingMessage(WaitingMessages waitingMessages)
			throws RemoteException;
}
