package gamedata;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import data.DeveloperData;
import data.SerializableDeveloperData;
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
import newengine.sprite.Sprite;
import player.helpers.GameLoadException;

/**
 * Class that creates a game from an XML file of translated GameData
 *  
 * @author tahiaemran, Keping
 */
public class GameCreator {

	private XStream xstream;

	public GameCreator() {
		xstream = new XStream(new DomDriver());
	}

	public Game createGame(String gameFilePath) throws GameLoadException {
		return createGame(new File(gameFilePath));
	}
	public Game createGame(File gameFile) throws GameLoadException {
		try {
			Game game = new Game();

			// Read out the game
			SerializableDeveloperData myData = (SerializableDeveloperData) xstream.fromXML(gameFile); // TODO
			// serializableDeveloperData?
			
			// sync the players authoring and game.
			// player 1: the user, towers
			// enemy: the monsters
			Player player1 = myData.getUserPlayer();
			
			List<Sprite> sprites = myData.getLevels().get(0).getSpawners().stream().map(
					(spriteMakerModel) -> (new AuthDataTranslator(spriteMakerModel)).getSprite()
			).collect(Collectors.toList());

			// TODO: tower builder
//			 Sprite towerBuilder = new Sprite(new
			// SpriteID("__TOWER_BUILDER"));
			myData.getSprites();

			EventBus bus = game.getBus();

			bus.on(GameInitializationEvent.ANY, (e) -> {
				bus.emit(new InitILevelsEvent(myData.getLevels()));
				bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
				bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprites));
				
				bus.emit(new MainPlayerEvent(player1));
				bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_LIVES))));
				bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, Integer.parseInt(myData.getGameData().get(DeveloperData.NUMBER_OF_STARTING_GOLD))));
				
				//TODO condition factory
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
				bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			});

			return game;
		} catch (Exception e) {
			throw new GameLoadException("Fail to load game: " + gameFile);
		}
	}

}
