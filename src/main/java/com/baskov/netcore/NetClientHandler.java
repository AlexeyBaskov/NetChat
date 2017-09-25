package com.baskov.netcore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetClientHandler implements Runnable {

	private final Socket socket; 
	
	public NetClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
        
		try (DataInputStream dis = new DataInputStream(socket.getInputStream());
			 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
			
			while(true) {
				
				if(dis.available() > 0) {
					
				}
				
				Thread.sleep(10);
				
			}
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
