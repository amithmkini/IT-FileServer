package org.it206.client.test;

import java.io.DataInputStream;
import java.io.IOException;
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
        Scanner portin = new Scanner(System.in);
        Scanner addrin = new Scanner(System.in);
        System.out.print("Enter port number: ");
        int pt = portin.nextInt();
        System.out.print("Enter server address: ");
        String ad = addrin.next();
        portin.close();
        addrin.close();
        Client me = new Client(pt, ad);
        try {
            System.out.println("Trying to connect to " + me.serverAddr + " at port " + me.port);
            Socket s = new Socket(me.serverAddr, me.port);
            System.out.println("Connected to " + s.getRemoteSocketAddress());
            DataInputStream inp = new DataInputStream(s.getInputStream());
            System.out.println(inp.readUTF());
            inp.close();
            s.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
