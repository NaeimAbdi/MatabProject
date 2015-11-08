package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;

public interface IPatientDossier extends Remote {

	public boolean addPatientDossier(PatientDossier patientDossier)
			throws RemoteException;

	public ArrayList<PatientDossier> getPatientDossier(
			PatientFolder patientFolder) throws RemoteException;

}
