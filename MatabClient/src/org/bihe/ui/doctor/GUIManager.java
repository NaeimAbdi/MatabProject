package org.bihe.ui.doctor;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import org.bihe.ui.login.LoginFrame;

public class GUIManager {

	private static DoctorMainFrame mainFrame;
	private static DoctorSearchFrame searchFrame;
	private static AddUserFrame addUserFrame;
	private static FixHolidaysFrame fixHolidaysFrame;
	private static LoginFrame loginFrame;
	private static ParametersFrame systemParametersFrame;
	private static UserListFrame userListFrame;

	private static JOptionPane ipJoptionPane;

	@SuppressWarnings("static-access")
	public static String visibleIpFrame() {
		if (ipJoptionPane == null) {
			ipJoptionPane = new JOptionPane();
		}
		return ipJoptionPane.showInputDialog("لطفا آی پی سرور را وارد کنید:");

	}

	public static void visibleUserListFrame() {
		userListFrame = new UserListFrame();
		userListFrame.setVisible(true);
	}

	public static void inVisibleUserListFrame() {
		userListFrame.setVisible(false);
	}

	public static void visibleAddUserFrame() {
		if (addUserFrame == null) {
			addUserFrame = new AddUserFrame();
		}
		addUserFrame.setVisible(true);
	}

	public static void invisibleAddUserFrame() {
		addUserFrame.setVisible(false);
	}

	public static void visibleMainFrame() {
		if (mainFrame == null) {
			mainFrame = new DoctorMainFrame();
		}
		mainFrame.setVisible(true);
	}

	public static void disposeMainFrame() {
		mainFrame.dispose();
		mainFrame = null;
	}

	public static void invisibleMainFrame() {
		mainFrame.setVisible(false);
	}

	public static void visibleSearchFrame() {
		if (searchFrame == null) {
			searchFrame = new DoctorSearchFrame();
		}
		searchFrame.setVisible(true);
	}

	public static void invisibleSearchFrame() {
		searchFrame.setVisible(false);
	}

	public static void visibleSystemParameters() {
		if (systemParametersFrame == null) {
			systemParametersFrame = new ParametersFrame();
		}
		systemParametersFrame.setVisible(true);
	}

	public static void invisibleSystemParametersFrame() {
		systemParametersFrame.setVisible(false);
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

	public static void visibleFixHolidays() {
		if (fixHolidaysFrame == null) {
			try {
				fixHolidaysFrame = new FixHolidaysFrame();
			} catch (RemoteException e) {

				e.printStackTrace();
			}

		}
		fixHolidaysFrame.setVisible(true);
	}

	public static void invisibleFixHolidays() {
		fixHolidaysFrame.setVisible(false);
	}

}
