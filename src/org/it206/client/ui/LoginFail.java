package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFail extends LoginPage{

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public void newWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFail window = new LoginFail();
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
	public LoginFail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIncorrectYouDo = new JLabel("Incorrect. Access Denied.");
		lblIncorrectYouDo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIncorrectYouDo.setBounds(40, 11, 164, 14);
		frame.getContentPane().add(lblIncorrectYouDo);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFlag = true;
				frame.dispose();
			}
		});
		btnOk.setBounds(70, 27, 89, 23);
		frame.getContentPane().add(btnOk);
	}
}
