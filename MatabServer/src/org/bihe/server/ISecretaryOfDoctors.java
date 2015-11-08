package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.SecretariesOfDoctors;

/**
 * This interface provides: add secretary of doctor and get secretary of doctor
 * services for client
 * 
 */
public interface ISecretaryOfDoctors extends Remote {
	/**
	 * This method provides add secretary of doctor service to client , when we
	 * give the object of secretaries of doctors to this method then we can add
	 * secretaries of doctor
	 * 
	 * @param secretariesOfDoctors
	 *            is a parameter to add secretaries of doctor
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addSecretaryOfDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException;

	/**
	 * This method provides get secretaries of doctor service to client
	 * 
	 * @param secretariesOfDoctors
	 *            is a parameter to get secretaries of doctor
	 * @return SecretariesOfDoctors
	 * @throws RemoteException
	 */
	public SecretariesOfDoctors getSecretaryOfDoctorByDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException;

}
