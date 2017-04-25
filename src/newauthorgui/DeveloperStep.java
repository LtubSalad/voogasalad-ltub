package newauthorgui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class DeveloperStep extends Label{
	private Node step;

	DeveloperStep(String name, Node step){
		super(name);
		this.step = step;
		this.setOnMouseClicked(e-> this.setTextFill(new Color(100, 100, 100, 100)));
	}
	
	public Node getStep(){
		return step;
	}

}
