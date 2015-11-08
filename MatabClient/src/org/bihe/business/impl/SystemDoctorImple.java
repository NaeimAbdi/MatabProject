package org.bihe.business.impl;

import org.bihe.bean.Doctor;
import org.bihe.business.SystemDoctorService;

public class SystemDoctorImple implements SystemDoctorService {

	private static Doctor currentDoctor;

	@Override
	public void setSystemDoctor(Doctor doctor) {

		currentDoctor = doctor;
	}

	@Override
	public Doctor getDoctor() {
		return currentDoctor;
	}

}
