package org.it206.server.test;

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
		try {
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			DataOutputStream out = new DataOutputStream(output);
			out.writeUTF("Hello! Thanks for connecting to the Kini server!");
			out.flush();
			output.close();
			input.close();
			System.out.println("Sent data successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}