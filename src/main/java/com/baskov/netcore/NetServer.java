package com.baskov.netcore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Exchanger;

public class NetServer implements Runnable {

	private int port;
	
	private boolean isRunning;
	private boolean isAvailable;
	
	public NetServer(int port) {
        this.port = port;
        isRunning = true;
        isAvailable = true;
	}
	
	public void closeServer() {
		isRunning = false;
	}
	
	@Override
	public void run() {
		
        try (ServerSocket server = new ServerSocket(port)) {
        	
        	while(isRunning) {
        		
        		///--- крутим в холостую
        		if(!isAvailable) continue;
        		
        		///--- слушаем сокет
        		Socket client = server.accept();
        		
        		///--- подключаем клиента
        	    new Thread(new NetClientHandler(client)).start();
        	    
        		Thread.sleep(10);
        	}
        	
        } catch(IOException | InterruptedException e) {
        	e.printStackTrace();
        }
	
	}
	
}