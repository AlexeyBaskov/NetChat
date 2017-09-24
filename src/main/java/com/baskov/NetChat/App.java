package com.baskov.NetChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
	
	private static final String fxml = "/fxml/main.fxml";
	
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource(fxml));
		primaryStage.setTitle("NetChat");
		primaryStage.setScene(new Scene(root, 640, 480));
		primaryStage.show();
	}
}
