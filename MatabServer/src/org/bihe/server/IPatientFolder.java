package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientFolder;

/**
 * This interface provides : add patient folder, update patient folder, update
 * patient folder and get patient folder for client
 * 
 */
public interface IPatientFolder extends Remote {
	/**
	 * this method provides add patient folder for client. when client give the
	 * object of patient folder this method return true
	 * 
	 * @param patientFolder
	 *            is a parameter to add patient folder
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	/**
	 * this method provides update patient folder service, when client give the
	 * object of patient folder to this method, method return true.
	 * 
	 * @param patientFolder
	 *            is a parameter to update patient folder
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean updatePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	/**
	 * this method provides delete patient folder for client
	 * 
	 * @param patientFolder
	 *            is a parameters to delete patient folder
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deletePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	/**
	 * This method provides get patient folder for client
	 * 
	 * @param patientFolder
	 *            is a parameters to getPatientFolder
	 * @return PatientFolder
	 * @throws RemoteException
	 */
	public PatientFolder getPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

}
