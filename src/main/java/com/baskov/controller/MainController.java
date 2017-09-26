package com.baskov.controller;

import java.util.Date;

import com.baskov.netcore.NetClient;
import com.baskov.netcore.NetServer;

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
	
	@FXML
	public void onStartServer() {
		server = new NetServer(8432);
		new Thread(server).start();
	}
	
	@FXML void onStartClient() {
		client = new NetClient("192.0.0.1", 8432);
		new Thread(client).start();
	}
	
	@FXML
	public void onClickSubmit() {
		
		String text = messageEdit.getText();
		
		messageArea.appendText(text + " | " + new Date() + "\n");
		
		messageEdit.clear();
		
		//client.send(text);
		
	}
	
	public void initialize() {	
		
		///--- создаем клиент TODO: передача параметров клиенту
		
		/*
		client = new NetClient("192.0.0.1", 8888);
		new Thread(client).start();
		*/
	}
	
}
