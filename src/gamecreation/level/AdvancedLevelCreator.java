package gamecreation.level;

import gameauthorgui.inputhelpers.DoubleParameterInput;
import gameauthorgui.inputhelpers.IntegerInputSlider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AdvancedLevelCreator extends LevelCreator{
	private VBox content;
	private IntegerInputSlider numEnemies;
	private DoubleParameterInput spawnTime;
	private DoubleParameterInput damageMultiplier;


	public AdvancedLevelCreator(int i, LevelCreatorHolder parent, LevelData data) {
		super(i, parent, data);
	}
	
	public AdvancedLevelCreator(String s, LevelCreatorHolder parent, LevelData data){
		super(s, parent, data);
	}

	@Override
	public void createContent() {
		content = new VBox();
		//TODO magic values
		numEnemies = new IntegerInputSlider("Number of Enemies", 0, 500);
		spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, 10);
		damageMultiplier = new DoubleParameterInput("Damage Multiplier", 0, 5);
		Button remove = new Button("remove");
		remove.setOnAction(e -> super.remove(this));
		content.getChildren().addAll(numEnemies, spawnTime, damageMultiplier, remove);
		this.setContent(content);
	}

}
