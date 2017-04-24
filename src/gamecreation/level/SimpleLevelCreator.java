package gamecreation.level;

import gamecreation.IntegerParameterInput;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimpleLevelCreator extends LevelCreator {
	private VBox content;

	public SimpleLevelCreator(int levelNum, LevelCreatorHolder parent) {
		super(levelNum, parent);
	}
	
	public SimpleLevelCreator(String s, LevelCreatorHolder parent){
		super(s, parent);
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
