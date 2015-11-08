package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;

/**
 * This interface provides: add patient, update patient, delete patient and
 * search to client.
 * 
 */
public interface IPatient extends Remote {
	/**
	 * This method provides add doctor when client give the object of Patient to
	 * it.
	 * 
	 * @param patient
	 *            is the parameter to add patient
	 * @return Patient
	 * @throws RemoteException
	 */
	public Patient addPatient(Patient patient) throws RemoteException;

	/**
	 * This method provides update doctor service to client
	 * 
	 * @param patient
	 *            is the parameters to update patient
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean updatePatient(Patient patient) throws RemoteException;

	/**
	 * 
	 * this method provides delete patient service to client
	 * 
	 * @param patient
	 *            is the parameters to delete patient
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deletePatient(Patient patient) throws RemoteException;

	/**
	 * this method provides search patient service to client
	 * 
	 * @param patient
	 *            is the first parameters to search
	 * @param folder
	 *            is the second parameters to search
	 * 
	 * @return ArrayList<Patient>
	 * @throws RemoteException
	 */
	public ArrayList<Patient> search(Patient patient, PatientFolder folder)
			throws RemoteException;

}
