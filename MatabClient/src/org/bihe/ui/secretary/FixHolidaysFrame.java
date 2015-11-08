package org.bihe.ui.secretary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import org.bihe.bean.FixHolidays;
import org.bihe.business.factory.BusinessServiceFactory;
import org.bihe.ui.secretary.ShowMessage;


public class FixHolidaysFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378298782021994194L;
	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel tmodel;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	/**
	 * Create the frame.
	 * 
	 */
	@SuppressWarnings({ "serial" })
	public FixHolidaysFrame() throws RemoteException {
		
		ImageIcon icon = new ImageIcon(
				Resource.getImage("MatabLogoResized.png"));
		setIconImage(icon.getImage());
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

		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setPreferredSize(new Dimension(400, 50));
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("روزهای تعطیل سال");
		northPanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tmodel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"\u0634\u0631\u062D", //$NON-NLS-1$
						"\u0645\u0627\u0647", "\u0631\u0648\u0632", "\u0631\u062F\u06CC\u0641" }) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(tmodel);
		scrollPane.setViewportView(table);

		table.getColumnModel().getColumn(0).setPreferredWidth(400);

		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);

		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);

		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setMaxWidth(50);

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

		addAll();
	}

	private void addAll() throws RemoteException {
		ArrayList<FixHolidays> syslist = new BusinessServiceFactory()
				.createFixHolidaysService().getAllFixHolidays();
		if (!syslist.isEmpty() && syslist != null) {
			for (int i = 0; i < syslist.size(); i++) {

				FixHolidays fh = syslist.get(i);

				String[] row = { fh.getDescribtion(),
						String.valueOf(fh.getMonth()),
						String.valueOf(fh.getDay()), i + "" }; //$NON-NLS-1$

				tmodel.addRow(row);

			}
		} else {
			ShowMessage.show("هیچ اطلاعاتی موجود نیست", ShowMessage.ERROR_ID,
					FixHolidaysFrame.this);
		}
	}
}
