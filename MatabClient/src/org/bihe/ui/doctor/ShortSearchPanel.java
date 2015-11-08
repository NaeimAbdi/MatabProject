package org.bihe.ui.doctor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ShortSearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField shortSearchField;

	/**
	 * Create the panel.
	 */
	public ShortSearchPanel() {
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 30, 30, 30, 30, 30, 30, 30,
				30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 30, 30,
				30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
				30, 30, 30, 30, 30 };
		gridBagLayout.rowHeights = new int[] { 25, 25 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0 };
		setLayout(gridBagLayout);

		JButton btnAddVisitTime = new JButton("Add Visi time");
		btnAddVisitTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnAddVisitTime = new GridBagConstraints();
		gbc_btnAddVisitTime.gridwidth = 4;
		gbc_btnAddVisitTime.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddVisitTime.gridx = 19;
		gbc_btnAddVisitTime.gridy = 0;
		add(btnAddVisitTime, gbc_btnAddVisitTime);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridwidth = 5;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 26;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);

		JButton btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridwidth = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 31;
		gbc_btnSearch.gridy = 0;
		add(btnSearch, gbc_btnSearch);

		shortSearchField = new JTextField();
		GridBagConstraints gbc_shortSearchField = new GridBagConstraints();
		gbc_shortSearchField.insets = new Insets(0, 0, 5, 5);
		gbc_shortSearchField.gridwidth = 8;
		gbc_shortSearchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_shortSearchField.gridx = 33;
		gbc_shortSearchField.gridy = 0;
		add(shortSearchField, gbc_shortSearchField);
		shortSearchField.setColumns(10);

		JLabel label = new JLabel("جستجو");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 41;
		gbc_label.gridy = 0;
		add(label, gbc_label);

		JLabel label_1 = new JLabel("123456");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 25;
		gbc_label_1.gridy = 1;
		add(label_1, gbc_label_1);

		JLabel lblNumber = new JLabel("Number");
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.gridwidth = 3;
		gbc_lblNumber.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumber.gridx = 27;
		gbc_lblNumber.gridy = 1;
		add(lblNumber, gbc_lblNumber);

		JLabel patientFound = new JLabel("New label");
		GridBagConstraints gbc_patientFound = new GridBagConstraints();
		gbc_patientFound.insets = new Insets(0, 0, 0, 5);
		gbc_patientFound.gridwidth = 8;
		gbc_patientFound.gridx = 33;
		gbc_patientFound.gridy = 1;
		add(patientFound, gbc_patientFound);

	}

}
