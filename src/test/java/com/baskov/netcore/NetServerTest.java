package com.baskov.netcore;

/*
 * Unit Test for NetServer
 */
public class NetServerTest {

	public void doServerConnection() {
		
		Thread server = new Thread(new NetServer(6666));
		
		
		
	}
	
}
