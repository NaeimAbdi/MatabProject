package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;
import org.bihe.dao.FixHolidaysDAO;

/**
 * This imple class that implement IFixHolidays which is interface, make
 * relation between FixHolidaysDAO and IFixHolidays services. this services set
 * for implementation RMI networking system and client can use them as server
 * services.
 */
public class FixHolidaysImple extends UnicastRemoteObject implements
		IFixHolidays {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FixHolidaysImple() throws RemoteException {
		super();

	}

	@Override
	public ArrayList<FixHolidays> getAllFixHolidays() throws RemoteException {

		return FixHolidaysDAO.getAllHolidays();
	}

}
