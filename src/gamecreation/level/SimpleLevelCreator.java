package gamecreation.level;

import gamecreation.IntegerParameterInput;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimpleLevelCreator extends LevelCreator {
	private VBox content;
	private IntegerParameterInput difficultyMod;

	public SimpleLevelCreator(int levelNum, LevelCreatorHolder parent) {
		super(levelNum, parent);
	}
	
	public SimpleLevelCreator(String s, LevelCreatorHolder parent){
		super(s, parent);
	}
	
	@Override
	public void createContent(){
		content = new VBox();
		difficultyMod = new IntegerParameterInput("Difficulty Modifier", 1, 10);
		Button remove = new Button("remove");
		remove.setOnAction(e -> super.remove(this));
		content.getChildren().addAll(difficultyMod.get(), remove);
		this.setContent(content);
	}
	
	public LevelData getData(){
		return new SimpleLevelData(getName(), difficultyMod.getValue());
	}
}
