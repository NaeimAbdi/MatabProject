package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.Users;

public interface IUser extends Remote {

	public boolean isAuthorized(String username, String password)
			throws RemoteException;

	public String getUserType(String username) throws RemoteException;

	public boolean addUser(Users users) throws RemoteException;

	public boolean deleteUser(Users users) throws RemoteException;

	public boolean updateUser(Users users) throws RemoteException;

	public Users getUserNameAndPassword(String username) throws RemoteException;

}
