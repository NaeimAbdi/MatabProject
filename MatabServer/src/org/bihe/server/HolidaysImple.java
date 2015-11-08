/**
 * This imple class that implement IHolidays which is interface, make
 * relation between HolidaysDAO and IHolidays services. this services
 * set for implementation RMI networking system and client can use them as
 * server services.
 */
package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.bihe.bean.Holidays;
import org.bihe.dao.HolidayDAO;

public class HolidaysImple extends UnicastRemoteObject implements IHolidays {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected HolidaysImple() throws RemoteException {
		super();
	}

	public boolean addHoliday(Holidays holiday) throws RemoteException {
		return HolidayDAO.addHoliday(holiday);
	}

	public ArrayList<Holidays> getAllHolidays() throws RemoteException {
		return HolidayDAO.getAllHolidays();
	}

}
