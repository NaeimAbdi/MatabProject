package org.bihe.business.impl;

import java.rmi.RemoteException;

import org.bihe.bean.Users;
import org.bihe.business.UserService;
import org.bihe.server.Service;

public class UserServiceImpl implements UserService {
	private Service service;

	public UserServiceImpl(Service service) {
		super();
		this.service = service;
	}

	@Override
	public boolean isAuthorized(String username, String password)
			throws RemoteException {

		return this.service.I_USER.isAuthorized(username, password);
	}

	@Override
	public String getUserType(String username) throws RemoteException {

		return this.service.I_USER.getUserType(username);
	}

	@Override
	public boolean addUser(Users users) throws RemoteException {

		return this.service.I_USER.addUser(users);
	}

	@Override
	public boolean deleteUser(Users users) throws RemoteException {

		return this.service.I_USER.deleteUser(users);
	}

	@Override
	public boolean updateUser(Users users) throws RemoteException {

		return this.service.I_USER.updateUser(users);
	}

	@Override
	public Users getUserNameAndPassword(String username) throws RemoteException {

		return this.service.I_USER.getUserNameAndPassword(username);
	};

}
