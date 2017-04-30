package gamecreation.level;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class SpawnerLevelEditorHolder extends LevelEditorHolder{

	public SpawnerLevelEditorHolder(ObservableList<LevelData> data, double prefHeight) {
		super(data, prefHeight);
		createSpawnerButton();
	}

	private void createSpawnerButton(){
		Button spawnerButton = new Button("Add Spawner to all levels");
		spawnerButton.setOnAction(action -> getLevelData().stream().forEach(level -> level.addSpawner(null)));
		addNode(spawnerButton);
	}
}
