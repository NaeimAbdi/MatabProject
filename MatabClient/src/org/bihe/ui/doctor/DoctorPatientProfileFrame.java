package org.bihe.ui.doctor;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import org.bihe.ui.secretary.AddPatientDossier;
import org.bihe.ui.secretary.ShowMessage;

public class DoctorPatientProfileFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */

	// AFTER CALLING CONSTRUCTOR THE PATIENT FIELD MUST BE SET

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
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

	private JButton btnAddPatientDossier;

	private PatientFolder patientFolder;
	private Patient patient;
	private ArrayList<PatientDossier> patientDossiers;
	private JButton addDoctorComm;



	/**
	 * Create the frame.
	 * 
	 * @throws RemoteException
	 */
	public DoctorPatientProfileFrame() throws RemoteException {
		
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
				50, 50, 50, 50, 25, 50, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50, 50, 50 };
		gbl_contentPane.columnWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_contentPane.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 1 };
		contentPane.setLayout(gbl_contentPane);

		docNoField = new JTextField();
		docNoField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		docNoField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_docNoField = new GridBagConstraints();
		gbc_docNoField.gridwidth = 3;
		gbc_docNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_docNoField.insets = new Insets(0, 0, 5, 5);
		gbc_docNoField.gridx = 2;
		gbc_docNoField.gridy = 1;
		contentPane.add(docNoField, gbc_docNoField);
		docNoField.setColumns(10);

		JLabel lblNewLabel = new JLabel("شماره پرونده:");
		lblNewLabel.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		familyField = new JTextField();
		familyField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		familyField.setHorizontalAlignment(SwingConstants.RIGHT);
		familyField.setColumns(10);
		GridBagConstraints gbc_familyField = new GridBagConstraints();
		gbc_familyField.gridwidth = 3;
		gbc_familyField.insets = new Insets(0, 0, 5, 5);
		gbc_familyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyField.gridx = 7;
		gbc_familyField.gridy = 1;
		contentPane.add(familyField, gbc_familyField);

		JLabel label_3 = new JLabel("نام خانوادگی:");
		label_3.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.gridwidth = 2;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 10;
		gbc_label_3.gridy = 1;
		contentPane.add(label_3, gbc_label_3);

		nameField = new JTextField();
		nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nameField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridwidth = 4;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 12;
		gbc_nameField.gridy = 1;
		contentPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("نام:");
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
		btnAddPatientDossier.setActionCommand("addPatientDossier");
		btnAddPatientDossier.addActionListener(this);
		
		
		btnAddPatientDossier.setContentAreaFilled(false);
		btnAddPatientDossier.setIcon(new ImageIcon(Resource.getImage("addDossier.png")));
		btnAddPatientDossier.setBorder(BorderFactory.createEmptyBorder());
		btnAddPatientDossier.setRolloverIcon(new ImageIcon(Resource.getImage("addDossierR.png")));
		btnAddPatientDossier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAddPatientDossier.setFocusable(false);

		phoneNoField = new JTextField();
		phoneNoField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		phoneNoField.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNoField.setEditable(false);
		GridBagConstraints gbc_phoneNoField = new GridBagConstraints();
		gbc_phoneNoField.gridwidth = 3;
		gbc_phoneNoField.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNoField.gridx = 3;
		gbc_phoneNoField.gridy = 4;
		contentPane.add(phoneNoField, gbc_phoneNoField);
		phoneNoField.setColumns(10);

		JLabel label_1 = new JLabel("شماره همراه:");
		label_1.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.gridwidth = 2;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 6;
		gbc_label_1.gridy = 4;
		contentPane.add(label_1, gbc_label_1);

		cityField = new JTextField();
		cityField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cityField.setHorizontalAlignment(SwingConstants.RIGHT);
		cityField.setEditable(false);
		GridBagConstraints gbc_cityField = new GridBagConstraints();
		gbc_cityField.gridwidth = 3;
		gbc_cityField.insets = new Insets(0, 0, 5, 5);
		gbc_cityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityField.gridx = 8;
		gbc_cityField.gridy = 4;
		contentPane.add(cityField, gbc_cityField);
		cityField.setColumns(10);

		JLabel label = new JLabel("شهر:");
		label.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 11;
		gbc_label.gridy = 4;
		contentPane.add(label, gbc_label);

		homeNoField = new JTextField();
		homeNoField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		homeNoField.setHorizontalAlignment(SwingConstants.RIGHT);
		homeNoField.setEditable(false);
		GridBagConstraints gbc_homeNoField = new GridBagConstraints();
		gbc_homeNoField.gridwidth = 3;
		gbc_homeNoField.insets = new Insets(0, 0, 5, 5);
		gbc_homeNoField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeNoField.gridx = 13;
		gbc_homeNoField.gridy = 4;
		contentPane.add(homeNoField, gbc_homeNoField);
		homeNoField.setColumns(10);

		JLabel label_2 = new JLabel("شماره ثابت:");
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
		gbc_btnAddPatientDossier.gridwidth = 4;
		gbc_btnAddPatientDossier.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddPatientDossier.gridx = 4;
		gbc_btnAddPatientDossier.gridy = 6;
		contentPane.add(btnAddPatientDossier, gbc_btnAddPatientDossier);

		addDoctorComm = new JButton();
		addDoctorComm.setActionCommand("com");
		addDoctorComm.addActionListener(this);
		
		
		
		addDoctorComm.setContentAreaFilled(false);
		addDoctorComm.setIcon(new ImageIcon(Resource.getImage("addComment.png")));
		addDoctorComm.setBorder(BorderFactory.createEmptyBorder());
		addDoctorComm.setRolloverIcon(new ImageIcon(Resource.getImage("commentR.png")));
		addDoctorComm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addDoctorComm.setFocusable(false);
		
		
		
		GridBagConstraints gbc_addDoctorComm = new GridBagConstraints();
		gbc_addDoctorComm.fill = GridBagConstraints.HORIZONTAL;
		gbc_addDoctorComm.gridwidth = 4;
		gbc_addDoctorComm.insets = new Insets(0, 0, 5, 5);
		gbc_addDoctorComm.gridx = 12;
		gbc_addDoctorComm.gridy = 6;
		contentPane.add(addDoctorComm, gbc_addDoctorComm);

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
						DoctorPatientProfileFrame.this);
				break;

			default:

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
					ShowMessage.ERROR_ID, DoctorPatientProfileFrame.this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		switch (command) {

		case "com":
			setAddComm();
			break;

		case "addPatientDossier":
			AddPatientDossier apd = new AddPatientDossier();
			apd.setPatient(this.patient);
			apd.setVisible(true);

		default:
			break;
		}
	}

	private void setAddComm() {
		DoctorCommentFrame docCM = new DoctorCommentFrame();
		PatientFolder pf = this.patientFolder;

		Patient p = this.patient;
		Doctor doc = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();

		pf.setDoctorID(doc.getId());
		pf.setPatientID(p.getId());

		docCM.setPatientFolder(pf);

		docCM.setVisible(true);
	}

	private void makeFiledsNotEditable() {

		nameField.setEditable(false);
		familyField.setEditable(false);
		docNoField.setEditable(false);
		cityField.setEditable(false);
		homeNoField.setEditable(false);
		phoneNoField.setEditable(false);

	}

	@SuppressWarnings("unused")
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
