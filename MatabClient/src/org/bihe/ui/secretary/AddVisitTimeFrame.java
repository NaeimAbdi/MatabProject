package org.bihe.ui.secretary;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.arso.calendar.JDateChooser;
import org.bihe.Resource.Resource;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.bean.VisitTimes;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.util.GregorianJalaliCoverter;

public class AddVisitTimeFrame extends JFrame implements ActionListener,
		MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4278247900968743298L;

	private static final int WIDTH = 450;
	private static final int HEIGHT = 370;

	private JButton setVisit;

	private JComboBox<VisitTimes> datecomboBox;

	private JLabel toplbl;
	private JTextField nameField;
	private JTextField familyField;
	private JTextField docNoField;
	private JCheckBox checkBox;
	private JLabel serachOrAddLbl;
	private JTextField phoneNoField;
	private JLabel phoneNolbl;
	private JLabel itemsFound;

	private JDateChooser jDateChooser;
	private Patient patient;

	private JSpinner spinner;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";

	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddVisitTimeFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		getContentPane().setFont(new Font("Sakkal Majalla", Font.PLAIN, 15));
		setResizable(false);
		setTitle("دادن نوبت");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		getContentPane().setLayout(null);

		setVisit = new JButton();
		setVisit.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		setVisit.addActionListener(this);
		setVisit.setActionCommand("addvisit");
		setVisit.setBounds(162, 278, 110, 35);
		
		
		setVisit.setContentAreaFilled(false);
		setVisit.setIcon(new ImageIcon(Resource.getImage("addVisitTime.png")));
		setVisit.setBorder(BorderFactory.createEmptyBorder());
		setVisit.setRolloverIcon(new ImageIcon(Resource.getImage("addVisitTimeR.png")));
		setVisit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setVisit.setFocusable(false);
		
		
		getContentPane().add(setVisit);

		datecomboBox = new JComboBox();
		datecomboBox.setBounds(183, 240, 249, 23);
		getContentPane().add(datecomboBox);

		toplbl = new JLabel("انتخاب زمان ویزیت:");
		toplbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		toplbl.setHorizontalAlignment(SwingConstants.RIGHT);
		toplbl.setBounds(324, 204, 108, 23);
		getContentPane().add(toplbl);

		JLabel label = new JLabel("نام:");
		label.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		label.setBounds(372, 89, 32, 16);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("نام خانوادگی:");
		label_1.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		label_1.setBounds(189, 89, 83, 16);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("شماره پرونده:");
		label_2.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		label_2.setBounds(22, 89, 95, 16);
		getContentPane().add(label_2);

		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.RIGHT);
		nameField.setBounds(337, 118, 95, 22);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		nameField.addKeyListener(this);

		familyField = new JTextField();
		familyField.setHorizontalAlignment(SwingConstants.RIGHT);
		familyField.setBounds(151, 118, 155, 22);
		getContentPane().add(familyField);
		familyField.setColumns(10);
		familyField.setActionCommand("familyField");
		familyField.addKeyListener(this);

		docNoField = new JTextField();
		docNoField.setHorizontalAlignment(SwingConstants.RIGHT);
		docNoField.setBounds(22, 118, 85, 22);
		getContentPane().add(docNoField);
		docNoField.setColumns(10);
		docNoField.setActionCommand("docNoField");
		docNoField.addKeyListener(this);

		itemsFound = new JLabel("موارد یافت شده");
		itemsFound.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		itemsFound.setForeground(Color.BLUE);
		itemsFound.setBounds(324, 153, 108, 16);
		getContentPane().add(itemsFound);
		Font font = itemsFound.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		itemsFound.setFont(new Font("Sakkal Majalla", Font.PLAIN, 15));
		itemsFound.setCursor(new Cursor(Cursor.HAND_CURSOR));
		itemsFound.addMouseListener(this);
		itemsFound.setVisible(false);

		checkBox = new JCheckBox("مراجعه کننده دارای پرونده است");
		checkBox.setFont(new Font("Sakkal Majalla", Font.PLAIN, 15));
		checkBox.setBounds(235, 9, 197, 25);
		checkBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(checkBox);
		checkBox.setSelected(true);
		checkBox.addActionListener(this);
		checkBox.setActionCommand("isNew");

		serachOrAddLbl = new JLabel("New label");
		serachOrAddLbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 16));
		serachOrAddLbl.setBounds(324, 55, 108, 16);
		serachOrAddLbl.setText("جستجو بر اساس:");
		getContentPane().add(serachOrAddLbl);

		phoneNoField = new JTextField();
		phoneNoField.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNoField.setBounds(22, 165, 116, 22);
		getContentPane().add(phoneNoField);
		phoneNoField.setColumns(10);
		phoneNoField.setVisible(false);

		phoneNolbl = new JLabel("شماره تلفن(همراه):");
		phoneNolbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		phoneNolbl.setBounds(151, 168, 121, 16);
		getContentPane().add(phoneNolbl);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		spinner.setBounds(121, 241, 52, 20);
		spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(spinner);

		JButton button = new JButton();
		button.addActionListener(this);
		button.setActionCommand("find");
		button.setBounds(10, 231, 121, 51);
		
		
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(Resource.getImage("searchTimes.png")));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setRolloverIcon(new ImageIcon(Resource.getImage("searchTimesR.png")));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setFocusable(false);
		
		
		getContentPane().add(button);

		jDateChooser = new JDateChooser();
		jDateChooser.setBounds(22, 204, 116, 23);
		getContentPane().add(jDateChooser);
		phoneNolbl.setVisible(false);
	}

	private void suggustionVisitTimes() {

		Date date = jDateChooser.getDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		GregorianJalaliCoverter gjc = new GregorianJalaliCoverter();
		gjc.PersianToGregorian(year, month + 1, day);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = gjc.toString();

		try {
			Date date1 = formatter.parse(dateInString);
			ArrayList<VisitTimes> sug = new BusinessServiceFactory()
					.createVisitTimesService().giveMeNearFreeTimes(
							(Integer) spinner.getValue(), date1);
			datecomboBox.removeAllItems();
			for (VisitTimes visitTimes : sug) {
				datecomboBox.addItem(visitTimes);

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	private void switchAddOrSearch() {

		if (checkBox.isSelected()) {

			serachOrAddLbl.setText("جستجو بر اساس:");
			phoneNoField.setVisible(false);
			phoneNolbl.setVisible(false);
			setVisit.setIcon(new ImageIcon(Resource.getImage("addVisitTime.png")));
			setVisit.setBorder(BorderFactory.createEmptyBorder());
			setVisit.setRolloverIcon(new ImageIcon(Resource.getImage("addVisitTimeR.png")));
		} else {
			serachOrAddLbl.setText("اضافه کردن:        ");
			phoneNoField.setVisible(true);
			phoneNolbl.setVisible(true);
			setVisit.setIcon(new ImageIcon(Resource.getImage("AddPatient.png")));
			setVisit.setBorder(BorderFactory.createEmptyBorder());
			setVisit.setRolloverIcon(new ImageIcon(Resource.getImage("AddPatientR.png")));

		}
	}

	private void countPatientForSearch() throws RemoteException {

		Patient patient = new Patient();
		patient.setName(nameField.getText());
		patient.setFamily(familyField.getText());

		PatientFolder pf = new PatientFolder();
		pf.setDoctorID(new BusinessServiceFactory().createSystemDoctor()
				.getDoctor().getId());
		pf.setFolderUniqueNumber(docNoField.getText());
		ArrayList<Patient> patients = new BusinessServiceFactory()
				.createPatientService().search(patient, pf);

		int count = patients.size();
		itemsFound.setText("مورد یافت شد" + " " + count);
		itemsFound.setVisible(true);

	}

	public void fillFields(Object patOrDoc) {

		if (patOrDoc instanceof Patient) {
			nameField.setText(((Patient) patOrDoc).getName());
			familyField.setText(((Patient) patOrDoc).getFamily());
		} else if (patOrDoc instanceof PatientFolder) {
			docNoField.setText(((PatientFolder) patOrDoc)
					.getFolderUniqueNumber());
		}

	}

	public AddVisitTimeFrame getThis() {
		return this;
	}

	private void createPatientsForShow() throws RemoteException {

		Patient patient = new Patient();
		patient.setName(nameField.getText());
		patient.setFamily(familyField.getText());

		PatientFolder pf = new PatientFolder();
		pf.setDoctorID(new BusinessServiceFactory().createSystemDoctor()
				.getDoctor().getId());
		pf.setFolderUniqueNumber(docNoField.getText());

		ArrayList<Patient> patients = new BusinessServiceFactory()
				.createPatientService().search(patient, pf);

		FoundPatientFrame founds = new FoundPatientFrame();
		founds.setAddVisitTimeFrame(this);
		founds.setPatients(patients);
		founds.fillTable();
		founds.setVisible(true);

	}

	private void createPatientForAdd() throws RemoteException {

		if (!nameField.getText().isEmpty() && !familyField.getText().isEmpty()
				&& !phoneNoField.getText().isEmpty()) {
			if (validate(phoneNoField.getText(), phoneNumberPattern)) {
				Patient patient = new Patient();
				patient.setName(nameField.getText());
				patient.setFamily(familyField.getText());
				patient.setMobileNumber(phoneNoField.getText());
				patient.setCity("");
				patient.setTelephoneNumber("");
				PatientFolder pf = new PatientFolder();

				pf.setDoctorID(new BusinessServiceFactory()
						.createSystemDoctor().getDoctor().getId());
				pf.setFolderUniqueNumber(docNoField.getText());

				patient = new BusinessServiceFactory().createPatientService()
						.addPatient(patient);
				pf.setPatientID(patient.getId());
				if (new BusinessServiceFactory().createPatientFolderService()
						.addPatientFolder(pf)) {

					this.patient = patient;
					ShowMessage.show("بیمار با موافقیت اضافه شد.",
							ShowMessage.INFORMATION_ID, this);
				} else {
					ShowMessage.show("اضافه کردن بیمار دچار مشکل شد.",
							ShowMessage.ERROR_ID, this);
				}
			} else {
				ShowMessage.show("شماره تلفن وارد شده صحیح نمی باشد!",
						ShowMessage.ERROR_ID, AddVisitTimeFrame.this);
			}
		} else {
			ShowMessage.show("لطفا تمامی موارد را برای ثبت نام وارد نمایید!",
					ShowMessage.ERROR_ID, AddVisitTimeFrame.this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {
		case "addvisit":

			if (!checkBox.isSelected()) {
				try {
					createPatientForAdd();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			}
			addVisitTime();

			break;

		case "isNew":
			switchAddOrSearch();
			break;

		case "find":
			suggustionVisitTimes();
			break;

		}

	}

	private void addVisitTime() {
		VisitTimes v = (VisitTimes) this.datecomboBox.getSelectedItem();
		v.setDoctorID(new BusinessServiceFactory().createSystemDoctor()
				.getDoctor().getId());
		v.setPatientID(this.patient.getId());

		try {
			if (new BusinessServiceFactory().createVisitTimesService()
					.update(v)) {
				ShowMessage.show(
						"وقت ملاقات به بیمار مورد نظر اختصاص داده شد.",
						ShowMessage.INFORMATION_ID, this);
			}
			SecretaryMainFrame.addApointments(new Date());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		try {

			createPatientsForShow();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getSource() instanceof JTextField) {
			((JTextField) e.getSource()).setText(((JTextField) e.getSource())
					.getText() + e.getKeyChar());
		}

		boolean isHere = false;

		if (checkBox.isSelected()) {
			if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE
					|| e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {

				isHere = true;
				String s = ((JTextField) e.getSource()).getText();

				((JTextField) e.getSource()).setText(s.substring(0,
						s.length() - 1));

				if (nameField.getText().isEmpty()
						&& familyField.getText().isEmpty()
						&& docNoField.getText().isEmpty()) {

					itemsFound.setVisible(false);
					itemsFound.repaint();
					itemsFound.revalidate();

				} else {
					// itemsFound.setVisible(true);
					try {
						countPatientForSearch();
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}

				}

			} else {
				try {

					countPatientForSearch();
				} catch (RemoteException e1) {

					e1.printStackTrace();
				}
			}

		} else {
			itemsFound.setVisible(false);

		}

		if (!isHere) {
			String s = ((JTextField) e.getSource()).getText();

			((JTextField) e.getSource())
					.setText(s.substring(0, s.length() - 1));
		}

	}

	@SuppressWarnings("unused")
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

	private static boolean validate(String txt, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}
}
