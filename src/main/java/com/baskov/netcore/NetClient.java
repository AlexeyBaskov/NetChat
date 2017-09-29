package com.baskov.netcore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.jar.Pack200.Packer;

import com.baskov.packets.AbstractPacket;
import com.baskov.packets.PacketManager;

public class NetClient implements Runnable {

	private DataInputStream dis;
	private DataOutputStream dos;
	
	private boolean flag_Running;
	
	ConcurrentLinkedQueue<AbstractPacket> packets;
	
	private String ip;
	private int port;
	
	public NetClient(String host, int port) {
		this.ip = host;
		this.port = port;
		this.flag_Running = true;
		packets = new ConcurrentLinkedQueue<>();
		
	}

	public synchronized AbstractPacket take() {
		return packets.poll();
	}
	
	public synchronized void send(AbstractPacket packet) {
		
		try {
			dos.writeShort(packet.getId());
			packet.write(dos);
			dos.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void run() {
		
		try (Socket socket = new Socket(this.ip, this.port)) {
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			while(flag_Running) {
				
				Thread.sleep(10);
				
				if(dis.available() > 0) {
				    short packet_id = dis.readShort();
				    AbstractPacket packet = PacketManager.getPacket(packet_id);
				    packet.read(dis);
				    packets.add(packet);
				}
			}
			
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
