package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.business.SecretaryService;
import org.bihe.server.Service;

public class SecretaryServiceImpl implements SecretaryService {
	private Service service;

	public SecretaryServiceImpl(Service service) {
		super();
		this.service = service;
	}

	public boolean addSecretary(Secretary secretary) throws RemoteException {
		return this.service.I_SECETARY.addSecretary(secretary);
	}

	public Secretary getSecretary(String username) throws RemoteException {
		return this.service.I_SECETARY.getSecretary(username);
	}

	public boolean editSecretary(Secretary secretary) throws RemoteException {
		return this.service.I_SECETARY.editSecretary(secretary);
	}

	public boolean deleteSecretary(Secretary secretary) throws RemoteException {
		return this.service.I_SECETARY.deleteSecretary(secretary);
	}

	public ArrayList<Doctor> findDoctorsForSecretary(Secretary secretary)
			throws RemoteException {
		return this.service.I_SECETARY.findDoctorsForSecretary(secretary);
	}
}
