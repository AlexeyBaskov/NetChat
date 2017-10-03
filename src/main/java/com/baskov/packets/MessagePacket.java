package com.baskov.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessagePacket extends AbstractPacket {

	private final short id = 2;

	private short messageGroup;
	private String messageAuthor;
	private String messageText;
	
	public MessagePacket() {}
	
	public MessagePacket(short messageGroup, String messageAuthor, String messageText) {
		this.messageGroup = messageGroup;
		this.messageAuthor = messageAuthor;
		this.messageText = messageText;
	}
	
	@Override
	public short getId() {
		return id;
	}

	@Override
	public void read(DataInputStream dis) throws IOException {
		messageGroup = dis.readShort();
		messageAuthor = dis.readUTF();
		messageText = dis.readUTF();
	}

	@Override
	public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(messageGroup);
        dos.writeUTF(messageAuthor);
        dos.writeUTF(messageText);
	}

	public short getMessageGroup() {
		return messageGroup;
	}

	public String getMessageAuthor() {
		return messageAuthor;
	}

	public String getMessageText() {
		return messageText;
	}

}
