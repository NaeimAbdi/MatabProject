package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;

/**
 * This interface provides: add system parameters, get all parameters, update
 * parameters services for client
 */
public interface ISystemParameters extends Remote {
	/**
	 * This method provides add system parameters service to client. when the
	 * object of System Parameters give to this method it return true
	 * 
	 * @param systemParameters
	 *            is a parameter to add system parameters
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addSystemParameters(SystemParameters systemParameters)
			throws RemoteException;

	/**
	 * This method provides get all parameters service to client. when the
	 * object of doctor give to this method it return the arrayList of system
	 * parameter
	 * 
	 * @param doctor
	 *            is a parameters to get all parameters
	 * @return ArrayList<SystemParameters>
	 * @throws RemoteException
	 */
	public ArrayList<SystemParameters> getAllParameters(Doctor doctor)
			throws RemoteException;

	/**
	 * 
	 * This method provides update parameters service to client. when the object
	 * of system parameters give to this method it return true parameter
	 * 
	 * @param systemParameters
	 *            is a parameter to update parameters
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateParameters(SystemParameters systemParameters)
			throws RemoteException;

	/**
	 * This method provides add system parameters service to client. when the
	 * object of System Parameters give to this method it return true
	 * 
	 * @param systemParameters
	 *            is a parameter to add system parameters
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addSystemParameter(SystemParameters systemParameters)
			throws RemoteException;

}
