package com.baskov.netcore;

public abstract class APacket {

	public abstract int getId();
	
	public abstract void read();
	
	public abstract void write();	
	
}
