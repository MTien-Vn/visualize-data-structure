package model;

import exception.DataStructLengthException;
import exception.IndexException;
import javafx.scene.paint.Color;
import model.interfaces.IStack;

public class Stack extends DataStruct implements IStack {
	int top = -1;
	@Override
	public void push(int number) throws IndexException {
		Element ele;
		if(super.elementContainer.getChildren().size() > 0 ) {
			ele = (Element) super.elementContainer.getChildren().get(this.top);
			ele.changeBackGroundColor(Color.WHITE);
		}
		top++;
		super.insertElement(false, number, top);
		
		ele = (Element) super.elementContainer.getChildren().get(top);
		ele.changeBackGroundColor(Color.SPRINGGREEN);
	}

	@Override
	public void pop() throws Exception {
		if(this.top > -1) {
			super.removeElement(false, top);
			top--;
		}
		
		if(top > -1) {
			Element ele = (Element) super.elementContainer.getChildren().get(top);
			ele.changeBackGroundColor(Color.SPRINGGREEN);
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
    	this.top = length -1;
    	super.createRandomList(length);
    	Element ele = (Element) super.elementContainer.getChildren().get(top);
		ele.changeBackGroundColor(Color.SPRINGGREEN);
    }

	@Override
	public void resetData() {
		super.resetData();
		top = -1;
	}
	
	public int getTop() {
		return top;
	}
}
