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
        
		try (InputStream sin = socket.getInputStream(); 
			 OutputStream sout = socket.getOutputStream()) {
		
			DataInputStream dis = new DataInputStream(sin);
			DataOutputStream dos = new DataOutputStream(sout);
			
			while(true) {
				
						
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
