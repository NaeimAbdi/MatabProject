package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.business.PatientService;
import org.bihe.server.Service;

public class PatientServiceImpl implements PatientService {
	private Service service;

	public PatientServiceImpl(Service service) {
		super();
		this.service = service;
	}

	@Override
	public Patient addPatient(Patient patient) throws RemoteException {

		return this.service.I_PATIENT.addPatient(patient);
	}

	@Override
	public boolean updatePatient(Patient patient) throws RemoteException {

		return this.service.I_PATIENT.updatePatient(patient);
	}

	@Override
	public boolean deletePatient(Patient patient) throws RemoteException {

		return this.service.I_PATIENT.deletePatient(patient);
	}

	@Override
	public ArrayList<Patient> search(Patient patient,
			PatientFolder patientFolder) throws RemoteException {

		return this.service.I_PATIENT.search(patient, patientFolder);
	}
}
