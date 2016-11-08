package org.it206.server.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MTServer implements Runnable{
	
	protected int serverPort = 9999;
	protected ServerSocket serverSocket = null;
	protected Thread runningThread = null;
	protected boolean isStopped = false;
	
	public MTServer(int port) {
		// TODO Auto-generated constructor stub
		this.serverPort = port;
	}

	
	@Override
	public void run(){
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while (!isStopped()){
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e){
				e.printStackTrace();
				if (isStopped()) {
					System.out.println("The server has stopped");
					return;
				}
				throw new RuntimeException("Error accepting client connection",e);
			}
			new Thread(new WorkerRunnable(clientSocket,"Multithreaded server")).start(); 			
		}
		System.out.println("The server has stopped!");
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	private void openServerSocket(){
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch (IOException e){
			throw new RuntimeException("Cannot open port " + this.serverPort);
		}
	}
	
	public synchronized void stop(){
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e){
			throw new RuntimeException("Error closing server",e);
		}
	}
}

