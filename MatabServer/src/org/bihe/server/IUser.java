package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bihe.bean.Users;

/**
 * 
 * This interface provides: is authorized, get user type, add user, delete
 * user,update user and get user name and password services for client.
 * 
 */
public interface IUser extends Remote {
	/**
	 * This method check user name and password of each person who wants to join
	 * to the system.
	 * 
	 * @param username
	 *            is first parameter to isAuthorized
	 * @param password
	 *            is second parameter to isAuthorized
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean isAuthorized(String username, String password)
			throws RemoteException;

	/**
	 * this method provides get user type.
	 * 
	 * @param username
	 *            is a parameter to get user type
	 * @return String
	 * @throws RemoteException
	 */
	public String getUserType(String username) throws RemoteException;

	/**
	 * this method provides add user to system. if we give the object of User to
	 * this method it return true.
	 * 
	 * @param users
	 *            is a parameter too add user.
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addUser(Users users) throws RemoteException;

	/**
	 * this method provides delete user to system. if we give the object of User
	 * to this method it return true.
	 * 
	 * @param users
	 *            is a parameter to delete user.
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deleteUser(Users users) throws RemoteException;

	/**
	 * this method provides update user to system. if we give the object of User
	 * to this method it return true.
	 * 
	 * @param users
	 *            is a parameter to update user
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean updateUser(Users users) throws RemoteException;

	/**
	 * this method provides add user to system. if we give the object of User to
	 * this method it return true.
	 * 
	 * @param username
	 *            is a parameter to get user name and password.
	 * @return Users
	 * @throws RemoteException
	 */
	public Users getUserNameAndPassword(String username) throws RemoteException;

}
