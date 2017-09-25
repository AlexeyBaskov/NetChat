package com.baskov.netcore;

import java.io.IOException;
import java.net.ServerSocket;

public class NetServer implements Runnable {

	private ServerSocket server;
	
	public NetServer() {
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		
	}
	
	public void stop() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
