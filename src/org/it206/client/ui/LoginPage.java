package org.it206.client.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class LoginPage extends Guest{

	JFrame frame;
	private JPasswordField passwordField;
	boolean loginFlag = false;
	private final String password = "password";
	
	/**
	 * Launch the application.
	 */
	public void openLoginPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setBounds(10, 11, 109, 22);
		frame.getContentPane().add(lblEnterPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password_check(passwordField.getPassword());
			}
		});
		btnLogin.setBounds(109, 44, 73, 23);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 12, 109, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(224, 44, 89, 23);
		frame.getContentPane().add(btnExit);
	}
	
	private void password_check(char[] x){
		String pw = new String(x);
		System.out.println(pw);
		if (pw.equals(password)){
			SuccessLogin success_pg = new SuccessLogin();
			success_pg.frame.setVisible(true);
			super.access = true;
//			if (loginFlag == true){
//				frame.dispose();
//			}
		}
		else{
			LoginFail fail_pg = new LoginFail();
			fail_pg.frame.setVisible(true);
			super.access = false;
		}
	}
}
