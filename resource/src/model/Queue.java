package model;

import exception.DataStructLengthException;
import exception.IndexException;
import javafx.scene.paint.Color;
import model.interfaces.IQueue;

public class Queue extends DataStruct implements IQueue {
	
	int rear = -1;
	int font = -1;


	@Override
	public void enQueue(int number) throws IndexException {
		Element ele;
		if(super.elementContainer.getChildren().size() > 0 ) {
			ele = (Element) super.elementContainer.getChildren().get(this.rear);
			ele.changeBackGroundColor(Color.WHITE);
		}
		
		super.insertElement(false, number, this.rear);
		this.font ++;
		if(this.font == 1) {
			ele = (Element) super.elementContainer.getChildren().get(this.font);
			ele.changeBackGroundColor(Color.PINK);
		}
		ele = (Element) super.elementContainer.getChildren().get(this.rear);
		ele.changeBackGroundColor(Color.SPRINGGREEN);
		if(this.font == this.rear) {
			ele = (Element) super.elementContainer.getChildren().get(this.font);
			ele.changeBackGroundColor(Color.AQUA);
		}
	}



	@Override
	public void deQueue() throws IndexException {
		if(this.font != -1) {
			super.removeElement(false, this.font);
			this.font--;
			if(this.font > 0) {
				Element ele = (Element) super.elementContainer.getChildren().get(this.font);
				ele.changeBackGroundColor(Color.PINK);
			}
			if(this.font == this.rear) {
				Element ele = (Element) super.elementContainer.getChildren().get(this.font);
				ele.changeBackGroundColor(Color.AQUA);
			}
		}
		
		
	}
	
	/*
     * purpose: create a random list of element
     * input: 
     * 		- length: length of list
     * output: none
     */
	@Override
	public void createRandomList(int length) throws DataStructLengthException{ //////////////// CREATE 
    	
    	this.rear = 0;
    	this.font = length -1;
    	
    	super.createRandomList(length);
    	
    	Element ele = (Element) super.elementContainer.getChildren().get(this.font);
		ele.changeBackGroundColor(Color.PINK);
		
		ele = (Element) super.elementContainer.getChildren().get(this.rear);
		ele.changeBackGroundColor(Color.SPRINGGREEN);
		
		if(this.font == this.rear) {
			ele = (Element) super.elementContainer.getChildren().get(this.font);
			ele.changeBackGroundColor(Color.AQUA);
		}
    }
	
	@Override
	public void resetData() {
		super.resetData();
		rear = font = -1;
	}

	public int getRear() {
		return rear;
	}


	public int getFont() {
		return font;
	}

}
