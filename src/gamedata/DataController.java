package gamedata;

import newengine.app.Game;
import newengine.model.SpriteModel;

import java.util.List;

import bus.BasicEventBus;
import data.SpriteMakerModel;

public class DataController {
	DataTranslator translator; 
	
	SpriteModel gameSprites; 
	
	BasicEventBus bus; 
	
	Game game; 
	
	public DataController(){
		translator = new DataTranslator(); 
	}
	
	public void createGame(List<SpriteMakerModel> spriteModels){
		translator.toSprites(spriteModels);
		this.gameSprites = translator.getGameSprites();
		this.bus = translator.getModelBus();  
		game =  new Game(gameSprites, bus);
	}
	
	
	public Game getGame(){
		return game; 
	}
	
	public void handleSprites(){
		// TODO: implement translation this way from sprite --> model
		;
	}
	
}
