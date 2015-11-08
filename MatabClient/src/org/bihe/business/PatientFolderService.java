package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientFolder;

public interface PatientFolderService extends Remote {

	public boolean addPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public boolean updatePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public boolean deletePatientFolder(PatientFolder patientFolder)
			throws RemoteException;

	public PatientFolder getPatientFolder(PatientFolder patientFolder)
			throws RemoteException;

}
