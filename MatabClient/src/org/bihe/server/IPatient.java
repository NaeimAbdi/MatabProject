package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;

public interface IPatient extends Remote {

	public Patient addPatient(Patient patient) throws RemoteException;

	public boolean updatePatient(Patient patient) throws RemoteException;

	public boolean deletePatient(Patient patient) throws RemoteException;

	public ArrayList<Patient> search(Patient patient, PatientFolder folder)
			throws RemoteException;

}
