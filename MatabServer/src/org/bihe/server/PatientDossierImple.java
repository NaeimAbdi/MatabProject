package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;
import org.bihe.dao.PatientDossierDAO;

/**
 * This imple class that implement IPatientDossier which is interface, make
 * relation between PatientDossierDAO and IPatientDossier services. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class PatientDossierImple extends UnicastRemoteObject implements
		IPatientDossier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PatientDossierImple() throws RemoteException {
		super();
	}

	public boolean addPatientDossier(PatientDossier patientDossier)
			throws RemoteException {
		return PatientDossierDAO.addPatientDossier(patientDossier);
	}

	// TODO why?
	public ArrayList<PatientDossier> getPatientDossier(PatientFolder pf)
			throws RemoteException {
		return PatientDossierDAO.getPatientDossier(pf);
	}

}
