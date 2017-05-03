package gamecreation.level;

import gameDevelopmentInterface.SpawnerCreation;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import utilities.AlertHandler;

public class SpawnerLevelEditorHolder extends LevelEditorHolder{
	private SpawnerCreation myCreationSpawner;

	public SpawnerLevelEditorHolder(ObservableList<LevelData> data, double prefHeight, SpawnerCreation creationSpawner) {
		super(data, prefHeight);
		myCreationSpawner = creationSpawner;
		createSpawnerButton();
	}

	private void createSpawnerButton(){
		Button spawnerButton = new Button("Add Spawner to all levels");
		spawnerButton.setOnAction(action -> getLevelData().stream().forEach(level -> {
			level.addSpawner(myCreationSpawner.getSpawner());
			AlertHandler.showMessage("Spawner saved to level "+level.getName());
		}));
		addNode(spawnerButton);
	}
	
	@Override
	public void render(){
		super.render();
		getLevelEditors().getChildren().stream().forEach(e -> ((SpawnerLevelEditor) e).setSpawnerCreation(myCreationSpawner));
	}
}
