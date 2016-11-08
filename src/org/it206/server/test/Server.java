package org.it206.server.test;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MTServer server = new MTServer(9999);
		new Thread(server).start();

		try {
		    Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();
	}
}
