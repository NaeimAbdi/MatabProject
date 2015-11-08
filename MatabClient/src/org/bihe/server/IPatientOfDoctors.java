package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.PatientsOfDoctors;

public interface IPatientOfDoctors extends Remote {
	public boolean addPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	public boolean deletePatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;

	public boolean getPatientOfDoctors(PatientsOfDoctors patientsOfDoctors)
			throws RemoteException;
}
