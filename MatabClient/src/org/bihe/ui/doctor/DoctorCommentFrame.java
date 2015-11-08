package org.bihe.ui.doctor;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.bihe.Resource.Resource;
import org.bihe.bean.DoctorComment;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.ui.secretary.ShowMessage;

public class DoctorCommentFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 450;
	private static final int HEIGHT = 300;

	private JTextArea textArea;

	private PatientFolder patientFolder;

	private JButton savebtn;

	

	public DoctorCommentFrame() {
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("توضیحات");
		setAlwaysOnTop(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int X = dim.width / 2 - WIDTH / 2;
		int Y = dim.height / 2 - WIDTH / 2;
		setBounds(X, Y, WIDTH, HEIGHT);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 414, 173);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textArea.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane.setViewportView(textArea);

		savebtn = new JButton();
		
		
		
		savebtn.setBounds(161, 237, 111, 23);
		
		savebtn.setIcon(new ImageIcon(Resource.getImage("save.png")));
		savebtn.setBorder(BorderFactory.createEmptyBorder());
		savebtn.setRolloverIcon(new ImageIcon(Resource
				.getImage("saveR.png")));
		savebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		savebtn.setContentAreaFilled(false);
		savebtn.addActionListener(this);
		getContentPane().add(savebtn);

		JLabel infolbl = new JLabel("لطفا توضیحات خود را وارد نمایید:");
		infolbl.setHorizontalAlignment(SwingConstants.RIGHT);
		infolbl.setFont(new Font("Sakkal Majalla", Font.BOLD, 15));
		infolbl.setBounds(161, 28, 263, 14);
		getContentPane().add(infolbl);
	}

	public void setPatientFolder(PatientFolder patientFolder) {

		this.patientFolder = patientFolder;

	}

	private void setAction() {
		if (patientFolder.getId() != 0) {
			DoctorComment docCM = new DoctorComment();

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			docCM.setComment(textArea.getText());
			docCM.setCommentDate(dateFormat.format(date));
			docCM.setPatientFolderID((patientFolder.getId() + ""));

			try {
				if (new BusinessServiceFactory().createDoctorCommentService()
						.addDoctorComment(docCM) != null) {
					ShowMessage.show("نظر با موفقیت ثبت شد.",
							ShowMessage.INFORMATION_ID, this);
					dispose();
				} else {
					ShowMessage.show("ثبت نظر با مشکل مواجه شد.",
							ShowMessage.ERROR_ID, this);
					dispose();
				}

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			ShowMessage.show("هیج پرونده ای برای بیمار موجود نمی باشد!",
					ShowMessage.ERROR_ID, DoctorCommentFrame.this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		setAction();

	}
}
