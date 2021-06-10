package model.interfaces;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public interface IQueue {
	void enQueue(int number) throws Exception;
	void deQueue() throws Exception;
	void resetData();
	Parent getElementContainer();
	void setElementContainer(Pane pane);
}
