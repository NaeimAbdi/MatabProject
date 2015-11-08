package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;

/**
 * This interface provides getAllFixHolidays, fix holidays are all fix holidays
 * through the year
 */
public interface IFixHolidays extends Remote {
	/**
	 * this method give all fix holidays
	 * 
	 * @return ArrayList<FixHolidays>
	 * @throws RemoteException
	 */
	public ArrayList<FixHolidays> getAllFixHolidays() throws RemoteException;

}
