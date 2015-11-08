package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bihe.bean.PatientsOfDoctors;
import org.bihe.dao.PatientOfDoctorsDAO;

/**
 * This imple class that implement IPatientOfDoctors which is interface, make
 * relation between PatientOfDoctorsDAO and IPatientOfDoctors services. this
 * services set for implementation RMI networking system and client can use them
 * as server services.
 */
public class PatientOfDoctorsImpl extends UnicastRemoteObject implements
		IPatientOfDoctors {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PatientOfDoctorsImpl() throws RemoteException {
		super();

	}

	@Override
	public boolean addPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {

		return PatientOfDoctorsDAO.addPatientOfDoctors(patientsOfDoctors);
	}

	@Override
	public boolean deletePatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {
		return PatientOfDoctorsDAO.deletePatientOfDoctors(patientsOfDoctors);
	}

	@Override
	public boolean getPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException {

		return PatientOfDoctorsDAO.getPatientOfDoctors(patientsOfDoctors);
	}

}
