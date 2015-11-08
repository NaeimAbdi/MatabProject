package org.bihe.business;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;

public interface DoctorCommentService {
	public DoctorComment addDoctorComment(DoctorComment doctorComment)
			throws RemoteException;

	public ArrayList<DoctorComment> getDoctorComment(DoctorComment doctorComment)
			throws RemoteException;
}
