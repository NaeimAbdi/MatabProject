package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.dao.DoctorDAO;

/**
 * This imple class that implement IDoctor which is interface, make relation
 * between DoctorDAO and IDoctor services. this services set for implementation
 * RMI networking system and client can use them as server services.
 */
public class DoctorImpl extends UnicastRemoteObject implements IDoctor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected DoctorImpl() throws RemoteException {
		super();
	}

	@Override
	public boolean addDoctor(Doctor doctor) throws RemoteException {
		return DoctorDAO.addDoctor(doctor);
	}

	@Override
	public boolean editDoctor(Doctor doctor) throws RemoteException {
		return DoctorDAO.editDoctor(doctor);
	}

	@Override
	public Doctor getDoctor(Doctor doctor) throws RemoteException {
		return DoctorDAO.getDoctor(doctor);
	}

	@Override
	public Doctor getDoctorByUsername(String username) throws RemoteException {

		return DoctorDAO.getDoctorByUsername(username);
	}

	@Override
	public ArrayList<Secretary> findDoctorsForSecretary(Doctor doctor)
			throws RemoteException {

		return DoctorDAO.secretaryOfDoctor(doctor);
	}

}
