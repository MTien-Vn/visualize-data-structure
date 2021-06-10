package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage myStage;
	public static Main myApp;
	
	public static void main(String[] args)  {
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		myStage = stage;
		myApp = this;
		
		Parent root = FXMLLoader.load(getClass().getResource("../screen/main.fxml"));
		Scene scene = new Scene(root, 900, 600);
		stage.setScene(scene);
		stage.setTitle("Data structure simulator");
		stage.show();
		
		changeScreen("../screen/option.fxml");
	}
	
	public void changeScreen(String file) throws IOException {
		BorderPane root = (BorderPane)myStage.getScene().getRoot();
		Parent screen = FXMLLoader.load(getClass().getResource(file));
		root.setCenter(screen);
	}

}
