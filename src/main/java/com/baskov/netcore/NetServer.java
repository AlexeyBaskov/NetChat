package com.baskov.netcore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class NetServer implements Runnable {

	private int port;
	
	private volatile boolean flag_Running;
	private volatile boolean flag_Available;
	
	private ServerSocket server;
	
	private List<NetClientHandler> clientList;
	
	public NetServer(int port) {
        this.port = port;
        flag_Running = true;
        flag_Available = true;
        
        clientList = new LinkedList<NetClientHandler>();
	}
	
	public void sendAllclients() {
		
	}
	
	/**
	 * Завершение работы сервера
	 */
	public synchronized void close() {
		
		flag_Running = false;
		
		disconnectAllClients();
		
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Отключает все соединения с клиентами
	 */
	private void disconnectAllClients() {
		for(NetClientHandler client : clientList) {
			client.disconnecnt();
		}
	}
	
	@Override
	public void run() {
		
        try {
        	
        	server = new ServerSocket(port);
        	
        	while(flag_Running) {
        		
        		///--- крутим в холостую
        		if(!flag_Available) continue;
        		
        		///--- слушаем сокет
        		Socket client = server.accept();
        		
        		///--- подключаем клиента
        		NetClientHandler newClient = new NetClientHandler(client, (short)0);
        	    new Thread(newClient).start();
        	    clientList.add(newClient);
        	    
        		Thread.sleep(10);
        	}
        	
        } catch(IOException | InterruptedException e) {
        	e.printStackTrace();
        }
	
	}
	
}