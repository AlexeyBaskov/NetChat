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
	private volatile boolean flag_Running;
	
	public NetClientHandler(Socket socket, short id) {
		this.socket = socket;
		this.id = id;
		this.flag_Running = true;
		System.out.println("client connected");
	}
	
	private AbstractPacket read(DataInputStream dis) throws IOException {
		
		if(dis.available() > 0) {
		    short packet_id = dis.readShort();
		    AbstractPacket packet = PacketManager.getPacket(packet_id);
		    packet.read(dis);
		    return packet;
		}
		return null;
	}
	
	private synchronized void write(DataOutputStream dos, AbstractPacket packet) throws IOException {
		dos.writeShort(packet.getId());
		packet.write(dos);
		dos.flush();
	}
	
	public synchronized boolean isRunning() {
		return flag_Running;
	}
	
	public synchronized void disconnecnt() {
		flag_Running = false;
	}
	
	@Override
	public void run() {
        
		try (DataInputStream dis = new DataInputStream(socket.getInputStream());
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
			
			while(flag_Running) {
				
				AbstractPacket packet = this.read(dis);
				
				if(packet != null) {
					System.out.println(packet.toString());
				    this.write(dos, packet);
				}
				
				Thread.sleep(10);
			}
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
