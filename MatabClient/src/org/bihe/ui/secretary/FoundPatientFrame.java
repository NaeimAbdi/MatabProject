package org.bihe.ui.secretary;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.bihe.Resource.Resource;
import org.bihe.bean.Patient;
import org.bihe.bean.PatientFolder;
import org.bihe.business.factory.BusinessServiceFactory;

public class FoundPatientFrame extends JFrame implements ActionListener,
		MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tModel;
	private ArrayList<Patient> patients = new ArrayList<>();
	private AddVisitTimeFrame addVisitTimeFrame;
	private JButton setPatBtn;
	private Patient patient;

	

	/**
	 * Create the frame.
	 * 
	 * @throws RemoteException
	 */
	public FoundPatientFrame() throws RemoteException {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 408, 167);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.addMouseListener(this);
		String[] columns = { "شماره پرونده", "شماره موبایل", "نام خانوادگی",
				"نام" };

		tModel = new DefaultTableModel(columns, 0);
		table.setModel(tModel);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		JTableHeader jtableHeader = table.getTableHeader();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		rend.setHorizontalAlignment(JLabel.RIGHT);
		jtableHeader.setDefaultRenderer(rend);

		setPatBtn = new JButton("انتخاب");
		setPatBtn.setBounds(147, 203, 110, 35);
		
		
		
		setPatBtn.setContentAreaFilled(false);
		setPatBtn.setIcon(new ImageIcon(Resource.getImage("select.png")));
		setPatBtn.setBorder(BorderFactory.createEmptyBorder());
		setPatBtn.setRolloverIcon(new ImageIcon(Resource.getImage("selectR.png")));
		setPatBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setPatBtn.setFocusable(false);
		
		setPatBtn.setEnabled(false);
		setPatBtn.addActionListener(this);
		contentPane.add(setPatBtn);

	}

	public void fillTable() throws RemoteException {

		for (Patient pat : patients) {

			PatientFolder pf = new PatientFolder();
			pf.setDoctorID(new BusinessServiceFactory().createSystemDoctor()
					.getDoctor().getId());
			pf.setPatientID(pat.getId());
			pf.setDoctorID(new BusinessServiceFactory().createSystemDoctor()
					.getDoctor().getId());
			pf = new BusinessServiceFactory().createPatientFolderService()
					.getPatientFolder(pf);

			String[] row = { pf.getFolderUniqueNumber(),
					pat.getTelephoneNumber(), pat.getFamily(), pat.getName() };
			tModel.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		addVisitTimeFrame.fillFields(createPatFromRow());
		addVisitTimeFrame.fillFields(ceratePatDocFromRow());
		addVisitTimeFrame.setPatient(this.patient);
		this.setVisible(false);
	}

	private Patient createPatFromRow() {
		Patient pat = new Patient();
		pat.setFamily((String) table.getValueAt(table.getSelectedRow(), 2));
		pat.setName((String) table.getValueAt(table.getSelectedRow(), 3));
		this.patient = this.patients.get(table.getSelectedRow());

		return pat;
	}

	private PatientFolder ceratePatDocFromRow() {

		PatientFolder pf = new PatientFolder();
		pf.setFolderUniqueNumber((String) table.getValueAt(
				table.getSelectedRow(), 0));
		return pf;
	}

	public void setAddVisitTimeFrame(AddVisitTimeFrame addVisitTimeFrame) {

		this.addVisitTimeFrame = addVisitTimeFrame;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		setPatBtn.setEnabled(true);
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
}
