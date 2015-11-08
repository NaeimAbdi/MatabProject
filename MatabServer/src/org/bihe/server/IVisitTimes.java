package org.bihe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.bihe.bean.Doctor;
import org.bihe.bean.VisitTimes;

/**
 * this interface provides : add visit times , get all visit times and closest
 * visit times
 * 
 */
public interface IVisitTimes extends Remote {
	/**
	 * this method provides add visit times services for client
	 * 
	 * @param visitTimes
	 *            is a parameters to add visit times
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean addVisitTimes(VisitTimes visitTimes) throws RemoteException;

	/**
	 * this method provides get all visit times services for client
	 * 
	 * @param date
	 *            is a parameters to add visit times
	 * @return ArrayList<VisitTimes>
	 * @throws RemoteException
	 */
	public boolean update(VisitTimes visitTimes) throws RemoteException;

	/**
	 * Gets all visit times after a special date for a special doctor.
	 * 
	 * @param date
	 * @param doctor
	 * @return
	 * @throws RemoteException
	 */

	public ArrayList<VisitTimes> getAllVisitTimes(Date date, Doctor doctor)
			throws RemoteException;

	/**
	 * At the first time when system parameters are going to set this method
	 * sets all predicted visit times
	 * 
	 * @param doctor
	 * @param startTimeOfDayHour
	 * @param startTimeOfDayMinutes
	 * @param endTimeOfDayHour
	 * @param endTimeOfDayMinutes
	 * @param minuteDurationOfVisit
	 * @param daysOfWeek
	 * @throws RemoteException
	 */
	public void createAllVisitTimes(Doctor doctor, int startTimeOfDayHour,
			int startTimeOfDayMinutes, int endTimeOfDayHour,
			int endTimeOfDayMinutes, int minuteDurationOfVisit,
			String daysOfWeek) throws RemoteException;

	/**
	 * suggest closest visit times after a special date
	 * 
	 * @param numberOfSuggestions
	 * @param fromThisDay
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<VisitTimes> giveMeNearFreeTimes(int numberOfSuggestions,
			Date fromThisDay) throws RemoteException;

	/**
	 * when the duration of visit times changes this method is going to re
	 * generate visit times;
	 * 
	 * @param daysOfWeek
	 * @param doctor
	 * @param startTimeOfDayHour
	 * @param startTimeOfDayMinutes
	 * @param minuteDurationOfVisit
	 * @param endTimeOfDayHour
	 * @param endTimeOfDayMinutes
	 * @throws RemoteException
	 */
	public void updateWithNewDuration(String daysOfWeek, Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) throws RemoteException;

}
