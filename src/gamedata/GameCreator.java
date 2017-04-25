package gamedata;

import java.io.File;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import data.SpriteMakerModel;
import newengine.app.Game;
import newengine.events.SpriteModelEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.sprite.Sprite;
import newengine.sprite.components.Owner;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 *
 *class that creates a game from an XML file 
 *
 */
public class GameCreator {
	File fileToRead; 
	//TODO: clean this up 
	XStreamHandler XSH; 
	XStream xstream; 
	
	private TranslationController translator; 
	private Game game; 
	
	
	
	public GameCreator(String filepath){
		// set up file reading 
		fileToRead= new File(filepath);
		XSH = new XStreamHandler(); 
		xstream = new XStream(new DomDriver());
		// create the game
		createGame(); 
	}

	public Game getGame(){ 
		return game; 
	}

	private void createGame() {
		List<SpriteMakerModel> models = (List<SpriteMakerModel>) xstream.fromXML(fileToRead);
		TranslationController translator = new TranslationController(fileToRead); 
		translator.setTranslatorForAuthFile();
		translator.translate();
		List<Sprite> createdSprites = (List<Sprite>) translator.getTranslated();  
		
//		AuthDataTranslator ADT = new AuthDataTranslator(models);
//		ADT.translate(); 
//		List<Sprite> createdSprites = ADT.getSprites();
		
		game = new Game(); 
		EventBus bus = game.getBus();
		
		// add sprites to model 
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, createdSprites));
		// TODO: figure this part out 
		bus.emit(new MainPlayerEvent(createdSprites.get(0).getComponent(Owner.TYPE).get().player()));
		bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, createdSprites.get(0).getComponent(Owner.TYPE).get().player(), 3));
		bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, createdSprites.get(0).getComponent(Owner.TYPE).get().player(),
				WealthType.GOLD, 100));
		
		
		
	}
	
}
