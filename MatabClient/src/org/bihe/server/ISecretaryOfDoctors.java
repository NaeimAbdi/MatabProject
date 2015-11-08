package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.SecretariesOfDoctors;

public interface ISecretaryOfDoctors extends Remote {

	public boolean addSecretaryOfDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException;

	public SecretariesOfDoctors getSecretaryOfDoctorByDoctor(
			SecretariesOfDoctors secretariesOfDoctors) throws RemoteException;

}
