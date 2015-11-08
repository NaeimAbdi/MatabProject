package org.bihe.util;

import java.util.ArrayList;
import java.util.Calendar;

import org.bihe.bean.Doctor;
import org.bihe.bean.Patient;
import org.bihe.bean.VisitTimes;
import org.bihe.bean.WaitingMessages;
import org.bihe.dao.PatientDAO;
import org.bihe.dao.VisitTimeDAO;
import org.bihe.dao.WaitingMessagesDAO;
import org.bihe.fileIO.VisitTimesMessageController;

public class GenerateWaitingMessages {

	private String txt = "زمان ملاقات شما فردا می باشد.";

	public GenerateWaitingMessages(Doctor doctor) {
		super();
		getWaitingMsg(doctor);
	}

	private boolean getWaitingMsg(Doctor doctor) {

		boolean result = false;

		java.util.Date utilDate = new java.util.Date();
		Calendar c = Calendar.getInstance();
		c.setTime(utilDate);
		c.add(Calendar.DATE, 1);
		utilDate = c.getTime();

		ArrayList<VisitTimes> visitList = VisitTimeDAO
				.getVisittimesOfDayByDoctor(utilDate, doctor);
		VisitTimesMessageController visitSavedList = new VisitTimesMessageController();
		visitSavedList.loadVisitTimesFile();

		ArrayList<VisitTimes> savedTimes = visitSavedList.getVisitTimesList();

		if (!visitList.isEmpty() && !savedTimes.equals(visitList)) {
			ArrayList<Patient> patList = new ArrayList<Patient>();
			WaitingMessages WM = new WaitingMessages();
			int id;
			Patient pat = new Patient();
			for (int i = 0; i < visitList.size(); i++) {

				id = visitList.get(i).getPatientID();
				pat.setId(id);
				patList.add(PatientDAO.getPatientByID(pat));

			}

			for (int j = 0; j < patList.size(); j++) {
				WM.setTelephoneNumber(patList.get(j).getMobileNumber());
				WM.setMessage(generateMessageTxt(patList.get(j)));

				WaitingMessagesDAO.insertMessage(WM);
			}
			result = true;

			visitSavedList.setVisitTimesList(visitList);
			visitSavedList.updateVisitTimesFile();

		} else {
			result = false;
		}

		return result;

	}

	private String generateMessageTxt(Patient patient) {

		String[] txtArray = txt.split(" ");
		String result = "";

		for (int f = 0; f < txtArray.length; f++) {
			result += txtArray[f] + "+";

		}
		String textRes = result.substring(0, result.length() - 1);

		String[] nameArray = patient.getFamily().split(" ");
		String nameResult = "";
		for (int i = 0; i < nameArray.length; i++) {
			nameResult += nameArray[i] + "+";
		}

		String finalNameRes = nameResult.substring(0, nameResult.length() - 1);

		String finlaRes = "آقای/خانم" + "+" + finalNameRes + textRes;

		return finlaRes;

	}

}
