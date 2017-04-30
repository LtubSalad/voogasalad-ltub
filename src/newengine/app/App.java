package newengine.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import commons.point.GamePoint;
import data.DeveloperData;
import data.SerializableDeveloperData;
import data.SpriteMakerModel;
import gameDevelopmentInterface.Path;
import gamedata.AuthDataTranslator;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.game.StartLevelEvent;
import newengine.events.player.MainPlayerEvent;
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
import newengine.sprite.Sprite;
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

public class App extends Application {
	DeveloperData myData;
	
	public App(DeveloperData mainData) {
		myData = mainData;
	}
	

	@Override
	public void start(Stage stage) throws Exception {

		Player player1 = new Player("Player 1");


//		XStreamHandler xHandler = new XStreamHandler();
//		XStream xStream = new XStream(new DomDriver());
		
		List<SpriteMakerModel> listSpriteModels = myData.getLevelData().get(0).getSpawners();
		
		//SpriteCreator jakeTestCreator = new SpriteCreator();
		//List<SpriteMakerModel> listSpriteModels = jakeTestCreator.getSprites();// = translator.getSprites();
		List<Sprite> listSprites = new ArrayList<>();
		for (SpriteMakerModel smm : listSpriteModels) {
			AuthDataTranslator translator = new AuthDataTranslator(smm);
			listSprites.add(translator.getSprite());
		}

		Game game = new Game();
		EventBus bus = game.getBus();
		
		
		
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



		
		
		Sprite sprite2 = new Sprite();
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(BuildSkill.TYPE, new BuildSkill(child));
		sprite2.addComponent(new Images("images/characters/bahamut_right.png"));
		sprite2.addComponent(new GameBus());
		sprite2.addComponent(new SkillSet(skillMap2));
		sprite2.addComponent(new Owner(player1));
		sprite2.addComponent(new Position(new GamePoint(100, 100), 0));
		sprite2.addComponent(new Spawner(100, new Path(), 0.01));

		
		
		//listSprites.add(sprite2);
		
		
		bus.on(GameInitializationEvent.ANY, (e) -> {
			//bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, listSprites));
			bus.emit(new MainPlayerEvent(player1));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, 3));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, 100));
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