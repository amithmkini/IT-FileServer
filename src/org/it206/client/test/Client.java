package org.it206.client.test;

import java.io.DataInputStream;
//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    protected int port = 9999;
    protected String serverAddr = null;

    public Client(int port, String serverAddr) {
        this.port = port;
        this.serverAddr = serverAddr;
    }

    public static void main(String[] args) {
    	Socket s = null;
    	Scanner in = null;
    	DataInputStream inp = null;
    	OutputStream output = null;
    	DataOutputStream out = null;
        Client me = new Client(9999, "localhost");
        try {
            System.out.println("Trying to connect to " + me.serverAddr + " at port " + me.port);
            s = new Socket(me.serverAddr, me.port);
            System.out.println("Connected to " + s.getRemoteSocketAddress());
            output = s.getOutputStream();
            out = new DataOutputStream(output);
            inp = new DataInputStream(s.getInputStream());
        }
        catch (IOException e) {
        	System.out.println("Cannot connect to the server!");
            e.printStackTrace();
            System.exit(1);
        }
        while (true) {
	        try {
		    	System.out.print("Enter a message: ");
		    	in = new Scanner(System.in);
		    	String message = in.nextLine();
				out.writeUTF(message);
				String inbox = inp.readUTF();
				System.out.println(inbox);
				if (message.equals("quit")) {
					System.out.println(inp.readUTF());
					break;
				}
			} catch (IOException e) {
				System.out.println("Error sending message!");
				e.printStackTrace();
			}
        }
        try {
        	in.close();
        	s.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
