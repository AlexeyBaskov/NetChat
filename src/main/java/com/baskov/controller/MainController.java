package com.baskov.controller;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.builder.api.PropertyComponentBuilder;

import com.baskov.netcore.NetClient;
import com.baskov.netcore.NetServer;
import com.baskov.packets.AbstractPacket;
import com.baskov.packets.HelloPacket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

	private NetClient client;
	private NetServer server;
	
	@FXML private Button btnSubmit;
	@FXML private TextField messageEdit;
	@FXML private TextArea messageArea;
	
	///--- тестовые поля
	@FXML private Button startServer;
	@FXML private Button startClient;
	@FXML private Label labelStatus;
	
	boolean flag_ServerStart = false;
	
    private static final Logger log = LogManager.getLogger(MainController.class);
	
	@FXML
	public void onStartServer() {
		
		if(!flag_ServerStart) {
		    server = new NetServer(8430);
		    new Thread(server).start();
		    startServer.setText("Server run");
		    
		} else {
			server.close();
			startServer.setText("Server");
		}
		
		flag_ServerStart = !flag_ServerStart;
	}
	
	@FXML void onStartClient() {
		
		log.info("Client start");
		
		client = new NetClient("localhost", 8430);
		new Thread(client).start();
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
	
	@FXML
	public void onClickSubmit() {
		
		log.info("Client send message");
		
		String text = messageEdit.getText() + " | " + new Date() + "\n";
		
		messageArea.appendText(text);
		
		messageEdit.clear();
		
		AbstractPacket packet = new HelloPacket("Andrey", text);
		
		client.send(packet);
		
	}
	
	public void initialize() {	
		
		///--- создаем клиент TODO: передача параметров клиенту

	}
	
}
