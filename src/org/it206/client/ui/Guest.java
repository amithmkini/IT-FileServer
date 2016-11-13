package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JFileChooser;

public class Guest {

	private JFrame frame;

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
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(128, 527, 99, 23);
		frame.getContentPane().add(btnDownload);
		
		JButton btnSelectAll = new JButton("Select all");
		btnSelectAll.setBounds(10, 527, 99, 23);
		frame.getContentPane().add(btnSelectAll);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(237, 527, 99, 23);
		frame.getContentPane().add(btnUpload);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(346, 527, 99, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(529, 527, 99, 23);
		frame.getContentPane().add(btnLogin);
		
		JTree tree = new JTree();
		tree.setBounds(10, 11, 764, 494);
		frame.getContentPane().add(tree);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(685, 527, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(25, 37, 582, 397);
		frame.getContentPane().add(fileChooser);
	}
}
