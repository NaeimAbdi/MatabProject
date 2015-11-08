package org.bihe.business.factory;

import org.bihe.business.DoctorCommentService;
import org.bihe.business.DoctorService;
import org.bihe.business.FixHolidaysService;
import org.bihe.business.GeneratedMessagesService;
import org.bihe.business.HolidaysService;
import org.bihe.business.PatientDossierService;
import org.bihe.business.PatientFolderService;
import org.bihe.business.PatientOfDoctorsService;
import org.bihe.business.PatientService;
import org.bihe.business.SecretaryOfDoctorsService;
import org.bihe.business.SecretaryService;
import org.bihe.business.SystemParametersService;
import org.bihe.business.UserService;
import org.bihe.business.VisitTimesService;
import org.bihe.business.WaitingMessagesService;
import org.bihe.business.impl.DoctorCommentServiceImpl;
import org.bihe.business.impl.DoctorServiceImpl;
import org.bihe.business.impl.FixHolidaysServiceImpl;
import org.bihe.business.impl.GeneratedMessagesServiceImpl;
import org.bihe.business.impl.HolidaysServiceImpl;
import org.bihe.business.impl.PatientDossierServiceImpl;
import org.bihe.business.impl.PatientFolderServiceImpl;
import org.bihe.business.impl.PatientOfDoctorsServiceImpl;
import org.bihe.business.impl.PatientServiceImpl;
import org.bihe.business.impl.SecretaryOfDoctorsServiceImpl;
import org.bihe.business.impl.SecretaryServiceImpl;
import org.bihe.business.impl.SystemDoctorImple;
import org.bihe.business.impl.SystemParametersServiceImpl;
import org.bihe.business.impl.UserServiceImpl;
import org.bihe.business.impl.VisitTimesServiceImpl;
import org.bihe.server.Service;

public class BusinessServiceFactory {
	private static Service service;

	private static Service getService() {
		if (service == null) {
			service = new Service();
		}
		return service;
	}

	public DoctorCommentService createDoctorCommentService() {
		DoctorCommentService temp = new DoctorCommentServiceImpl(getService());
		return temp;

	}

	public FixHolidaysService createFixHolidaysService() {

		return new FixHolidaysServiceImpl(getService());

	}

	public HolidaysService createHolidaysService() {

		return new HolidaysServiceImpl(getService());

	}

	public PatientDossierService createPatientDossierService() {

		return new PatientDossierServiceImpl(service);

	}

	public PatientFolderService createPatientFolderService() {

		return new PatientFolderServiceImpl(getService());

	}

	public PatientService createPatientService() {

		return new PatientServiceImpl(getService());

	}

	public SystemParametersService createSystemParametersService() {

		return new SystemParametersServiceImpl(getService());

	}

	public UserService createUserService() {

		return new UserServiceImpl(getService());

	}

	public VisitTimesService createVisitTimesService() {

		return new VisitTimesServiceImpl(getService());

	}

	public WaitingMessagesService createWaitingMessagesService() {

		return new org.bihe.business.impl.WatingMessagesServiceImpl(
				getService());

	}

	public SecretaryService createSecretaryService() {
		return new SecretaryServiceImpl(getService());
	}

	public PatientOfDoctorsService createPatientOfDoctorsService() {
		return new PatientOfDoctorsServiceImpl(getService());

	}

	public SystemDoctorImple createSystemDoctor() {
		return new SystemDoctorImple();
	}

	public DoctorService createDoctorService() {
		return new DoctorServiceImpl(getService());
	}

	public SecretaryOfDoctorsService createSecretaryOfDoctorsService() {
		return new SecretaryOfDoctorsServiceImpl(getService());
	}

	public GeneratedMessagesService createGeneratedMessagesService() {
		return new GeneratedMessagesServiceImpl(getService());
	}

}
