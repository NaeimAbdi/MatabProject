package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;

public interface DoctorService extends Remote {
	public boolean addDoctor(Doctor doctor) throws RemoteException;

	public boolean editDoctor(Doctor doctor) throws RemoteException;

	public Doctor getDoctor(Doctor doctor) throws RemoteException;

	public Doctor getDoctorByUsername(String username) throws RemoteException;

	public ArrayList<Secretary> findSecretaryForDoctors(Doctor doctor)
			throws RemoteException;
}
