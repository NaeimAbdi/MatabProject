package org.bihe.business.impl;

import java.rmi.RemoteException;

import org.bihe.bean.PatientFolder;
import org.bihe.business.PatientFolderService;
import org.bihe.server.Service;

public class PatientFolderServiceImpl implements PatientFolderService {

	private Service service;

	public PatientFolderServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public boolean addPatientFolder(PatientFolder patientFolder)
			throws RemoteException {

		return this.service.I_PATIENT_FOLDER.addPatientFolder(patientFolder);
	}

	@Override
	public boolean updatePatientFolder(PatientFolder patientFolder)
			throws RemoteException {

		return this.service.I_PATIENT_FOLDER.updatePatientFolder(patientFolder);
	}

	@Override
	public boolean deletePatientFolder(PatientFolder patientFolder)
			throws RemoteException {

		return this.service.I_PATIENT_FOLDER.deletePatientFolder(patientFolder);
	}

	@Override
	public PatientFolder getPatientFolder(PatientFolder patientFolder)
			throws RemoteException {

		return this.service.I_PATIENT_FOLDER.getPatientFolder(patientFolder);
	}

}
