package org.it206.server.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;
	
	public WorkerRunnable(Socket cS, String sT) {
		// TODO Auto-generated constructor stub
		this.clientSocket = cS;
		this.serverText = sT;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		OutputStream output = null;
		DataOutputStream out = null;
		InputStream in = null;
		DataInputStream inp = null;
		try {
			in = clientSocket.getInputStream();
			inp = new DataInputStream(in);
			output = clientSocket.getOutputStream();
			out = new DataOutputStream(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true)
			try {
				String message = inp.readUTF();
				out.writeUTF("Got the message!");
				System.out.println(message);
				if (message.equals("quit")) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			out.writeUTF("Thanks for connecting to the server!");
			out.flush();
			System.out.println("Sent data successfully!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}