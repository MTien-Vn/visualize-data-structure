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
import model.Queue;


public class QueueController extends DataStructController implements Initializable{
	private Queue queue = new Queue();
	
	@FXML
    private Pane pane;

    @FXML
    private HBox hbInsert;

    @FXML
    private HBox hbRemove;

    @FXML
    private TextField tfValue;
    
    @FXML
    private Button btnDequeue;

    @FXML
    void btnInsertNewElementHandler(ActionEvent event) {
    	hbInsert.setVisible(!hbInsert.isVisible());
    }

    @FXML
    void btnRemoveElementHandler(ActionEvent event) {
    	try {
    		this.queue.deQueue();
    		this.noiticeLablel.setText("Dequeue successfull !");
    		checkValidInput(true);
		} catch (Exception e) {
			this.noiticeLablel.setText(e.getMessage());
		}
    }

    @Override
    void btnCreateHandler(ActionEvent event) {
    	super.btnCreateHandler(event);
    	checkValidInput(false);
    }

    @FXML
    void btnInsertHandler(ActionEvent event) {
    	try {
    		int value = Integer.parseInt(tfValue.getText());
			this.queue.enQueue(value);
			this.noiticeLablel.setText("Insert successfull !");
			checkValidInput(false);
		} catch (Exception e) {
			this.noiticeLablel.setText("Input should be a number !");
		}
    }
    
    @FXML
    void btnResetData(ActionEvent event) {
    	this.btnDequeue.setDisable(false);
    	this.queue.resetData();
    	this.noiticeLablel.setText("Queue is empty !");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.setDataStruct(queue);
		this.queue.setElementContainer(pane);
		checkValidInput(true);
	}
	
	void checkValidInput(boolean notify) {
		int size = this.queue.getElementContainer().getChildren().size();
		if(size == 0) {
			this.btnDequeue.setDisable(true);
			if(notify) this.noiticeLablel.setText("Queue is empty !");
		} else this.btnDequeue.setDisable(false);
	}

}
