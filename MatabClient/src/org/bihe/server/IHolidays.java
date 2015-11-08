package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Holidays;

public interface IHolidays extends Remote {

	public boolean addHoliday(Holidays holiday) throws RemoteException;

	public ArrayList<Holidays> getAllHolidays() throws RemoteException;

}
