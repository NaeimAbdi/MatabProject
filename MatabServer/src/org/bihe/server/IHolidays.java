package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Holidays;

/**
 * This interface which provides : add holiday and get all holidays
 * 
 */
public interface IHolidays extends Remote {
	/**
	 * this method add holidays of doctor
	 * 
	 * @param holiday
	 *            is the parameter to add holiday
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addHoliday(Holidays holiday) throws RemoteException;

	/**
	 * this method give all doctor's holidays
	 * 
	 * @return ArrayList<Holiday>
	 * @throws RemoteException
	 */
	public ArrayList<Holidays> getAllHolidays() throws RemoteException;

}
