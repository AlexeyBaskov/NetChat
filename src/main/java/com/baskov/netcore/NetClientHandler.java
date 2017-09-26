package com.baskov.netcore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetClientHandler implements Runnable {

	private final Socket socket;
	
	public NetClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	private void read(DataInputStream dis) throws IOException {
		
		if(dis.available() > 0) {
		    String message = dis.readUTF();
		    System.out.println(message);
		}
		
	}
	
	private void write(DataOutputStream dos) {
		
	}
	
	@Override
	public void run() {
        
		try (DataInputStream dis = new DataInputStream(socket.getInputStream());
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
			
			while(true) {
				
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
