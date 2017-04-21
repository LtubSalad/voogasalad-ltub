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
	
	public DataController(){
		translator = new DataTranslator(); 
	}
	
	public Game createGame(List<SpriteMakerModel> spriteModels){
		translator.toSprites(spriteModels);
		this.gameSprites = translator.getGameSprites();
		this.bus = translator.getModelBus();  
		return new Game(gameSprites, bus);
	}
	
	
	public void handleSprites(){
		// TODO: implement translation this way from sprite --> model
		;
	}
	
}
