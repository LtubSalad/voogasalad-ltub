package newengine.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import bus.EventBus;
import commons.point.GamePoint;
import data.SpriteMakerModel;
import gameDevelopmentInterface.Path;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.game.StartLevelEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
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
import newengine.sprite.components.Spawner;
import newengine.sprite.components.Speed;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Game game = new Game();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");



		EventBus bus = game.getBus();

		// sprite 1: the tower
		Sprite tower = new Sprite();
		LtubImage image1 = new LtubImage("images/characters/tower2_resized.gif");
		ImageSet imageSet1 = new ImageSet(image1);
		Map<SkillType<? extends Skill>, Skill> skillMap1 = new HashMap<>();
		skillMap1.put(MoveSkill.TYPE, new MoveSkill());
		FireProjectileSkill fireSkill1 = new FireProjectileSkill();
		fireSkill1.setCooldown(3); // add cooldown to the fireProjectilSkill
		tower.addComponent(new Cooldown());
		skillMap1.put(FireProjectileSkill.TYPE, fireSkill1);
		tower.addComponent(new GameBus());
		tower.addComponent(new SkillSet(skillMap1));
		tower.addComponent(new Owner(player1));
		tower.addComponent(new Position(new GamePoint(200, 100), 0));
		//		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		tower.addComponent(new Images("images/characters/tower2_resized.gif"));
		//		sprite1.addComponent(new Speed(200));
		tower.addComponent(new Collidable(CollisionBoundType.IMAGE));
		tower.addComponent(new Selectable(SelectionBoundType.IMAGE));
		tower.addComponent(new Range(200));
		tower.addComponent(new Attacker(25));
		tower.addComponent(new EventQueue(new LinkedList<>()));
		tower.addComponent(new RangeShootingAI());




		SpriteMakerModel child = new SpriteMakerModel();
		Map<SkillType<? extends Skill>, Skill> childSkillMap = new HashMap<>();
		child.addComponent(new GameBus());
		child.addComponent(new SkillSet(childSkillMap));
		child.addComponent(new Owner(player2));
		child.addComponent(new Position(new GamePoint(300, 50), 0));
		child.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		child.addComponent(new Images("images/characters/bahamut_left.png"));
		child.addComponent(new Speed(100));
		child.addComponent(new Collidable(CollisionBoundType.IMAGE));
		child.addComponent(new Selectable(SelectionBoundType.IMAGE));
		child.addComponent(new Range(128));
		child.addComponent(new Attacker(25));
		child.addComponent(new Health(100));
		child.addComponent(new EventQueue(new LinkedList<>()));
		child.addComponent(new PathFollower(new Path()));

		
		Sprite monster = new Sprite();
		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
		ImageSet imageSet2 = new ImageSet(image2);
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
		skillMap2.put(BuildSkill.TYPE, new BuildSkill(child));
		FireProjectileSkill fireSkill2 = new FireProjectileSkill();
		fireSkill2.setCooldown(3); // add cooldown to the fireProjectilSkill
		monster.addComponent(new Cooldown());
//		skillMap2.put(FireProjectileSkill.TYPE, fireSkill2);
		monster.addComponent(new GameBus());
		monster.addComponent(new EventQueue(new LinkedList<>()));
		monster.addComponent(new SkillSet(skillMap2));
		monster.addComponent(new Owner(player2));
		monster.addComponent(new PathFollower(new Path()));
		monster.addComponent(new Position(new GamePoint(100, 100), 0));
		monster.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		monster.addComponent(new Images("images/characters/bahamut_right.png"));
		monster.addComponent(new Speed(100));
		monster.addComponent(new Collidable(CollisionBoundType.IMAGE));
		monster.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		sprite2.addComponent(new Attacker(25));
//		sprite2.addComponent(new Spawner());
		monster.addComponent(new Health(600));
		


		SpriteMakerModel building = new SpriteMakerModel();
		Map<SkillType<? extends Skill>, Skill> monsterMap = new HashMap<>();
		monsterMap.put(MoveSkill.TYPE, new MoveSkill());
		building.addComponent(new GameBus());
		building.addComponent(new SkillSet(monsterMap));
		building.addComponent(new Owner(player1));
		building.addComponent(new Images("images/characters/bahamut_right.png"));
		building.addComponent(new Speed(100));
		building.addComponent(new Health(100));
		building.addComponent(new EventQueue(new LinkedList<>()));
		building.addComponent(new PathFollower(new Path()));
		
	

		
		
//		Sprite sprite2 = new Sprite();
//		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
//		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
//		skillMap2.put(BuildSkill.TYPE, new BuildSkill(child));
//		FireProjectileSkill fireSkill2 = new FireProjectileSkill();
//		fireSkill2.setCooldown(3); // add cooldown to the fireProjectilSkill
//		sprite2.addComponent(new Cooldown());
//		skillMap2.put(FireProjectileSkill.TYPE, fireSkill2);
//		sprite2.addComponent(new GameBus());
//		sprite2.addComponent(new SkillSet(skillMap2));
//
//		sprite2.addComponent(new Owner(player1));
//		sprite2.addComponent(new Position(new GamePoint(100, 100), 0));
//		
//		sprite2.addComponent(new Spawner(100, new Path(), 0.01));
//		sprite2.addComponent(new Spawner(10, new Path(), 0.1));


		
		
		List<Sprite> spritesToAdd = new ArrayList<>();
		spritesToAdd.add(tower);
		spritesToAdd.add(monster);

		
		
		bus.on(GameInitializationEvent.ANY, (e) -> {
			bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToAdd));
			bus.emit(new MainPlayerEvent(player1));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, 3));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player2, 3));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, 100));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player2, WealthType.GOLD, 100));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			bus.emit(new PeriodicEvent(10, 5.0, () -> new StartLevelEvent(StartLevelEvent.START)));
		});
		
		stage.setScene(game.getScene());
		game.start();
		stage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}



}
