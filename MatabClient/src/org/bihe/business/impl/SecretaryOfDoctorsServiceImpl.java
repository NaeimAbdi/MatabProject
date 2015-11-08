package org.bihe.business.impl;

import java.rmi.RemoteException;

import org.bihe.bean.SecretariesOfDoctors;
import org.bihe.business.SecretaryOfDoctorsService;
import org.bihe.server.Service;

public class SecretaryOfDoctorsServiceImpl implements SecretaryOfDoctorsService {
	private Service service;

	public SecretaryOfDoctorsServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public boolean addSecretaryOfDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException {

		return this.service.I_Secretary_Of_Doctors
				.addSecretaryOfDoctor(secretariesOfDoctors);
	}

	@Override
	public SecretariesOfDoctors getSecretaryOfDoctorByDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException {

		return this.service.I_Secretary_Of_Doctors
				.getSecretaryOfDoctorByDoctor(secretariesOfDoctors);
	}
}
