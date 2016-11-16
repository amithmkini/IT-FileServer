package org.it206.server.test;

import java.io.BufferedOutputStream;
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
		String choice = null;
		String fileName = null;
		String folder = null;
		if (System.getProperty("os.name").contains("Windows")){
			folder = "D:\\IT206\\";
		}
		else{
			String home = System.getProperty("user.home");
			folder = home+'/';
		}
		// Initialize objects here
		
		try {
			input = clientSocket.getInputStream();
			sin = new DataInputStream(input);
			output = clientSocket.getOutputStream();
			sout = new DataOutputStream(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (true) {
				choice = sin.readUTF();
				if (choice.equals("exit")){
					break;
				}
				else if (choice.equals("dload")){
					fileName = sin.readUTF();
					String path = folder + fileName;
					FileTransfer(path, input, output);
				}
				else if (choice.equals("list")) {
					listFiles(folder, input, output);
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			input.close();
			sin.close();
			sout.close();
			output.close();
		} catch (IOException e) {
			System.out.println("Error closing sockets!");
		}
	}
	
	private static void FileTransfer(String path, InputStream input, OutputStream output) {
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
			if (fin != null ) fin.close();
			Thread.sleep(5000);
			output.close();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void listFiles (String path, InputStream input, OutputStream output) {
		BufferedOutputStream bos = new BufferedOutputStream(output);
		DataOutputStream dos = new DataOutputStream(bos);
		File[] files = new File(path).listFiles();
		
		try {
			dos.writeInt(files.length);
			
			for (File file : files) {
				String name = file.getName();
				dos.writeUTF(name);
			}
			
			bos.close();
			dos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}