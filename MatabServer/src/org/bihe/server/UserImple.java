package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bihe.bean.Users;
import org.bihe.dao.UsersDAO;

/**
 * This imple class that implement IUser which is interface, make relation
 * between UserDAO and IUser services. this services set for implementation RMI
 * networking system and client can use them as server services.
 */
public class UserImple extends UnicastRemoteObject implements IUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected UserImple() throws RemoteException {
		super();

	}

	public boolean isAuthorized(String username, String password)
			throws RemoteException {
		return UsersDAO.isAuthenticate(username, password);
	}

	public String getUserType(String username) throws RemoteException {
		return null;
	}

	public boolean addUser(Users users) throws RemoteException {
		return UsersDAO.insertUsers(users);
	}

	public boolean deleteUser(Users user) throws RemoteException {
		return UsersDAO.deleteUser(user);
	}

	public boolean updateUser(Users users) throws RemoteException {
		return UsersDAO.updateUsersID(users);
	}

	@Override
	public Users getUserNameAndPassword(String username) throws RemoteException {

		return UsersDAO.getUserNameAndPassword(username);
	}

}
