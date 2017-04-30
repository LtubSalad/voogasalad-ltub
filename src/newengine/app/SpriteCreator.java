package newengine.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commons.point.GamePoint;
import data.SpriteMakerModel;
import gameDevelopmentInterface.Path;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.Spawner;
import newengine.sprite.components.Speed;
import utilities.XStreamHandler;

public class SpriteCreator {//extends Application {
	private List<SpriteMakerModel> mySprites;
	private Player player1 = new Player("Player 1");
	
	public SpriteCreator() {
		mySprites = new ArrayList<SpriteMakerModel>();
		mySprites.add(createSpawner(createNewSprite()));
	}
	
	public SpriteMakerModel createNewSprite() {
		SpriteMakerModel child = new SpriteMakerModel();
		
		child.addComponent(new GameBus());
		child.addComponent(new Owner(player1));
		child.addComponent(new Position(new GamePoint(300, 50), 0));
		child.addComponent(new Images("images/skills/build.png"));
		child.addComponent(new Speed(1000));
		child.addComponent(new Collidable(CollisionBoundType.IMAGE));
		child.addComponent(new Selectable(SelectionBoundType.IMAGE));
		child.addComponent(new Range(128));
		child.addComponent(new Attacker());
		child.addComponent(new Health(100));
		child.addComponent(new EventQueue(new LinkedList<>()));
		child.addComponent(new PathFollower(new Path()));
		return child;
	}
	
	public SpriteMakerModel createSpawner(SpriteMakerModel toSpawn) {
		SpriteMakerModel sprite2 = new SpriteMakerModel();
		
		
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(BuildSkill.TYPE, new BuildSkill(toSpawn));
		sprite2.addComponent(new Images("images/characters/bahamut_right.png"));
		sprite2.addComponent(new GameBus());
		sprite2.addComponent(new SkillSet(skillMap2));
		sprite2.addComponent(new Owner(player1));
		sprite2.addComponent(new Position(new GamePoint(100, 100), 0));
		sprite2.addComponent(new Spawner(100, new Path(), 0.01));
		XStreamHandler xstream = new XStreamHandler();
		xstream.saveToFile(sprite2);
		SpriteMakerModel spawner = (SpriteMakerModel) xstream.getAttributeFromFile();
		
		return spawner;
	}
	
	public List<SpriteMakerModel> getSprites() {
		return mySprites;
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		mySprites.add(createSpawner(createNewSprite()));
//	}
//	
//	public static void main(String[] args) {
//		Application.launch(args);
//	}

}
