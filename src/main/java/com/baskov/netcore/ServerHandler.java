package com.baskov.netcore;

import java.util.LinkedList;

public class ServerHandler {

	private LinkedList<NetClientHandler> clients;
	private NetServer server;
	
	/*
	 * Отключение всех клиентов
	 */
	public synchronized void disconnectAll() {
		for(NetClientHandler client : clients) {
			client.disconnecnt();
		}
	}
	
	/*
	 * Отключение клиента по идентификатору
	 */
	public synchronized void disconnect(short id) {
		for(NetClientHandler client : clients) {
			if(client.getId() == id) {
				client.disconnecnt();
				clients.remove(client);
				break;
			}
		}
	}
	
	public ServerHandler() {
		
		clients = new LinkedList<NetClientHandler>();
		server = new NetServer(8430);
		new Thread(server).start();
		
	}
	
	
	
}
