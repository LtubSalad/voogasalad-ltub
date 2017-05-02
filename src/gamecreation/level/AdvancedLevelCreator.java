package gamecreation.level;

import gameauthorgui.inputhelpers.DoubleParameterInput;
import gameauthorgui.inputhelpers.IntegerInputSlider;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * This a concrete implementation of Level Creator which basically which take in a LevelData object 
 * and set values accordingly. In this specific concrete implementation, it is set to edit
 * number of enemies, spawn time, and damage multiplier 
 * @author Matthew Tribby
 */
public class AdvancedLevelCreator extends LevelCreator{
	public static final int MAX_NUM_ENEMIES = 500;
	public static final int MAX_TIME_SPAWN = 10;
	public static final double MAX_DAMAGE_MULTI = 5;
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
		numEnemies = new IntegerInputSlider("Number of Enemies", 0, MAX_NUM_ENEMIES);
		spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, MAX_TIME_SPAWN);
		damageMultiplier = new DoubleParameterInput("Damage Multiplier", 0, MAX_DAMAGE_MULTI);
		Button remove = new Button("remove");
		remove.setOnAction(e -> super.remove());
		content.getChildren().addAll(numEnemies, spawnTime, damageMultiplier, remove);
		this.setContent(content);
	}

}
