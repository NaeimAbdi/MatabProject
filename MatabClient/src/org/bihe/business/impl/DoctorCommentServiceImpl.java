package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.DoctorComment;
import org.bihe.business.DoctorCommentService;
import org.bihe.server.Service;

public class DoctorCommentServiceImpl implements DoctorCommentService {
	private Service service;

	public DoctorCommentServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public DoctorComment addDoctorComment(DoctorComment doctorComment)
			throws RemoteException {
		return service.I_DOCTOR_COMMENT.addDoctorComment(doctorComment);
	}

	@Override
	public ArrayList<DoctorComment> getDoctorComment(DoctorComment doctorComment)
			throws RemoteException {
		// TODO Auto-generated method stub
		return this.service.I_DOCTOR_COMMENT.getDoctorComment(doctorComment);
	}

}
