package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientsOfDoctors;

public interface PatientOfDoctorsService extends Remote {
	public boolean addPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	public boolean deletePatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	public boolean getPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;
}
