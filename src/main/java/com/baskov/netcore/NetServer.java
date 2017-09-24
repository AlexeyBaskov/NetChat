package com.baskov.netcore;

import java.io.IOException;
import java.net.ServerSocket;

public class NetServer {

	private ServerSocket server;
	
	public NetServer() {
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
