package gamedata;

import java.io.File;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import data.SerializableDeveloperData;
import data.SpriteMakerModel;
import gamecreation.level.ILevelData;
import newengine.app.Game;
import newengine.events.SpriteModelEvent;
import newengine.sprite.Sprite;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 *
 *class that creates a game from an XML file of translated GameData 
 *
 */
public class GameCreator {
	File fileToRead; 
	XStreamHandler XSH; 
	XStream xstream; 
	
	
	private Game game; 

	
	public GameCreator(String filepath){
		// set up file reading 
		fileToRead= new File(filepath);
		XSH = new XStreamHandler(); 
		xstream = new XStream(new DomDriver());
		// create the game
		createGame(); 
	}
	
	public GameCreator(File file){
		this.fileToRead = file; 
		XSH = new XStreamHandler();
		xstream = new XStream(new DomDriver());
		createGame(); 
	}

	public Game getGame(){ 
		return game; 
	}

	private void createGame() {
		// Read out the game 
		SerializableDeveloperData gameData = (SerializableDeveloperData) xstream.fromXML(fileToRead);
	
		//Process Levels
		List<ILevelData> levels = gameData.getLevels();
		System.out.println(levels.get(0).getName());
		
		//Process Sprites 
		List<SpriteMakerModel> initialSpriteModels =  gameData.getSprites();
		System.out.println(initialSpriteModels);
		TranslationController translator = new TranslationController(initialSpriteModels); 
		translator.translate();
		List<Sprite> createdSprites = (List<Sprite>) translator.getTranslated();  
		
		game = new Game(); 
		EventBus bus = game.getBus();
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, createdSprites));
		
		
//		// TODO: figure this part out 
//		bus.emit(new MainPlayerEvent(createdSprites.get(0).getComponent(Owner.TYPE).get().player()));
//		bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, 
//				createdSprites.get(0).getComponent(Owner.TYPE).get().player(), 
//				gameData.getStartingLives()));
//		bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE,
//				createdSprites.get(0).getComponent(Owner.TYPE).get().player(),
//				WealthType.GOLD, gameData.getStartingGold()));
//		
	}
	
}
