package org.bihe.ui.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.Secretary;
import org.bihe.bean.Users;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.ui.secretary.ShowMessage;

public class UserListFrame extends JFrame implements MouseListener,
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTxtfield;
	private JTextField familyTxtfield;
	private JTextField telnumtextfield;
	private JTextField usernameTxtfield;
	private JTextField passwordTxtfield;
	private JTextField telhomeTxtfield;

	private static final int WIDTH = 650;
	private static final int HEIGHT = 550;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";

	private ArrayList<Secretary> secreteryList;
	private JButton deleteBtn;
	private JButton saveBtn;
	private ArrayList<Secretary> secList;
	private JTable table;
	private JLabel lblNewLabel;
	private DefaultTableModel tmodel;
	private JScrollPane scrollPane;


	/**
	 * Create the frame.
	 */
	public UserListFrame() {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());

		setTitle("لیست کاربران");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(new LineBorder(
				new Color(192, 192, 192)), "لیست کاربران", TitledBorder.RIGHT,
				TitledBorder.TOP, null, new Color(192, 192, 192)));
		panel.setPreferredSize(new Dimension(650, 200));
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50 };
		gbl_panel.rowHeights = new int[] { 35, 35, 35, 35, 35 };
		gbl_panel.columnWeights = new double[] { Double.MIN_VALUE, 1.0, 1.0,
				0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		telnumtextfield = new JTextField();
		telnumtextfield.setHorizontalAlignment(SwingConstants.RIGHT);
		telnumtextfield.setEnabled(false);
		telnumtextfield.addKeyListener(numberFormat());
		GridBagConstraints gbc_telnumtextfield = new GridBagConstraints();
		gbc_telnumtextfield.gridwidth = 2;
		gbc_telnumtextfield.insets = new Insets(0, 0, 5, 5);
		gbc_telnumtextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_telnumtextfield.gridx = 1;
		gbc_telnumtextfield.gridy = 1;
		panel.add(telnumtextfield, gbc_telnumtextfield);
		telnumtextfield.setColumns(10);

		JLabel tellbl = new JLabel("تلفن(همراه):");
		GridBagConstraints gbc_tellbl = new GridBagConstraints();
		gbc_tellbl.gridwidth = 2;
		gbc_tellbl.insets = new Insets(0, 0, 5, 5);
		gbc_tellbl.anchor = GridBagConstraints.WEST;
		gbc_tellbl.gridx = 3;
		gbc_tellbl.gridy = 1;
		panel.add(tellbl, gbc_tellbl);

		familyTxtfield = new JTextField();
		familyTxtfield.setHorizontalAlignment(SwingConstants.RIGHT);
		familyTxtfield.setEnabled(false);
		GridBagConstraints gbc_familyTxtfield = new GridBagConstraints();
		gbc_familyTxtfield.gridwidth = 2;
		gbc_familyTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_familyTxtfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyTxtfield.gridx = 5;
		gbc_familyTxtfield.gridy = 1;
		panel.add(familyTxtfield, gbc_familyTxtfield);
		familyTxtfield.setColumns(10);

		JLabel familylbl = new JLabel("نام خانوادگی:*");
		GridBagConstraints gbc_familylbl = new GridBagConstraints();
		gbc_familylbl.anchor = GridBagConstraints.WEST;
		gbc_familylbl.gridwidth = 2;
		gbc_familylbl.insets = new Insets(0, 0, 5, 5);
		gbc_familylbl.gridx = 7;
		gbc_familylbl.gridy = 1;
		panel.add(familylbl, gbc_familylbl);

		nameTxtfield = new JTextField();
		nameTxtfield.setEnabled(false);
		nameTxtfield.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_nameTxtfield = new GridBagConstraints();
		gbc_nameTxtfield.gridwidth = 2;
		gbc_nameTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_nameTxtfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTxtfield.gridx = 9;
		gbc_nameTxtfield.gridy = 1;
		panel.add(nameTxtfield, gbc_nameTxtfield);
		nameTxtfield.setColumns(10);

		JLabel namelbl = new JLabel("نام:*");
		GridBagConstraints gbc_namelbl = new GridBagConstraints();
		gbc_namelbl.anchor = GridBagConstraints.WEST;
		gbc_namelbl.gridwidth = 2;
		gbc_namelbl.insets = new Insets(0, 0, 5, 5);
		gbc_namelbl.gridx = 11;
		gbc_namelbl.gridy = 1;
		panel.add(namelbl, gbc_namelbl);

		telhomeTxtfield = new JTextField();
		telhomeTxtfield.setHorizontalAlignment(SwingConstants.RIGHT);
		telhomeTxtfield.addKeyListener(numberFormat());
		telhomeTxtfield.setEnabled(false);
		GridBagConstraints gbc_telhomeTxtfield = new GridBagConstraints();
		gbc_telhomeTxtfield.gridwidth = 2;
		gbc_telhomeTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_telhomeTxtfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_telhomeTxtfield.gridx = 1;
		gbc_telhomeTxtfield.gridy = 3;
		panel.add(telhomeTxtfield, gbc_telhomeTxtfield);
		telhomeTxtfield.setColumns(10);

		JLabel homelbl = new JLabel("تلفن(ثابت):");
		GridBagConstraints gbc_homelbl = new GridBagConstraints();
		gbc_homelbl.anchor = GridBagConstraints.WEST;
		gbc_homelbl.gridwidth = 2;
		gbc_homelbl.insets = new Insets(0, 0, 5, 5);
		gbc_homelbl.gridx = 3;
		gbc_homelbl.gridy = 3;
		panel.add(homelbl, gbc_homelbl);

		passwordTxtfield = new JTextField();
		passwordTxtfield.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordTxtfield.setEnabled(false);
		GridBagConstraints gbc_passwordTxtfield = new GridBagConstraints();
		gbc_passwordTxtfield.gridwidth = 2;
		gbc_passwordTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTxtfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTxtfield.gridx = 5;
		gbc_passwordTxtfield.gridy = 3;
		panel.add(passwordTxtfield, gbc_passwordTxtfield);
		passwordTxtfield.setColumns(10);

		JLabel passwordlbl = new JLabel("کلمه عبور:*");
		GridBagConstraints gbc_passwordlbl = new GridBagConstraints();
		gbc_passwordlbl.anchor = GridBagConstraints.WEST;
		gbc_passwordlbl.gridwidth = 2;
		gbc_passwordlbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordlbl.gridx = 7;
		gbc_passwordlbl.gridy = 3;
		panel.add(passwordlbl, gbc_passwordlbl);

		usernameTxtfield = new JTextField();
		usernameTxtfield.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameTxtfield.setEnabled(false);
		GridBagConstraints gbc_usernameTxtfield = new GridBagConstraints();
		gbc_usernameTxtfield.gridwidth = 2;
		gbc_usernameTxtfield.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTxtfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTxtfield.gridx = 9;
		gbc_usernameTxtfield.gridy = 3;
		panel.add(usernameTxtfield, gbc_usernameTxtfield);
		usernameTxtfield.setColumns(10);

		JLabel usernamelbl = new JLabel("نام کاربری:*");
		GridBagConstraints gbc_usernamelbl = new GridBagConstraints();
		gbc_usernamelbl.anchor = GridBagConstraints.WEST;
		gbc_usernamelbl.gridwidth = 2;
		gbc_usernamelbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernamelbl.gridx = 11;
		gbc_usernamelbl.gridy = 3;
		panel.add(usernamelbl, gbc_usernamelbl);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		table.setToolTipText("");
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tmodel = new DefaultTableModel(new Object[][] {}, new String[] {
				"نام کاربری", "نقش", "نام خانوادگی", "نام", "شناسه" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(tmodel);
		scrollPane.setViewportView(table);

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		table.addMouseListener(this);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);

		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMaxWidth(70);

		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setMaxWidth(170);

		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setMaxWidth(170);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setMaxWidth(70);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setPreferredSize(new Dimension(650, 100));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 50, 50, 50, 50, 50, 50, 50, 50,
				50, 50, 50, 50, 50 };
		gbl_panel_2.rowHeights = new int[] { 25, 25, 25, 0, 25 };
		gbl_panel_2.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 0.0, 0.0 };
		panel_2.setLayout(gbl_panel_2);

		deleteBtn = new JButton("حذف");
		deleteBtn.setActionCommand("delete");
		deleteBtn.setEnabled(false);
		deleteBtn.addActionListener(this);
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.anchor = GridBagConstraints.EAST;
		gbc_deleteBtn.gridwidth = 3;
		gbc_deleteBtn.insets = new Insets(0, 0, 5, 5);
		gbc_deleteBtn.gridx = 4;
		gbc_deleteBtn.gridy = 2;
		panel_2.add(deleteBtn, gbc_deleteBtn);

		saveBtn = new JButton("ذخیره");
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(this);
		saveBtn.setEnabled(false);
		GridBagConstraints gbc_saveBtn = new GridBagConstraints();
		gbc_saveBtn.anchor = GridBagConstraints.WEST;
		gbc_saveBtn.gridwidth = 2;
		gbc_saveBtn.insets = new Insets(0, 0, 5, 5);
		gbc_saveBtn.gridx = 8;
		gbc_saveBtn.gridy = 2;
		panel_2.add(saveBtn, gbc_saveBtn);

		lblNewLabel = new JLabel(
				"-وارد کردن موارد ستاره دار (*) اجباری می باشد");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 9;
		gbc_lblNewLabel.gridy = 4;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);

		update();

	}

	private void update() {
		tmodel = new DefaultTableModel(new Object[][] {}, new String[] {
				"نام کاربری", "نقش", "نام خانوادگی", "نام", "شناسه" }) {

			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(tmodel);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		table.addMouseListener(this);

		table.getColumnModel().getColumn(0).setPreferredWidth(10);

		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMaxWidth(70);

		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setMaxWidth(170);

		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setMaxWidth(170);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setMaxWidth(70);

		Doctor doc = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();

		String[] doctorUser = { doc.getUserID(), "دکتر", doc.getFamily(),
				doc.getName(), doc.getId() + "" };

		tmodel.addRow(doctorUser);
		secList = getAllSecretery();

		if (!secList.isEmpty()) {

			for (int i = 0; i < secList.size(); i++) {

				String[] str = { secList.get(i).getUserID(), "منشی",
						secList.get(i).getFamily(), secList.get(i).getName(),
						secList.get(i).getId() + "" };
				tmodel.addRow(str);

			}
		} else {
			ShowMessage.show("کاربری موجود نمی باشد!", 1, UserListFrame.this);
		}
	}

	private void setBtnEnableTrue() {
		saveBtn.setEnabled(true);
		deleteBtn.setEnabled(true);
	}

	private void setBtnEnableFalse() {
		saveBtn.setEnabled(false);
		deleteBtn.setEnabled(false);
	}

	private void textFieldclear() {

		telhomeTxtfield.setText("");
		telnumtextfield.setText("");
		nameTxtfield.setText("");
		familyTxtfield.setText("");
		usernameTxtfield.setText("");
		passwordTxtfield.setText("");

		telhomeTxtfield.setEnabled(false);
		telnumtextfield.setEnabled(false);
		nameTxtfield.setEnabled(false);
		familyTxtfield.setEnabled(false);
		usernameTxtfield.setEnabled(false);
		passwordTxtfield.setEnabled(false);

	}

	private void setEnableTextFieldtrue() {

		telhomeTxtfield.setText("");
		telnumtextfield.setText("");
		nameTxtfield.setText("");
		familyTxtfield.setText("");
		usernameTxtfield.setText("");
		passwordTxtfield.setText("");

		telhomeTxtfield.setEnabled(true);
		telnumtextfield.setEnabled(true);
		nameTxtfield.setEnabled(true);
		familyTxtfield.setEnabled(true);
		usernameTxtfield.setEnabled(false);
		passwordTxtfield.setEnabled(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			JTable target = (JTable) e.getSource();
			int row = target.getSelectedRow();
			if (row > 0) {
				deleteBtn.setEnabled(true);
			} else {
				deleteBtn.setEnabled(false);

			}
		}

		if (e.getClickCount() == 2) {
			JTable target = (JTable) e.getSource();
			int row = target.getSelectedRow();

			if (row == 0) {

				try {

					Doctor doc = new BusinessServiceFactory()
							.createSystemDoctor().getDoctor();
					Users docterUser = new BusinessServiceFactory()
							.createUserService().getUserNameAndPassword(
									doc.getUserID());
					setEnableTextFieldtrue();
					nameTxtfield.setText(doc.getName());
					familyTxtfield.setText(doc.getFamily());
					telhomeTxtfield.setText(doc.getPhoneNumber());
					telnumtextfield.setText(doc.getMobileNumber());
					usernameTxtfield.setText(docterUser.getUsername());
					passwordTxtfield.setText(docterUser.getPassword());

					setBtnEnableTrue();
					deleteBtn.setEnabled(false);

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

			} else if (row > 0) {
				Users secUser;
				setBtnEnableTrue();
				setEnableTextFieldtrue();

				try {
					secUser = new BusinessServiceFactory().createUserService()
							.getUserNameAndPassword(
									secList.get(row - 1).getUserID());
					nameTxtfield.setText(secList.get(row - 1).getName());
					familyTxtfield.setText(secList.get(row - 1).getFamily());
					telhomeTxtfield.setText(secList.get(row - 1)
							.getPhoneNumber());
					telnumtextfield.setText(secList.get(row - 1)
							.getMobileNumber());
					usernameTxtfield.setText(secUser.getUsername());
					passwordTxtfield.setText(secUser.getPassword());

				} catch (RemoteException e1) {

					e1.printStackTrace();
				}

			}

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

	private ArrayList<Secretary> getAllSecretery() {

		Doctor doctor = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();

		try {
			secreteryList = new BusinessServiceFactory().createDoctorService()
					.findSecretaryForDoctors(doctor);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return secreteryList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actioCom = e.getActionCommand();
		switch (actioCom) {
		case "save":
			saveAction();
			update();
			setBtnEnableFalse();
			break;

		case "delete":

			deleteAction();
			update();
			setBtnEnableFalse();

			break;

		}

	}

	private static boolean validate(String txt, String pat) {
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(txt);
		return matcher.matches();
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

	private void deleteAction() {
		if (table.getSelectedRow() != 0) {
			try {
				new BusinessServiceFactory().createSecretaryService()
						.deleteSecretary(
								secList.get(table.getSelectedRow() - 1));
				Users u = new Users();
			    u.setUsername(secList.get(table.getSelectedRow() - 1)
			      .getUserID());
			    new BusinessServiceFactory().createUserService().deleteUser(u);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveAction() {
		if (nameTxtfield.isEnabled()) {
			if (!nameTxtfield.getText().isEmpty()
					&& !familyTxtfield.getText().isEmpty()
					&& !usernameTxtfield.getText().isEmpty()
					&& !passwordTxtfield.getText().isEmpty()) {

				if (telnumtextfield.getText().isEmpty()
						|| validate(telnumtextfield.getText(),
								phoneNumberPattern)) {

					if (table.getSelectedRow() == 0) {
						Doctor doc = new Doctor();

						Users userdoc = new Users();

						doc.setName(nameTxtfield.getText());
						doc.setFamily(familyTxtfield.getText());
						doc.setMobileNumber(telnumtextfield.getText());
						doc.setPhoneNumber(telhomeTxtfield.getText());

						userdoc.setUsername(usernameTxtfield.getText());
						userdoc.setPassword(passwordTxtfield.getText());

						try {
							new BusinessServiceFactory().createDoctorService()
									.editDoctor(doc);
							new BusinessServiceFactory().createUserService()
									.updateUser(userdoc);
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						tmodel = new DefaultTableModel(new Object[][] {},
								new String[] { "نام کاربری", "نقش",
										"نام خانوادگی", "نام", "شناسه" }) {

							private static final long serialVersionUID = 1L;
							boolean[] columnEditables = new boolean[] { false,
									false, false, false, false };

							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};

						table = new JTable();
						table.setToolTipText("");
						table.setFont(new Font("Tahoma", Font.PLAIN, 11));
						table.setModel(tmodel);
						scrollPane.setViewportView(table);

						secList = getAllSecretery();

						if (!secList.isEmpty()) {
							Doctor doc1 = new BusinessServiceFactory()
									.createSystemDoctor().getDoctor();

							String[] doctorUser = { doc1.getUserID(), "دکتر",
									doc1.getFamily(), doc1.getName(),
									doc1.getId() + "" };

							tmodel.addRow(doctorUser);
							for (int i = 0; i < secList.size(); i++) {

								String[] str = { secList.get(i).getUserID(),
										"منشی", secList.get(i).getFamily(),
										secList.get(i).getName(),
										secList.get(i).getId() + "" };
								tmodel.addRow(str);

							}
						} else {
							ShowMessage.show("کاربری موجود نمی باشد!", 1,
									UserListFrame.this);
						}

						textFieldclear();

						ShowMessage.show("تغییرات با موفقیت انجام شد", 2,
								UserListFrame.this);
					} else if (table.getSelectedRow() > 0) {

						Secretary sec = new Secretary();

						Users userSec = new Users();

						sec.setName(nameTxtfield.getText());
						sec.setFamily(familyTxtfield.getText());
						sec.setMobileNumber(telnumtextfield.getText());
						sec.setPhoneNumber(telhomeTxtfield.getText());
						int id = Integer.parseInt((String) table.getValueAt(
								table.getSelectedRow(), 4));
						System.out.println(id);
						sec.setId(id);

						userSec.setUsername(usernameTxtfield.getText());
						userSec.setPassword(passwordTxtfield.getText());

						try {
							new BusinessServiceFactory().createUserService()
									.updateUser(userSec);
							new BusinessServiceFactory()
									.createSecretaryService()
									.editSecretary(sec);

						} catch (RemoteException e) {

							e.printStackTrace();
						}

						textFieldclear();

						ShowMessage.show("تغییرات با موفقیت انجام شد", 2,
								UserListFrame.this);
					}

				} else {
					ShowMessage.show("شماره موبایل وارد شده صحیح نمی باشد", 1,
							UserListFrame.this);
				}

			} else if (nameTxtfield.getText().isEmpty()
					|| familyTxtfield.getText().isEmpty()
					|| usernameTxtfield.getText().isEmpty()
					|| passwordTxtfield.getText().isEmpty()) {
				ShowMessage.show("لطفا تمام موارد اجبرای را وارد نمایید", 1,
						UserListFrame.this);
			}
		}
	}

}
