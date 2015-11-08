package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bihe.Resource.Resource;
import org.bihe.bean.Constant;
import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.business.factory.BusinessServiceFactory;

public class SelectDoctorFrame extends JFrame implements ActionListener,
		KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	private JButton enter;

	private ArrayList<Doctor> doctors;

	private JComboBox<Doctor> comboBox;
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 160;



	/**
	 * Create the frame.
	 */
	public SelectDoctorFrame() {
		
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel label = new JLabel("لطفا دکتر مورد نظر را انتخاب کنید:");
		label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPane.add(label, BorderLayout.NORTH);

		enter = new JButton();
		
		enter.setContentAreaFilled(false);
		enter.setIcon(new ImageIcon(Resource.getImage("Login.png")));
		enter.setBorder(BorderFactory.createEmptyBorder());
		enter.setRolloverIcon(new ImageIcon(Resource.getImage("LoginR.png")));
		enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		enter.setFocusable(false);
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		contentPane.add(enter, BorderLayout.SOUTH);

		JPanel panel = new JPanel(new FlowLayout());
		contentPane.add(panel, BorderLayout.CENTER);

		comboBox = new JComboBox<Doctor>();
		comboBox.setPreferredSize(new Dimension(200, 30));
		comboBox.addKeyListener(this);

		// ADD DOCTORS TO COMBOBOX

		panel.add(comboBox);
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
		for (int i = 0; i < this.doctors.size(); i++) {
			this.comboBox.addItem(this.doctors.get(i));
		}
	}

	private void enter() {
		new BusinessServiceFactory().createSystemDoctor().setSystemDoctor(
				(Doctor) this.comboBox.getSelectedItem());

		GUIManager.invisibleSelectDoctorFrame();
		GUIManager.visibleMainFrame();

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		switch (command) {
		case "enter":

			enter();
			sendMessages();
			break;

		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enter();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	private void sendMessages() {
		Doctor d = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		try {
			ArrayList<SystemParameters> sys = new BusinessServiceFactory()
					.createSystemParametersService().getAllParameters(d);
			for (SystemParameters systemParameters : sys) {
				if (systemParameters.getEnglishName().equals(
						Constant.IS_MESSAGE_SENDING_ACTIVE_ID)) {
					new BusinessServiceFactory()
							.createGeneratedMessagesService()
							.sendWaitingMessage(d);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
