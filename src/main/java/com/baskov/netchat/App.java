package com.baskov.netchat;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.baskov.controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static final String fxml_login = "/fxml/login.fxml";
	private static final Logger log = LogManager.getLogger(App.class);
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_login));
		Parent root = loader.load();
		
		LoginController controller = loader.getController();
		
		try {
		    controller.initialize();
		} catch (IOException e) {
			log.error("Не удалсь проинициализировать mainController");
			e.printStackTrace();
			System.exit(-1);
		}
		
		primaryStage.setTitle("NetChat login");
		primaryStage.setScene(new Scene(root, 400, 200));
		primaryStage.show();
	}
}
