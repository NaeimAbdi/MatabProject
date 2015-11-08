package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bihe.Resource.Resource;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.bean.VisitTimes;
import org.bihe.business.factory.BusinessServiceFactory;

public class SecretaryMainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JInternalFrame jif;

	/**
	 * Create the frame.
	 */



	public SecretaryMainFrame() {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		
		setTitle("منو اصلی");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu_1 = new JMenu("تنظیمات");

		menuBar.add(menu_1);

		JMenuItem sysParameters = new JMenuItem("تنظیمات کلی");

		menu_1.add(sysParameters);
		sysParameters.setActionCommand("sysParameters");
		sysParameters.addActionListener(this);

		JMenuItem fixHolidays = new JMenuItem("تعطیلات ثابت");
		menu_1.add(fixHolidays);
		fixHolidays.setActionCommand("fixHolidays");
		fixHolidays.addActionListener(this);

		JMenuItem doctorHoliDays = new JMenuItem("تعطیلات دکتر");
		menu_1.add(doctorHoliDays);
		doctorHoliDays.setActionCommand("doctorHoliDays");
		doctorHoliDays.addActionListener(this);

		JMenuItem ipSetting = new JMenuItem("تنظیمات آی پی");
		menu_1.add(ipSetting);
		ipSetting.setActionCommand("ipSetting");
		ipSetting.addActionListener(this);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JButton addPatientBtn = new JButton();
		addPatientBtn.setActionCommand("addPatient");
		addPatientBtn.addActionListener(this);
		addPatientBtn.setContentAreaFilled(false);

		addPatientBtn
				.setIcon(new ImageIcon(Resource.getImage("AddPatient.png")));
		addPatientBtn.setBorder(BorderFactory.createEmptyBorder());
		addPatientBtn.setRolloverIcon(new ImageIcon(Resource
				.getImage("AddPatientR.png")));
		addPatientBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addPatientBtn.setFocusable(false);

		// addPatientBtn.setIcon(new ImageIcon(Resource.getImage("AddP.png")));

		panel.add(addPatientBtn);

		JButton searchPatient = new JButton();
		searchPatient.setActionCommand("search");
		searchPatient.setContentAreaFilled(false);
		searchPatient.addActionListener(this);

		searchPatient.setIcon(new ImageIcon(Resource.getImage("search.png")));
		searchPatient.setBorder(BorderFactory.createEmptyBorder());
		searchPatient.setRolloverIcon(new ImageIcon(Resource
				.getImage("searchR.png")));
		searchPatient.setCursor(new Cursor(Cursor.HAND_CURSOR));

		searchPatient.setFocusable(false);

		panel.add(searchPatient);

		JButton addVisitTimeBtn = new JButton();
		addVisitTimeBtn.setActionCommand("visitTime");
		addVisitTimeBtn.addActionListener(this);

		
		addVisitTimeBtn.setContentAreaFilled(false);
		addVisitTimeBtn.setIcon(new ImageIcon(Resource.getImage("addVisitTime.png")));
		addVisitTimeBtn.setBorder(BorderFactory.createEmptyBorder());
		addVisitTimeBtn.setRolloverIcon(new ImageIcon(Resource.getImage("addVisitTimeR.png")));
		addVisitTimeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addVisitTimeBtn.setFocusable(false);

		panel.add(addVisitTimeBtn);

		JButton sendSms = new JButton();

		sendSms.setActionCommand("sendSms");
		sendSms.setContentAreaFilled(false);
		sendSms.addActionListener(this);

		sendSms.setIcon(new ImageIcon(Resource.getImage("sendMsg.png")));
		sendSms.setBorder(BorderFactory.createEmptyBorder());
		sendSms.setRolloverIcon(new ImageIcon(Resource.getImage("sendMsgR.png")));
		sendSms.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendSms.setFocusable(false);

		panel.add(sendSms);

		jif = new org.bihe.ui.secretary.AppFrame();
		contentPane.add(jif.getContentPane(), BorderLayout.CENTER);
		try {
			Date d = new Date();
			addApointments(d);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public static void addApointments(Date date) throws RemoteException {

		ArrayList<VisitTimes> visilist = new BusinessServiceFactory()
				.createVisitTimesService().getAllVisitTimes(
						date,
						new BusinessServiceFactory().createSystemDoctor()
								.getDoctor());
		if (visilist != null) {
			for (int i = 0; i < visilist.size(); i++) {
				VisitTimes v = visilist.get(i);
				try {
					Date start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.parse(v.getStart());
					Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.parse(v.getFinish());

					PatientFolder pf = new PatientFolder();
					pf.setPatientID(v.getPatientID());
					pf.setDoctorID(new BusinessServiceFactory()
							.createSystemDoctor().getDoctor().getId());
					Patient p = new Patient();

					p.setId(v.getPatientID());
					Patient temp = new BusinessServiceFactory()
							.createPatientService().search(p, pf).get(0);
					((org.bihe.ui.secretary.AppFrame) jif).addActivity(
							start.getTime(), end.getTime(), temp.getName()
									+ " " + temp.getFamily(), p.getId());

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		switch (command) {

		case "sendSms":
			GUIManager.visibleSendSMSFrame();
			break;
		case "fixHolidays":
			try {
				GUIManager.visibleFixHolidaysFrame();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case "sysParameters":
			GUIManager.visibleSystemParameters();
			break;

		case "visitTime":
			AddVisitTimeFrame avtf = new AddVisitTimeFrame();
			avtf.setVisible(true);
			break;

		case "search":
			GUIManager.visibleSearchFrame();
			break;
		case "addPatient":
			AddPatientFrame addPatient = new AddPatientFrame();
			addPatient.setVisible(true);
			break;

		case "doctorHoliDays":
			GUIManager.visibleDoctorHolidaysFrame();
			break;
		case "ipSetting":

			GUIManager.visibleIpFrame();
			break;

		default:
			break;
		}
	}
}
