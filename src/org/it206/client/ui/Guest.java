package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Guest {

	private JFrame frame;
	boolean access = false;
	JButton btnDownload = null;
	JButton btnExit = null;
	LoginPage login_window = null;
	private JTable table;
	Object fileToDownload;
	private String ipAddress, port;
	
	/**
	 * Launch the application.
	 */
	public void newWindow(String x, String y) {
		this.ipAddress = x;
		this.port = y;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guest window = new Guest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Guest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(fileToDownload);
			}
		});
		btnDownload.setBounds(100, 527, 99, 23);
		frame.getContentPane().add(btnDownload);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnExit.setBounds(595, 527, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblItwebserver = new JLabel("                                            IT-WEBSERVER");
		lblItwebserver.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblItwebserver.setBounds(100, 11, 584, 36);
		frame.getContentPane().add(lblItwebserver);
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				developTable();
			}
		});
		btnRefresh.setBounds(250, 527, 89, 23);
		frame.getContentPane().add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 58, 584, 458);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        fileToDownload = table.getModel().getValueAt(row, col);
		    }
		});
		developTable();
	}
	
	void developTable(){
		String[] columnName = new String[1];
		columnName[0] = "Files";
		
		String[][] files = new String[50][1];
		for (int i = 0; i < 50; i++) {
			files[i][0] = Integer.toString(i+1);
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(files, columnName);
		table.setModel(tableModel);
	}
}
