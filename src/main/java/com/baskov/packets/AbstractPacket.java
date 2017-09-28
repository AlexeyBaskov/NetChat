package com.baskov.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public abstract class AbstractPacket {

	public abstract int getId();
	
	public abstract void read(DataInputStream dis) throws IOException;
	
	public abstract void write(DataOutputStream dos) throws IOException;	
	
}
