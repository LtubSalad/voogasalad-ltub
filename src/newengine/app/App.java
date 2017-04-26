package newengine.app;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import data.SpriteMakerModel;
import gamedata.AuthDataTranslator;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.managers.conditions.SetEndConditionEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.components.Position;
import utilities.XStreamHandler;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Game game = new Game();
		Player player1 = new Player("Player 1");
		EventBus bus = game.getBus();
		XStreamHandler xHandler = new XStreamHandler();
		XStream xStream = new XStream(new DomDriver());
		List<SpriteMakerModel> spriteModelsFromFile = xHandler.getScreenModelFile();
		
		for (SpriteMakerModel smm : spriteModelsFromFile) {
			Position imageComponent = (Position) smm.getComponentByType(Position.TYPE);
			System.out.println(imageComponent.pos().x() + " " + imageComponent.pos().y());
		}
		
		AuthDataTranslator translator = new AuthDataTranslator(spriteModelsFromFile);
		translator.translate();
		List<Sprite> listSprites = translator.getSprites();
		//List<Sprite> listSprites = spriteModel.getSprites();
		//System.out.println(listSprites.size());
		for (Sprite s : listSprites) {
			System.out.println("Looping");
			//System.out.println(s.getComponent(Images.TYPE).get().image().getFileName());
		}
		
		bus.on(GameInitializationEvent.ANY, (e) -> {
			bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, listSprites));
			bus.emit(new MainPlayerEvent(player1));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, 3));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, 100));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			// call the spawner to spawn
//			GamePoint targetSpawnPos = new GamePoint(10, 20);
//			bus.emit(new PeriodicEvent(5, 3.0, () -> {
//				spawner.emit(new TriggerSkillEvent(BuildSkill.TYPE, new Target(targetSpawnPos)));
//			}));
		});
		
		stage.setScene(game.getScene());
		game.start();
		stage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}