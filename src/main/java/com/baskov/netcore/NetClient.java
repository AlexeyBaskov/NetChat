package com.baskov.netcore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class NetClient implements Runnable {

	private boolean isRunning;
	String ip;
	int port;
	
	public NetClient(String host, int port) {
		this.ip = host;
		this.port = port;
		this.isRunning = true;
	}

	public void send(String x) {
		
	}
	
	@Override
	public void run() {
		
		try (Socket socket = new Socket(this.ip, this.port)) {
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			while(isRunning) {
				
				Thread.sleep(1000);
				dos.writeUTF("cliet send message !");
			}
			
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
