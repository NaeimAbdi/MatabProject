package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;

/**
 * this interface provides : add patient dossier and get patient dossier
 */
public interface IPatientDossier extends Remote {
	/**
	 * this method provides add patient dossier for client
	 * 
	 * @param patientDossier
	 *            is the parameter to add patient dossier
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addPatientDossier(PatientDossier patientDossier)
			throws RemoteException;

	/**
	 * this method provides get patient dossier service for client
	 * 
	 * @param patient
	 *            is the parameters to get patient dossier
	 * @return arrayList<PatientDossier>
	 * @throws RemoteException
	 */
	public ArrayList<PatientDossier> getPatientDossier(
			PatientFolder patientFolder) throws RemoteException;

}
