package gamecreation.level;

import gameauthorgui.inputhelpers.IntegerInputSlider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimpleLevelEditor extends LevelCreator {
	private VBox content;
	private IntegerInputSlider difficultyMod;

	public SimpleLevelEditor(int levelNum, LevelCreatorHolder parent, LevelData data) {
		super(levelNum, parent, data);
	}
	
	public SimpleLevelEditor(String s, LevelCreatorHolder parent, LevelData data){
		super(s, parent, data);
	}
	

	@Override
	public void createContent(){
		content = new VBox();
		difficultyMod = new IntegerInputSlider("Difficulty Modifier", 1, 10);
		content.getChildren().add(difficultyMod);
		this.setContent(content);
		
	}
	
	public LevelData getData(){
		return new SimpleLevelData();
	}
}
