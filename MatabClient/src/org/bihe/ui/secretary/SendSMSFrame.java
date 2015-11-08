package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.bihe.Resource.Resource;
import org.bihe.bean.Doctor;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;

public class SendSMSFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField telNumtxtFiled;
	private JRadioButton comboboxradioBtn;
	private JRadioButton enterNumberRadiobtn;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton sendBtn;

	private static final String phoneNumberPattern = "^[9][0-9]{9}$";

	

	/**
	 * Create the frame.
	 */
	public SendSMSFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		
		setTitle("ارسال پیامک");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(new LineBorder(new Color(192,
				192, 192)), "ارسال پیامک", TitledBorder.RIGHT,
				TitledBorder.TOP, null, new Color(192, 192, 192)));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 35, 35, 35, 35, 35, 35, 35,
				35, 35, 35, 35, 35, 35, 35, 35, 35, 35 };
		gbl_centerPanel.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40,
				40, 40, 40, 40 };
		gbl_centerPanel.columnWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1 };
		gbl_centerPanel.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1 };
		centerPanel.setLayout(gbl_centerPanel);

		JLabel infolbl = new JLabel("انتخاب مخاطب");
		infolbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 16));
		GridBagConstraints gbc_infolbl = new GridBagConstraints();
		gbc_infolbl.gridheight = 2;
		gbc_infolbl.gridwidth = 18;
		gbc_infolbl.insets = new Insets(0, 0, 5, 5);
		gbc_infolbl.gridx = 0;
		gbc_infolbl.gridy = 0;
		centerPanel.add(infolbl, gbc_infolbl);

		comboboxradioBtn = new JRadioButton("انتخاب شماره");
		comboboxradioBtn.setActionCommand("list");
		comboboxradioBtn.addActionListener(this);
		comboboxradioBtn.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.EAST;
		gbc_rdbtnNewRadioButton_1.gridwidth = 5;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 4;
		gbc_rdbtnNewRadioButton_1.gridy = 2;
		centerPanel.add(comboboxradioBtn, gbc_rdbtnNewRadioButton_1);

		enterNumberRadiobtn = new JRadioButton("وارد کردن شماره ");
		enterNumberRadiobtn.setActionCommand("setnumber");
		enterNumberRadiobtn.addActionListener(this);
		enterNumberRadiobtn.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_enterNumberRadiobtn = new GridBagConstraints();
		gbc_enterNumberRadiobtn.anchor = GridBagConstraints.WEST;
		gbc_enterNumberRadiobtn.gridwidth = 7;
		gbc_enterNumberRadiobtn.insets = new Insets(0, 0, 5, 5);
		gbc_enterNumberRadiobtn.gridx = 9;
		gbc_enterNumberRadiobtn.gridy = 2;
		centerPanel.add(enterNumberRadiobtn, gbc_enterNumberRadiobtn);

		ButtonGroup radioGrp = new ButtonGroup();
		radioGrp.add(comboboxradioBtn);
		radioGrp.add(enterNumberRadiobtn);

		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 5;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		centerPanel.add(comboBox, gbc_comboBox);

		JLabel comboBoxlbl = new JLabel("شماره را انتخاب کنید");
		comboBoxlbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBoxlbl = new GridBagConstraints();
		gbc_comboBoxlbl.anchor = GridBagConstraints.WEST;
		gbc_comboBoxlbl.gridwidth = 3;
		gbc_comboBoxlbl.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxlbl.gridx = 6;
		gbc_comboBoxlbl.gridy = 3;
		centerPanel.add(comboBoxlbl, gbc_comboBoxlbl);

		JLabel lblNewLabel = new JLabel("(بدون صفر)");
		lblNewLabel.setFont(new Font("Sakkal Majalla", Font.PLAIN, 10));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 9;
		gbc_lblNewLabel.gridy = 3;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);

		telNumtxtFiled = new JTextField();
		telNumtxtFiled.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_telNumtxtFiled = new GridBagConstraints();
		gbc_telNumtxtFiled.gridwidth = 3;
		gbc_telNumtxtFiled.insets = new Insets(0, 0, 5, 5);
		gbc_telNumtxtFiled.fill = GridBagConstraints.HORIZONTAL;
		gbc_telNumtxtFiled.gridx = 11;
		gbc_telNumtxtFiled.gridy = 3;
		centerPanel.add(telNumtxtFiled, gbc_telNumtxtFiled);
		telNumtxtFiled.setColumns(10);

		JLabel telnumLbl = new JLabel("شماره تلفن(همراه)");
		telnumLbl.setFont(new Font("Sakkal Majalla", Font.PLAIN, 14));
		GridBagConstraints gbc_telnumLbl = new GridBagConstraints();
		gbc_telnumLbl.anchor = GridBagConstraints.WEST;
		gbc_telnumLbl.gridwidth = 4;
		gbc_telnumLbl.insets = new Insets(0, 0, 5, 0);
		gbc_telnumLbl.gridx = 14;
		gbc_telnumLbl.gridy = 3;
		centerPanel.add(telnumLbl, gbc_telnumLbl);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 6;
		centerPanel.add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel_1 = new JLabel("متن پیامک:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 14;
		gbc_lblNewLabel_1.gridy = 6;
		centerPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		sendBtn = new JButton();
		
		
		
		
		sendBtn.setContentAreaFilled(false);
		sendBtn.setIcon(new ImageIcon(Resource.getImage("send.png")));
		sendBtn.setBorder(BorderFactory.createEmptyBorder());
		sendBtn.setRolloverIcon(new ImageIcon(Resource.getImage("sendR.png")));
		sendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sendBtn.setFocusable(false);
		
		sendBtn.setActionCommand("send");
		sendBtn.addActionListener(this);
		GridBagConstraints gbc_sendBtn = new GridBagConstraints();
		gbc_sendBtn.gridheight = 2;
		gbc_sendBtn.gridwidth = 6;
		gbc_sendBtn.insets = new Insets(0, 0, 5, 5);
		gbc_sendBtn.gridx = 6;
		gbc_sendBtn.gridy = 10;
		centerPanel.add(sendBtn, gbc_sendBtn);

		telNumtxtFiled.addKeyListener(numberFormat());
		textArea.addKeyListener(textAreaFormat());
		textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		setnumberSetEnableFalse();
		setComboBoxSetEnableFalse();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "list":

			setnumberSetEnableFalse();
			setComboBoxSetEnableTrue();
			break;
		case "setnumber":

			setnumberSetEnableTrue();
			setComboBoxSetEnableFalse();

			break;

		case "send":

			checkMessageParametrs();
			break;
		default:
			break;
		}
	}

	private void checkMessageParametrs() {

		if (enterNumberRadiobtn.isSelected()) {
			if (!telNumtxtFiled.getText().isEmpty()) {
				if (validate(telNumtxtFiled.getText(), phoneNumberPattern)) {
					if (!textArea.getText().isEmpty()) {

						try {
							if (new BusinessServiceFactory()
									.createGeneratedMessagesService()
									.sendMessage(textArea.getText(),
											telNumtxtFiled.getText())) {
								ShowMessage.show(
										"پیامک به لیست ارسال اضافه شد",
										ShowMessage.INFORMATION_ID, this);
								dispose();

							} else {

								ShowMessage.show(
										"ارسال پیامک موفقیت آمیز نبود",
										ShowMessage.ERROR_ID, this);
								dispose();

							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}

					} else {
						ShowMessage.show("لطفا متن پیامک را وارد نمایید!",
								ShowMessage.ERROR_ID, SendSMSFrame.this);
					}

				} else {
					ShowMessage.show("شماره تلفن وارد شده معتبر نمی باشد",
							ShowMessage.ERROR_ID, SendSMSFrame.this);
				}
			} else {

				ShowMessage.show("لطفا شماره تلفن مقصد را انتخاب کنید!",
						ShowMessage.ERROR_ID, SendSMSFrame.this);
			}
		} else if (comboboxradioBtn.isSelected()) {
			if (comboBox.getSelectedIndex() != -1) {
				if (!textArea.getText().isEmpty()) {

					try {
						String number = (String) comboBox.getSelectedItem();

						String[] arrayNumber = number.split(" ");
						String finalNember = arrayNumber[1].toString();
						if (new BusinessServiceFactory()
								.createGeneratedMessagesService().sendMessage(
										textArea.getText(), finalNember)) {

							ShowMessage.show("پیامک به لیست ارسال اضافه شد",
									ShowMessage.INFORMATION_ID, this);
							dispose();

						} else {

							ShowMessage.show("ارسال پیامک موفقیت آمیز نبود",
									ShowMessage.ERROR_ID, this);
							dispose();

						}

					} catch (RemoteException e) {
						e.printStackTrace();
					}

				} else {
					ShowMessage.show("لطفا متن پیامک را وارد نمایید!",
							ShowMessage.ERROR_ID, SendSMSFrame.this);
				}
			} else {
				ShowMessage.show("لطفا شماره را برای انتخاب نمایید!",
						ShowMessage.ERROR_ID, SendSMSFrame.this);
			}

		}

	}

	private void setnumberSetEnableTrue() {

		telNumtxtFiled.setText("");
		telNumtxtFiled.setEnabled(true);

	}

	private void setnumberSetEnableFalse() {

		telNumtxtFiled.setText("");
		telNumtxtFiled.setEnabled(false);

	}

	private void setComboBoxSetEnableTrue() {

		comboBox.setEnabled(true);

		Doctor doc = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		Patient p = new Patient("", "", "", "", "", "");
		PatientFolder pf = new PatientFolder("", doc.getId());

		try {
			ArrayList<Patient> pList = new BusinessServiceFactory()
					.createPatientService().search(p, pf);
			if (!pList.isEmpty()) {
				for (int i = 0; i < pList.size(); i++) {

					comboBox.addItem(pList.get(i).getFamily() + " "
							+ pList.get(i).getMobileNumber());

				}
			} else {
				ShowMessage.show("هیچ بیماری موجود نمی باشد!",
						ShowMessage.ERROR_ID, SendSMSFrame.this);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void setComboBoxSetEnableFalse() {

		comboBox.removeAllItems();
		comboBox.setEnabled(false);
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

	private KeyListener textAreaFormat() {
		KeyListener key = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c == KeyEvent.VK_ENTER))) {
					getToolkit().beep();
					e.consume();
				}

			}
		};
		return key;
	}
}
