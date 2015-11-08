package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import org.bihe.bean.PatientDossier;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;

public class AddPatientDossier extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton chooseFileBtn;
	private JScrollPane scrollPane;
	private JTextArea descripTeextArea;
	private JButton saveBtn;
	private JLabel lblNewLabel;
	private Patient patient;

	

	/**
	 * Create the frame.
	 */
	public AddPatientDossier() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new LineBorder(new Color(192, 192, 192)),
				"\u0627\u0636\u0627\u0641\u0647 \u06A9\u0631\u062F\u0646 \u067E\u0631\u0648\u0646\u062F\u0647 \u0628\u06CC\u0645\u0627\u0631",
				TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(192, 192,
						192)));

		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 50, 50, 0, 50, 50, 50, 50 };
		gbl_panel.rowHeights = new int[] { 30, 30, 0, 30, 30, 30, 30, 30, 30 };
		gbl_panel.columnWeights = new double[] { 1.0, 1, 1, 1, 1, 1, 1 };
		gbl_panel.rowWeights = new double[] { 1, 1, 0.0, 1.0, 1, 1, 1, 1, 1 };
		panel.setLayout(gbl_panel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 2;
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		chooseFileBtn = new JButton("انتخاب فایل");
		chooseFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jFileChooser();

			}
		});
		GridBagConstraints gbc_chooseFileBtn = new GridBagConstraints();
		gbc_chooseFileBtn.gridheight = 2;
		gbc_chooseFileBtn.gridwidth = 2;
		gbc_chooseFileBtn.insets = new Insets(0, 0, 5, 0);
		gbc_chooseFileBtn.gridx = 5;
		gbc_chooseFileBtn.gridy = 0;
		panel.add(chooseFileBtn, gbc_chooseFileBtn);

		lblNewLabel = new JLabel("توضیحات:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);

		descripTeextArea = new JTextArea();
		descripTeextArea
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		descripTeextArea.setWrapStyleWord(true);
		descripTeextArea.setLineWrap(true);
		scrollPane.setViewportView(descripTeextArea);

		saveBtn = new JButton();
		GridBagConstraints gbc_saveBtn = new GridBagConstraints();
		gbc_saveBtn.gridheight = 2;
		gbc_saveBtn.gridwidth = 3;
		gbc_saveBtn.insets = new Insets(0, 0, 0, 5);
		gbc_saveBtn.gridx = 2;
		gbc_saveBtn.gridy = 7;
		saveBtn.addActionListener(this);
		
		saveBtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		saveBtn.setBorder(BorderFactory.createEmptyBorder());
		saveBtn.setRolloverIcon(new ImageIcon(Resource
				.getImage("saveR.png")));
		saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		saveBtn.setContentAreaFilled(false);


		saveBtn.setFocusable(false);
		
		panel.add(saveBtn, gbc_saveBtn);
	}

	protected void jFileChooser() {

		final JFileChooser upFileChooser = new JFileChooser();
		upFileChooser.setDialogTitle("فایل مورد نظر را انتخاب کنید");

		int returnVal = upFileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			upFileChooser.getSelectedFile().getName();
			String path = upFileChooser.getSelectedFile().getAbsolutePath();
			textField.setText(path);
		} else {
			return;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		PatientDossier pd = new PatientDossier();
		pd.setDescription(descripTeextArea.getText());
		Doctor d = new BusinessServiceFactory().createSystemDoctor()
				.getDoctor();
		PatientFolder pf = new PatientFolder();
		pf.setPatientID(this.patient.getId());
		pf.setDoctorID(d.getId());

		try {
			pd.setPatientFolderID(new BusinessServiceFactory()
					.createPatientFolderService().getPatientFolder(pf).getId());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String standardDate = formatter.format(today);

		pd.setRecordDate(standardDate);

		pd.setImagePath(textField.getText());
		try {
			if (new BusinessServiceFactory().createPatientDossierService()
					.addPatientDossier(pd)) {

				ShowMessage.show("ثبت پرونده موفقیت آمیز بود",
						ShowMessage.INFORMATION_ID, this);

			} else {
				ShowMessage.show("ثبت پرونده موفقیت آمیز نبود!",
						ShowMessage.ERROR_ID, this);

			}

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
