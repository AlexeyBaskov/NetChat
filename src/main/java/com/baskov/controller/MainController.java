package com.baskov.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

	@FXML
	private Button btnSubmit;
	
	@FXML
	public void onClickSubmit() {
		btnSubmit.setText("set text");
	}
	
	public void initialize() {	
		
	}
	
}
