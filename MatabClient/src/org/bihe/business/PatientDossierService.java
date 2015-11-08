package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;

public interface PatientDossierService extends Remote {

	public boolean addPatientDossier(PatientDossier patientDossier)
			throws RemoteException;

	// TODO why?
	public ArrayList<PatientDossier> getPatientDossier(
			PatientFolder patientFolder) throws RemoteException;

}
