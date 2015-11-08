package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientsOfDoctors;

/**
 * this interface provides delete patient of doctor and get patient of doctors
 * 
 */
public interface IPatientOfDoctors extends Remote {
	/**
	 * this method provides add patient for client
	 * 
	 * @param patientsOfDoctors
	 *            is a parameter to add patient of doctor
	 * @return
	 * @throws RemoteException
	 */
	public boolean addPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	/**
	 * this method provides delete patient of doctor for client.if user give the
	 * object of patients of doctors to this method it return true;
	 * 
	 * @param patientsOfDoctors
	 *            is a parameters to delete patients of doctors
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deletePatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	/**
	 * this method provides get patient of doctor, and it return boolean
	 * 
	 * @param patientsOfDoctors
	 *            is a parameters to get patients of doctors
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean getPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;
}
