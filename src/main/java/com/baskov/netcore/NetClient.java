package com.baskov.netcore;

import java.io.IOException;
import java.net.Socket;

public class NetClient implements Runnable {

	String ip;
	int port;
	
	public NetClient(String host, int port) {
		this.ip = host;
		this.port = port;
	}

	@Override
	public void run() {
		
		try (Socket socket = new Socket(this.ip, this.port)) {
			
			while(true) {
				///--- главный цикл клиента
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
