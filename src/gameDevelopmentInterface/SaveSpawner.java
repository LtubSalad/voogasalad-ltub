package gameDevelopmentInterface;

import data.SpriteMakerModel;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import newengine.sprite.components.Spawner;

public class SaveSpawner extends HBox {
	private Button saveSpawnerToFile = new Button("Save Spawner to File");
	private Button saveSpawnerToData = new Button("Save Spawner to Data");
	private SpawnerCreation mySpawnerCreation;
	private MonsterAdder myMonsterAdder;
	
	public SaveSpawner(SpawnerCreation spawnCreation, MonsterAdder monsterAdder) {
		myMonsterAdder = monsterAdder;
		mySpawnerCreation = spawnCreation;
		makeButtons();
		this.getChildren().addAll(saveSpawnerToFile, saveSpawnerToData);
	}
	
	private void makeButtons() {
		saveSpawnerToFile.setOnAction(click -> {
			SpriteMakerModel spawner = mySpawnerCreation.getSpawner();
			spawner.addComponent(new Spawner(myMonsterAdder.getNumMonsters(), mySpawnerCreation.getPath(), mySpawnerCreation.getSpawnBetweenTime()));
		});
		
		saveSpawnerToData.setOnAction(click -> {
			
		});
	}

}
