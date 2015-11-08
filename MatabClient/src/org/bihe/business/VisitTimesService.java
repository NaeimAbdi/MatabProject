package org.bihe.business;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.bihe.bean.Doctor;
import org.bihe.bean.VisitTimes;

public interface VisitTimesService extends Remote {

	public boolean addVisitTimes(VisitTimes visitTimes) throws RemoteException;

	public boolean update(VisitTimes visitTimes) throws RemoteException;

	public ArrayList<VisitTimes> getAllVisitTimes(Date date, Doctor doctor)
			throws RemoteException;

	public void createAllVisitTimes(Doctor doctor, int startTimeOfDayHour,
			int startTimeOfDayMinutes, int endTimeOfDayHour,
			int endTimeOfDayMinutes, int minuteDurationOfVisit,
			String daysOfWeek) throws RemoteException;

	public ArrayList<VisitTimes> giveMeNearFreeTimes(int numberOfSuggestions,
			Date fromThisDay) throws RemoteException;

	public void updateWithNewDuration(String daysOfWeek, Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) throws RemoteException;

}
