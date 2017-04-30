package gamecreation.level;

import gameauthorgui.inputhelpers.DoubleParameterInput;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SpawnerLevelEditor extends LevelEditor{
	private VBox content;
	private DoubleParameterInput spawnTime;
	

	public SpawnerLevelEditor(LevelData data) {
		super(data);
		createContent();
	}

	public void createContent(){
		content = new VBox();
		spawnTime = new DoubleParameterInput("Time between spawn (sec)",0, 10);
		spawnTime.getDoubleProperty().addListener(e -> getData().setSpawnTime(spawnTime.getValue()));
		Button addSpawner = new Button("Add this Spawner to this Level");
		addSpawner.setOnAction(e -> getData().addSpawner(null));
		
		content.getChildren().addAll(spawnTime, addSpawner);
		this.setContent(content);
	}
}
