package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;

public interface ISecretary extends Remote {
	public boolean addSecretary(Secretary secretary) throws RemoteException;

	public Secretary getSecretary(String username) throws RemoteException;

	public boolean editSecretary(Secretary secretary) throws RemoteException;

	public boolean deleteSecretary(Secretary secretary) throws RemoteException;

	public ArrayList<Doctor> findDoctorsForSecretary(Secretary secretary)
			throws RemoteException;
}
