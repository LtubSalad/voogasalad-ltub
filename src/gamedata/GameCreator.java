package gamedata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import bus.EventBus;
import commons.RunningMode;
import commons.point.GamePoint;
import data.SerializableDeveloperData;
import data.SpriteMakerModel;
import newengine.app.Game;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.levels.InitILevelsEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.BuildSkillFactory;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.Position;
import newengine.sprite.components.SkillSet;
import newengine.utils.image.LtubImage;
import player.helpers.GameLoadException;
import utilities.XStreamHandler;

/**
 * Class that creates a game from an XML file of translated GameData
 *  
 * @author tahiaemran, Keping
 */
public class GameCreator {

	public GameCreator() { }

	private Sprite createTowerBuilder(Player player, List<SpriteMakerModel> availableTowers) {
		Sprite towerBuilder = new Sprite(SpriteID.TOWER_BUILDER_ID);
		towerBuilder.addComponent(new GameBus());
		towerBuilder.addComponent(new Owner(player));
		Map<SkillType<? extends Skill>, Skill> skillMap = new HashMap<>();
		for (SpriteMakerModel availableTower : availableTowers) {
			BuildSkill buildSkill = BuildSkillFactory.createBuildSkill(availableTower);
			skillMap.put(buildSkill.getType(), buildSkill);
		}
		towerBuilder.addComponent(new SkillSet(skillMap));
		towerBuilder.addComponent(new Images("images/characters/bahamut_left.png"));
		return towerBuilder;
	}
	
	public Game createGame(String gameFilePath) throws GameLoadException {
		return createGame(new File(gameFilePath));
	}
	public Game createGame(File gameFile) throws GameLoadException {
		try {
			Game game = new Game();

			
			XStreamHandler xstreamHandler = new XStreamHandler();
			SerializableDeveloperData myData = xstreamHandler.<SerializableDeveloperData>getObjectFromFile(gameFile);
			// serializableDeveloperData?
			
			// sync the players authoring and game.
			// player 1: the user, towers
			// enemy: the monsters
			Player userPlayer = myData.getUserPlayer();
			
			List<SpriteMakerModel> spriteMakerModels = myData.getLevels().get(0).getSpawners();
			List<Sprite> sprites = new ArrayList<>();
			sprites.addAll(spriteMakerModels.stream().map((spriteMakerModel) -> {
				return (new AuthDataTranslator(spriteMakerModel)).getSprite();
			}).collect(Collectors.toList()));
			System.out.println("see my datax");
			sprites.add(createTowerBuilder(myData.getUserPlayer(), myData.getSprites()));

//			List<Sprite> pathSprites = new ArrayList<>();
//			SkillSet skillSet = (SkillSet) spriteMakerModels.get(0).getComponentByType(SkillSet.TYPE);
//			BuildSkill buildSkill = (BuildSkill) skillSet.getSkill(BuildSkill.TYPE);
//			PathFollower pathFollowerComponent = (PathFollower) buildSkill.getSpriteMakerModel().getComponentByType(PathFollower.TYPE);
//			List<GamePoint> points = new ArrayList<> (pathFollowerComponent.getPath().getPath());
//			for (GamePoint pathPoint : points) {
//				Sprite step = new Sprite();
//				step.addComponent(new Position(pathPoint));
//				LtubImage ltubimage = new LtubImage("images/characters/Stone.jpg");
//				step.addComponent(new Images(ltubimage));
//				step.addComponent(new GameBus());
//				step.addComponent(new Owner(Player.NATURE));
//				pathSprites.add(step);
//			}
			
			EventBus bus = game.getBus();
			bus.on(GameInitializationEvent.ANY, (e) -> {
				bus.emit(new InitILevelsEvent(myData.getLevels()));
				bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
				bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprites));
				bus.emit(new MainPlayerEvent(userPlayer));
//				bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, pathSprites));

//				bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, userPlayer, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_LIVES))));
				bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, userPlayer, 20)); // Hard-coded
//				bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, userPlayer, WealthType.GOLD, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_STARTING_GOLD))));
				bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, userPlayer, WealthType.GOLD, 200));
				
				//TODO condition factory
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			});

			return game;
		} catch (Exception e) {
			if (RunningMode.DEV_MODE) {
				e.printStackTrace();
			}
			throw new GameLoadException("Fail to load game: " + gameFile);
		}
	}
	
	private List<Sprite> makePathSprites(List<Sprite> sprites){
		List<Sprite> pathSprites = new ArrayList<>();
		
		
		
//		List<GamePoint> alreadyAdded = new ArrayList<>();
//		for (Sprite sprite : sprites) {
//			System.out.println("sprites path is being added " + sprite.getID());
//			System.out.println(sprite.hasComponent(PathFollower.TYPE));
//			sprite.getComponent(PathFollower.TYPE).ifPresent((pathFollower) -> {
//				System.out.println("has path follower and makes points queue");
//				Queue<GamePoint> points = pathFollower.getPath().getPath();
//				while (!points.isEmpty()){
//					if (alreadyAdded.contains(points.peek())) continue;
//					Sprite step = new Sprite();
//					step.addComponent(new Position(points.poll()));
//					LtubImage ltubimage = new LtubImage("images/characters/Grass.jpg");
//					step.addComponent(new Images(ltubimage));
//					step.addComponent(new GameBus());
//					pathSprites.add(step);
//				}
//			});
//		}
		return pathSprites;
	}

}
