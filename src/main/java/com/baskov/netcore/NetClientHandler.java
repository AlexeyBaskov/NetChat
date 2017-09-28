package com.baskov.netcore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.baskov.packets.AbstractPacket;
import com.baskov.packets.PacketManager;

public class NetClientHandler implements Runnable {

	private final short id;
	private final Socket socket;
	private volatile boolean isRunning;
	
	public NetClientHandler(Socket socket, short id) {
		this.socket = socket;
		this.id = id;
		this.isRunning = true;
	}
	
	private void read(DataInputStream dis) throws IOException {
		
		if(dis.available() > 0) {
		    short packet_id = dis.readShort();
		    AbstractPacket packet = PacketManager.getPacket(packet_id);
		    packet.read(dis);
		}
		
	}
	
	
	
	private void write(DataOutputStream dos) {
		
	}
	
	public synchronized boolean isRunning() {
		return isRunning;
	}
	
	public synchronized void disconnecnt() {
		isRunning = false;
	}
	
	@Override
	public void run() {
        
		try (DataInputStream dis = new DataInputStream(socket.getInputStream());
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
			
			while(isRunning) {
				
				this.read(dis);
				this.write(dos);
				Thread.sleep(10);
			}
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
