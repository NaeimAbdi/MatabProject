package org.bihe.business;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;

public interface FixHolidaysService {
	public ArrayList<FixHolidays> getAllFixHolidays() throws RemoteException;
}
