package org.bihe.ui.secretary;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;

public class PatientProfileFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */

	// AFTER CALLING CONSTRUCTOR THE PATIENT FIELD MUST BE SET

	private static final long serialVersionUID = 1L;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField docNoField;
	private JTextField cityField;
	private JTextField phoneNoField;
	private JTextField homeNoField;
	private JTextField familyField;
	private JTable table;
	private DefaultTableModel tmodel;
	private JButton deleteBtn;
	private JButton editBtn;
	private JButton addVisitTimeBtn;

	private JButton btnAddPatientDossier;
	private JLabel lblNewLabel_2;

	private PatientFolder patientFolder;
	private Patient patient;
	private ArrayList<PatientDossier> patientDossiers;



	/**
	 * Create the frame.
	 * 
	 * @throws RemoteException
	 */
	public PatientProfileFrame() throws RemoteException {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("پرونده ی مراجعه کننده");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 650);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.columnWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_contentPane.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 1 };
		contentPane.setLayout(gbl_contentPane);

		docNoField = new JTextField();
		GridBagConstraints gbc_docNoField = new GridBagConstraints();
		gbc_docNoField.gridwidth = 3;
		gbc_docNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_docNoField.insets = new Insets(0, 0, 5, 5);
		gbc_docNoField.gridx = 2;
		gbc_docNoField.gridy = 1;
		contentPane.add(docNoField, gbc_docNoField);
		docNoField.setColumns(10);

		JLabel lblNewLabel = new JLabel("شماره پرونده");
		lblNewLabel.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		familyField = new JTextField();
		familyField.setColumns(10);
		GridBagConstraints gbc_familyField = new GridBagConstraints();
		gbc_familyField.gridwidth = 3;
		gbc_familyField.insets = new Insets(0, 0, 5, 5);
		gbc_familyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyField.gridx = 7;
		gbc_familyField.gridy = 1;
		contentPane.add(familyField, gbc_familyField);

		JLabel label_3 = new JLabel("نام خانوادگی");
		label_3.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.gridwidth = 2;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 10;
		gbc_label_3.gridy = 1;
		contentPane.add(label_3, gbc_label_3);

		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridwidth = 4;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 12;
		gbc_nameField.gridy = 1;
		contentPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("نام");
		lblNewLabel_1.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 16;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		// JButton btnNewButton_2 = new JButton("New button");
		JScrollPane scrollPane = new JScrollPane();
		if (this.patient != null) {
			setPatientDossiersByService();
		}
		String[] s = { "نمایش عکس", "تاریخ", "توضیحات" };
		String[][] data = new String[0][3];
		if (patientDossiers != null && !patientDossiers.isEmpty()) {
			data = new String[patientDossiers.size()][3];
			for (int i = 0; i < patientDossiers.size(); i++) {
				PatientDossier pd = patientDossiers.get(i);
				data[i][0] = "نمایش";
				data[i][1] = pd.getRecordDate();
				data[i][2] = pd.getImagePath();
			}
		}
		tmodel = new DefaultTableModel(data, s);
		table = new JTable(tmodel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// Returning the Class of each column will allow different
			// renderers to be used based on Class
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		// ButtonColumn buttonColumn1 = new ButtonColumn(table, 0);

		btnAddPatientDossier = new JButton();
		
		
		btnAddPatientDossier.setContentAreaFilled(false);
		btnAddPatientDossier.setIcon(new ImageIcon(Resource.getImage("addDossier.png")));
		btnAddPatientDossier.setBorder(BorderFactory.createEmptyBorder());
		btnAddPatientDossier.setRolloverIcon(new ImageIcon(Resource.getImage("addDossierR.png")));
		btnAddPatientDossier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAddPatientDossier.setFocusable(false);
		
		btnAddPatientDossier.setActionCommand("addPatientDossier");
		btnAddPatientDossier.addActionListener(this);

		lblNewLabel_2 = new JLabel("(بدون صفر)");
		lblNewLabel_2.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		phoneNoField = new JTextField();
		phoneNoField.setEditable(false);
		GridBagConstraints gbc_phoneNoField = new GridBagConstraints();
		gbc_phoneNoField.gridwidth = 3;
		gbc_phoneNoField.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNoField.gridx = 3;
		gbc_phoneNoField.gridy = 4;
		contentPane.add(phoneNoField, gbc_phoneNoField);
		phoneNoField.setColumns(10);
		phoneNoField.addKeyListener(numberFormat());

		JLabel label_1 = new JLabel("شماره همراه");
		label_1.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 6;
		gbc_label_1.gridy = 4;
		contentPane.add(label_1, gbc_label_1);

		cityField = new JTextField();
		cityField.setEditable(false);
		GridBagConstraints gbc_cityField = new GridBagConstraints();
		gbc_cityField.gridwidth = 3;
		gbc_cityField.insets = new Insets(0, 0, 5, 5);
		gbc_cityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityField.gridx = 8;
		gbc_cityField.gridy = 4;
		contentPane.add(cityField, gbc_cityField);
		cityField.setColumns(10);

		JLabel label = new JLabel("شهر");
		label.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 11;
		gbc_label.gridy = 4;
		contentPane.add(label, gbc_label);

		homeNoField = new JTextField();
		homeNoField.setEditable(false);
		GridBagConstraints gbc_homeNoField = new GridBagConstraints();
		gbc_homeNoField.gridwidth = 4;
		gbc_homeNoField.insets = new Insets(0, 0, 5, 5);
		gbc_homeNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeNoField.gridx = 12;
		gbc_homeNoField.gridy = 4;
		contentPane.add(homeNoField, gbc_homeNoField);
		homeNoField.setColumns(10);
		homeNoField.addKeyListener(numberFormat());

		JLabel label_2 = new JLabel("شماره ثابت");
		label_2.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.gridwidth = 2;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 16;
		gbc_label_2.gridy = 4;
		contentPane.add(label_2, gbc_label_2);
		GridBagConstraints gbc_btnAddPatientDossier = new GridBagConstraints();
		gbc_btnAddPatientDossier.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddPatientDossier.gridwidth = 3;
		gbc_btnAddPatientDossier.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPatientDossier.gridx = 3;
		gbc_btnAddPatientDossier.gridy = 6;
		contentPane.add(btnAddPatientDossier, gbc_btnAddPatientDossier);

		deleteBtn = new JButton();
		
		
		

		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setIcon(new ImageIcon(Resource.getImage("delete.png")));
		deleteBtn.setBorder(BorderFactory.createEmptyBorder());
		deleteBtn.setRolloverIcon(new ImageIcon(Resource.getImage("deleteR.png")));
		deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteBtn.setFocusable(false);
		
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteBtn.gridwidth = 3;
		gbc_deleteBtn.insets = new Insets(0, 0, 5, 5);
		gbc_deleteBtn.gridx = 6;
		gbc_deleteBtn.gridy = 6;
		contentPane.add(deleteBtn, gbc_deleteBtn);

		deleteBtn.addActionListener(this);
		deleteBtn.setActionCommand("delete");

		addVisitTimeBtn = new JButton();
		
		
		addVisitTimeBtn.setContentAreaFilled(false);
		addVisitTimeBtn.setIcon(new ImageIcon(Resource.getImage("addVisitTime.png")));
		addVisitTimeBtn.setBorder(BorderFactory.createEmptyBorder());
		addVisitTimeBtn.setRolloverIcon(new ImageIcon(Resource.getImage("addVisitTimeR.png")));
		addVisitTimeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addVisitTimeBtn.setFocusable(false);
		
		GridBagConstraints gbc_addVisitTimeBtn = new GridBagConstraints();
		gbc_addVisitTimeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addVisitTimeBtn.gridwidth = 3;
		gbc_addVisitTimeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addVisitTimeBtn.gridx = 10;
		gbc_addVisitTimeBtn.gridy = 6;
		contentPane.add(addVisitTimeBtn, gbc_addVisitTimeBtn);

		addVisitTimeBtn.addActionListener(this);
		addVisitTimeBtn.setActionCommand("addVisit");

		editBtn = new JButton();
		
		editBtn.setContentAreaFilled(false);
		editBtn.setIcon(new ImageIcon(Resource.getImage("edit.png")));
		editBtn.setBorder(BorderFactory.createEmptyBorder());
		editBtn.setRolloverIcon(new ImageIcon(Resource.getImage("editR.png")));
		editBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editBtn.setFocusable(false);
		
		editBtn.addActionListener(this);
		GridBagConstraints gbc_editBtn = new GridBagConstraints();
		gbc_editBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_editBtn.gridwidth = 3;
		gbc_editBtn.insets = new Insets(0, 0, 5, 5);
		gbc_editBtn.gridx = 13;
		gbc_editBtn.gridy = 6;
		contentPane.add(editBtn, gbc_editBtn);

		// table.setModel(tmodel);
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridheight = 6;
		gbc_btnNewButton_2.gridwidth = 17;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 7;
		contentPane.add(scrollPane, gbc_btnNewButton_2);
		makeFiledsNotEditable();

	}

	private void setPatientDossiersByService() {
		try {
			this.patientDossiers = new ArrayList<>();
			Doctor d = new BusinessServiceFactory().createSystemDoctor()
					.getDoctor();
			PatientFolder temp = new PatientFolder();
			temp.setPatientID(this.patient.getId());
			temp.setDoctorID(d.getId());
			PatientFolder pf = new BusinessServiceFactory()
					.createPatientFolderService().getPatientFolder(temp);
			this.patientDossiers = new BusinessServiceFactory()
					.createPatientDossierService().getPatientDossier(pf);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
	}

	class ButtonColumn extends AbstractCellEditor implements TableCellRenderer,
			TableCellEditor, ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * 
		 */

		JTable table;
		JButton renderButton;
		JButton editButton;
		String text;
		PatientDossier pd;

		// int row;

		public ButtonColumn(JTable table, int column) {
			super();
			this.table = table;
			renderButton = new JButton();
			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			// pd= new PatientDossier(recordDate, description, imagePath,
			// patientFolderID)
			if (row > -1) {
				pd = patientDossiers.get(row);
			}
			if (hasFocus) {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager
						.getColor("Button.background"));
			} else if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			} else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager
						.getColor("Button.background"));
			}

			renderButton.setText((value == null) ? "" : value.toString());
			return renderButton;
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "" : value.toString();
			editButton.setText(text);
			editBtn.setActionCommand("show");
			return editButton;
		}

		public Object getCellEditorValue() {
			return text;
		}

		public void actionPerformed(ActionEvent e) {
			fireEditingStopped();
			JButton j = (JButton) e.getSource();
			String name = j.getActionCommand();
			switch (name) {
			case "نمایش":
				ShowMessage.show(pd.getImagePath(), ShowMessage.INFORMATION_ID,
						PatientProfileFrame.this);
				break;

			default:
				System.out.println(j.getText());
				System.out.println(j.getActionCommand());

				break;
			}
		}
	}

	public void fillFields() throws RemoteException {

		nameField.setText(patient.getName());
		familyField.setText(patient.getFamily());
		docNoField.setText(patientFolder.getFolderUniqueNumber());

		cityField.setText(patient.getCity());
		homeNoField.setText(patient.getTelephoneNumber());
		phoneNoField.setText(patient.getMobileNumber());

		fillTable();
	}

	@SuppressWarnings("unused")
	public void fillTable() throws RemoteException {
		setPatientDossiersByService();
		Doctor d = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		PatientFolder temp = new PatientFolder();
		temp.setPatientID(this.patient.getId());
		temp.setDoctorID(d.getId());
		PatientFolder pf = new BusinessServiceFactory()
				.createPatientFolderService().getPatientFolder(temp);
		this.patientDossiers = new BusinessServiceFactory()
				.createPatientDossierService().getPatientDossier(pf);
		String[] s = { "نمایش عکس", "تاریخ", "توضیحات" };
		String[][] data = new String[0][3];
		if (patientDossiers != null && !patientDossiers.isEmpty()) {
			data = new String[patientDossiers.size()][3];
			for (int i = 0; i < patientDossiers.size(); i++) {
				PatientDossier pd = patientDossiers.get(i);
				data[i][0] = "نمایش";
				data[i][1] = pd.getRecordDate();
				data[i][2] = pd.getImagePath();
			}
			tmodel = new DefaultTableModel(data, s);
			table.setModel(tmodel);

			ButtonColumn buttonColumn1 = new ButtonColumn(table, 0);

		}

		else {
			ShowMessage.show("هیچ پرونده ای برای بیمار یافت نشد.",
					ShowMessage.ERROR_ID, PatientProfileFrame.this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		switch (command) {

		case "edit":
			makeFiledsEditable();
			break;

		case "save":
			makeFiledsNotEditable();
			try {
				saveChangesToPatient();
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}

			break;

		case "delete":
			try {
				deletePatient();
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			break;

		case "addVisit":

			AddVisitTimeFrame av = new AddVisitTimeFrame();
			av.setPatient(this.patient);
			av.fillFields(this.patient);
			av.setVisible(true);
			break;
		case "addPatientDossier":
			AddPatientDossier apd = new AddPatientDossier();
			apd.setPatient(this.patient);
			apd.setVisible(true);

		default:
			break;
		}
	}

	private void makeFiledsEditable() {

		nameField.setEditable(true);
		familyField.setEditable(true);
		docNoField.setEditable(true);
		cityField.setEditable(true);
		homeNoField.setEditable(true);
		phoneNoField.setEditable(true);

		editBtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		editBtn.setRolloverIcon(new ImageIcon(Resource.getImage("saveR.png")));
		
		editBtn.setActionCommand("save");

	}

	private void saveChangesToPatient() throws RemoteException {

		Patient p = new Patient();
		p.setId(patient.getId());
		p.setBirthday(patient.getBirthday());
		p.setCity(cityField.getText());
		p.setName(nameField.getText());
		p.setFamily(familyField.getText());
		p.setMobileNumber(phoneNoField.getText());
		p.setTelephoneNumber(homeNoField.getText());
		if (validate(phoneNoField.getText(), phoneNumberPattern)) {
			new BusinessServiceFactory().createPatientService()
					.updatePatient(p);

		} else {
			JOptionPane.showMessageDialog(this,
					"شماره موبایل رو به صورت صحیح وارد نمایید");
		}
		// ALSO CAN CHANGE THE PATIENT'S FOLDER NUMBER

	}

	private void makeFiledsNotEditable() {

		nameField.setEditable(false);
		familyField.setEditable(false);
		docNoField.setEditable(false);
		cityField.setEditable(false);
		homeNoField.setEditable(false);
		phoneNoField.setEditable(false);

		editBtn.setIcon(new ImageIcon(Resource.getImage("edit.png")));
		editBtn.setRolloverIcon(new ImageIcon(Resource.getImage("editR.png")));
		
		editBtn.setActionCommand("edit");

	}

	private static boolean validate(String txt, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
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

	private void deletePatient() throws RemoteException {

		new BusinessServiceFactory().createPatientService().deletePatient(
				patient);

	}

	public void setPatient(Patient p, PatientFolder patientFolder)
			throws RemoteException {

		patient = p;
		this.patientFolder = patientFolder;
		fillFields();
	}

}
