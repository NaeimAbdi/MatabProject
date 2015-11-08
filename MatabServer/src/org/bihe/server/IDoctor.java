package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;

/**
 * This interface provides this services : add doctor, edit doctor, get doctor,
 * get doctor by user name and find doctors for secretary
 */
public interface IDoctor extends Remote {
	/**
	 * 
	 * this method provide add doctor service to client, if we set the object of
	 * doctor it return true.
	 * 
	 * @param doctor
	 *            this is the parameters to add doctor
	 * 
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addDoctor(Doctor doctor) throws RemoteException;

	/**
	 * 
	 * this method provides Edit Doctor service to client, if client give doctor
	 * object to this method, can editDoctor
	 * 
	 * @param doctor
	 *            this is the parameters to edit doctor
	 * 
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean editDoctor(Doctor doctor) throws RemoteException;

	/**
	 * 
	 * this method provide get doctor service to client, so client can see
	 * specifications of doctor
	 * 
	 * @param doctor
	 *            this is the parameters to get doctor
	 * 
	 * @return Doctor (Object of doctor)
	 * @throws RemoteException
	 */
	public Doctor getDoctor(Doctor doctor) throws RemoteException;

	/**
	 * this method provide doctor if client give it the String of user name
	 * which is unique for users
	 * 
	 * @param username
	 *            this is the parameters to get doctor by id
	 * 
	 * @return Doctor
	 * @throws RemoteException
	 */
	public Doctor getDoctorByUsername(String username) throws RemoteException;

	/**
	 * 
	 * we need to have doctor for each secretary , this method provides find
	 * doctor for each secretary for client
	 * 
	 * @param doctor
	 *            this is the parameters to find doctors for each secretary
	 * 
	 * @return ArayList<Secretary>
	 * @throws RemoteException
	 */
	public ArrayList<Secretary> findDoctorsForSecretary(Doctor doctor)
			throws RemoteException;
}
