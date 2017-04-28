package gamecreation.level;

import gamecreation.IntegerInputSlider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimpleLevelCreator extends LevelCreator {
	private VBox content;
	private IntegerInputSlider difficultyMod;

	public SimpleLevelCreator(int levelNum, LevelCreatorHolder parent) {
		super(levelNum, parent);
	}
	
	public SimpleLevelCreator(String s, LevelCreatorHolder parent){
		super(s, parent);
	}
	
	@Override
	public void createContent(){
		content = new VBox();
		difficultyMod = new IntegerInputSlider("Difficulty Modifier", 1, 10);
		Button remove = new Button("remove");
		remove.setOnAction(e -> super.remove(this));
		content.getChildren().addAll(difficultyMod, remove);
		this.setContent(content);
		
	}
	
	public LevelData getData(){
		return new SimpleLevelData(getName(), difficultyMod.getValue());
	}
}
