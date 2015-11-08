package org.bihe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import org.bihe.bean.Doctor;
import org.bihe.bean.VisitTimes;
import org.bihe.dao.VisitTimeDAO;

/**
 * This imple class that implement IVisitTimes which is interface, make relation
 * between VisitTimesDAO and IVisitTimes services. this services set for
 * implementation RMI networking system and client can use them as server
 * services.
 */
public class VisitTimesImple extends UnicastRemoteObject implements IVisitTimes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected VisitTimesImple() throws RemoteException {
		super();

	}

	public boolean addVisitTimes(VisitTimes visitTimes) throws RemoteException {
		return VisitTimeDAO.insertVistTime(visitTimes);
	}

	public ArrayList<VisitTimes> getAllVisitTimes(Date date, Doctor doctor)
			throws RemoteException {
		return VisitTimeDAO.getAllVisitTimes(date, doctor);
	}

	public boolean update(VisitTimes visitTimes) throws RemoteException {
		return VisitTimeDAO.updateVisitTimeByID(visitTimes);
	}

	@Override
	public void createAllVisitTimes(Doctor doctor, int startTimeOfDayHour,
			int startTimeOfDayMinutes, int endTimeOfDayHour,
			int endTimeOfDayMinutes, int minuteDurationOfVisit,
			String daysOfWeek) throws RemoteException {

		VisitTimeDAO.createAllVisitTimes(doctor, startTimeOfDayHour,
				startTimeOfDayMinutes, endTimeOfDayHour, endTimeOfDayMinutes,
				minuteDurationOfVisit, daysOfWeek);
	}

	@Override
	public ArrayList<VisitTimes> giveMeNearFreeTimes(int numberOfSuggestions,
			Date fromThisDay) throws RemoteException {

		return VisitTimeDAO.giveMeNearFreeTimes(numberOfSuggestions,
				fromThisDay);
	}

	@Override
	public void updateWithNewDuration(String daysOfWeek, Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) throws RemoteException {

		VisitTimeDAO.updateWithNewDuration(daysOfWeek, doctor,
				startTimeOfDayHour, startTimeOfDayMinutes,
				minuteDurationOfVisit, endTimeOfDayHour, endTimeOfDayMinutes);

	}

}
