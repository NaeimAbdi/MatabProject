package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientFolder;

public interface IPatientFolder extends Remote {

	public boolean addPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public boolean updatePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public boolean deletePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public PatientFolder getPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

}
