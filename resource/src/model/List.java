package model;

import exception.DataStructLengthException;
import exception.IndexException;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.interfaces.IList;

public class List extends DataStruct implements IList {
	
	private Color bgColor = Color.SPRINGGREEN;
	private int numberOfItems = 0;
	
	public List() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Insert(int pos, int value) throws IndexException {
		try {
			this.numberOfItems += 1;
			if (pos == 0) {
				if (elementContainer.getChildren().size() > 0) {
					Element head = (Element)elementContainer.getChildren().get(0);
					head.changeBackGroundColor(Color.WHITE);
				}
				this.insertElement(true, value, pos);
				Element head = (Element)elementContainer.getChildren().get(0);
				head.changeBackGroundColor(bgColor);
			}
			else {
				this.insertElement(true, value, pos);
			}
		}catch(IndexException e) {
			this.numberOfItems -= 1;
			throw e;
		}

	}

	@Override
	public void Remove(int pos) throws IndexException {
		try {
			this.numberOfItems -= 1;
			if (pos == 0 && elementContainer.getChildren().size() > 1) {
				Element head = (Element)elementContainer.getChildren().get(1);
				head.changeBackGroundColor(bgColor);
			}
			this.removeElement(true, pos);
		}catch(IndexException e) {
			this.numberOfItems += 1;
			throw e;
		}
	}

	@Override
	public int Find(int value){
		int n = elementContainer.getChildren().size();
    	SequentialTransition seq = new SequentialTransition();
    	int pos = -1;
    	
		for(int i = 0; i < n; i++) { // IF run sequentially
    		Element element = (Element)elementContainer.getChildren().get(i);
    		Color newColor = bgSelectedColor;
    		float waitTime = 1000;
    		if(element.getValue() == value) {
    			newColor = Color.RED;
    			waitTime = 2000;
    			pos = i;
    		}

    		Circle circle = (Circle)element.getChildren().get(0);
			Color currentColor = element.getBgColor(); 
			FillTransition selected = new FillTransition(Duration.millis(1), circle, currentColor, newColor);
			PauseTransition pause = new PauseTransition(Duration.millis(waitTime));
			FillTransition unselected = new FillTransition(Duration.millis(1), circle, newColor, currentColor);
			seq.getChildren().addAll(selected, pause, unselected);
			if(element.getValue() == value)
				break;
    	}
		seq.play();
		return pos;
	}
	
	@Override
	public void createRandomList(int length) throws DataStructLengthException {
		super.createRandomList(length);
		Element head = (Element)elementContainer.getChildren().get(0);
		head.changeBackGroundColor(bgColor);
		this.numberOfItems = length;
	}
	
	public boolean IsEmpty() {
		return this.numberOfItems == 0;
	}
	
	public void setNumberOfItems(int size) {
		this.numberOfItems = size;
	}
}
