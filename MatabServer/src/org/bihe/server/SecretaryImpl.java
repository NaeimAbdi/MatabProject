package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.dao.SecretaryDAO;

/**
 * This imple class that implement ISecretary which is interface, make relation
 * between SecretaryDAO and ISecretary services. this services set for
 * implementation RMI networking system and client can use them as server
 * services.
 */
public class SecretaryImpl extends UnicastRemoteObject implements ISecretary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected SecretaryImpl() throws RemoteException {
		super();

	}

	public boolean addSecretary(Secretary secretary) throws RemoteException {
		return SecretaryDAO.addSecretary(secretary);
	}

	public Secretary getSecretary(String username) throws RemoteException {
		return SecretaryDAO.getSecretary(username);
	}

	public boolean editSecretary(Secretary secretary) throws RemoteException {
		return SecretaryDAO.editSecretary(secretary);
	}

	public boolean deleteSecretary(Secretary secretary) throws RemoteException {
		return SecretaryDAO.deleteSecretary(secretary);
	}

	public ArrayList<Doctor> findDoctorsForSecretary(Secretary secretary)
			throws RemoteException {
		return SecretaryDAO.findDoctorsForSecretary(secretary);
	}
}
