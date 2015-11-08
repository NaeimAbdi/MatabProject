package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;

public interface IDoctorComment extends Remote {

	public DoctorComment addDoctorComment(DoctorComment doctorComment)
			throws RemoteException;

	public ArrayList<DoctorComment> getDoctorComment(DoctorComment doctorComment)
			throws RemoteException;

}
