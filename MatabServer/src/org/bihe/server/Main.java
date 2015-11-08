package org.bihe.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
	private static IDoctorComment iDoctorComment;
	private static IFixHolidays iFixHolidays;
	private static IHolidays iHolidays;
	private static IPatient iPatient;
	private static IPatientDossier iPatientDossier;
	private static IPatientFolder iPatientFolder;
	private static IUser iUser;
	private static IVisitTimes iVisitTimes;
	private static IWaitingMessages iWaitingMessages;
	private static ISystemParameters iSystemParameters;
	private static IDoctor iDoctor;
	private static ISecretary iSecretary;
	private static IPatientOfDoctors iPatientOfDoctor;
	private static ISecretaryOfDoctors iSecretaryOfDoctors;
	private static IGeneratedMessages iGeneratedMessages;

	public static void main(String[] args) {
		try {
			start();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

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
	private static String IDOCTOR = "IDOCTOR";
	private static String ISECETARY = "ISECETARY";
	private static String IPATIENTOFDOCTOR = "IPATIENTOFDOCTOR";
	private static String ISECRETARYOFDOCTORS = "ISECRETARYOFDOCTORS";
	private static String IGENERATEDMESSAGES = "IGENERATEDMESSAGES";

	private static void makeServerReady() throws RemoteException {
		iDoctorComment = new DoctorCommentImple();
		iFixHolidays = new FixHolidaysImple();
		iHolidays = new HolidaysImple();
		iPatient = new PatientImple();
		iPatientDossier = new PatientDossierImple();
		iPatientFolder = new PatientFolderImple();
		iUser = new UserImple();
		iVisitTimes = new VisitTimesImple();
		iWaitingMessages = new WaitingMessagesImple();
		iSystemParameters = new SystemParametersImple();
		iDoctor = new DoctorImpl();
		iSecretary = new SecretaryImpl();
		iPatientOfDoctor = new PatientOfDoctorsImpl();
		iSecretaryOfDoctors = new SecretaryOfDoctorsImpl();
		iGeneratedMessages = new GeneratedMessagesImpl();

	}

	private static void binding() throws RemoteException, MalformedURLException {
		Naming.rebind(IDOCTORCOMMENT, iDoctorComment);
		Naming.rebind(IFIXHOLIDAYS, iFixHolidays);
		Naming.rebind(IHOLIDAYS, iHolidays);
		Naming.rebind(IPATIENT, iPatient);
		Naming.rebind(IPATIENTDOSSIER, iPatientDossier);
		Naming.rebind(IPATIENTFOLDER, iPatientFolder);
		Naming.rebind(IUSER, iUser);
		Naming.rebind(IVISITTIMES, iVisitTimes);
		Naming.rebind(IWAITINGMESSAGES, iWaitingMessages);
		Naming.rebind(ISYSTEMPARAMETERS, iSystemParameters);
		Naming.rebind(IDOCTOR, iDoctor);
		Naming.rebind(ISECETARY, iSecretary);
		Naming.rebind(IPATIENTOFDOCTOR, iPatientOfDoctor);
		Naming.rebind(ISECRETARYOFDOCTORS, iSecretaryOfDoctors);
		Naming.rebind(IGENERATEDMESSAGES, iGeneratedMessages);

	}

	private static void start() throws RemoteException, MalformedURLException {
		makeServerReady();
		LocateRegistry.createRegistry(1099);
		binding();

	}
}
