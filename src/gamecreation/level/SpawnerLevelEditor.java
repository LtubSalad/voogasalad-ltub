package gamecreation.level;

import gameauthorgui.inputhelpers.DoubleParameterInput;
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
		content.getChildren().add(spawnTime);
		this.setContent(content);
	}
}
