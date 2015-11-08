package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;

/**
 * This interface provides: add secretary , get secretary, edit secretary,
 * delete secretary and find doctors for each secretary services to client
 * 
 */
public interface ISecretary extends Remote {

	/**
	 * this method provides add secretary service for client, when the object of
	 * secretary give to this method it return true
	 * 
	 * @param secretary
	 *            is the parameters to add secretary
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addSecretary(Secretary secretary) throws RemoteException;

	/**
	 * this method provides get secretary service for client. by giving user
	 * name to this method, it gives us true
	 * 
	 * @param username
	 *            is a parameters to get secretary
	 * @return Secretary
	 * @throws RemoteException
	 */
	public Secretary getSecretary(String username) throws RemoteException;

	/**
	 * this method provides edit secretary service for client
	 * 
	 * @param secretary
	 *            is a parameters to edit secretary
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean editSecretary(Secretary secretary) throws RemoteException;

	/**
	 * this method provides delete secretary service for client
	 * 
	 * @param secretary
	 *            is a parameter to delete secretary
	 * @return boolean
	 * @throws RemoteExceptio
	 * */
	public boolean deleteSecretary(Secretary secretary) throws RemoteException;

	/**
	 * 
	 * this method provides find doctors for secretary service for client
	 * 
	 * @param secretary
	 *            is a parameter to find doctors for each secretary
	 * @return ArrayList<Doctor>
	 * @throws RemoteException
	 */
	public ArrayList<Doctor> findDoctorsForSecretary(Secretary secretary)
			throws RemoteException;
}
