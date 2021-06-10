package model;

import exception.DataStructLengthException;
import exception.IndexException;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class DataStruct {

    @FXML
    protected Pane elementContainer; // pane to hold model element
    
    protected float radius = 10f; // radius of circle   
    
    protected float space = 10f; // space between 2 circles  
    
    protected Color bgColor = Color.WHITE; // background color of circle when not selected
    
    protected Color bgSelectedColor = Color.AQUA; // background color of circle when selected
    
	/*
	 * purpose: get space between centers of two circles
	 * input: none
	 * output: result
	 */
    protected double spaceBetweenCenters() { 
    	return 2*radius + space;
    }
    
    /*
     * purpose: create a random list of element
     * input: 
     * 		- length: length of list
     * output: none
     */
    public void createRandomList(int length) throws DataStructLengthException { //////////////// CREATE 
    	int min = 0, max = 100;
    	int centerX = 0;
    	
    	if(length <= 0) throw new DataStructLengthException("Invalid length of data structure");
    	
    	elementContainer.getChildren().clear();;
    	for(int i = 0; i < length; i++) {
    		int random = min + (int)(Math.random() * ((max - min) + 1));
    		Element element = new Element(random, centerX, 0, radius, bgColor);
    		centerX += spaceBetweenCenters();
    		elementContainer.getChildren().add(element);
    	}
    }
    
    /*
     * purpose: insert element into list
     * input:
     * 		- isSeq: true: show step by step when searching for index
     * 		- value: value of number which will be inserted
     * 		- pos: index of number which will be inserted
     * output: none
     */
    protected void insertElement(boolean isSeq, int value, int pos) throws IndexException { /////////////// INSERT
    	int n = elementContainer.getChildren().size();
    	SequentialTransition seq = new SequentialTransition();
    	
    	// EXCEPTION
    	if(pos < 0 || pos > n) throw new IndexException("Invalid position");
    	
    	
    	if(n == 0) { // IF container is empty
    		Element element = new Element(value, radius, bgColor);
    		elementContainer.getChildren().add(element);
    		return;
    	}
    	if(!isSeq) { // IF not run sequentially
			double tranX;
			double tranY;
			
			if(n != pos) { // IF not add to tail
				Element element = (Element)elementContainer.getChildren().get(pos);
				tranX = element.getTranslateX();
				tranY = element.getTranslateY() + 50;
				seq.getChildren().add(moveTheRestHorizontal(tranX+spaceBetweenCenters(), pos));
			} else { // IF add to tail
				Element element = (Element)elementContainer.getChildren().get(n-1);
				tranX = element.getTranslateX() + spaceBetweenCenters();
				tranY = element.getTranslateY() + 50;
			}
			
			Element newElement = new Element(value, tranX, tranY, radius, this.bgColor);
			elementContainer.getChildren().add(pos, newElement);
			newElement.setOpacity(0.0);
			seq.getChildren().add(moveElementVertical(false, tranY-50, newElement));
		} else {
			for(int i = 0; i < n; i++) { // IF run sequentially
	    		Element element = (Element)elementContainer.getChildren().get(i);
	    		if(i == pos) {
	    			double tranX = element.getTranslateX();
	    			seq.getChildren().add(moveTheRestHorizontal(tranX + spaceBetweenCenters(), pos)); // move elements out
	    			// insert element
	    			double tranY = element.getTranslateY() + 50;
	    			Element newElement = new Element(value, tranX, tranY, radius, bgColor);
	    			elementContainer.getChildren().add(pos, newElement);
	    			newElement.setOpacity(0.0);
	    			
	    			// fade in
	    			seq.getChildren().add(moveElementVertical(false, tranY-50, newElement));
	    			
	    			break;
	    		} else {
	    			Circle circle = (Circle)element.getChildren().get(0);
	    			Color currentColor = element.getBgColor(); 
	    			FillTransition selected = new FillTransition(Duration.millis(1), circle, currentColor, bgSelectedColor);
	    			PauseTransition pause = new PauseTransition(Duration.millis(1000));
	    			FillTransition unselected = new FillTransition(Duration.millis(1), circle, bgSelectedColor, currentColor);
	    			seq.getChildren().addAll(selected, pause, unselected);
	    		}
	    	}
			if(n == pos) { // IF add to the tail
				Element element = (Element)elementContainer.getChildren().get(n-1);
				double tranX = element.getTranslateX() + spaceBetweenCenters();
				double tranY = element.getTranslateY() + 50;
				
				Element newElement = new Element(value, tranX, tranY, radius, bgColor);
    			elementContainer.getChildren().add(pos, newElement);
    			newElement.setOpacity(0.0);
    			
    			// fade in
    			seq.getChildren().add(moveElementVertical(false, tranY-50, newElement));
			}
			
		}
    	
    	seq.play();
    }
    
    
    /*
     * purpose: remove element in list
     * input: 
     *		- isSeq: true: show step by step when searching for index
     * 		- pos: index of element which will be removed
     * output: none
     */
    protected void removeElement(boolean isSeq, int pos) throws IndexException { /////////////// INSERT
    	int n = elementContainer.getChildren().size();
    	SequentialTransition seq = new SequentialTransition();
    	
    	
    	// EXCEPTION
    	if(pos < 0 || pos >= n) throw new IndexException("Invalid position");
    	
    	
    	if(!isSeq) { // IF not run sequentially
    		Element element = (Element)elementContainer.getChildren().get(pos);
			double tranX = element.getTranslateX();
			double tranY = element.getTranslateY();
			seq.getChildren().add(moveElementVertical(true, tranY+50, element));
			seq.getChildren().add(moveTheRestHorizontal(tranX-spaceBetweenCenters(), pos+1));
		} else {
			for(int i = 0; i < n; i++) { // IF run sequentially
	    		Element element = (Element)elementContainer.getChildren().get(i);
	    		if(i == pos) {
	    			double tranX = element.getTranslateX();
	    			double tranY = element.getTranslateY();
	    			// fade out
	    			seq.getChildren().add(moveElementVertical(true, tranY+50, element));
	    			seq.getChildren().add(moveTheRestHorizontal(tranX - spaceBetweenCenters(), pos)); // move elements out
	    			break;
	    		} else {
	    			Circle circle = (Circle)element.getChildren().get(0);
	    			Color currentColor = element.getBgColor(); 
	    			FillTransition selected = new FillTransition(Duration.millis(1), circle, currentColor, bgSelectedColor);
	    			PauseTransition pause = new PauseTransition(Duration.millis(1000));
	    			FillTransition unselected = new FillTransition(Duration.millis(1), circle, bgSelectedColor, currentColor);
	    			seq.getChildren().addAll(selected, pause, unselected);
	    		}
	    	}
		}
    	
    	seq.setOnFinished(e -> {
    		elementContainer.getChildren().remove(pos);
    	});
    	
    	seq.play();
    }
    
    /*
     * purpose: move an element vertically (insert: move in, remove: move out)
     * input:
     * 		- fadeOut: true: element will slowly disappear (should be used when removing)
     * 				false: element will slowly appear (should be used when inserting)
     * 		- toTranY: location to be moved in
     * 		- element: Element to be affected
     * output: SequentialTransition
     */
    protected SequentialTransition moveElementVertical(boolean fadeOut, double toTranY, Element element) {
    	SequentialTransition seq = new SequentialTransition();
    	
    	// move to list
		TranslateTransition tran = new TranslateTransition(Duration.millis(1000), element);
		tran.setToY(toTranY);
    	
    	// fade transition
		FadeTransition fade = new FadeTransition(Duration.millis(1000), element);
		if(fadeOut) {
			fade.setFromValue(1.0);
			fade.setToValue(0.0);
			seq.getChildren().addAll(tran, fade);
		} else {
			fade.setFromValue(0.0);
			fade.setToValue(1.0);
			seq.getChildren().addAll(fade, tran);
		}
		
		return seq;
    }
    
    
    /*
     * purpose: move the rest of elements in list (insert: move to the right, remove: move to the left)
     * input:
     * 		- startX: first location to be moved in
     * 		- idx: start from index idx
     * output: ParallelTransition
     */
    protected ParallelTransition moveTheRestHorizontal(double startX, int idx) {
    	ParallelTransition par = new ParallelTransition();
    	int n = elementContainer.getChildren().size();
    	
    	for(int i = idx; i < n; i++) {
    		Element self = (Element)elementContainer.getChildren().get(i);
    		TranslateTransition translate = new TranslateTransition(Duration.millis(1000), self);
    		
    		// TO 
    		translate.setToX(startX);
    		startX += 2*radius+space;
    		
    		par.getChildren().add(translate);
    	}
    	
    	return par;
    }
    
    /*
     * purpose: reset list
     * 
     */
    public void resetData() {
    	this.elementContainer.getChildren().clear();
    }
    
	public Pane getElementContainer() {
		return elementContainer;
	}

	public void setElementContainer(Pane elementContainer) {
		this.elementContainer = elementContainer;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getSpace() {
		return space;
	}

	public void setSpace(float space) {
		this.space = space;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Color getBgSelectedColor() {
		return bgSelectedColor;
	}

	public void setBgSelectedColor(Color bgSelectedColor) {
		this.bgSelectedColor = bgSelectedColor;
	}

	
}
