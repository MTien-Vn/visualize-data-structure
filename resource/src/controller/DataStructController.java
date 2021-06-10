package controller;

import exception.DataStructLengthException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.DataStruct;

public class DataStructController {
	private DataStruct ds;
	
	@FXML
    protected HBox hbCreate;
	
	@FXML
	protected Label noiticeLablel;
	
	@FXML
	protected TextField tfLength;
	
	@FXML
    void btnCreateRandomListHandler(ActionEvent event) {
    	hbCreate.setVisible(!hbCreate.isVisible());
    }
	
	@FXML
    void btnCreateHandler(ActionEvent event) {
    	try {
    		int length = Integer.parseInt(tfLength.getText());
    		this.ds.createRandomList(length);
    		this.noiticeLablel.setText("Create Successfully!");
    	} catch(NumberFormatException e) {
    		this.noiticeLablel.setText("Length must be integer");
    	} catch(DataStructLengthException e) {
    		this.noiticeLablel.setText("Length must not  be negative");
    	}
    }
	
	@FXML
    void btnResetHandler(ActionEvent event) {
    	this.ds.resetData();
    	this.noiticeLablel.setText("Reset successfully");
    }
	
	public void setDataStruct(DataStruct ds) {
		this.ds = ds;
	}
}
