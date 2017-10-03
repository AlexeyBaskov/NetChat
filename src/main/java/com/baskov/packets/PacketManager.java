package com.baskov.packets;

import java.util.HashMap;
import java.util.Map;

public class PacketManager {

	private final static Map<Short, Class<? extends AbstractPacket>> packets = new HashMap<>();
	
	static {
		packets.put((short)1, HelloPacket.class);
		packets.put((short)2, MessagePacket.class);
	}
	
	public static AbstractPacket getPacket(short id) {
	    try {
		    return packets.get(id).newInstance();	
	    } catch(InstantiationException | IllegalAccessException e) {
	    	
	    }
	    return null;
	}
	
	
}
