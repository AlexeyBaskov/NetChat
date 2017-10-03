package com.baskov.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.baskov.netcore.NetClient;
import com.baskov.netcore.NetServer;
import com.baskov.packets.AbstractPacket;
import com.baskov.packets.HelloPacket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

	private Stage this_stage;
	private NetClient client;
	private NetServer server;
	
	@FXML private Button btnSubmit;
	@FXML private TextField messageEdit;
	@FXML private TextArea messageArea;
	
	boolean flag_ServerStart = false;
	
    private static final Logger log = LogManager.getLogger(MainController.class);
	
    /*
	 * Инициализация контроллера
	 */
	public void initialize(Stage stage) {	
		this.this_stage = stage;
		flag_ServerStart = false;
	}
    
    /*
     * Инициализация сервера
     */
    public void initServer(String serverName, String serverPort) {
    	///--- при старте сервера стартует и локальный клиент
    	int port = 0;
    	
    	try {
    	    port = Integer.parseInt(serverPort);
    	} catch(NumberFormatException e) {
    		log.error("Некорректный параметр: ", serverPort);
    		e.printStackTrace();
    		this_stage.close();
    		return;
    	}
    	server = new NetServer(port);
    	new Thread(server).start();
    	
    	client = new NetClient("localhost", port);
    	new Thread(client);
    	
    	startClientTimer();
    }
    
    /*
     * Инициализация клиента
     */
    public void initClient(String clientName, String serverAdress, String clientPort) {
    	server = null;
    	try {
    	    client = new NetClient(serverAdress, Integer.parseInt(clientPort));
    	} catch (NumberFormatException e) {
    		log.error("Некорректный параметр: ", clientPort);
    		e.printStackTrace();
    		this_stage.close();
    		return;
    	}
    	
    	new Thread(client).start();
    	
    	startClientTimer();
    	
    }
	
	private void startClientTimer() {
		
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				HelloPacket packet = (HelloPacket)client.take();
				if(packet != null) {
					messageArea.appendText("ECHO RESPONSE: " + packet.toString() + "\n");
				}
			}
		};
		
		timer.schedule(task, 500, 100);
		
	}
	
	@FXML public void onClickSubmit() {
		
		log.info("Client send message");
		
		String text = messageEdit.getText() + " | " + new Date() + "\n";
		
		messageArea.appendText(text);
		
		messageEdit.clear();
		
		AbstractPacket packet = new HelloPacket("Andrey", text);
		
		client.send(packet);
		
	}
	
}
