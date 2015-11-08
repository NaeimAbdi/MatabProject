package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;

public class SearchFrame extends JFrame implements ActionListener,
		MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 750;
	private static final int HEIGHT = 650;

	private JPanel contentPane;
	private JTextField patientIdtextField;
	private JTextField nameField;
	private JTextField familyTextField;
	private JTextField telephoneTextField;
	private JTextField cityTextField;
	private JCheckBox patientIdCheckBox;
	private JLabel lblNewLabel;
	private JCheckBox familyCheckBox;
	private JCheckBox nameCheckBox;
	private JCheckBox telophoneCheckBox;
	private JCheckBox cityCheckBox;
	private JPanel panel;
	private JTable table;
	private JButton searchbtn;
	DefaultTableModel tmodel;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";
	private JScrollPane scrollPane;
	private JButton setVisit;
	private JLabel label;

	private ArrayList<Patient> patientList;

	
	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("جست و جو");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 0, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.rowHeights = new int[] { 25, 25, 25, 25, 0, 25, 25, 0,
				25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
				25, 25, 25, 25 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1, 1.0, 1, 1.0, 1,
				1.0, 1.0, 1.0, 1.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 1, 1, 1, 1, 0.0, 1, 1, 0.0,
				1, 1, 1, 1.0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		contentPane.setLayout(gbl_contentPane);

		lblNewLabel = new JLabel("لطفا مورد جست و جو را در وارد نمایید:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 10;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		familyTextField = new JTextField();
		familyTextField
				.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		familyTextField.setEnabled(false);
		familyTextField.setEditable(false);
		GridBagConstraints gbc_familyTextField = new GridBagConstraints();
		gbc_familyTextField.gridwidth = 3;
		gbc_familyTextField.insets = new Insets(0, 0, 5, 5);
		gbc_familyTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyTextField.gridx = 1;
		gbc_familyTextField.gridy = 4;
		contentPane.add(familyTextField, gbc_familyTextField);
		familyTextField.setColumns(10);

		patientIdtextField = new JTextField();
		patientIdtextField
				.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		patientIdtextField.addKeyListener(numberFormat());

		familyCheckBox = new JCheckBox("نام خانوادگی");
		familyCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (familyCheckBox.isSelected() == true) {
					familyTextField.setText("");
					familyTextField.setEnabled(true);
					familyTextField.setEditable(true);
				} else {
					familyTextField.setText("");
					familyTextField.setEnabled(false);
					familyTextField.setEditable(false);
				}
			}
		});
		GridBagConstraints gbc_familyCheckBox = new GridBagConstraints();
		gbc_familyCheckBox.gridwidth = 3;
		gbc_familyCheckBox.anchor = GridBagConstraints.WEST;
		gbc_familyCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_familyCheckBox.gridx = 4;
		gbc_familyCheckBox.gridy = 4;
		contentPane.add(familyCheckBox, gbc_familyCheckBox);
		patientIdtextField.setEnabled(false);
		patientIdtextField.setEditable(false);
		GridBagConstraints gbc_patientIdtextField = new GridBagConstraints();
		gbc_patientIdtextField.gridwidth = 3;
		gbc_patientIdtextField.insets = new Insets(0, 0, 5, 5);
		gbc_patientIdtextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_patientIdtextField.gridx = 12;
		gbc_patientIdtextField.gridy = 4;
		contentPane.add(patientIdtextField, gbc_patientIdtextField);
		patientIdtextField.setColumns(10);

		nameField = new JTextField();
		nameField.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		nameField.setEnabled(false);
		nameField.setEditable(false);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 4;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 7;
		gbc_nameField.gridy = 4;
		contentPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		nameCheckBox = new JCheckBox("نام");
		nameCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameCheckBox.isSelected()) {
					nameField.setText("");
					nameField.setEnabled(true);
					nameField.setEditable(true);
				} else {
					nameField.setText("");
					nameField.setEnabled(false);
					nameField.setEditable(false);
				}

			}
		});
		GridBagConstraints gbc_nameCheckBox = new GridBagConstraints();
		gbc_nameCheckBox.anchor = GridBagConstraints.WEST;
		gbc_nameCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_nameCheckBox.gridx = 11;
		gbc_nameCheckBox.gridy = 4;
		contentPane.add(nameCheckBox, gbc_nameCheckBox);

		patientIdCheckBox = new JCheckBox("شماره پرونده");
		patientIdCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (patientIdCheckBox.isSelected()) {
					patientIdtextField.setText("");
					patientIdtextField.setEnabled(true);
					patientIdtextField.setEditable(true);
				} else {
					patientIdtextField.setText("");
					patientIdtextField.setEnabled(false);
					patientIdtextField.setEditable(false);
				}
			}
		});

		GridBagConstraints gbc_patientIdCheckBox = new GridBagConstraints();
		gbc_patientIdCheckBox.anchor = GridBagConstraints.WEST;
		gbc_patientIdCheckBox.gridwidth = 3;
		gbc_patientIdCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_patientIdCheckBox.gridx = 15;
		gbc_patientIdCheckBox.gridy = 4;
		contentPane.add(patientIdCheckBox, gbc_patientIdCheckBox);

		telephoneTextField = new JTextField();
		telephoneTextField
				.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		telephoneTextField.setEnabled(false);
		telephoneTextField.setEditable(false);
		telephoneTextField.addKeyListener(numberFormat());

		label = new JLabel("(بدون صفر)");
		label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 5;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);

		GridBagConstraints gbc_telephoneTextField = new GridBagConstraints();
		gbc_telephoneTextField.gridwidth = 4;
		gbc_telephoneTextField.insets = new Insets(0, 0, 5, 5);
		gbc_telephoneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telephoneTextField.gridx = 7;
		gbc_telephoneTextField.gridy = 7;
		contentPane.add(telephoneTextField, gbc_telephoneTextField);
		telephoneTextField.setColumns(10);

		telophoneCheckBox = new JCheckBox("تلفن");
		telophoneCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (telophoneCheckBox.isSelected()) {
					telephoneTextField.setText("");
					telephoneTextField.setEnabled(true);
					telephoneTextField.setEditable(true);
				} else {
					telephoneTextField.setText("");
					telephoneTextField.setEnabled(false);
					telephoneTextField.setEditable(false);
				}

			}
		});
		GridBagConstraints gbc_telophoneCheckBox = new GridBagConstraints();
		gbc_telophoneCheckBox.anchor = GridBagConstraints.WEST;
		gbc_telophoneCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_telophoneCheckBox.gridx = 11;
		gbc_telophoneCheckBox.gridy = 7;
		contentPane.add(telophoneCheckBox, gbc_telophoneCheckBox);

		cityTextField = new JTextField();
		cityTextField
				.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cityTextField.setEnabled(false);
		cityTextField.setEditable(false);
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.gridwidth = 3;
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 12;
		gbc_cityTextField.gridy = 7;
		contentPane.add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);

		cityCheckBox = new JCheckBox("شهر");

		cityCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (cityCheckBox.isSelected()) {
					cityTextField.setText("");
					cityTextField.setEnabled(true);
					cityTextField.setEditable(true);
				} else {
					cityTextField.setText("");
					cityTextField.setEnabled(false);
					cityTextField.setEditable(false);
				}
			}
		});

		GridBagConstraints gbc_cityCheckBox = new GridBagConstraints();
		gbc_cityCheckBox.gridwidth = 3;
		gbc_cityCheckBox.anchor = GridBagConstraints.WEST;
		gbc_cityCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_cityCheckBox.gridx = 15;
		gbc_cityCheckBox.gridy = 7;
		contentPane.add(cityCheckBox, gbc_cityCheckBox);

		searchbtn = new JButton();
		searchbtn.setActionCommand("search");
		searchbtn.addActionListener(this);
		
		
		
		searchbtn.setContentAreaFilled(false);
		searchbtn.setIcon(new ImageIcon(Resource.getImage("searchTimes.png")));
		searchbtn.setBorder(BorderFactory.createEmptyBorder());
		searchbtn.setRolloverIcon(new ImageIcon(Resource.getImage("searchTimesR.png")));
		searchbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchbtn.setFocusable(false);

		GridBagConstraints gbc_searchbtn = new GridBagConstraints();
		gbc_searchbtn.anchor = GridBagConstraints.SOUTH;
		gbc_searchbtn.gridheight = 3;
		gbc_searchbtn.gridwidth = 9;
		gbc_searchbtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchbtn.gridx = 4;
		gbc_searchbtn.gridy = 8;
		contentPane.add(searchbtn, gbc_searchbtn);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 14;
		gbc_panel.gridwidth = 17;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 11;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		tmodel = new DefaultTableModel(new Object[][] {}, new String[] { "شهر",
				"تاریخ تولد", "تلفن (ثابت)", "تلفن(موبایل)", "نام خانوادگی",
				"نام", "شماره پرونده" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table = new JTable();
		table.setModel(tmodel);
		scrollPane.setViewportView(table);

		table.addMouseListener(this);

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		setVisit = new JButton();
		
		
		setVisit.setContentAreaFilled(false);
		setVisit.setIcon(new ImageIcon(Resource.getImage("addVisitTime.png")));
		setVisit.setBorder(BorderFactory.createEmptyBorder());
		setVisit.setRolloverIcon(new ImageIcon(Resource.getImage("addVisitTimeR.png")));
		setVisit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setVisit.setFocusable(false);
		setVisit.setActionCommand("setVisit");
		setVisit.addActionListener(this);
		setVisit.setEnabled(false);
		GridBagConstraints gbc_setVisit = new GridBagConstraints();
		gbc_setVisit.gridwidth = 11;
		gbc_setVisit.insets = new Insets(0, 0, 5, 5);
		gbc_setVisit.gridx = 3;
		gbc_setVisit.gridy = 26;
		contentPane.add(setVisit, gbc_setVisit);

	}

	private Patient searchPatient() {

		String name = "";
		String family = "";
		String telephone = "";
		String mobile = "";
		String city = "";

		if (!nameField.getText().isEmpty() && nameCheckBox.isEnabled()) {

			name = nameField.getText();
		}
		if (!familyTextField.getText().isEmpty() && familyCheckBox.isEnabled()) {
			family = familyTextField.getText();
		}
		if (!telephoneTextField.getText().isEmpty()
				&& telophoneCheckBox.isEnabled()) {

			telephone = telephoneTextField.getText();
			mobile = telephoneTextField.getText();

		}
		if (!cityTextField.getText().isEmpty() && cityCheckBox.isEnabled()) {

			city = cityTextField.getText();

		}

		Patient p = new Patient(0, name, family, mobile, telephone, "", city);

		return p;
	}

	private PatientFolder searchFolder() {

		String uniqNum = "";

		if (!patientIdtextField.getText().isEmpty()
				&& patientIdCheckBox.isSelected()) {
			uniqNum = patientIdtextField.getText();

		}

		PatientFolder patinFolder = new PatientFolder();
		patinFolder.setFolderUniqueNumber(uniqNum);
		patinFolder.setDoctorID(new BusinessServiceFactory()
				.createSystemDoctor().getDoctor().getId());

		return patinFolder;
	}

	private KeyListener numberFormat() {
		KeyListener key = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		};
		return key;
	}

	private void searchAction() {
		tmodel = new DefaultTableModel(new Object[][] {}, new String[] { "شهر",
				"تاریخ تولد", "تلفن (ثابت)", "تلفن(موبایل)", "نام خانوادگی",
				"نام", "شماره پرونده" }) {
			/**
			 * 
			 */

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(tmodel);
		scrollPane.setViewportView(table);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		try {
			patientList = new ArrayList<>();
			patientList = new BusinessServiceFactory().createPatientService()
					.search(searchPatient(), searchFolder());

			Doctor doctor = new BusinessServiceFactory().createSystemDoctor()
					.getDoctor();

			if (!patientList.isEmpty()) {
				for (int i = 0; i < patientList.size(); i++) {

					PatientFolder patFolder = new PatientFolder();
					patFolder.setDoctorID(doctor.getId());
					patFolder.setPatientID(patientList.get(i).getId());

					PatientFolder searchPatFolder = new BusinessServiceFactory()
							.createPatientFolderService().getPatientFolder(
									patFolder);

					String[] strTable = { patientList.get(i).getCity(),
							patientList.get(i).getBirthday(),
							patientList.get(i).getTelephoneNumber(),
							patientList.get(i).getMobileNumber(),
							patientList.get(i).getFamily(),
							patientList.get(i).getName(),
							searchPatFolder.getFolderUniqueNumber() };
					tmodel.addRow(strTable);
				}

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private static boolean validate(String txt, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

	private void checkSearchAction() {
		if (!patientIdtextField.getText().isEmpty()
				|| !nameField.getText().isEmpty()
				|| !familyTextField.getText().isEmpty()
				|| !telephoneTextField.getText().isEmpty()
				|| !cityTextField.getText().isEmpty()) {
			if (!telephoneTextField.getText().isEmpty()) {
				if (validate(telephoneTextField.getText(), phoneNumberPattern)) {
					searchAction();
				} else {
					ShowMessage.show("شماره موبایل وارد شده صحیح نمی باشد", 1,
							SearchFrame.this);
				}
			} else if (telephoneTextField.getText().isEmpty()) {
				searchAction();
			}
		} else {
			ShowMessage.show("هیچ موردی برای جست و جو وارد نشده است", 1,
					SearchFrame.this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "search":
			checkSearchAction();
			break;
		case "setVisit":
			setVisitAction();
			break;

		default:
			break;
		}
	}

	private void setVisitAction() {
		if (table.getSelectedRow() != -1) {
			AddVisitTimeFrame addvi = new AddVisitTimeFrame();
			Patient p = patientList.get(table.getSelectedRow());
			addvi.setPatient(p);
			addvi.fillFields(p);
			addvi.setVisible(true);
		} else {

			ShowMessage.show("هیچ موردی انتخاب نشده است.",
					ShowMessage.ERROR_ID, SearchFrame.this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {

			setVisit.setEnabled(true);

		}
		if (e.getClickCount() == 2) {
			JTable target = (JTable) e.getSource();
			int row = target.getSelectedRow();

			try {
				PatientProfileFrame patFrame = new PatientProfileFrame();
				PatientFolder patientFolder = new PatientFolder();

				Patient getP = patientList.get(row);

				patientFolder.setPatientID(getP.getId());
				patientFolder.setDoctorID(new BusinessServiceFactory()
						.createSystemDoctor().getDoctor().getId());

				PatientFolder finalP = new BusinessServiceFactory()
						.createPatientFolderService().getPatientFolder(
								patientFolder);
				patFrame.setPatient(getP, finalP);

				patFrame.setVisible(true);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
