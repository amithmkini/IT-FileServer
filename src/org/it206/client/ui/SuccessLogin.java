package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SuccessLogin {

	private JFrame frame;
	private final String password = "password";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuccessLogin window = new SuccessLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void password_check(char[] x){
		String pw = new String(x);
		if (pw.equals(password)){
			
		}
		else{
			
		}
	}

	/**
	 * Create the application.
	 */
	public SuccessLogin() {
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
		
		JLabel lblSuccessful = new JLabel("Successful!");
		lblSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccessful.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuccessful.setBounds(10, 11, 199, 14);
		frame.getContentPane().add(lblSuccessful);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(62, 27, 89, 23);
		frame.getContentPane().add(btnOk);
	}

}
