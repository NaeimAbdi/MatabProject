package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.dao.SystemParametersDAO;

/**
 * This imple class that implement ISystemParameters which is interface, make
 * relation between SystemParametersDAO and ISystemParameters services. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class SystemParametersImple extends UnicastRemoteObject implements
		ISystemParameters {

	protected SystemParametersImple() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public ArrayList<SystemParameters> getAllParameters(Doctor doctor)
			throws RemoteException {
		return SystemParametersDAO.getAllParameters(doctor);
	}

	public boolean updateParameters(SystemParameters systemParameters)
			throws RemoteException {
		return SystemParametersDAO.updateParameters(systemParameters);
	}

	@Override
	public boolean addSystemParameters(SystemParameters systemParameters) {

		return SystemParametersDAO.addSystemParameters(systemParameters);
	}

	@Override
	public boolean addSystemParameter(SystemParameters systemParameters)
			throws RemoteException {

		return SystemParametersDAO.addSystemParameters(systemParameters);
	}

}
