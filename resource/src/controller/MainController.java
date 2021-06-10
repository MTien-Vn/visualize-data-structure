package controller;

import java.io.IOException;
import java.util.Optional;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MainController {

    @FXML
    void miMainMenuHandler(ActionEvent event) throws IOException {
    	Main.myApp.changeScreen("../screen/option.fxml");
    }

    @FXML
    void miHelpHandler(ActionEvent event) throws IOException {
    	Main.myApp.changeScreen("../screen/help.fxml");
    }

    @FXML
    void miQuitHandler(ActionEvent event) throws IOException{
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Do you want exit ?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		System.exit(0);
    	}
    }
}
