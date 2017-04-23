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
public class TranslationController implements FileTranslator, Translator {
	private Translator translator;

	private File fileToTranslate; 
	private XStreamHandler handler; 

	// TODO: using ScreenModelData --> Sprites 
	// TODO: using XML file of SpriteMakerModels --> Sprites 
	// TODO: using XML file of current game state (Sprites)  --> sprite maker models 
	// TODO: using SpriteModel of current game state --> sprite maker models

	public TranslationController(ScreenModelData screenModel){
		translator = new AuthDataTranslator(screenModel.getData()); 
	}

	public TranslationController(SpriteModel spriteModel){
		translator = new EngineDataTranslator(spriteModel.getData()); 
	}

	public TranslationController(String filepath){
		fileToTranslate = new File(filepath);	
	}

	public void setTranslatorForAuthFile(){
		translator = new AuthDataTranslator(handler.getScreenModel(fileToTranslate));
	}

	public void setTranslatorForGameFile(){
		translator = new EngineDataTranslator(handler.getSpriteModel(fileToTranslate));
	}

	public void translate(){
		translator.translate();
	}

}
