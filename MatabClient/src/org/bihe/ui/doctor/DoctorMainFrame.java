package org.bihe.ui.doctor;

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

public class DoctorMainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JInternalFrame jif;



	public DoctorMainFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 700);
		setLocationRelativeTo(null);
		setTitle("منو اصلی");

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

		JButton addUserButton = new JButton();
		addUserButton.setActionCommand("addUser");
		
		
		addUserButton.setContentAreaFilled(false);
		addUserButton.setIcon(new ImageIcon(Resource.getImage("addUser.png")));
		addUserButton.setBorder(BorderFactory.createEmptyBorder());
		addUserButton.setRolloverIcon(new ImageIcon(Resource.getImage("addUserR.png")));
		addUserButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addUserButton.setFocusable(false);
		addUserButton.addActionListener(this);
		panel.add(addUserButton);

		JButton userList = new JButton();
		userList.setActionCommand("userList");


		userList.setContentAreaFilled(false);
		userList.setIcon(new ImageIcon(Resource.getImage("usersList.png")));
		userList.setBorder(BorderFactory.createEmptyBorder());
		userList.setRolloverIcon(new ImageIcon(Resource.getImage("usersListR.png")));
		userList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userList.setFocusable(false);

		userList.addActionListener(this);
		panel.add(userList);

		JButton searchButton = new JButton();
		searchButton.setActionCommand("search");
		
		searchButton.addActionListener(this);
		
		
		searchButton.setContentAreaFilled(false);
		searchButton.setIcon(new ImageIcon(Resource.getImage("search.png")));
		searchButton.setBorder(BorderFactory.createEmptyBorder());
		searchButton.setRolloverIcon(new ImageIcon(Resource.getImage("searchR.png")));
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.setFocusable(false);

		panel.add(searchButton);
	

		jif = new AppFrame();
		contentPane.add(jif.getContentPane(), BorderLayout.CENTER);
		// contentPane.add(new ShortSearchPanel(), BorderLayout.NORTH);

		try {
			Date d = new Date();
			addApointments(d);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void addApointments(Date date) throws RemoteException {

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
					((AppFrame) jif).addActivity(start.getTime(),
							end.getTime(),
							temp.getName() + " " + temp.getFamily(), p.getId());

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

		case "userList":
			GUIManager.visibleUserListFrame();
			break;

		case "addUser":
			GUIManager.visibleAddUserFrame();
			break;

		case "search":
			GUIManager.visibleSearchFrame();
			break;

		case "fixHolidays":
			GUIManager.visibleFixHolidays();
			;
			break;
		case "ipSetting":

			GUIManager.visibleIpFrame();

			break;

		case "sysParameters":
			GUIManager.visibleSystemParameters();
			break;
		default:
			break;
		}
	}
}
