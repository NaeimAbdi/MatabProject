package org.bihe.ui.secretary;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowMessage {

	private static JOptionPane joptionpane;
	public static final String ERROR_TITLE = "خطا";
	public static final String INFORMATION_TITLE = "اطلاعات";
	public static final String WARNING_TITLE = "هشدار";
	public static final int ERROR_ID = 1;
	public static final int INFORMATION_ID = 2;
	public static final int WARNING_ID = 3;

	@SuppressWarnings("static-access")
	public static void show(String text, int type, JFrame frame) {

		if (type == ERROR_ID) {

			joptionpane.showMessageDialog(frame, text, ERROR_TITLE,
					joptionpane.ERROR_MESSAGE);

		} else if (type == INFORMATION_ID) {

			joptionpane.showMessageDialog(frame, text, INFORMATION_TITLE,
					joptionpane.INFORMATION_MESSAGE);

		} else if (type == WARNING_ID) {

			joptionpane.showMessageDialog(frame, text, WARNING_TITLE,
					joptionpane.WARNING_MESSAGE);

		}

	}

}
