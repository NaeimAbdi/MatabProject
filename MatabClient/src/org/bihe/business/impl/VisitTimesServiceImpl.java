package org.bihe.business.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.bihe.bean.Doctor;
import org.bihe.bean.VisitTimes;
import org.bihe.business.VisitTimesService;
import org.bihe.server.Service;

public class VisitTimesServiceImpl implements VisitTimesService {
	private Service service;

	public VisitTimesServiceImpl(Service service) {
		super();
		this.service = service;
	}

	@Override
	public boolean addVisitTimes(VisitTimes visitTimes) throws RemoteException {

		return this.service.I_VISIT_TIMES.addVisitTimes(visitTimes);
	}

	@Override
	public boolean update(VisitTimes visitTimes) throws RemoteException {

		return this.service.I_VISIT_TIMES.update(visitTimes);
	}

	@Override
	public ArrayList<VisitTimes> getAllVisitTimes(Date date, Doctor doctor)
			throws RemoteException {

		return this.service.I_VISIT_TIMES.getAllVisitTimes(date, doctor);
	}

	@Override
	public void createAllVisitTimes(Doctor doctor, int startTimeOfDayHour,
			int startTimeOfDayMinutes, int endTimeOfDayHour,
			int endTimeOfDayMinutes, int minuteDurationOfVisit,
			String daysOfWeek) throws RemoteException {
		this.service.I_VISIT_TIMES.createAllVisitTimes(doctor,
				startTimeOfDayHour, startTimeOfDayMinutes, endTimeOfDayHour,
				endTimeOfDayMinutes, minuteDurationOfVisit, daysOfWeek);

	}

	@Override
	public ArrayList<VisitTimes> giveMeNearFreeTimes(int numberOfSuggestions,
			Date fromThisDay) throws RemoteException {

		return this.service.I_VISIT_TIMES.giveMeNearFreeTimes(
				numberOfSuggestions, fromThisDay);
	}

	@Override
	public void updateWithNewDuration(String daysOfWeek, Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) throws RemoteException {

		System.out.println(doctor.getId() + "serviceimple");
		this.service.I_VISIT_TIMES.updateWithNewDuration(daysOfWeek, doctor,
				startTimeOfDayHour, startTimeOfDayMinutes,
				minuteDurationOfVisit, endTimeOfDayHour, endTimeOfDayMinutes);
	}

}
