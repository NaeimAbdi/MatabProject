package org.bihe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.bihe.bean.Doctor;
import org.bihe.bean.VisitTimes;
import org.bihe.jdbc.DBManager;

/**
 * secretary can add visit time for patients, so secretary can insert, get, get
 * all and update visit times in database
 */
public class VisitTimeDAO {

	private static final String INSERT_VISIT_TIME = "INSERT INTO visit_times(start,finish,patient_id,doctor_id) VALUES(?,?,?,?)";
	private static final String INSERT_VISIT_TIME_WITH_NO_PATIENT = "INSERT INTO visit_times(start,finish,doctor_id) VALUES(?,?,?)";

	private static final String SHOW_ALL_TIMES = "SELECT * FROM visit_times WHERE start > ? AND patient_id is not null AND doctor_id=?";
	private static final String SHOW_ALL_TIMES_OF_DAY_BY_DOCTOR = "SELECT * FROM visit_times WHERE start like '?%' AND doctor_id = ?";
	private static final String UPDATE_VISIT_TIME = "UPDATE visit_times set start = ?, finish = ?,patient_id = ? , doctor_id = ? WHERE id = ?";
	private static final String DELETE_VISIT_TIME_BY_ID = "DELETE FROM visit_times WHERE id = ?";
	private static final String FIND_N_NEAR_FREE_TIMES = "SELECT * FROM (SELECT * FROM visit_times WHERE start > ? AND patient_id is null order by start) V LIMIT  ?";
	private static final String FIND_NOT_NULL_PATIENT_OF_A_DAY = "SELECT * FROM visit_times WHERE start LIKE '%";
	private static final String DELETE_PATIENT_OF_A_DAY = "DELETE FROM visit_times WHERE start LIKE '%";

	public static boolean insertVistTime(VisitTimes visitTime) {
		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_VISIT_TIME;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, visitTime.getStart());
			ps.setString(2, visitTime.getFinish());
			ps.setInt(3, visitTime.getPatientID());
			ps.setInt(4, visitTime.getDoctorID());
			//
			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static boolean insertVistTimeWithNoPatient(VisitTimes visitTime) {
		boolean retVal = false;

		Connection con = DBManager.getConnection();
		String sql = INSERT_VISIT_TIME_WITH_NO_PATIENT;

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			//
			ps.setString(1, visitTime.getStart());
			ps.setString(2, visitTime.getFinish());
			ps.setInt(3, visitTime.getDoctorID());
			//
			int nRecord = ps.executeUpdate();
			retVal = nRecord == 1;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return retVal;
	}

	public static ArrayList<VisitTimes> getAllVisitTimes(Date date, Doctor d) {
		// TODO
		ArrayList<VisitTimes> retVal = new ArrayList<VisitTimes>();
		//
		String sql = SHOW_ALL_TIMES;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement st = connection.prepareStatement(sql);
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateFormatted = fmt.format(date);

			st.setString(1, dateFormatted);
			st.setInt(2, d.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {

				VisitTimes visitTime = new VisitTimes();
				//
				visitTime.setStart(res.getString("start"));
				visitTime.setFinish(res.getString("finish"));
				visitTime.setPatientID(res.getInt("patient_id"));
				visitTime.setDoctorID(res.getInt("doctor_id"));

				//
				retVal.add(visitTime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static ArrayList<VisitTimes> getVisittimesOfDayByDoctor(Date date,
			Doctor doctor) {

		// TODO
		ArrayList<VisitTimes> retVal = new ArrayList<VisitTimes>();
		//
		String sql = SHOW_ALL_TIMES_OF_DAY_BY_DOCTOR;
		Connection connection = DBManager.getConnection();

		try {
			PreparedStatement st = connection.prepareStatement(sql);

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormatted = fmt.format(date.getTime());

			st.setString(1, dateFormatted);
			st.setInt(2, doctor.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) {

				VisitTimes visitTime = new VisitTimes();
				//
				visitTime.setStart(res.getString("start"));
				visitTime.setFinish(res.getString("finish"));
				visitTime.setPatientID(res.getInt("patient_id"));
				visitTime.setDoctorID(res.getInt("doctor_id"));
				visitTime.setId(res.getInt("id"));
				//
				retVal.add(visitTime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retVal;

	}

	public static boolean deleteTime(VisitTimes visitTime) throws SQLException {
		boolean retVal = false;

		Connection connection = DBManager.getConnection();
		String sql = DELETE_VISIT_TIME_BY_ID;

		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, visitTime.getId());

		int nRecord = ps.executeUpdate();

		retVal = nRecord == 1;

		return retVal;
	}

	public static boolean updateVisitTimeByID(VisitTimes visitTime) {
		boolean result = false;
		//
		Connection connection = DBManager.getConnection();
		String sql = UPDATE_VISIT_TIME;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			//
			ps.setString(1, visitTime.getStart());
			ps.setString(2, visitTime.getFinish());
			ps.setInt(3, visitTime.getPatientID());
			ps.setInt(4, visitTime.getDoctorID());
			ps.setInt(5, visitTime.getId());

			int nRecord = ps.executeUpdate();

			result = nRecord == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static ArrayList<VisitTimes> findNearVisitTimes(String startTimes,
			int rowCount) {

		ArrayList<VisitTimes> suggestions = new ArrayList<>();
		Connection connection = DBManager.getConnection();
		String sql = FIND_N_NEAR_FREE_TIMES;

		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, startTimes);
			ps.setInt(2, rowCount);
			//
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				VisitTimes visitTime = new VisitTimes();
				//
				visitTime.setStart(rs.getString("start"));
				visitTime.setFinish(rs.getString("finish"));
				visitTime.setPatientID(rs.getInt("patient_id"));
				visitTime.setDoctorID(rs.getInt("doctor_id"));
				visitTime.setId(rs.getInt("id"));

				//
				suggestions.add(visitTime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suggestions;

	}

	private static boolean deleteAllPatientsOfADay(String date)
			throws SQLException {

		Connection connection = DBManager.getConnection();
		String sql = DELETE_PATIENT_OF_A_DAY + date + "%'";

		Statement ps = connection.createStatement();
		//
		int nRecord = ps.executeUpdate(sql);

		return nRecord > 0;

	}

	public static ArrayList<VisitTimes> findNotNullPatientsOfADay(
			String startTimes, Doctor doctor) {

		ArrayList<VisitTimes> suggestions = new ArrayList<>();
		Connection connection = DBManager.getConnection();
		String sql = FIND_NOT_NULL_PATIENT_OF_A_DAY + startTimes
				+ "%' AND doctor_id = " + doctor.getId()
				+ " AND patient_id is not null";

		try {

			Statement s = connection.createStatement();

			//
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				VisitTimes visitTime = new VisitTimes();
				//
				visitTime.setStart(rs.getString("start"));
				visitTime.setFinish(rs.getString("finish"));
				visitTime.setPatientID(rs.getInt("patient_id"));
				visitTime.setDoctorID(rs.getInt("doctor_id"));
				visitTime.setId(rs.getInt("id"));

				//
				suggestions.add(visitTime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return suggestions;

	}

	public static void createAllVisitTimes(Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int endTimeOfDayHour, int endTimeOfDayMinutes,
			int minuteDurationOfVisit, String daysOfWeek) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, startTimeOfDayHour);
		cal.set(Calendar.MINUTE, startTimeOfDayMinutes);
		Date today = cal.getTime();

		int i = 0;

		while (i <= 365) {

			if (isAnActiceDay(today, daysOfWeek)) {
				fillEachDayByNullPatients(today, doctor, endTimeOfDayHour,
						endTimeOfDayMinutes, minuteDurationOfVisit);

			}
			today = giveMeNextDay(today);
			i++;
		}

	}

	public static void fillEachDayByNullPatients(Date thisDay, Doctor doctor,
			int endTimeOfDayHour, int endTimeOfDayMinutes,
			int minuteDurationOfVisit) {

		int hour = 0;
		int min = 0;

		while ((hour < endTimeOfDayHour)
				|| ((hour == endTimeOfDayHour) && (min < endTimeOfDayMinutes))) {

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd kk:mm");
			String startAndEndTime = formatter.format(thisDay);
			VisitTimes visitTime = new VisitTimes();
			visitTime.setDoctorID(doctor.getId());
			// visitTime.setPatientID(1);
			visitTime.setStart(startAndEndTime);
			Calendar c = Calendar.getInstance();
			c.setTime(thisDay);

			c.add(Calendar.MINUTE, minuteDurationOfVisit);
			thisDay = c.getTime();
			startAndEndTime = formatter.format(thisDay);
			visitTime.setFinish(startAndEndTime);
			visitTime.setDoctorID(doctor.getId());
			insertVistTimeWithNoPatient(visitTime);

			String[] temp = startAndEndTime.split(" ");
			String[] hourAndMinute = temp[1].split(":");

			hour = Integer.parseInt(hourAndMinute[0]);
			min = Integer.parseInt(hourAndMinute[1]);

		}
	}

	public static boolean isAnActiceDay(Date date, String daysOfWeek) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String[] days = daysOfWeek.split(",");

		for (String day : days) {
			if (dayOfWeek == Integer.parseInt(day)) {
				return true;
			}
		}
		return false;

	}

	public static void updateWithNewDuration(String daysOfWeek, Doctor doctor,
			int startTimeOfDayHour, int startTimeOfDayMinutes,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, startTimeOfDayHour);
		cal.set(Calendar.MINUTE, startTimeOfDayMinutes);
		Date today = cal.getTime();

		ArrayList<VisitTimes> visitTimes = new ArrayList<>();

		int i = 0;

		while (i <= 365) {

			if (isAnActiceDay(today, daysOfWeek)) {
				try {

					ArrayList<VisitTimes> temp = rePutVisitTimesOfADay(today,
							doctor);
					if (!temp.isEmpty()) {
						addThesePatientsToThisDay(visitTimes, today,
								minuteDurationOfVisit, endTimeOfDayHour,
								endTimeOfDayMinutes);
					} else {
						fillEachDayByNullPatients(today, doctor,
								endTimeOfDayHour, endTimeOfDayMinutes,
								minuteDurationOfVisit);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			today = giveMeNextDay(today);
			i++;
		}

	}

	public static ArrayList<VisitTimes> rePutVisitTimesOfADay(Date date,
			Doctor doctor) throws SQLException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);

		ArrayList<VisitTimes> visitTimes = findNotNullPatientsOfADay(
				dateString, doctor);

		// ArrayList<VisitTimes> = SELECT FROM VISITTIMES WHERE start LIKE
		// '%dateString%' AND patientID is not null
		deletePatientsOfAday(date);

		return visitTimes;

	}

	public static void addThesePatientsToThisDay(
			ArrayList<VisitTimes> visitTimes, Date thisDay,
			int minuteDurationOfVisit, int endTimeOfDayHour,
			int endTimeOfDayMinutes) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		String startAndEndTime = formatter.format(thisDay);
		Calendar c = Calendar.getInstance();

		Doctor tempDoctorToGetDocID = new Doctor();
		boolean itIsFirstTimeInLoop = true;
		for (VisitTimes v : visitTimes) {

			if (itIsFirstTimeInLoop) {
				tempDoctorToGetDocID.setId(v.getDoctorID());
				itIsFirstTimeInLoop = false;
			}

			v.setStart(startAndEndTime);

			c.setTime(thisDay);
			c.add(Calendar.MINUTE, minuteDurationOfVisit);
			thisDay = c.getTime();

			startAndEndTime = formatter.format(thisDay);
			v.setFinish(startAndEndTime);

			insertVistTime(v);
		}

		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE));

		Date date = c.getTime();
		fillEachDayByNullPatients(date, tempDoctorToGetDocID, endTimeOfDayHour,
				endTimeOfDayMinutes, minuteDurationOfVisit);
	}

	private static void deletePatientsOfAday(Date day) throws SQLException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(day);

		deleteAllPatientsOfADay(dateString);
	}

	public static ArrayList<VisitTimes> giveMeNearFreeTimes(
			int numberOfSuggestions, Date fromThisDay) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(fromThisDay);

		ArrayList<VisitTimes> suggestions = findNearVisitTimes(date,
				numberOfSuggestions);

		return suggestions;

	}

	public static Date giveMeNextDay(Date thisDay) {

		Calendar c = Calendar.getInstance();
		c.setTime(thisDay);
		c.add(Calendar.DATE, 1);
		Date nextDay = c.getTime();

		return nextDay;
	}

}
