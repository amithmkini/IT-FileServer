package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Guest {

	private JFrame frame;
	boolean access = false;
	JButton btnDownload = null;
	JButton btnExit = null;
	LoginPage login_window = null;
	private JTable table;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 498, 584, -430);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				refresh the table list
			}
		});
		btnRefresh.setBounds(250, 527, 89, 23);
		frame.getContentPane().add(btnRefresh);
	}
}
