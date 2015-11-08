package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bihe.bean.PatientFolder;
import org.bihe.dao.PatientFolderDAO;

/**
 * This imple class that implement IPatientFolder which is interface, make
 * relation between PatientFolderDAO and IPatientFolder services. this services
 * set for implementation RMI networking system and client can use them as
 * server services.
 */
public class PatientFolderImple extends UnicastRemoteObject implements
		IPatientFolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PatientFolderImple() throws RemoteException {
		super();
	}

	public boolean addPatientFolder(PatientFolder patientFolder)
			throws RemoteException {
		return PatientFolderDAO.addPatientFolder(patientFolder);
	}

	public boolean updatePatientFolder(PatientFolder patientFolder)
			throws RemoteException {
		return PatientFolderDAO.updatePatientFolder(patientFolder);
	}

	public boolean deletePatientFolder(PatientFolder patientFolder)
			throws RemoteException {
		return PatientFolderDAO.deletePatientFolder(patientFolder);
	}

	public PatientFolder getPatientFolder(PatientFolder patientFolder)
			throws RemoteException {
		return PatientFolderDAO.getPatientFolder(patientFolder);
	}

}
