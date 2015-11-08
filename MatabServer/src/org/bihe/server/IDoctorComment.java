package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;

/**
 * This interface provides this services : add doctor comment and get doctor
 * comment
 * 
 */
public interface IDoctorComment extends Remote {
	/**
	 * this method provides add doctor comment service to client, if client
	 * gives the object of Doctor comment to this method, it return Doctor
	 * comment
	 * 
	 * @param doctorComment
	 *            this is the parameters to add doctor comment
	 * @return DoctorComment
	 * @throws RemoteException
	 */
	public DoctorComment addDoctorComment(DoctorComment doctorComment)
			throws RemoteException;

	/**
	 * this method provides get doctor comment service to client, if client
	 * gives the object of Patient to this method it returns boolean
	 * 
	 * @param patient
	 *            this is the parameters to get doctor comment
	 * @return boolean
	 * @throws RemoteException
	 */
	public ArrayList<DoctorComment> getDoctorComment(DoctorComment doctorComment)
			throws RemoteException;

}
