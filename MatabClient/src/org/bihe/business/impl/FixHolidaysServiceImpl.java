package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.bihe.bean.FixHolidays;
import org.bihe.server.Service;

public class FixHolidaysServiceImpl implements org.bihe.business.FixHolidaysService {
	private Service service;

	public FixHolidaysServiceImpl(Service service) {
		this.service = service;
	}

	@Override
	public ArrayList<FixHolidays> getAllFixHolidays() throws RemoteException {
		return this.service.I_FIX_HOLIDAY.getAllFixHolidays();
	}
}
