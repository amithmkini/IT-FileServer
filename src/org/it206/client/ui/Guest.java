package org.it206.client.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
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
	private JTable table;
	Object fileToDownload;
	String ipAddress, port;

	/**
	 * Launch the application.
	 */
	public void newWindow(String x, String y) {
		this.ipAddress = x;
		this.port = y;
		System.out.println(x+" "+y);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ipAddress = x;
					port = y;
					Guest window = new Guest(x,y);
					window.frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private synchronized String[] listFiles() {
		Socket s = null;
    	InputStream in = null;
    	OutputStream output = null;
    	DataOutputStream out = null;
    	DataInputStream inp = null;
    	String ipAddress = this.ipAddress;
    	String port = this.port;

        try {
            System.out.println("Trying to connect to " + ipAddress + " at port " + port);
            s = new Socket(ipAddress, Integer.parseInt(port));
            System.out.println("Connected to " + s.getRemoteSocketAddress());
            in = s.getInputStream();
            output = s.getOutputStream();
            inp = new DataInputStream(in);
            out = new DataOutputStream(output);
        }
        catch (IOException e) {
        	System.out.println("Cannot connect to the server!");
            e.printStackTrace();
            System.exit(1);
        }

        String choice = "list";
        String[] list = {"1","2","3"};
        try {
        	out.writeUTF(choice);

        	int filesCount = inp.readInt();
        	list = new String[filesCount];

        	for(int i = 0; i < filesCount; i++)
        	{
        	    String fileName = inp.readUTF();
        	    list[i] = fileName;
        	}

        	out.close();
        	output.close();
        	in.close();
        	s.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return list;

	}

	private synchronized void FileTransfer(String path) {
		Socket s = null;
    	InputStream in = null;
    	OutputStream output = null;
    	DataOutputStream out = null;
    	String ipAddress = this.ipAddress;
    	String port = this.port;

        try {
            System.out.println("Trying to connect to " + ipAddress + " at port " + port);
            s = new Socket(ipAddress, Integer.parseInt(port));
            System.out.println("Connected to " + s.getRemoteSocketAddress());
            in = s.getInputStream();
            output = s.getOutputStream();
            out = new DataOutputStream(output);
        }
        catch (IOException e) {
        	System.out.println("Cannot connect to the server!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
        	out.writeUTF("dload");
        	out.writeUTF(path);
        } catch (IOException e) {
        	e.printStackTrace();
        }

        String saveTo = null;
        if (System.getProperty("os.name").contains("Windows")){
			saveTo = "D:\\Downloads\\"+path;
		}
		else{
			String home = System.getProperty("user.home");
			saveTo = home+"/Downloads/";
		}

		FileOutputStream fout = null;
		byte[] buffer = new byte[8*1024];

		try {
			fout = new FileOutputStream(saveTo);
		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}

		int count;
		try {
			do {
				count = in.read(buffer);
				fout.write(buffer, 0, count);
				System.out.println(count);
			} while (count > 0);
		} catch (IOException e) {
			System.out.println("Error sending file");
			e.printStackTrace();
		}

		System.out.println("File received successfully!");

		try {
			fout.close();
			in.close();
			out.close();
        	output.close();
        	s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 * @param y 
	 * @param x 
	 */
	public Guest(String x, String y) {
		this.ipAddress = x;
		this.port = y;
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
//				System.out.println(fileToDownload);
				FileTransfer((String)fileToDownload);
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

		String[] list = listFiles();

		String[][] files = new String[list.length][1];
		for (int i = 0; i < list.length; i++) {
			files[i][0] = list[i];
		}
		DefaultTableModel tableModel = new DefaultTableModel(files, columnName);
		table.setModel(tableModel);
	}
}
