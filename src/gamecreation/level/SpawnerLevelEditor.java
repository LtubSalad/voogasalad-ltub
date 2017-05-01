package gamecreation.level;

import gameDevelopmentInterface.SpawnerCreation;
import gameauthorgui.inputhelpers.DoubleParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SpawnerLevelEditor extends LevelEditor{
	private VBox content;
	private DoubleParameterInput spawnTime;
	private SpawnerCreation spawnerCreation;
	

	public SpawnerLevelEditor(LevelData data) {
		super(data);
		createContent();
	}

	public void createContent(){
		content = new VBox();
		spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, 10);
		spawnTime.getDoubleProperty().addListener(e -> getData().setSpawnTime(spawnTime.getValue()));
		Button addSpawner = new Button("Add this Spawner to this Level");
		addSpawner.setOnMouseClicked(e -> {System.out.println("spawn " + spawnerCreation.getSpawner() == null);getData().addSpawner(spawnerCreation.getSpawner());});
		
		content.getChildren().addAll(spawnTime, addSpawner);
		this.setContent(content);
	}
	
	public void setSpawnerCreation(SpawnerCreation spawner){
		spawnerCreation = spawner;
	}
}
