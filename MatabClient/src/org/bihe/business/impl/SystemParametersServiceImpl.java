package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.business.SystemParametersService;
import org.bihe.server.Service;

public class SystemParametersServiceImpl implements SystemParametersService {
	private Service service;

	public SystemParametersServiceImpl(Service service) {
		super();
		this.service = service;
	}

	@Override
	public ArrayList<SystemParameters> getAllParameters(Doctor doctor)
			throws RemoteException {

		return this.service.I_SYSTEM_PARAMETERS.getAllParameters(doctor);
	}

	@Override
	public boolean updateParameters(SystemParameters systemParameters)
			throws RemoteException {

		return this.service.I_SYSTEM_PARAMETERS
				.updateParameters(systemParameters);
	}

	public boolean addSystemParameters(SystemParameters systemParameters)
			throws RemoteException {
		return this.service.I_SYSTEM_PARAMETERS
				.addSystemParameters(systemParameters);
	}

	@Override
	public boolean addSystemParameter(SystemParameters systemParameters)
			throws RemoteException {

		return this.service.I_SYSTEM_PARAMETERS
				.addSystemParameter(systemParameters);
	}

}
