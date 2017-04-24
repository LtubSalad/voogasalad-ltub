package gamecreation.level;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public abstract class LevelCreator extends TitledPane implements ILevelCreator{
	private VBox content;
	
	public LevelCreator(int i) {
		this(Integer.toString(i));
	}
	
	public LevelCreator(String s){
		super();
		//TODO resource file
		this.setText(s);
		createContent();
	}
	
	public abstract void createContent();
	
	public void addToContent(Node node){
		content.getChildren().add(node);
	}
	
	public VBox getCurrentContent(){
		return content;
	}
}
