package gamedata;

import java.io.File;

import data.ScreenModelData;
import newengine.model.SpriteModel;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 * 
 * class used to facilitate translation from one side of the game to another
 * 
 *
 */
public class TranslationController {
	private Translator translator;
	
	private File fileToTranslate; 
	private XStreamHandler handler; 
	
	// TODO: using ScreenModelData --> Sprites 
	// TODO: using XML file of SpriteMakerModels --> Sprites 
	// TODO: using XML file of current game state (Sprites)  --> sprite maker models 
	// TODO: using SpriteModel of current game state --> sprite maker models
	
	public TranslationController(ScreenModelData screenModel){
		// in this case, the data fed instantiates the translator 
		translator = new AuthDataTranslator(screenModel.getAllObjectsOnScreen());
	}
	
	public TranslationController(SpriteModel spriteModel){
		// in this case, the data fed instantiates the translator 
		translator = new EngineDataTranslator(spriteModel.getSprites());
	}
	
	public TranslationController(String filepath){
		// in this case, the translator is instantiated based on the public method called by the querying class 
		fileToTranslate = new File(filepath);	
	}
	
	public void setTranslatorForAuthFile(){
		translator = new AuthDataTranslator(handler.getScreenModel(fileToTranslate));
	}
	
	public void setTranslatorForGameFile(){
		translator = new EngineDataTranslator(handler.getSpriteModel(fileToTranslate));
	}
	
	public void translateData(){
		translator.translate();
	}

}
