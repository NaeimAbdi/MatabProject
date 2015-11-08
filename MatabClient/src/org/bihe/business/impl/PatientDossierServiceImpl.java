package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;
import org.bihe.business.PatientDossierService;
import org.bihe.server.Service;

public class PatientDossierServiceImpl implements PatientDossierService {

	private Service service;

	public PatientDossierServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public boolean addPatientDossier(PatientDossier patientDossier)
			throws RemoteException {

		return this.service.I_PATIENT_DOSSIER.addPatientDossier(patientDossier);
	}

	@Override
	public ArrayList<PatientDossier> getPatientDossier(
			PatientFolder patientFolder) throws RemoteException {

		return this.service.I_PATIENT_DOSSIER.getPatientDossier(patientFolder);
	}

}
