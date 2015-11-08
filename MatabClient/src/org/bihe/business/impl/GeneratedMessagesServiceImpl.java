package org.bihe.business.impl;

import java.rmi.RemoteException;

import org.bihe.bean.Doctor;
import org.bihe.business.GeneratedMessagesService;
import org.bihe.server.Service;

public class GeneratedMessagesServiceImpl implements GeneratedMessagesService {

	private Service service;

	public GeneratedMessagesServiceImpl(Service service) {
		this.service = service;
	}

	public boolean doctorMessageRunner(Doctor doctor) throws RemoteException {
		return this.service.I_GENERATED_MESSAGES.doctorMessageRunner(doctor);
	}

	public boolean sendMessage(String text, String number)
			throws RemoteException {
		return this.service.I_GENERATED_MESSAGES.sendMessage(text, number);
	}

	public boolean automaticSendMessage(boolean on_off, Doctor doctor)
			throws RemoteException {
		return this.service.I_GENERATED_MESSAGES.automaticSendMessage(on_off,
				doctor);
	}

	@Override
	public boolean sendWaitingMessage(Doctor doctor) throws RemoteException {
		return this.service.I_GENERATED_MESSAGES.sendWaitingMessage(doctor);
	}

}
