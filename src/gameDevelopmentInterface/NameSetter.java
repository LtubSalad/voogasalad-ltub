package gameDevelopmentInterface;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NameSetter extends HBox{
	private String initialName;
	private TextField nameStorer;
	public NameSetter(String initialName, boolean modifiable){
		this.initialName=initialName;
		this.getChildren().add(new Label("Name:   "));
		if(modifiable){
			nameStorer=new TextField(this.initialName);
			this.getChildren().add(nameStorer);
		}else{
			this.getChildren().add(new Label(this.initialName));
		}
	}
	
	public String getName(){
		if(nameStorer!=null){
			return nameStorer.getText();
		}
		return initialName;
	}

}
