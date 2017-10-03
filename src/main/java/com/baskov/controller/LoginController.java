package com.baskov.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML private TextField serverName;
	@FXML private TextField clientName;
	@FXML private TextField serverPort;
	@FXML private TextField clientPort;
	@FXML private TextField serverAdress;
	
	private static final String fxml_main = "/fxml/main.fxml";
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	private MainController mainController;
	private Stage mainStage;
	
	public void initialize() throws IOException {
	    
		log.info("Инициализация контроллера основной формы");
	    
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_main));
	    
		Parent root = loader.load();
	    mainController = loader.getController();
	    
	    mainStage = new Stage();
	    mainStage.setTitle("NetChat");
	    mainStage.setScene(new Scene(root));
	    mainController.initialize(mainStage);
	}
	
	private void createMainServer(String serverName, String serverPort) {
		mainController.initServer(serverName, serverPort);
		mainStage.show();
	}
	
	private void createMainClient(String clientName, String serverAdress, String clientPort) {
		mainController.initClient(clientName, serverAdress, clientPort);
		mainStage.show();
	}
	
	@FXML public void onServerStart() {
		log.info("Запуск сервера");
		String server_name = serverName.getText();
		String server_port = serverPort.getText();
		createMainServer(server_name, server_port);
	}
	
	@FXML public void onClientStart() {
		log.info("Запуск клиента");
		String client_name = clientName.getText();
		String client_port = clientPort.getText();
		String server_adress = serverAdress.getText();
	    createMainClient(client_name, server_adress, client_port);
	}
	
}
