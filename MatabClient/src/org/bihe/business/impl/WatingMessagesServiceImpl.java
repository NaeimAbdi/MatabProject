package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.WaitingMessages;
import org.bihe.business.WaitingMessagesService;
import org.bihe.server.Service;

public class WatingMessagesServiceImpl implements WaitingMessagesService {

	private Service service;

	public WatingMessagesServiceImpl(Service service) {
		super();
		this.service = service;
	}

	@Override
	public boolean sendMessages(ArrayList<WaitingMessages> messages)
			throws RemoteException {

		return this.service.I_WAITING_MESSAGES.sendMessages(messages);
	}

	@Override
	public boolean addWaitingMessage(WaitingMessages waitingMessages)
			throws RemoteException {

		return this.service.I_WAITING_MESSAGES
				.addWaitingMessage(waitingMessages);
	}

}
