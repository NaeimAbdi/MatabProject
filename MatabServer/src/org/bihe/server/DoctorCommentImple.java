package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;
import org.bihe.dao.DoctorCommentDAO;

public class DoctorCommentImple extends UnicastRemoteObject implements
		IDoctorComment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoctorCommentImple() throws RemoteException {
		super();

	}

	public DoctorComment addDoctorComment(DoctorComment doctorComment)
			throws RemoteException {

		return DoctorCommentDAO.addDoctorComment(doctorComment);
	}

	public ArrayList<DoctorComment> getDoctorComment(DoctorComment doctorComment)
			throws RemoteException {
		return DoctorCommentDAO.getDoctorComment(doctorComment);
	}

}
