package org.bihe.ui.login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.ui.secretary.GUIManager;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameField;
	private JPasswordField passwordField;

	private static final int WIDTH = 350;
	private static final int HEIGHT = 481;

	/**
	 * Create the frame.
	 */

	public LoginFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("ورود");
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setForeground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(180, 180,
				180)), "ورود کاربران", TitledBorder.CENTER, TitledBorder.TOP,
				null, SystemColor.inactiveCaptionText));
		panel_1.setBounds(10, 11, 324, 430);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton loginButton = new JButton();
		loginButton.setIcon(new ImageIcon(Resource.getImage("Login.png")));
		loginButton.setRolloverIcon(new ImageIcon(Resource
				.getImage("LoginR.png")));

		loginButton.setContentAreaFilled(false);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setBounds(91, 343, 140, 51);
		panel_1.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginButton.setActionCommand("login");

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Resource
				.getImage("MatabLogoResized.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(63, 29, 185, 150);
		panel_1.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(46, 280, 160, 22);
		panel_1.add(passwordField);
		passwordField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("کلمه ی عبور:");
		lblNewLabel_1.setBounds(216, 283, 98, 16);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);

		userNameField = new JTextField();
		userNameField.setBounds(46, 230, 160, 22);
		panel_1.add(userNameField);
		userNameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("شناسه ی کاربری:");
		lblNewLabel.setBounds(216, 233, 111, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.addKeyListener(this);
		loginButton.addActionListener(this);
	}

	private void showDialog(boolean isCorrect) {
		if (isCorrect == false) {
			JOptionPane
					.showMessageDialog(
							this,
							"شناسه ی کاربری یا رمز عبور اشتباه است، لطفا مجددا تلاش فرمایید.",
							"خطا", JOptionPane.ERROR_MESSAGE);
			userNameField.setText("");
			passwordField.setText("");

		}
	}

	@SuppressWarnings("deprecation")
	private void checkUserAndPass() {
		if (!userNameField.getText().equals("")
				&& !passwordField.getText().equals("")) {
			String username = userNameField.getText();
			String password = passwordField.getText();

			try {
				if (new BusinessServiceFactory().createUserService()
						.isAuthorized(username, password)) {

					Secretary s = new BusinessServiceFactory()
							.createSecretaryService().getSecretary(username);
					Doctor d = null;
					if (s != null) {
						GUIManager.invisibleLoginFrame();
						GUIManager.visibleSelectDoctorFrame();
						GUIManager.getselectedDoctroFrame().setDoctors(
								new BusinessServiceFactory()
										.createSecretaryService()
										.findDoctorsForSecretary(s));


					}

					else if ((d = new BusinessServiceFactory()
							.createDoctorService()
							.getDoctorByUsername(username)) != null) {
						new BusinessServiceFactory().createSystemDoctor()
								.setSystemDoctor(d);
						GUIManager.invisibleLoginFrame();
						org.bihe.ui.doctor.GUIManager.visibleMainFrame();

						new BusinessServiceFactory().createSystemDoctor()
								.setSystemDoctor(d);
						if (d.getId() == 1) {
							org.bihe.ui.doctor.GUIManager.visibleAddUserFrame();
						}
					}
				} else {

					showDialog(false);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(LoginFrame.this,
					"لطفا نام کاربری یا رمز عبور را وارد فرمایید", "خطا",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "exit":
			System.exit(0);
			break;
		case "login":
			checkUserAndPass();
			break;

		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			checkUserAndPass();
			userNameField.requestFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	
}
