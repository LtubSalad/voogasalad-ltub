package gamedata;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bus.EventBus;
import commons.RunningMode;
import data.DeveloperData;
import data.SerializableDeveloperData;
import data.SpriteMakerModel;
import newengine.app.Game;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.levels.InitILevelsEvent;
import newengine.events.player.MainPlayerEvent;
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
import newengine.sprite.components.SkillSet;
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
			
			List<Sprite> sprites = myData.getLevels().get(0).getSpawners().stream().map(
					(spriteMakerModel) -> (new AuthDataTranslator(spriteMakerModel)).getSprite()
			).collect(Collectors.toList());
			sprites.add(createTowerBuilder(myData.getUserPlayer(), myData.getLevels().get(0).getSpawners()));
			

			EventBus bus = game.getBus();
			bus.on(GameInitializationEvent.ANY, (e) -> {
				bus.emit(new InitILevelsEvent(myData.getLevels()));
				bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
				bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprites));
				bus.emit(new MainPlayerEvent(userPlayer));
				
				System.out.println(userPlayer.getName());
				
				bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, userPlayer, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_LIVES))));
				System.out.println("Initial gold: " + Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_STARTING_GOLD)));
				bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, userPlayer, WealthType.GOLD, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_STARTING_GOLD))));
				
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

}
