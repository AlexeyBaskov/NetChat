package com.baskov.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

public class HelloPacket extends AbstractPacket {

	private final int id = 1;
	
	private String name;
	private String status;
	private String date;
	
	public HelloPacket() {
		
	}
	
	public HelloPacket(String name, String status) {
		this.name = name;
		this.status = status;
		this.date = new Date().toString();
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void read(DataInputStream dis) throws IOException {
		name = dis.readUTF();
		status = dis.readUTF();
		date = dis.readUTF();
		
	}

	@Override
	public void write(DataOutputStream dos) throws IOException {
		dos.writeUTF(name);
		dos.writeUTF(status);
		dos.writeUTF(date);
		
	}

}
