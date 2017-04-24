package gamecreation.level;

import gamecreation.DoubleParameterInput;
import gamecreation.IntegerParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AdvancedLevelCreator extends LevelCreator{
	private VBox content;
	private IntegerParameterInput numEnemies;
	private DoubleParameterInput spawnTime;
	private DoubleParameterInput damageMultiplier;


	public AdvancedLevelCreator(int i, LevelCreatorHolder parent) {
		super(i, parent);
	}
	
	public AdvancedLevelCreator(String s, LevelCreatorHolder parent){
		super(s, parent);
	}

	@Override
	public void createContent() {
		content = new VBox();
		//TODO magic values
		numEnemies = new IntegerParameterInput("Number of Enemies", 0, 500);
		spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, 10);
		damageMultiplier = new DoubleParameterInput("Damage Multiplier", 0, 5);
		Button remove = new Button("remove");
		remove.setOnAction(e -> super.remove(this));
		content.getChildren().addAll(numEnemies.get(), spawnTime.get(), damageMultiplier.get(), remove);
		this.setContent(content);
	}
	
	public LevelData getData(){
		return new AdvancedLevelData(getName(), numEnemies.getValue(), spawnTime.getValue(), damageMultiplier.getValue());
	}
	
	
}
