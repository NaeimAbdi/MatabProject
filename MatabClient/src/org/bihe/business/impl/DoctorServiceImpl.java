package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.business.DoctorService;
import org.bihe.server.Service;

public class DoctorServiceImpl implements DoctorService {

	private Service service;

	public DoctorServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public boolean addDoctor(Doctor doctor) throws RemoteException {
		return this.service.I_DOCTOR.addDoctor(doctor);
	}

	@Override
	public boolean editDoctor(Doctor doctor) throws RemoteException {

		return this.service.I_DOCTOR.editDoctor(doctor);
	}

	@Override
	public Doctor getDoctor(Doctor doctor) throws RemoteException {

		return this.service.I_DOCTOR.getDoctor(doctor);
	}

	@Override
	public Doctor getDoctorByUsername(String username) throws RemoteException {
		return this.service.I_DOCTOR.getDoctorByUsername(username);
	}

	@Override
	public ArrayList<Secretary> findSecretaryForDoctors(Doctor doctor)
			throws RemoteException {

		return this.service.I_DOCTOR.findDoctorsForSecretary(doctor);
	}

}
