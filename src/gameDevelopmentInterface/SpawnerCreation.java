package gameDevelopmentInterface;

import java.util.HashMap;
import java.util.Map;

import commons.point.GamePoint;
import data.DeveloperData;
import data.SpriteMakerModel;
import gamecreation.level.SpawnerLevelEditorHolder;
import javafx.scene.layout.BorderPane;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.Spawner;

public class SpawnerCreation extends BorderPane {
	private SpriteMakerModel spawnerData;
	private SpriteMakerModel spriteToSpawn;
	private DeveloperData model;
	private Path pathForChildrenToFollow;
	private double spawnBetweenTime;
	private MonsterAdder myMonsterAdder;
	
	public SpawnerCreation(DeveloperData model) {
		this.model = model;
		instantiate();
	}
	
	public void instantiate() {
		spawnerData = new SpriteMakerModel();
		SpawnerInfoPane mySpawnerInfo = new SpawnerInfoPane();
		AllPossibleMonsters myPossibleMonsters = new AllPossibleMonsters(this, this.model, mySpawnerInfo);
		myMonsterAdder = new MonsterAdder(myPossibleMonsters);
		this.setLeft(myPossibleMonsters);
		this.setCenter(mySpawnerInfo);
		this.setTop(myMonsterAdder);

		//this.setBottom(new SaveSpawner(model, this, myMonsterAdder));
		this.setRight(new SpawnerLevelEditorHolder(model.getLevelData(), 600, this));
	}
	
	public void saveSpawnerToModel() {
		this.model.addSprite(spawnerData);
	}
	
	public void setCurrentMonsterToSpawn(SpriteMakerModel monster) {
		monster.addComponent(new Position(0.1,0.1,0));
		spriteToSpawn = monster;
	}
	
	public SpriteMakerModel getSpawner() {
		Map<SkillType<? extends Skill>, Skill> spawnerSkills = new HashMap<>();
		spawnerSkills.put(BuildSkill.TYPE, new BuildSkill(spriteToSpawn));
		spawnerData.addComponent(new GameBus());
		spawnerData.addComponent(new SkillSet(spawnerSkills));
		spawnerData.addComponent(new Owner(new Player("Spawner")));
		spawnerData.addComponent(new Position(new GamePoint(0.1, 0.1), 0));
		spawnerData.addComponent(new Spawner(myMonsterAdder.getNumMonsters(), new Path(), 0.5, spriteToSpawn));
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
