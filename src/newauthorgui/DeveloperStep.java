package newauthorgui;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DeveloperStep extends HBox{
	private Node step;
	private String name;

	DeveloperStep(String name, Node step){
		super();
		this.getChildren().add(new Text(name));
		this.step = step;
		this.name = name;
		setBaseColor();
	}
	
	public void highlight(){
		this.setStyle("-fx-background-color: #ffff00;");
	}
	
	public void setBaseColor(){
		this.setStyle("-fx-background-color: #ffffff;");
	}
	
	public Node getStep(){
		return step;
	}
	
	public String getName(){
		return name;
	}

}
