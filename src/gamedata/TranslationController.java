package gamedata;

import java.io.File;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.ScreenModelData;
import data.SpriteMakerModel;
import newengine.model.SpriteModel;
import utilities.XStreamHandler;

/**
 * @author tahiaemran
 * 
 * class used to facilitate translation from one side of the game to another
 * 
 *
 */
public class TranslationController implements FileTranslator {
	private Translator translator;

	private File fileToTranslate; 
	private XStreamHandler handler; 

	// TODO: using ScreenModelData --> Sprites 
	// TODO: using XML file of SpriteMakerModels --> Sprites 
	// TODO: using XML file of current game state (Sprites)  --> sprite maker models 
	// TODO: using SpriteModel of current game state --> sprite maker models

	public TranslationController(String filepath){
		fileToTranslate = new File(filepath);	
	}
	
	public TranslationController(File file){
		fileToTranslate = file;
	}
	
	public TranslationController (List<SpriteMakerModel> sprites){
		translator = new AuthDataTranslator(sprites);
	}

	public void setTranslatorForAuthFile(){
		XStream xstream = new XStream(new DomDriver());
		translator = new AuthDataTranslator((List<SpriteMakerModel>)xstream.fromXML(fileToTranslate));
	}

	public void setTranslatorForGameFile(){
		translator = new EngineDataTranslator(handler.getSpriteModel(fileToTranslate));
	}

	public void translate(){
		translator.translate();
	}

	@Override
	public List<?> getTranslated() {
		return translator.getTranslated();
	}

	
	//FIXME: ya fix this
//	@Override
//	public List<Sprite> getTranslated() {
//		return (List<Sprite>) translator.getTranslated();
//	}
//	
	
	//FIXME fix this somehow
	
//	public List<Sprite> getSprites(){
//		return translator.getSprites(); 
//	}

}
