package org.bihe.business;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Holidays;

public interface HolidaysService {
	public boolean addHoliday(Holidays holiday) throws RemoteException;

	public ArrayList<Holidays> getAllHolidays() throws RemoteException;
}
