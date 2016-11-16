package org.it206.server.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogBox {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void newWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogBox window = new DialogBox();
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
	public DialogBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblServerHasBeen = new JLabel("Server has been started!");
		lblServerHasBeen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServerHasBeen.setBounds(32, 11, 174, 35);
		frame.getContentPane().add(lblServerHasBeen);
		
		JButton btnOk = new JButton("Exit");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				frame.dispose();
			}
		});
		btnOk.setBounds(67, 57, 89, 23);
		frame.getContentPane().add(btnOk);
	}
}
