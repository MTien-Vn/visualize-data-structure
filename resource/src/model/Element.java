package model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Element extends StackPane {
	private float radius; // radius of circle, default  radius = 10f
	
	private Color bgColor; // Background color of circle when not selected, default: white
	
	private Circle circle; // shape of the element
	
	private Text text; //text in the element
	
	private double centerX;
	
	private double centerY;
	
	private int value;
	
	public Element(int number, float radius, Color color) {
		this.radius = radius;
		this.bgColor = color;
		this.circle = createCircle(this.radius, this.bgColor);
		this.text = createText(number);
		this.value = number;
		
		this.getChildren().addAll(this.circle, this.text);
	}
	
	public Element(int number, double centerX, double centerY, float radius, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.bgColor = color;
		this.circle = createCircle(this.radius, this.bgColor);
		this.text = createText(number);
		this.value = number;
		
		this.getChildren().addAll(circle, text);
		this.setTranslateX(this.centerX);
		this.setTranslateY(this.centerY);
	}
	
	private Circle createCircle(float radius, Color color) {
		Circle circle = new Circle(radius, color);
		circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        return circle;
	}
	
	private Text createText(int number) {
		Text text = new Text(Integer.toString(number));
		text.setBoundsType(TextBoundsType.VISUAL); 
		return text;
	}
	
	public void changeBackGroundColor(Color color) {
		this.bgColor = color;
		this.circle.setFill(this.bgColor);
	}
	
	public Color getBgColor() {
		return bgColor;
	}
	
	public int getValue() {
		return this.value;
	}
}
