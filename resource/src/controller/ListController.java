package controller;

import java.net.URL;
import java.util.ResourceBundle;

import exception.IndexException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Element;
import model.List;

public class ListController extends DataStructController implements Initializable {
	private List list = new List(); 

    @FXML
    private HBox hbInsert;

    @FXML
    private HBox hbRemove;

    @FXML
    private TextField tfValue;

    @FXML
    private TextField tfFindValue;

    @FXML
    private TextField tfPositionInsert;

    @FXML
    private Pane pane;

    @FXML
    private TextField tfPositionRemove;

    @FXML
    private HBox hbFind;
    
    @FXML
    private Button findBtn;

    @FXML
    private Button removeBtn;

    @FXML
    void btnInsertNewElementHandler(ActionEvent event) {
    	hbInsert.setVisible(!hbInsert.isVisible());
    }

    @FXML
    void btnRemoveElementHandler(ActionEvent event) {
    	hbRemove.setVisible(!hbRemove.isVisible());
    }

    @FXML
    void btnFindElementHandler(ActionEvent event) {
    	hbFind.setVisible(!hbFind.isVisible());
    }
    
    @Override
    void btnCreateHandler(ActionEvent event) {
    	super.btnCreateHandler(event);
    	checkValidList();
    }

    @FXML
    void btnInsertHandler(ActionEvent event) {
    	try {
    		int value = Integer.parseInt(tfValue.getText());
        	int pos = Integer.parseInt(tfPositionInsert.getText());
        	this.list.Insert(pos,value);
        	this.noiticeLablel.setText("Element inserted successfully");
        	checkValidList();
    	} catch(NumberFormatException e) {
    		this.noiticeLablel.setText("Input must be integer");
    	} catch(IndexException e) {
    		this.noiticeLablel.setText("Invalid position!");
    	} 
    }

    @FXML
    void btnRemoveHandler(ActionEvent event) {
    	try {
    		int pos = Integer.parseInt(tfPositionRemove.getText());
    		this.list.Remove(pos);
    		this.noiticeLablel.setText("Element removed successfully");
    		checkValidList();
    	} catch(NumberFormatException e) {
    		this.noiticeLablel.setText("Input must be integer");
    	} catch(IndexException e) {
    		this.noiticeLablel.setText("Invalid position!");
    	}  	
    }

    @FXML
    void btnFindHandler(ActionEvent event) {
    	try {
    		int value = Integer.parseInt(tfFindValue.getText());
    		int pos = this.list.Find(value);
    		if(pos != -1)
    			this.noiticeLablel.setText("Element is found at index " + pos);
    		else 
    			this.noiticeLablel.setText("Element does not exist in list");
    	} catch(NumberFormatException e) {
    		this.noiticeLablel.setText("Input must be integer");
    	}

    }
	public void checkValidList() {
		if (list.IsEmpty()) {
			this.findBtn.setDisable(true);
			this.removeBtn.setDisable(true);
		}
		else {
			this.findBtn.setDisable(false);
			this.removeBtn.setDisable(false);
		}
	}
	
	@Override
	void btnResetHandler(ActionEvent e) {
		super.btnResetHandler(e);
		this.list.setNumberOfItems(0);
		checkValidList();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.list.setElementContainer(pane);
		this.setDataStruct(list);
	}

}
