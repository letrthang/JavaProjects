package Splendid.Middleware.Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainForm {

	private JFrame frmLlrpinfo;
	private JTextField txtController;
	private JTextField txtIP;
	private DefaultTableModel model;
	private CheckReader checkReader;
	private JTable table;
	private JTextField txtLocalhost;
	private JLabel lblOnOff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmLlrpinfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		checkReader = new CheckReader(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLlrpinfo = new JFrame();
		frmLlrpinfo.setResizable(false);
		frmLlrpinfo.setTitle("LLRPInfo 1.01");
		frmLlrpinfo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				checkReader.CheckReaderStatus();
			}
		});
		frmLlrpinfo.setBounds(100, 100, 636, 440);
		frmLlrpinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmLlrpinfo.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtController = new JTextField();
		txtController.setToolTipText("controller name");
		txtController.setText("E1");
		txtController.setBounds(196, 38, 70, 20);
		panel.add(txtController);
		txtController.setColumns(10);

		txtIP = new JTextField();
		txtIP.setText("192.168.10.250");
		txtIP.setBounds(351, 38, 107, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);

		JLabel lblNewLabel = new JLabel("Controller Name");
		lblNewLabel.setBounds(92, 38, 94, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Reader IP");
		lblNewLabel_1.setBounds(276, 40, 78, 17);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setToolTipText("Add a new reader, should stop monitoring before adding");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				checkReader.AddReader(txtController.getText(), txtIP.getText());
			}
		});
		btnNewButton.setBounds(529, 38, 89, 23);
		panel.add(btnNewButton);
		model = new DefaultTableModel();

		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("To delete a selected reader in the table. Should stop monitoring before deleting");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Vector<?> data = model.getDataVector();
				Vector<?> row = (Vector<?>) data.elementAt(table.getSelectedRow());
				row = (Vector<?>) row.clone();
				String deleteIP = (String) row.get(1);// get IP
				model.removeRow(table.getSelectedRow());

				checkReader.deleteReader(deleteIP.trim());
			}
		});
		btnDelete.setBounds(529, 110, 89, 23);
		panel.add(btnDelete);

		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.setToolTipText("Starts monotoring status of all reader in the table. ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				checkReader.CheckReaderStatus();
			}
		});
		btnNewButton_1.setBounds(529, 156, 89, 23);
		panel.add(btnNewButton_1);

		JButton btnStop = new JButton("Stop");
		btnStop.setToolTipText("Stops monotoring status of all reader in the table. ");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkReader.stopCheck();
				setTextOnOff("OFF");
			}
		});
		btnStop.setBounds(529, 209, 89, 23);
		panel.add(btnStop);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(84, 113, 360, 249);
		panel.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("www.letrungthang.blogspot.com - 2012");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(383, 373, 247, 32);
		panel.add(lblNewLabel_2);

		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost:8089");
		txtLocalhost.setToolTipText("Enpoint = http:// + IP:port  + /stockID_Plugin/ReaderInfo?wsdl");
		txtLocalhost.setBounds(92, 82, 123, 20);
		panel.add(txtLocalhost);
		txtLocalhost.setColumns(10);

		JLabel lblEndpoint = new JLabel("Endpoint");
		lblEndpoint.setBounds(10, 85, 81, 14);
		panel.add(lblEndpoint);

		lblOnOff = new JLabel("OFF");
		lblOnOff.setForeground(Color.BLACK);
		lblOnOff.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnOff.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOnOff.setBounds(519, 262, 99, 39);
		panel.add(lblOnOff);
	}

	public String getEndPoint() {
		if (txtLocalhost.getText() != null) {
			return txtLocalhost.getText().trim();
		}
		txtLocalhost.setText("localhost:8080");
		return "localhost:8080";
	}

	public void setEndPoint(String endpoint) {

		txtLocalhost.setText(endpoint);

	}

	public void setTextOnOff(final String text) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Update the model here
				lblOnOff.setText(text);
				if (text.equals("ON")) {
					lblOnOff.setForeground(Color.BLUE);
				} else {
					lblOnOff.setForeground(Color.BLACK);
				}
			}
		});

	}

	public void updateReaderTable(final List<ReaderStatus> readerList) {

		if (model.getColumnCount() == 0) {
			model.addColumn("Controller");
			model.addColumn("IPReader");
			model.addColumn("Status");
		}
		// int rowCount = model.getRowCount();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Update the model here
				while (model.getRowCount() > 0) {
					model.removeRow(0);
				}
			}
		});

		// for (int i = 0; i < rowCount; i++) {
		// model.removeRow(i);
		// }
		String status = "";
		for (final ReaderStatus readerStat : readerList) {
			if (readerStat.status) status = "OK";
			else status = "FAIL";

			final String messStatus = status;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// Update the model here
					model.addRow(new Object[] { readerStat.controllerName, readerStat.ip, messStatus });
				}
			});

		}
		table.revalidate();

	}
}
