package org.bihe.ui.doctor;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.SecretariesOfDoctors;
import org.bihe.bean.Secretary;
import org.bihe.bean.SystemParameters;
import org.bihe.bean.Users;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.ui.secretary.ShowMessage;

public class AddUserFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JLabel namelbl;
	private JLabel famililbl;
	private JTextField familyTextField;
	private JLabel mobileNumberlbl;
	private JTextField mobileNumberTextField;
	private JTextField homeNumberTextField;
	private JLabel homerNumberlbl;
	private JLabel passwordlbl;
	private JButton savebtn;
	private JTextField usernameTextField;
	private JLabel usernamelbl;
	private JLabel infolbl;

	private static final int WIDTH = 440;
	private static final int HEIGHT = 550;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";
	private JLabel lblNewLabel;
	private JRadioButton doctorrediobtn;
	private JRadioButton secretery;
	private JPasswordField passwordTextField;


	/**
	 * Create the frame.
	 */
	public AddUserFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("اضافه کردن کاربر");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.inactiveCaptionBorder);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 40, 40, 40, 40, 40, 40, 40,
				40, 40, 40 };
		gbl_contentPane.rowHeights = new int[] { 60, 60, 60, 60, 60, 60, 60,
				60, 60, 60 };
		gbl_contentPane.columnWeights = new double[] { 1, 1.0, 1.0, 1, 1.0,
				1.0, 8.0, 8.0, 8.8, 1 };
		gbl_contentPane.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1 };
		contentPane.setLayout(gbl_contentPane);

		JLabel formlbl = new JLabel("لطفافرم زیر را تکمیل نمایید:");
		formlbl.setFont(new Font("Sakkal Majalla", Font.BOLD, 19));
		GridBagConstraints gbc_formlbl = new GridBagConstraints();
		gbc_formlbl.anchor = GridBagConstraints.EAST;
		gbc_formlbl.gridwidth = 8;
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
		gbc_nameTextField.gridx = 3;
		gbc_nameTextField.gridy = 1;
		contentPane.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		namelbl = new JLabel("نام:*");
		namelbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_namelbl = new GridBagConstraints();
		gbc_namelbl.anchor = GridBagConstraints.WEST;
		gbc_namelbl.gridwidth = 3;
		gbc_namelbl.insets = new Insets(0, 0, 5, 5);
		gbc_namelbl.gridx = 7;
		gbc_namelbl.gridy = 1;
		contentPane.add(namelbl, gbc_namelbl);

		familyTextField = new JTextField();
		familyTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_familyTextField = new GridBagConstraints();
		gbc_familyTextField.gridwidth = 4;
		gbc_familyTextField.insets = new Insets(0, 0, 5, 5);
		gbc_familyTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyTextField.gridx = 3;
		gbc_familyTextField.gridy = 2;
		contentPane.add(familyTextField, gbc_familyTextField);
		familyTextField.setColumns(10);

		famililbl = new JLabel("نام خانوادگی:*");
		famililbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_famililbl = new GridBagConstraints();
		gbc_famililbl.anchor = GridBagConstraints.WEST;
		gbc_famililbl.gridwidth = 3;
		gbc_famililbl.insets = new Insets(0, 0, 5, 5);
		gbc_famililbl.gridx = 7;
		gbc_famililbl.gridy = 2;
		contentPane.add(famililbl, gbc_famililbl);

		lblNewLabel = new JLabel("(بدون صفر)");
		lblNewLabel.setFont(new Font("Sakkal Majalla", Font.ITALIC, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		mobileNumberTextField = new JTextField();
		mobileNumberTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_mobileNumberTextField = new GridBagConstraints();
		gbc_mobileNumberTextField.gridwidth = 4;
		gbc_mobileNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_mobileNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mobileNumberTextField.gridx = 3;
		gbc_mobileNumberTextField.gridy = 3;
		contentPane.add(mobileNumberTextField, gbc_mobileNumberTextField);
		mobileNumberTextField.setColumns(10);

		mobileNumberlbl = new JLabel("شماره تلفن(همراه):");
		mobileNumberTextField.addKeyListener(numberFormat());
		mobileNumberlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_mobileNumberlbl = new GridBagConstraints();
		gbc_mobileNumberlbl.anchor = GridBagConstraints.WEST;
		gbc_mobileNumberlbl.gridwidth = 5;
		gbc_mobileNumberlbl.insets = new Insets(0, 0, 5, 0);
		gbc_mobileNumberlbl.gridx = 7;
		gbc_mobileNumberlbl.gridy = 3;
		contentPane.add(mobileNumberlbl, gbc_mobileNumberlbl);

		homeNumberTextField = new JTextField();
		homeNumberTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		homeNumberTextField.addKeyListener(numberFormat());
		GridBagConstraints gbc_homeNumberTextField = new GridBagConstraints();
		gbc_homeNumberTextField.gridwidth = 4;
		gbc_homeNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_homeNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homeNumberTextField.gridx = 3;
		gbc_homeNumberTextField.gridy = 4;
		contentPane.add(homeNumberTextField, gbc_homeNumberTextField);
		homeNumberTextField.setColumns(10);

		homerNumberlbl = new JLabel("شماره تلفن(ثابت):");
		homerNumberlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_homerNumberlbl = new GridBagConstraints();
		gbc_homerNumberlbl.anchor = GridBagConstraints.WEST;
		gbc_homerNumberlbl.gridwidth = 3;
		gbc_homerNumberlbl.insets = new Insets(0, 0, 5, 5);
		gbc_homerNumberlbl.gridx = 7;
		gbc_homerNumberlbl.gridy = 4;
		contentPane.add(homerNumberlbl, gbc_homerNumberlbl);

		usernameTextField = new JTextField();
		usernameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameTextField.setColumns(10);
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.gridwidth = 4;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.gridx = 3;
		gbc_usernameTextField.gridy = 5;
		contentPane.add(usernameTextField, gbc_usernameTextField);

		usernamelbl = new JLabel("نام کاربری:*");
		usernamelbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_usernamelbl = new GridBagConstraints();
		gbc_usernamelbl.anchor = GridBagConstraints.WEST;
		gbc_usernamelbl.gridwidth = 3;
		gbc_usernamelbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernamelbl.gridx = 7;
		gbc_usernamelbl.gridy = 5;
		contentPane.add(usernamelbl, gbc_usernamelbl);

		passwordTextField = new JPasswordField();
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.gridwidth = 4;
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.gridx = 3;
		gbc_passwordTextField.gridy = 6;
		contentPane.add(passwordTextField, gbc_passwordTextField);

		passwordlbl = new JLabel("رمز عبور:*");
		passwordlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 17));
		GridBagConstraints gbc_citylbl = new GridBagConstraints();
		gbc_citylbl.anchor = GridBagConstraints.WEST;
		gbc_citylbl.gridwidth = 3;
		gbc_citylbl.insets = new Insets(0, 0, 5, 5);
		gbc_citylbl.gridx = 7;
		gbc_citylbl.gridy = 6;
		contentPane.add(passwordlbl, gbc_citylbl);

		savebtn = new JButton();
		savebtn.addActionListener(this);
		
		savebtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		savebtn.setBorder(BorderFactory.createEmptyBorder());
		savebtn.setRolloverIcon(new ImageIcon(Resource
				.getImage("saveR.png")));
		savebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		savebtn.setContentAreaFilled(false);

		secretery = new JRadioButton("منشی");

		GridBagConstraints gbc_secretery = new GridBagConstraints();
		gbc_secretery.gridwidth = 2;
		gbc_secretery.insets = new Insets(0, 0, 5, 5);
		gbc_secretery.gridx = 3;
		gbc_secretery.gridy = 7;
		contentPane.add(secretery, gbc_secretery);

		doctorrediobtn = new JRadioButton("دکتر");
		doctorrediobtn.setSelected(true);
		GridBagConstraints gbc_doctorrediobtn = new GridBagConstraints();
		gbc_doctorrediobtn.gridwidth = 3;
		gbc_doctorrediobtn.insets = new Insets(0, 0, 5, 5);
		gbc_doctorrediobtn.gridx = 5;
		gbc_doctorrediobtn.gridy = 7;
		contentPane.add(doctorrediobtn, gbc_doctorrediobtn);
		GridBagConstraints gbc_savebtn = new GridBagConstraints();
		gbc_savebtn.gridwidth = 7;
		gbc_savebtn.insets = new Insets(0, 0, 5, 5);
		gbc_savebtn.gridx = 2;
		gbc_savebtn.gridy = 8;
		contentPane.add(savebtn, gbc_savebtn);

		infolbl = new JLabel("-وارد کردن موارد ستاره دار (*) اجباری می باشد");
		infolbl.setFont(new Font("Sakkal Majalla", Font.BOLD, 14));
		infolbl.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_infolbl = new GridBagConstraints();
		gbc_infolbl.insets = new Insets(0, 0, 0, 5);
		gbc_infolbl.anchor = GridBagConstraints.NORTHEAST;
		gbc_infolbl.gridwidth = 10;
		gbc_infolbl.gridx = 1;
		gbc_infolbl.gridy = 9;
		contentPane.add(infolbl, gbc_infolbl);

		ButtonGroup btnGro = new ButtonGroup();
		btnGro.add(doctorrediobtn);
		btnGro.add(secretery);

	}

	private void clearFields() {

		nameTextField.setText("");
		familyTextField.setText("");
		passwordTextField.setText("");
		mobileNumberTextField.setText("");
		homeNumberTextField.setText("");
		usernameTextField.setText("");
		passwordTextField.setText("");

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

	@SuppressWarnings("deprecation")
	private void saveAction() throws RemoteException {

		if (!nameTextField.getText().isEmpty()
				&& !familyTextField.getText().isEmpty()
				&& !usernameTextField.getText().isEmpty()
				&& !passwordTextField.getText().isEmpty()) {

			if (mobileNumberTextField.getText().isEmpty()
					|| validate(mobileNumberTextField.getText(),
							phoneNumberPattern)) {

				boolean u = false;
				boolean s = false;
				boolean d = false;
				boolean sd = false;
				if (doctorrediobtn.isSelected()) {
					Doctor doctor = new Doctor();
					doctor.setName(nameTextField.getText());
					doctor.setFamily(familyTextField.getText());
					doctor.setMobileNumber(mobileNumberTextField.getText());
					doctor.setPhoneNumber(homeNumberTextField.getText());
					doctor.setUserID(usernameTextField.getText());

					Users user = new Users();

					user.setPassword(passwordTextField.getText());
					user.setUsername(usernameTextField.getText());

					try {
						u = new BusinessServiceFactory().createUserService()
								.addUser(user);
						d = new BusinessServiceFactory().createDoctorService()
								.addDoctor(doctor);

						new BusinessServiceFactory().createSystemDoctor()
								.setSystemDoctor(
										new BusinessServiceFactory()
												.createDoctorService()
												.getDoctorByUsername(
														user.getUsername()));

					} catch (RemoteException e) {

						e.printStackTrace();
					}

				} else {

					Secretary secretary = new Secretary();
					secretary.setName(nameTextField.getText());
					secretary.setFamily(familyTextField.getText());
					secretary.setMobileNumber(mobileNumberTextField.getText());
					secretary.setPhoneNumber(homeNumberTextField.getText());
					secretary.setUserID(usernameTextField.getText());

					Users user = new Users();

					user.setPassword(passwordTextField.getText());
					user.setUsername(usernameTextField.getText());

					try {
						u = new BusinessServiceFactory().createUserService()
								.addUser(user);
						s = new BusinessServiceFactory()
								.createSecretaryService().addSecretary(
										secretary);
						Doctor curDoc = new BusinessServiceFactory()
								.createSystemDoctor().getDoctor();
						Secretary newSecretary = new BusinessServiceFactory()
								.createSecretaryService().getSecretary(
										usernameTextField.getText());
						sd = new BusinessServiceFactory()
								.createSecretaryOfDoctorsService()
								.addSecretaryOfDoctor(
										new SecretariesOfDoctors(
												curDoc.getId(), newSecretary
														.getId()));

					} catch (RemoteException e) {

						e.printStackTrace();
					}

				}

				clearFields();
				if (u && s && sd) {
					ShowMessage.show("منشی با موفقیت اضافه شد",
							ShowMessage.INFORMATION_ID, AddUserFrame.this);
				} else if (u && d) {
					ShowMessage.show(".دکتر با موفقیت اضافه شد",
							ShowMessage.INFORMATION_ID, AddUserFrame.this);
					GUIManager.disposeMainFrame();
					GUIManager.invisibleAddUserFrame();
					GUIManager.visibleMainFrame();
					try {
						addSystermParameters();
					} catch (RemoteException e) {

						e.printStackTrace();
					}
				} else {
					ShowMessage.show("اضافه کردن کاربر با مشکل مواجه شد.",
							ShowMessage.ERROR_ID, AddUserFrame.this);
				}

			} else {
				ShowMessage.show(".شماره موبایل وارد شده صحیح نمی باشد", 1,
						AddUserFrame.this);
			}

		} else if (!usernameTextField.getText().isEmpty()
				&& !passwordTextField.getText().isEmpty()) {
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();

			if (new BusinessServiceFactory().createUserService().isAuthorized(
					username, password)) {
				Secretary s = new BusinessServiceFactory()
						.createSecretaryService().getSecretary(username);
				if (s != null) {
					SecretariesOfDoctors sod = new SecretariesOfDoctors(
							new BusinessServiceFactory().createSystemDoctor()
									.getDoctor().getId(), s.getId());
					if (new BusinessServiceFactory()
							.createSecretaryOfDoctorsService()
							.addSecretaryOfDoctor(sod)) {
						ShowMessage.show("اضافه کردن کاربر با مشکل مواجه شد.",
								ShowMessage.ERROR_ID, AddUserFrame.this);
					}

				} else {
					ShowMessage.show("لطفا تمام موارد اجباری را وارد نمایید",
							1, AddUserFrame.this);
				}
			} else {
				ShowMessage.show("لطفا تمام موارد اجباری را وارد نمایید", 1,
						AddUserFrame.this);
			}

		} else if (nameTextField.getText().isEmpty()
				|| familyTextField.getText().isEmpty()
				|| usernameTextField.getText().isEmpty()
				|| passwordTextField.getText().isEmpty()) {
			ShowMessage.show("لطفا تمام موارد اجباری را وارد نمایید", 1,
					AddUserFrame.this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			saveAction();
		} catch (RemoteException e) {

			e.printStackTrace();
		}

	}

	private void addSystermParameters() throws RemoteException {

		Doctor d = new Doctor();
		d.setId(1);
		ArrayList<SystemParameters> systemParameters = new BusinessServiceFactory()
				.createSystemParametersService().getAllParameters(d);
		int doctorID = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor().getId();

		for (SystemParameters s : systemParameters) {

			s.setDoctorID(doctorID);
			new BusinessServiceFactory().createSystemParametersService()
					.addSystemParameter(s);
		}
	}
}
