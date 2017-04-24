package gamecreation.level;

import gamecreation.IntegerParameterInput;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class SimpleLevelCreator extends LevelCreator {
	private VBox content;

	public SimpleLevelCreator(int i) {
		super(i);
	}
	
	public SimpleLevelCreator(String s){
		super(s);
	}
	
	@Override
	public void createContent(){
		content = new VBox();
		IntegerParameterInput difficultyMod = new IntegerParameterInput("Difficulty Modifier", 1, 10);
		content.getChildren().add(difficultyMod.get());
		this.setContent(content);
		
	}

	@Override
	public void addToContent(Node node) {
		content.getChildren().add(node);
	}
}
