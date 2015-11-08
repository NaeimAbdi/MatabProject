package org.bihe.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import org.bihe.main.Main;

public class Service {

	private static final String IP = "rmi://" + Main.getServerIp() + ":1099/";

	private static String IDOCTORCOMMENT = "IDOCTORCOMMENT";
	private static String IFIXHOLIDAYS = "IFIXHOLIDAYS";
	private static String IHOLIDAYS = "IHOLIDAYS";
	private static String IPATIENT = "IPATIENT";
	private static String IPATIENTDOSSIER = "IPATIENTDOSSIER";
	private static String IPATIENTFOLDER = "IPATIENTFOLDER";
	private static String IUSER = "IUSER";
	private static String IVISITTIMES = "IVISITTIMES";
	private static String IWAITINGMESSAGES = "IWAITINGMESSAGES";
	private static String ISYSTEMPARAMETERS = "ISYSTEMPARAMETERS";
	private static String ISECETARY = "ISECETARY";
	private static String IPATIENTOFDOCTOR = "IPATIENTOFDOCTOR";
	private static String IDOCTOR = "IDOCTOR";
	private static String ISECRETARYOFDOCTORS = "ISECRETARYOFDOCTORS";
	private static String IGENERATEDMESSAGES = "IGENERATEDMESSAGES";

	public IUser I_USER;
	public IDoctorComment I_DOCTOR_COMMENT;
	public IFixHolidays I_FIX_HOLIDAY;
	public IHolidays I_HOLIDAYS;
	public IPatient I_PATIENT;
	public IPatientDossier I_PATIENT_DOSSIER;
	public IPatientFolder I_PATIENT_FOLDER;
	public ISystemParameters I_SYSTEM_PARAMETERS;
	public IVisitTimes I_VISIT_TIMES;
	public IWaitingMessages I_WAITING_MESSAGES;
	public ISecretary I_SECETARY;
	public IPatientOfDoctors I_PATIENT_OF_DOCTOR;
	public IDoctor I_DOCTOR;
	public ISecretaryOfDoctors I_Secretary_Of_Doctors;
	public IGeneratedMessages I_GENERATED_MESSAGES;

	public Service() {
		Properties p = new Properties();
		p.setProperty("KEY", "VALUE");
		// A file near to project
		// p.load(arg0);
		// .properties
		// p.store(out, comments);
		try {

			I_USER = (IUser) Naming.lookup(IP + IUSER);
			// UnicastRemoteObject.exportObject(I_USER);

			I_DOCTOR_COMMENT = (IDoctorComment) Naming.lookup(IP
					+ IDOCTORCOMMENT);

			I_FIX_HOLIDAY = (IFixHolidays) Naming.lookup(IP + IFIXHOLIDAYS);

			I_HOLIDAYS = (IHolidays) Naming.lookup(IP + IHOLIDAYS);

			I_PATIENT = (IPatient) Naming.lookup(IP + IPATIENT);

			I_PATIENT_DOSSIER = (IPatientDossier) Naming.lookup(IP
					+ IPATIENTDOSSIER);

			I_PATIENT_FOLDER = (IPatientFolder) Naming.lookup(IP
					+ IPATIENTFOLDER);

			I_SYSTEM_PARAMETERS = (ISystemParameters) Naming.lookup(IP
					+ ISYSTEMPARAMETERS);
			I_VISIT_TIMES = (IVisitTimes) Naming.lookup(IP + IVISITTIMES);

			I_WAITING_MESSAGES = (IWaitingMessages) Naming.lookup(IP
					+ IWAITINGMESSAGES);

			I_SECETARY = (ISecretary) Naming.lookup(IP + ISECETARY);

			I_PATIENT_OF_DOCTOR = (IPatientOfDoctors) Naming.lookup(IP
					+ IPATIENTOFDOCTOR);

			I_Secretary_Of_Doctors = (ISecretaryOfDoctors) Naming.lookup(IP
					+ ISECRETARYOFDOCTORS);

			I_GENERATED_MESSAGES = (IGeneratedMessages) Naming.lookup(IP
					+ IGENERATEDMESSAGES);

			I_DOCTOR = (IDoctor) Naming.lookup(IP + IDOCTOR);

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (RemoteException e) {

			e.printStackTrace();
		} catch (NotBoundException e) {

			e.printStackTrace();
		}

	}

}
