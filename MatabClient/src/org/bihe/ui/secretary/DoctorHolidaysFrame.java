package org.bihe.ui.secretary;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.arso.calendar.FarsiCalendar;
import org.bihe.Resource.Resource;
import org.bihe.bean.Constant;
import org.bihe.bean.Doctor;
import org.bihe.bean.Holidays;
import org.bihe.bean.SystemParameters;
import org.bihe.business.factory.BusinessServiceFactory;

public class DoctorHolidaysFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descTextField;
	private JLabel dateLable;
	private JTable table;
	private DefaultTableModel tmodel;
	private DaysPanel days;

	private static final int WIDTH = 700;
	private static final int HEIGHT = 650;


	/**
	 * Create the frame.
	 */
	public DoctorHolidaysFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());

		setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		setTitle("تعطیلات دکتر");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 66, 47, 61, 69, 80, 80, 66,
				80, 80, 80 };
		gbl_contentPane.rowHeights = new int[] { 65, 65, 65, 65, 65, 65, 65,
				65, 65, 65, 50, 50 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0 };
		contentPane.setLayout(gbl_contentPane);

		final FarsiCalendar farsiCalendar = new FarsiCalendar(new Date(), false);
		farsiCalendar.addDaySelectionActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				dateLable.setText(sdf.format(farsiCalendar
						.getJalaliSelectedDate()));
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 3;
		gbc_btnNewButton.gridwidth = 9;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(farsiCalendar, gbc_btnNewButton);

		JButton btnInsert = new JButton();
		btnInsert.setActionCommand("insert");
		btnInsert.addActionListener(this);
		
		
		btnInsert.setContentAreaFilled(false);
		btnInsert.setIcon(new ImageIcon(Resource.getImage("save.png")));
		btnInsert.setBorder(BorderFactory.createEmptyBorder());
		btnInsert.setRolloverIcon(new ImageIcon(Resource.getImage("saveR.png")));
		btnInsert.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnInsert.setFocusable(false);
		
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.gridwidth = 3;
		gbc_btnInsert.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 3;
		contentPane.add(btnInsert, gbc_btnInsert);

		descTextField = new JTextField();
		descTextField
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_descTextField = new GridBagConstraints();
		gbc_descTextField.gridwidth = 3;
		gbc_descTextField.insets = new Insets(0, 0, 5, 5);
		gbc_descTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descTextField.gridx = 3;
		gbc_descTextField.gridy = 3;
		contentPane.add(descTextField, gbc_descTextField);
		descTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("مناسبت");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 6;
		gbc_lblNewLabel_1.gridy = 3;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		dateLable = new JLabel();
		dateLable.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 7;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(dateLable, gbc_lblNewLabel);

		tmodel = new DefaultTableModel(new Object[][] {}, new String[] {
				"مناسبت", " روز" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setModel(tmodel);
		scrollPane.setViewportView(table);

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		try {
			fillTable();
		} catch (RemoteException e1) {

			e1.printStackTrace();
		}
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setMaxWidth(160);
		JLabel label = new JLabel("روزهای کاری هفته :");
		label.setFont(new Font("Sakkal Majalla", Font.PLAIN, 27));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 7;
		gbc_label.gridy = 8;
		contentPane.add(label, gbc_label);

		days = new DaysPanel();
		days.setForeground(Color.WHITE);
		days.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 8;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 9;
		contentPane.add(days, gbc_btnNewButton_1);

		JButton addButton = new JButton();
		
		addButton.setContentAreaFilled(false);
		addButton.setIcon(new ImageIcon(Resource.getImage("save.png")));
		addButton.setBorder(BorderFactory.createEmptyBorder());
		addButton.setRolloverIcon(new ImageIcon(Resource.getImage("saveR.png")));
		addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addButton.setFocusable(false);
		

		addButton.setActionCommand("add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.gridwidth = 8;
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 10;
		contentPane.add(addButton, gbc_addButton);
		setDays();

	}

	private void setDays() {
		Doctor thisDoctor = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		try {
			ArrayList<SystemParameters> sys = new BusinessServiceFactory()
					.createSystemParametersService().getAllParameters(
							thisDoctor);
			for (SystemParameters s : sys) {
				if (s.getEnglishName().equals(Constant.WORK_DAYS)) {
					this.days.setDays(s.getValue());
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void addHoliday() throws RemoteException {
		Holidays holiday = new Holidays();
		holiday.setDescription(descTextField.getText());
		holiday.setHolidayDate(dateLable.getText());

		new BusinessServiceFactory().createHolidaysService()
				.addHoliday(holiday);

	}

	private void fillTable() throws RemoteException {

		ArrayList<Holidays> allHolidays = new BusinessServiceFactory()
				.createHolidaysService().getAllHolidays();
		for (int i = 0; table.getRowCount() > i; i++) {
			tmodel.removeRow(i);
		}
		for (Holidays h : allHolidays) {

			String[] row = { h.getDescription(),
					String.valueOf(h.getHolidayDate()) }; //$NON-NLS-1$

			tmodel.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "add":
			String daysOfWork = days.daysOfWork();
			SystemParameters s = new SystemParameters();
			s.setEnglishName(Constant.WORK_DAYS);
			s.setValue(daysOfWork);
			try {
				new BusinessServiceFactory().createSystemParametersService()
						.updateParameters(s);
			} catch (RemoteException e2) {

				e2.printStackTrace();
			}
			break;

		case "insert":
			try {
				addHoliday();
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			break;
		}
	}

}
