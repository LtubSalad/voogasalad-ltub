package gameDevelopmentInterface;

import java.util.HashMap;
import java.util.Map;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.scene.layout.BorderPane;

public class SpawnerCreation extends BorderPane {
	private SpriteMakerModel spawnerData;
	private SpriteMakerModel spriteToSpawn;
	private DeveloperData model;
	private Path pathForChildrenToFollow;
	private double spawnBetweenTime;
	
	public SpawnerCreation(DeveloperData model) {
		this.model = model;
		instantiate();
	}
	
	public void instantiate() {
		spawnerData = new SpriteMakerModel();
		SpawnerInfoPane mySpawnerInfo = new SpawnerInfoPane();
		AllPossibleMonsters myPossibleMonsters = new AllPossibleMonsters(this, this.model, mySpawnerInfo);
		MonsterAdder myMonsterAdder = new MonsterAdder(myPossibleMonsters);
		this.setLeft(myPossibleMonsters);
		this.setCenter(mySpawnerInfo);
		this.setTop(myMonsterAdder);
		this.setBottom(new SaveSpawner(this, myMonsterAdder));
	}
	
	public void setCurrentMonsterToSpawn(SpriteMakerModel monster) {
		spriteToSpawn = monster;
	}
	
	public SpriteMakerModel getSpawner() {
		return spawnerData;
	}
	
	public void setPath(Path path) {
		pathForChildrenToFollow = path;
	}
	
	public Path getPath() {
		return pathForChildrenToFollow;
	}
	
	public void setSpawnBetween(double time) {
		spawnBetweenTime = time;
	}
	
	public double getSpawnBetweenTime() {
		return spawnBetweenTime;
	}
}