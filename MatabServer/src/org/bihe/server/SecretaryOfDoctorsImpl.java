package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bihe.bean.SecretariesOfDoctors;
import org.bihe.dao.SecretariesOfDoctorDAO;

/**
 * This imple class that implement ISecretaryOfDoctors which is interface, make
 * relation between SecretaryOfDoctorsDAO and ISecretaryOfDoctors services. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class SecretaryOfDoctorsImpl extends UnicastRemoteObject implements
		ISecretaryOfDoctors {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SecretaryOfDoctorsImpl() throws RemoteException {
		super();
	}

	public boolean addSecretaryOfDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException {
		return SecretariesOfDoctorDAO
				.addSecretariesOfDoctor(secretariesOfDoctors);
	}

	public SecretariesOfDoctors getSecretaryOfDoctorByDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException {
		return SecretariesOfDoctorDAO
				.getSecretaryOfDoctorByDoctor(secretariesOfDoctors);
	}

}
