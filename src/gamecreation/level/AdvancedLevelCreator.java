package gamecreation.level;

import gamecreation.DoubleParameterInput;
import gamecreation.IntegerParameterInput;
import javafx.scene.layout.VBox;

public class AdvancedLevelCreator extends LevelCreator{
	private VBox content;

	public AdvancedLevelCreator(int i) {
		super(i);
	}
	
	public AdvancedLevelCreator(String s){
		super(s);
	}

	@Override
	public void createContent() {
		content = new VBox();
		//TODO magic values
		IntegerParameterInput numEnemies = new IntegerParameterInput("Number of Enemies", 0, 500);
		DoubleParameterInput spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, 10);
		DoubleParameterInput damageMultiplier = new DoubleParameterInput("Damage Multiplier", 0, 5);
		content.getChildren().addAll(numEnemies.get(), spawnTime.get(), damageMultiplier.get());
		this.setContent(content);
	}

}
