package org.bihe.ui.secretary;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.arso.calendar.JDateChooser;
import org.bihe.Resource.Resource;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.bean.PatientsOfDoctors;
import org.bihe.business.factory.BusinessServiceFactory;

public class AddPatientFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 450;
	private static final int HEIGHT = 600;

	private JPanel contentPane;
	private JTextField nameTextField;
	private JLabel namelbl;
	private JLabel famililbl;
	private JTextField familyTextField;
	private JLabel mobileNumberlbl;
	private JTextField mobileNumberTextField;
	private JTextField homeNumberTextField;
	private JLabel homerNumberlbl;
	private JLabel birthDatelbl;
	private JLabel citylbl;
	private JTextField cityTextField;
	private JButton savebtn;
	private JLabel label;
	private JTextField folderUniqueNumberTextField;
	private JLabel label_1;
	private JTextField diseaseBriefCase;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";
	private JLabel infolbl;
	private JLabel info2Lbl;

	private JDateChooser dateChooser;


	/**
	 * Create the frame.
	 */
	public AddPatientFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("اضافه کردن بیمار");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 3 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.inactiveCaptionBorder);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 40, 40, 40, 40, 40, 40, 40,
				40, 40, 40 };
		gbl_contentPane.rowHeights = new int[] { 60, 60, 60, 60, 60, 60, 60,
				60, 60, 60, 60 };
		gbl_contentPane.columnWeights = new double[] { 1, 1.0, 1.0, 1, 1.0,
				1.0, 1, 1, 1, 1 };
		gbl_contentPane.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1 };
		contentPane.setLayout(gbl_contentPane);

		JLabel formlbl = new JLabel("لطفافرم زیر را تکمیل نمایید:");
		formlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 24));
		GridBagConstraints gbc_formlbl = new GridBagConstraints();
		gbc_formlbl.anchor = GridBagConstraints.EAST;
		gbc_formlbl.gridwidth = 6;
		gbc_formlbl.insets = new Insets(0, 0, 5, 5);
		gbc_formlbl.gridx = 3;
		gbc_formlbl.gridy = 0;
		contentPane.add(formlbl, gbc_formlbl);

		nameTextField = new JTextField();
		nameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 4;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 2;
		gbc_nameTextField.gridy = 1;
		contentPane.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		namelbl = new JLabel("نام:*");
		namelbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_namelbl = new GridBagConstraints();
		gbc_namelbl.anchor = GridBagConstraints.WEST;
		gbc_namelbl.gridwidth = 4;
		gbc_namelbl.insets = new Insets(0, 0, 5, 0);
		gbc_namelbl.gridx = 6;
		gbc_namelbl.gridy = 1;
		contentPane.add(namelbl, gbc_namelbl);

		familyTextField = new JTextField();
		familyTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_familyTextField = new GridBagConstraints();
		gbc_familyTextField.gridwidth = 4;
		gbc_familyTextField.insets = new Insets(0, 0, 5, 5);
		gbc_familyTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyTextField.gridx = 2;
		gbc_familyTextField.gridy = 2;
		contentPane.add(familyTextField, gbc_familyTextField);
		familyTextField.setColumns(10);

		famililbl = new JLabel("نام خانوادگی:*");
		famililbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_famililbl = new GridBagConstraints();
		gbc_famililbl.anchor = GridBagConstraints.WEST;
		gbc_famililbl.gridwidth = 4;
		gbc_famililbl.insets = new Insets(0, 0, 5, 0);
		gbc_famililbl.gridx = 6;
		gbc_famililbl.gridy = 2;
		contentPane.add(famililbl, gbc_famililbl);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		mobileNumberTextField.addKeyListener(numberFormat());

		info2Lbl = new JLabel("(بدون صفر)");
		info2Lbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 11));
		GridBagConstraints gbc_info2Lbl = new GridBagConstraints();
		gbc_info2Lbl.gridwidth = 2;
		gbc_info2Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_info2Lbl.anchor = GridBagConstraints.EAST;
		gbc_info2Lbl.gridx = 0;
		gbc_info2Lbl.gridy = 3;
		contentPane.add(info2Lbl, gbc_info2Lbl);
		GridBagConstraints gbc_mobileNumberTextField = new GridBagConstraints();
		gbc_mobileNumberTextField.gridwidth = 4;
		gbc_mobileNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_mobileNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mobileNumberTextField.gridx = 2;
		gbc_mobileNumberTextField.gridy = 3;
		contentPane.add(mobileNumberTextField, gbc_mobileNumberTextField);
		mobileNumberTextField.setColumns(10);

		mobileNumberlbl = new JLabel("شماره تلفن(همراه):*");
		mobileNumberlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_mobileNumberlbl = new GridBagConstraints();
		gbc_mobileNumberlbl.anchor = GridBagConstraints.WEST;
		gbc_mobileNumberlbl.gridwidth = 4;
		gbc_mobileNumberlbl.insets = new Insets(0, 0, 5, 0);
		gbc_mobileNumberlbl.gridx = 6;
		gbc_mobileNumberlbl.gridy = 3;
		contentPane.add(mobileNumberlbl, gbc_mobileNumberlbl);

		homeNumberTextField = new JTextField();
		homeNumberTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		homeNumberTextField.addKeyListener(numberFormat());
		GridBagConstraints gbc_homeNumberTextField = new GridBagConstraints();
		gbc_homeNumberTextField.gridwidth = 4;
		gbc_homeNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_homeNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeNumberTextField.gridx = 2;
		gbc_homeNumberTextField.gridy = 4;
		contentPane.add(homeNumberTextField, gbc_homeNumberTextField);
		homeNumberTextField.setColumns(10);

		homerNumberlbl = new JLabel("شماره تلفن(ثابت):");
		homerNumberlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_homerNumberlbl = new GridBagConstraints();
		gbc_homerNumberlbl.anchor = GridBagConstraints.WEST;
		gbc_homerNumberlbl.gridwidth = 4;
		gbc_homerNumberlbl.insets = new Insets(0, 0, 5, 0);
		gbc_homerNumberlbl.gridx = 6;
		gbc_homerNumberlbl.gridy = 4;
		contentPane.add(homerNumberlbl, gbc_homerNumberlbl);

		GridBagConstraints gbc_btnDate = new GridBagConstraints();
		gbc_btnDate.gridwidth = 3;
		gbc_btnDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnDate.gridx = 2;
		gbc_btnDate.gridy = 5;
		dateChooser = new JDateChooser(false);
		dateChooser.setPreferredSize(new Dimension(160, 25));

		contentPane.add(dateChooser, gbc_btnDate);

		birthDatelbl = new JLabel("تاریخ تولد:");
		birthDatelbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_birthDatelbl = new GridBagConstraints();
		gbc_birthDatelbl.anchor = GridBagConstraints.WEST;
		gbc_birthDatelbl.gridwidth = 4;
		gbc_birthDatelbl.insets = new Insets(0, 0, 5, 0);
		gbc_birthDatelbl.gridx = 6;
		gbc_birthDatelbl.gridy = 5;
		contentPane.add(birthDatelbl, gbc_birthDatelbl);

		cityTextField = new JTextField();
		cityTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.gridwidth = 4;
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 2;
		gbc_cityTextField.gridy = 6;
		contentPane.add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);

		citylbl = new JLabel("شهر:");
		citylbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_citylbl = new GridBagConstraints();
		gbc_citylbl.anchor = GridBagConstraints.WEST;
		gbc_citylbl.gridwidth = 2;
		gbc_citylbl.insets = new Insets(0, 0, 5, 5);
		gbc_citylbl.gridx = 6;
		gbc_citylbl.gridy = 6;
		contentPane.add(citylbl, gbc_citylbl);

		savebtn = new JButton();
		savebtn.addActionListener(this);
		
		savebtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		savebtn.setBorder(BorderFactory.createEmptyBorder());
		savebtn.setRolloverIcon(new ImageIcon(Resource
				.getImage("saveR.png")));
		savebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		savebtn.setContentAreaFilled(false);

		folderUniqueNumberTextField = new JTextField();
		folderUniqueNumberTextField
				.setHorizontalAlignment(SwingConstants.RIGHT);
		folderUniqueNumberTextField.addKeyListener(numberFormat());
		folderUniqueNumberTextField.setColumns(10);
		GridBagConstraints gbc_folderUniqueNumberTextField = new GridBagConstraints();
		gbc_folderUniqueNumberTextField.gridwidth = 2;
		gbc_folderUniqueNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_folderUniqueNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_folderUniqueNumberTextField.gridx = 4;
		gbc_folderUniqueNumberTextField.gridy = 7;
		contentPane.add(folderUniqueNumberTextField,
				gbc_folderUniqueNumberTextField);

		label = new JLabel("شماره پرونده:*");
		label.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 6;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);

		diseaseBriefCase = new JTextField();
		diseaseBriefCase.setHorizontalAlignment(SwingConstants.RIGHT);
		diseaseBriefCase.setColumns(10);
		GridBagConstraints gbc_diseaseBriefCase = new GridBagConstraints();
		gbc_diseaseBriefCase.gridwidth = 4;
		gbc_diseaseBriefCase.insets = new Insets(0, 0, 5, 5);
		gbc_diseaseBriefCase.fill = GridBagConstraints.HORIZONTAL;
		gbc_diseaseBriefCase.gridx = 2;
		gbc_diseaseBriefCase.gridy = 8;
		contentPane.add(diseaseBriefCase, gbc_diseaseBriefCase);

		label_1 = new JLabel("مختصری از سابقه:");
		label_1.setFont(new Font("Sakkal Majalla", Font.PLAIN, 21));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridwidth = 3;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 6;
		gbc_label_1.gridy = 8;
		contentPane.add(label_1, gbc_label_1);
		GridBagConstraints gbc_savebtn = new GridBagConstraints();
		gbc_savebtn.anchor = GridBagConstraints.NORTH;
		gbc_savebtn.gridwidth = 6;
		gbc_savebtn.insets = new Insets(0, 0, 5, 5);
		gbc_savebtn.gridx = 2;
		gbc_savebtn.gridy = 9;
		contentPane.add(savebtn, gbc_savebtn);

		infolbl = new JLabel(
				"-وارد کردن تمام موارد ستاره دار (*) اجباری می باشد.");
		infolbl.setFont(new Font("Sakkal Majalla", Font.BOLD, 13));
		GridBagConstraints gbc_infolbl = new GridBagConstraints();
		gbc_infolbl.anchor = GridBagConstraints.NORTHEAST;
		gbc_infolbl.gridwidth = 7;
		gbc_infolbl.insets = new Insets(0, 0, 0, 5);
		gbc_infolbl.gridx = 2;
		gbc_infolbl.gridy = 10;
		contentPane.add(infolbl, gbc_infolbl);

	}

	private boolean addPatient() throws RemoteException {
		Patient p = new Patient();
		p.setName(nameTextField.getText());
		p.setFamily(familyTextField.getText());
		p.setCity(cityTextField.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		p.setBirthday(sdf.format(dateChooser.getDate()));
		p.setMobileNumber(mobileNumberTextField.getText());
		p.setTelephoneNumber(homeNumberTextField.getText());
		p = new BusinessServiceFactory().createPatientService().addPatient(p);

		PatientFolder patientFolder = new PatientFolder();
		patientFolder.setFolderUniqueNumber(folderUniqueNumberTextField
				.getText());
		patientFolder.setDiseaseBriefDesc(diseaseBriefCase.getText());
		patientFolder.setPatientID(p.getId());
		patientFolder.setDoctorID(new BusinessServiceFactory()
				.createSystemDoctor().getDoctor().getId());
		boolean addPatientFolder = new BusinessServiceFactory()
				.createPatientFolderService().addPatientFolder(patientFolder);

		PatientsOfDoctors patientOfDoctors = new PatientsOfDoctors();
		patientOfDoctors.setDoctorID(new BusinessServiceFactory()
				.createSystemDoctor().getDoctor().getId());
		patientOfDoctors.setPatiendID(p.getId());
		boolean addPatientOfDoctor = new BusinessServiceFactory()
				.createPatientOfDoctorsService().addPatientOfDoctors(
						patientOfDoctors);

		return p.getId() >= 0 && addPatientFolder && addPatientOfDoctor;

	}

	private void clearFields() {

		nameTextField.setText("");
		familyTextField.setText("");
		cityTextField.setText("");
		mobileNumberTextField.setText("");
		homeNumberTextField.setText("");
		folderUniqueNumberTextField.setText("");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			if (!nameTextField.getText().isEmpty()
					&& !familyTextField.getText().isEmpty()
					&& !mobileNumberTextField.getText().isEmpty()
					&& !folderUniqueNumberTextField.getText().isEmpty()) {
				if (mobileNumberTextField.getText().isEmpty()
						|| validate(mobileNumberTextField.getText(),
								phoneNumberPattern)) {
					if (addPatient()) {

						String info = "بیمار با موفقیت اضافه شد." + "\n"
								+ "نام:" + nameTextField.getText() + "\n"
								+ "نام خانوادگی:" + familyTextField.getText()
								+ "\n" + "شماره پرونده:"
								+ folderUniqueNumberTextField.getText();
						ShowMessage.show(info, ShowMessage.INFORMATION_ID,
								AddPatientFrame.this);
						clearFields();
					} else {
						ShowMessage.show("اضافه کردن بیمار موفقیت آمیز نبود",
								ShowMessage.ERROR_ID, AddPatientFrame.this);
						clearFields();

					}

				} else {
					ShowMessage.show("شماره موبایل وارد شده صحیح نمی باشد",
							ShowMessage.ERROR_ID, AddPatientFrame.this);
				}
			} else if (nameTextField.getText().isEmpty()
					|| familyTextField.getText().isEmpty()
					|| mobileNumberTextField.getText().isEmpty()
					|| folderUniqueNumberTextField.getText().isEmpty()) {
				ShowMessage.show("لطفا تمام موارد اجباری را وارد نمایید",
						ShowMessage.ERROR_ID, AddPatientFrame.this);
			}

		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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

	private static boolean validate(String txt, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
	}

}
