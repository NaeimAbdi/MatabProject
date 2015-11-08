package org.bihe.ui.secretary;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import org.bihe.ui.login.LoginFrame;

public class GUIManager {

	private static SecretaryMainFrame mainFrame;
	private static ParametersFrame systemParameterFrame;
	private static SearchFrame searchFrame;
	private static LoginFrame loginFrame;
	private static SelectDoctorFrame selectDoctorFrame;
	private static DoctorHolidaysFrame doctorHolidaysFrame;
	private static FixHolidaysFrame fixHolidaysFrame;

	private static SendSMSFrame sendSMSFrame;

	private static JOptionPane ipJoptionPane;

	@SuppressWarnings("static-access")
	public static String visibleIpFrame() {
		if (ipJoptionPane == null) {
			ipJoptionPane = new JOptionPane();
		}
		return ipJoptionPane.showInputDialog("لطفا آی پی سرور را وارد کنید:");

	}

	public static void invisibleSendSMSFrame() {
		sendSMSFrame.setVisible(false);
	}

	public static void visibleSendSMSFrame() {
		sendSMSFrame = new SendSMSFrame();
		sendSMSFrame.setVisible(true);
	}

	public static void invisibleIPpFrame() {
		ipJoptionPane.setVisible(false);
	}

	public static void visibleMainFrame() {
		mainFrame = new SecretaryMainFrame();
		mainFrame.setVisible(true);
	}

	public static void invisibleMainFrame() {
		mainFrame.setVisible(false);
	}

	public static void visibleSystemParameters() {
		if (systemParameterFrame == null) {
			systemParameterFrame = new ParametersFrame();

		}
		systemParameterFrame.setVisible(true);
	}

	public static void invisibleSystemParameters() {
		systemParameterFrame.setVisible(false);
	}

	public static void visibleSearchFrame() {
		if (searchFrame == null) {
			searchFrame = new SearchFrame();

		}
		searchFrame.setVisible(true);
	}

	public static void invisibleSearchFrame() {
		searchFrame.setVisible(false);
	}

	public static void visibleLoginFrame() {

		if (loginFrame == null) {
			loginFrame = new LoginFrame();
		}
		loginFrame.setVisible(true);
	}

	public static void invisibleLoginFrame() {
		loginFrame.setVisible(false);
	}

	public static void visibleSelectDoctorFrame() {

		if (selectDoctorFrame == null) {
			selectDoctorFrame = new SelectDoctorFrame();

		}
		selectDoctorFrame.setVisible(true);
	}

	public static void invisibleSelectDoctorFrame() {
		selectDoctorFrame.setVisible(false);
	}

	public static void visibleDoctorHolidaysFrame() {

		if (doctorHolidaysFrame == null) {
			doctorHolidaysFrame = new DoctorHolidaysFrame();
		}
		doctorHolidaysFrame.setVisible(true);
	}

	public static void invisibleDoctorHolidaysFrame() {
		doctorHolidaysFrame.setVisible(false);
	}

	public static void visibleFixHolidaysFrame() throws RemoteException {

		if (fixHolidaysFrame == null) {
			fixHolidaysFrame = new FixHolidaysFrame();
		}
		fixHolidaysFrame.setVisible(true);
	}

	public static SelectDoctorFrame getselectedDoctroFrame() {
		return selectDoctorFrame;
	}

}
