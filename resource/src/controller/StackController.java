package controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Stack;

public class StackController extends DataStructController implements Initializable{
	private Stack stack = new Stack();
	
	@FXML
    private Pane pane;

    @FXML
    private HBox hbInsert;

    @FXML
    private HBox hbRemove;

    @FXML
    private TextField tfValue;
   
    @FXML
    private Button btnPop;

    @Override
    void btnCreateHandler(ActionEvent event) {
    	super.btnCreateHandler(event);
    	checkValidInput(false);
    }
    
    @FXML
    void btnInsertNewElementHandler(ActionEvent event) {
    	hbInsert.setVisible(!hbInsert.isVisible());
    }

    @FXML
    void btnRemoveElementHandler(ActionEvent event) {
    	try {
			this.stack.pop();
			this.noiticeLablel.setText("Pop successfull !");
			checkValidInput(true);
		} catch (Exception e) {
			this.noiticeLablel.setText("Some thing went wrong !");
		}
    }

    @FXML
    void btnInsertHandler(ActionEvent event) {
    	try {
    		int value = Integer.parseInt(tfValue.getText());
			this.stack.push(value);
			this.noiticeLablel.setText("Insert successfull !");
			this.btnPop.setDisable(false);
		} catch (Exception e) {
			this.noiticeLablel.setText("Input should be a number !");
		}
    }
    
    @Override
    void btnResetHandler(ActionEvent event) {
    	super.btnResetHandler(event);
    	checkValidInput(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.setDataStruct(stack);
		this.stack.setElementContainer(pane);
		checkValidInput(true);
	}
	
	void checkValidInput(boolean notify) {
		int size = this.stack.getElementContainer().getChildren().size();
		if(size == 0) {
			this.btnPop.setDisable(true);
			if(notify) this.noiticeLablel.setText("Stack is empty !");
		} else this.btnPop.setDisable(false);
	}

}
