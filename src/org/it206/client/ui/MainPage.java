package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class MainPage {

	private JFrame mainpage;
	private JTextField ipaddr;
	private JTextField portNo;

	String ipaddress;
	String port;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.mainpage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainpage = new JFrame();
		mainpage.setBounds(100, 100, 483, 208);
		mainpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpage.getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainpage.dispose();
			}
		});
		btnExit.setBounds(368, 135, 89, 23);
		mainpage.getContentPane().add(btnExit);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(44, 71, 46, 14);
		mainpage.getContentPane().add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(44, 96, 46, 14);
		mainpage.getContentPane().add(lblPort);
		
		ipaddr = new JTextField();
		ipaddr.setBounds(145, 62, 200, 20);
		mainpage.getContentPane().add(ipaddr);
		ipaddr.setColumns(10);
		
		portNo = new JTextField();
		portNo.setBounds(145, 93, 86, 20);
		mainpage.getContentPane().add(portNo);
		portNo.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest open = new Guest(ipaddr.getText(), portNo.getText());
				open.newWindow(ipaddr.getText(), portNo.getText());
				mainpage.dispose();
			}
		});
		btnConnect.setBounds(64, 135, 89, 23);
		mainpage.getContentPane().add(btnConnect);
		
		JLabel lblItwebserver = new JLabel("IT-WEBSERVER");
		lblItwebserver.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblItwebserver.setBounds(164, 11, 148, 36);
		mainpage.getContentPane().add(lblItwebserver);
	}
}
