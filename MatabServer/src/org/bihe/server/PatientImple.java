package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.dao.PatientDAO;

/**
 * This imple class that implement IPatient which is interface, make relation
 * between PatientDAO and IPatient services. this services set for
 * implementation RMI networking system and client can use them as server
 * services.
 */
public class PatientImple extends UnicastRemoteObject implements IPatient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PatientImple() throws RemoteException {
		super();

	}

	public Patient addPatient(Patient patient) throws RemoteException {
		return PatientDAO.addPatient(patient);
	}

	public boolean updatePatient(Patient patient) throws RemoteException {
		return PatientDAO.editPatientByID(patient);
	}

	public boolean deletePatient(Patient patient) throws RemoteException {
		return PatientDAO.deletePatient(patient);
	}

	public ArrayList<Patient> search(Patient patient,
			PatientFolder patientFolder) throws RemoteException {
		return PatientDAO.searchPatientBy(patient, patientFolder);
	}

}
