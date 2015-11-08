package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;

public interface IFixHolidays extends Remote {

	public ArrayList<FixHolidays> getAllFixHolidays() throws RemoteException;

}
