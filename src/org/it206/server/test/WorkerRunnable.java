package org.it206.server.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;
	
	public WorkerRunnable(Socket cS, String sT) {
		this.clientSocket = cS;
		this.serverText = sT;
	}
	
	@Override
	public void run() {
		// Add object declaration here
		OutputStream output = null;
		DataOutputStream sout = null;
		InputStream input = null;
		DataInputStream sin = null;
		// Initialize objects here
		
		try {
			input = clientSocket.getInputStream();
			sin = new DataInputStream(input);
			output = clientSocket.getOutputStream();
			sout = new DataOutputStream(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileTransfer("D:\\test.txt",input,output);
		
		try {
			input.close();
			sin.close();
			sout.close();
			output.close();
		} catch (IOException e) {
			System.out.println("Error closing sockets!");
		}
	}
	
	private synchronized void FileTransfer(String path, InputStream input, OutputStream output) {
		FileInputStream fin = null;
		byte[] buffer = new byte[8*1024];
		File file = new File(path);
		
		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}
		
		int count;
		try {
			while ((count = fin.read(buffer)) > 0) {
				output.write(buffer, 0, count);
			}
		} catch (IOException e) {
			System.out.println("Error sending file");
			e.printStackTrace();
		}
		
		System.out.println("File sent successfully!");
		
		try {
			input.close();
			output.close();
			if (fin != null ) fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}