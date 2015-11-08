package org.bihe.ui.secretary

;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.bihe.Resource.Resource;
import org.bihe.bean.Constant;
import org.bihe.bean.Doctor;
import org.bihe.bean.SystemParameters;
import org.bihe.business.factory.BusinessServiceFactory;

public class ParametersFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372011176441418654L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tmodel;
	private int size;
	private ArrayList<SystemParameters> syslist;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private int startM = 0;
	private int startH = 0;
	private int endH = 0;
	private int endM = 0;
	private int duration = 0;
	@SuppressWarnings("unused")
	private String daysOfWork;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public ParametersFrame() {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setTitle("تنظیمات");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton saveBtn = new JButton();
		
		saveBtn.setContentAreaFilled(false);
		saveBtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		saveBtn.setBorder(BorderFactory.createEmptyBorder());
		saveBtn.setRolloverIcon(new ImageIcon(Resource.getImage("saveR.png")));
		saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveBtn.setFocusable(false);

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		southPanel.add(saveBtn);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();

		panel.add(scrollPane);

		tmodel = new DefaultTableModel(new Object[][] {}, new String[] {
				"توضیحات", "تنظیمات", "ردیف" }) {
			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(tmodel);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);
		// table.getColumnModel().getColumn(0)
		// .setCellRenderer(new TableCellRenderer() {
		//
		// @Override
		// public Component getTableCellRendererComponent(JTable arg0,
		// Object arg1, boolean arg2, boolean arg3, int arg4,
		// int arg5) {
		// JPanel jp = new JPanel(new FlowLayout());
		// jp.add(new Button("MM"));
		// return jp;
		// }
		// });
		scrollPane.setViewportView(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(50);

		try {
			addAll();
		} catch (RemoteException e) {

			e.printStackTrace();
		}

		this.repaint();
		this.validate();
	}

	private void addAll() throws RemoteException {
		syslist = new BusinessServiceFactory().createSystemParametersService()
				.getAllParameters(
						new BusinessServiceFactory().createSystemDoctor()
								.getDoctor());
		for (SystemParameters sy : syslist) {
			String temp = sy.getValue();

			if (temp != null) {
				Object[] sttr = { temp, sy.getFarsiName(), sy.getId() + "" };
				tmodel.addRow(sttr);
			} else {
				Object[] sttr = { "", sy.getFarsiName(), sy.getId() + "" };
				tmodel.addRow(sttr);
			}

		}
		size = syslist.size();
	}

	private void update() {
		for (int i = 0; i < size; i++) {
			String value = String.valueOf(table.getValueAt(i, 0));
			if (value == null || value.equals("")) {
				ShowMessage.show("لطفا تمامی موارد را پر کنید.",
						ShowMessage.ERROR_ID, this);
				return;
			}
		}

		ArrayList<SystemParameters> sysParLis = new ArrayList<>();

		SystemParameters sp = new SystemParameters();

		String daysOfWork = String.valueOf(table.getValueAt(0, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.WORK_DAYS);
		sp.setValue(daysOfWork);
		sysParLis.add(sp);

		String startHour = String.valueOf(table.getValueAt(1, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.DOCTOR_START_HOUR_ID);
		sp.setValue(startHour);
		sysParLis.add(sp);

		String endHour = String.valueOf(table.getValueAt(2, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.DOCTOR_END_HOUR_ID);
		sp.setValue(endHour);
		sysParLis.add(sp);

		String isMessageSending = String.valueOf(table.getValueAt(3, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.IS_MESSAGE_SENDING_ACTIVE_ID);
		sp.setValue(isMessageSending);
		sysParLis.add(sp);

		String smsDuration = String.valueOf(table.getValueAt(4, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.MESSAGE_SENDING_DURATION_ID);
		sp.setValue(smsDuration);
		sysParLis.add(sp);

		String visitDuration = String.valueOf(table.getValueAt(5, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.VISIT_DURATION_ID);
		sp.setValue(visitDuration);
		sysParLis.add(sp);

		String webServiceAddress = String.valueOf(table.getValueAt(6, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.WEB_SERVIS_ADDRESS_ID);
		sp.setValue(webServiceAddress);
		sysParLis.add(sp);

		String webServiceUsername = String.valueOf(table.getValueAt(7, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.WEB_SERVIS_USERNAME_ID);
		sp.setValue(webServiceUsername);
		sysParLis.add(sp);

		String webServicePassword = String.valueOf(table.getValueAt(8, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.WEB_SERVIS_PASSWORD_ID);
		sp.setValue(webServicePassword);
		sysParLis.add(sp);

		String from = String.valueOf(table.getValueAt(9, 0));
		sp = new SystemParameters();
		sp.setEnglishName(Constant.FROM_ID);
		sp.setValue(from);
		sysParLis.add(sp);

		Doctor d = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		for (int i = 0; i < sysParLis.size(); i++) {
			SystemParameters s = sysParLis.get(i);
			s.setDoctorID(d.getId());

			try {
				new BusinessServiceFactory().createSystemParametersService()
						.updateParameters(s);
			} catch (RemoteException e) {

				e.printStackTrace();
			}

		}

		if (isMessageSending.equals("1")) {
			try {
				new BusinessServiceFactory().createGeneratedMessagesService()
						.sendWaitingMessage(d);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		String[] strt = startHour.split(":");

		startH = Integer.parseInt(strt[0]);
		endH = Integer.parseInt(strt[1]);

		String[] end = endHour.split(":");
		endH = Integer.parseInt(end[0]);
		endM = Integer.parseInt(end[1]);

		duration = Integer.parseInt(visitDuration);

		try {
			for (int i = 0; i < size; i++) {
				if (syslist.get(i).getEnglishName()
						.equals(Constant.VISIT_DURATION_ID)) {
					if (visitDuration.equals(syslist.get(i).getValue())) {
					} else if ((syslist.get(i).getValue() == null || syslist
							.get(i).getValue().isEmpty())
							&& !visitDuration.equals("")) {
						new BusinessServiceFactory().createVisitTimesService()
								.createAllVisitTimes(d, startH, startM, endH,
										endM, duration, daysOfWork);
					} else if (!(visitDuration
							.equals(syslist.get(i).getValue()))) {

						String[] options = { "بله", "خیر" };
						int opt = JOptionPane
								.showOptionDialog(
										this,
										"تنظیماتی که شما اعمال کردید ممکن است باعث ایجاد تغییرات زیادی در زمان های نوبتی در اینده شود. آیا مایلید تغییرات اعمال شود؟",
										"تایید", JOptionPane.DEFAULT_OPTION,
										JOptionPane.WARNING_MESSAGE, null,
										options, options[0]);
						if (opt <= JOptionPane.YES_OPTION) {

							ShowMessage.show("تغییرات اعمال شد",
									ShowMessage.INFORMATION_ID, this);
							Doctor doctor = new BusinessServiceFactory()
									.createSystemDoctor().getDoctor();
							new BusinessServiceFactory()
									.createVisitTimesService()
									.updateWithNewDuration(daysOfWork, doctor,
											startH, startM, duration, endH,
											endM);

						} else {

							break;
						}

					}
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
