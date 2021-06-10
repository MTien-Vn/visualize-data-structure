package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OptionController {
	
	@FXML
	void btnLinkedListHandler(ActionEvent event) throws IOException {
		Main.myApp.changeScreen("../screen/listScreen.fxml");
	}
	
	@FXML
    void btnStackHandler(ActionEvent event) throws IOException {
		Main.myApp.changeScreen("../screen/stackScreen.fxml");
    }
	
	@FXML
    void btnQueueHandler(ActionEvent event) throws IOException {
		Main.myApp.changeScreen("../screen/queueScreen.fxml");
    }
}
