package gamecreation.level;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public abstract class LevelCreator extends TitledPane implements ILevelCreator{
	private VBox content;
	private LevelCreatorHolder parent;
	private String name;
	private LevelData data;
	
	public LevelCreator(int i, LevelCreatorHolder parent, LevelData data) {
		this(Integer.toString(i), parent, data);
	}
	
	public LevelCreator(String s, LevelCreatorHolder parent, LevelData data){
		super();
		this.parent = parent;
		//TODO resource file
		this.name = s;
		this.data = data;
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
	
	public void remove(LevelCreator level){
		parent.remove(level);
	}
	
	public String getName(){
		return name;
	}

	public LevelData getData(){
		return data;
	}
	
	@Override
	public boolean equals(Object other){
		return(other instanceof LevelCreator && ((LevelCreator) other).getName().equals(this.name));
	}
}
