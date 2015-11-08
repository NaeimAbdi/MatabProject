package org.bihe.ui.secretary;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DaysPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox saturdayCheckBox;
	private JCheckBox sundayCheckBox;
	private JCheckBox mondayCheckBox;
	private JCheckBox tuesdayCheckBox;
	private JCheckBox thursdayCheckBox;
	private JCheckBox wednesdayCheckBox;
	private JCheckBox fridayCheckBox;

	/**
	 * Create the panel.
	 */
	public DaysPanel() {
		setLayout(new GridLayout(0, 7, 0, 0));

		fridayCheckBox = new JCheckBox("جمعه");
		fridayCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		fridayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(fridayCheckBox);

		thursdayCheckBox = new JCheckBox("پنج شنبه");
		thursdayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(thursdayCheckBox);

		wednesdayCheckBox = new JCheckBox("چهارشنبه");
		wednesdayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(wednesdayCheckBox);

		tuesdayCheckBox = new JCheckBox("سه شنبه");
		tuesdayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(tuesdayCheckBox);

		mondayCheckBox = new JCheckBox("دوشنبه");
		mondayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(mondayCheckBox);

		sundayCheckBox = new JCheckBox("یکشنبه");
		sundayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(sundayCheckBox);

		saturdayCheckBox = new JCheckBox("شنبه");
		saturdayCheckBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		add(saturdayCheckBox);

	}

	public void setDays(String days) {
		if (days != null && !days.equals("")) {
			String[] daysOfwork = days.split(",");
			for (int i = 0; i < daysOfwork.length; i++) {
				String key = daysOfwork[i];
				switch (key) {
				case "7":
					saturdayCheckBox.setSelected(true);
					break;
				case "1":
					sundayCheckBox.setSelected(true);
					break;
				case "2":
					mondayCheckBox.setSelected(true);
					break;
				case "3":
					tuesdayCheckBox.setSelected(true);
					break;
				case "4":
					wednesdayCheckBox.setSelected(true);
					break;
				case "5":
					thursdayCheckBox.setSelected(true);
					break;

				case "6":
					fridayCheckBox.setSelected(true);
					break;

				default:
					break;
				}
			}
		}
	}

	public String daysOfWork() {
		String result = "";
		if (saturdayCheckBox.isSelected())
			result += "7,";
		if (sundayCheckBox.isSelected())
			result += "1,";
		if (mondayCheckBox.isSelected())
			result += "2,";
		if (tuesdayCheckBox.isSelected())
			result += "3,";
		if (wednesdayCheckBox.isSelected())
			result += "4,";
		if (thursdayCheckBox.isSelected())
			result += "5,";
		if (fridayCheckBox.isSelected())
			result += "7,";
		result.substring(0, result.length() - 1);
		return result;
	}

}
