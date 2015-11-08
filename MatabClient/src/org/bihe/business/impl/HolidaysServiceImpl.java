package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.Holidays;
import org.bihe.business.HolidaysService;
import org.bihe.server.Service;

public class HolidaysServiceImpl implements HolidaysService {
	private Service service;

	public HolidaysServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public boolean addHoliday(Holidays holiday) throws RemoteException {
		return service.I_HOLIDAYS.addHoliday(holiday);
	}

	@Override
	public ArrayList<Holidays> getAllHolidays() throws RemoteException {

		return service.I_HOLIDAYS.getAllHolidays();
	}

}
