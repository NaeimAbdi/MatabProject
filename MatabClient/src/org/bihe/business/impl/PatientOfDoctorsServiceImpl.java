package org.bihe.business.impl;

import java.rmi.RemoteException;

import org.bihe.bean.PatientsOfDoctors;
import org.bihe.business.PatientOfDoctorsService;
import org.bihe.server.Service;

public class PatientOfDoctorsServiceImpl implements PatientOfDoctorsService {
	private Service service;

	public PatientOfDoctorsServiceImpl(Service service) {
		super();
		this.service = service;
	}

	public boolean addPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {
		return this.service.I_PATIENT_OF_DOCTOR
				.addPatientOfDoctors(patientsOfDoctors);
	}

	public boolean deletePatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {
		return this.service.I_PATIENT_OF_DOCTOR
				.deletePatientOfDoctors(patientsOfDoctors);
	}

	public boolean getPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {
		return this.service.I_PATIENT_OF_DOCTOR
				.getPatientOfDoctors(patientsOfDoctors);
	}
}
