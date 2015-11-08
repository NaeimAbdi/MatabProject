package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;

public interface SystemParametersService extends Remote {

	public boolean addSystemParameters(SystemParameters systemParameters)
			throws RemoteException;

	public ArrayList<SystemParameters> getAllParameters(Doctor doctor)
			throws RemoteException;

	public boolean updateParameters(SystemParameters systemParameters)
			throws RemoteException;

	public boolean addSystemParameter(SystemParameters systemParameters)
			throws RemoteException;

}
