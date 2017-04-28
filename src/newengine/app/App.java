package newengine.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bus.EventBus;
import commons.point.GamePoint;
import gameDevelopmentInterface.Path;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.QueueEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.Sprite;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.Cooldown;
import newengine.sprite.components.DamageStrength;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.RangeShootingAI;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.trigger.Trigger;
import newengine.utils.Target;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Game game = new Game();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		// building
		Sprite building = new Sprite();
		LtubImage buildingImage = new LtubImage("images/skills/build.png");
		ImageSet imageSetBuildSkill = new ImageSet(buildingImage);
		building.addComponent(new Images(imageSetBuildSkill));
		building.addComponent(new Selectable(SelectionBoundType.IMAGE));
		
		
		EventBus bus = game.getBus();
		
		// sprite 1: the tower
		Sprite sprite1 = new Sprite();
		LtubImage image1 = new LtubImage("images/characters/tower2_resized.gif");
		ImageSet imageSet1 = new ImageSet(image1);
		Map<SkillType<? extends Skill>, Skill> skillMap1 = new HashMap<>();
		skillMap1.put(MoveSkill.TYPE, new MoveSkill());
		FireProjectileSkill fireSkill1 = new FireProjectileSkill();
		fireSkill1.setCooldown(3); // add cooldown to the fireProjectilSkill
		sprite1.addComponent(new Cooldown());
		skillMap1.put(FireProjectileSkill.TYPE, fireSkill1);
		sprite1.addComponent(new GameBus());
		sprite1.addComponent(new DamageStrength(25));
		sprite1.addComponent(new SkillSet(skillMap1));
		sprite1.addComponent(new Owner(player1));
		sprite1.addComponent(new Position(new GamePoint(400, 100), 0));
		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite1.addComponent(new Images(imageSet1));
		sprite1.addComponent(new Speed(200));
		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite1.addComponent(new Range(200));
		sprite1.addComponent(new Attacker());
		sprite1.addComponent(new Health(200));
		sprite1.addComponent(new EventQueue(new LinkedList<>()));
		sprite1.addComponent(new RangeShootingAI());
			
		// sprite 2
		Sprite sprite2 = new Sprite();
		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
		ImageSet imageSet2 = new ImageSet(image2);
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
		FireProjectileSkill fireSkill2 = new FireProjectileSkill();
		fireSkill2.setCooldown(3); // add cooldown to the fireProjectilSkill
		sprite2.addComponent(new Cooldown());
		skillMap2.put(FireProjectileSkill.TYPE, fireSkill2);
		sprite2.addComponent(new GameBus());
		sprite2.addComponent(new SkillSet(skillMap2));
		sprite2.addComponent(new Owner(player2));
		sprite2.addComponent(new Position(new GamePoint(0, 100), 0));
		sprite2.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite2.addComponent(new Images(imageSet2));
		sprite2.addComponent(new Speed(100));
		sprite2.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite2.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite2.addComponent(new EventQueue(new LinkedList<>()));
		sprite2.addComponent(new Attacker());
		sprite2.addComponent(new Health(600));
//		sprite2.addComponent(new RangeShootingAI());
//		sprite2.addComponent(new Range(200));
		sprite2.addComponent(new PathFollower(new Path()));
		
		
		
		// spawned sprite
		Sprite child = new Sprite();
		LtubImage childImage = new LtubImage("images/characters/bahamut_left.png");
		ImageSet childImageSet = new ImageSet(childImage);
		Map<SkillType<? extends Skill>, Skill> childSkillMap = new HashMap<>();
		child.addComponent(new GameBus());
		child.addComponent(new SkillSet(childSkillMap));
		child.addComponent(new Owner(player2));
		child.addComponent(new Position(new GamePoint(300, 50), 0));
		child.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		child.addComponent(new Images(childImageSet));
		child.addComponent(new Speed(200));
		child.addComponent(new Collidable(CollisionBoundType.IMAGE));
		child.addComponent(new Selectable(SelectionBoundType.IMAGE));
		child.addComponent(new Range(128));
		child.addComponent(new Attacker());
		child.addComponent(new Health(200));
		child.addComponent(new EventQueue(new LinkedList<>()));
		child.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, child, 
			new Target(new GamePoint(300,300)))));
		
		// The spawner
		Sprite spawner = new Sprite();
		Map<SkillType<? extends Skill>, Skill> spawnerSkillMap = new HashMap<>();
		spawnerSkillMap.put(BuildSkill.TYPE, new BuildSkill(child));
		spawner.addComponent(new GameBus());
		spawner.addComponent(new SkillSet(spawnerSkillMap));
		spawner.addComponent(new Owner(player1));
		spawner.addComponent(new Position(new GamePoint(-100, -100), 0));
		spawner.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		spawner.addComponent(new Images(imageSet1));
		spawner.addComponent(new Speed(200));
		spawner.addComponent(new Collidable(CollisionBoundType.IMAGE));
		spawner.addComponent(new Selectable(SelectionBoundType.IMAGE));
		spawner.addComponent(new Range(128));
		spawner.addComponent(new Attacker());
		spawner.addComponent(new Health(200));
		spawner.addComponent(new EventQueue(new LinkedList<>()));

		
		List<Sprite> spritesToAdd = new ArrayList<>();
		spritesToAdd.add(sprite1);
		spritesToAdd.add(sprite2);
//		spritesToAdd.add(child);
//		spritesToAdd.add(spawner);
		
		
		bus.on(GameInitializationEvent.ANY, (e) -> {
			bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToAdd));
			bus.emit(new MainPlayerEvent(player1));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, 3));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, 100));
//			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
//			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			// call the spawner to spawn
//			GamePoint targetSpawnPos = new GamePoint(10, 20);
//			bus.emit(new PeriodicEvent(5, 3.0, () -> {
//				spawner.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(targetSpawnPos)));
//			}));
		});
		
		// Triggers
		for (Trigger trigger : (new TestTriggers()).getTriggers()) {
			game.addTrigger(trigger);
		}
		
		stage.setScene(game.getScene());
		stage.setFullScreen(false);
		game.start();
		stage.show();
		

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
