package com.baskov.netchat;

import java.util.Properties;

import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.builder.api.PropertyComponentBuilder;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static final String fxml_login = "/fxml/login.fxml";
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(fxml_login));
		primaryStage.setTitle("NetChat login");
		primaryStage.setScene(new Scene(root, 400, 200));
		primaryStage.show();
	}
}
