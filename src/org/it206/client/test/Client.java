package org.it206.client.test;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    protected int port = 9999;
    protected String serverAddr = null;

    public Client(int port, String serverAddr) {
        this.port = port;
        this.serverAddr = serverAddr;
    }

    public static void main(String[] args) {
    	Socket s = null;
    	InputStream in = null;
    	OutputStream output = null;
    	DataOutputStream out = null;

        Client me = new Client(9999, "localhost");
        
        try {
            System.out.println("Trying to connect to " + me.serverAddr + " at port " + me.port);
            s = new Socket(me.serverAddr, me.port);
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
        
        FileTransfer("D:\\in.txt",in,output);
        
        try {
        	in.close();
        	out.close();
        	s.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
    private synchronized static void FileTransfer(String path, InputStream input, OutputStream output) {
		FileOutputStream fout = null;
		byte[] buffer = new byte[8*1024];
		
		try {
			fout = new FileOutputStream(path);
		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
			ex.printStackTrace();
		}
		
		int count;
		try {
			while ((count = input.read(buffer)) > 0) {
				fout.write(buffer, 0, count);
			}
		} catch (IOException e) {
			System.out.println("Error sending file");
			e.printStackTrace();
		}
		
		System.out.println("File received successfully!");
		
		try {
			input.close();
			output.close();
			if (fout != null ) fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
