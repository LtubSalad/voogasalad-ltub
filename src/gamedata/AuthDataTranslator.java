package gamedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus.BasicEventBus;
import data.EventHandleData;
import data.SpriteMakerModel;
import javafx.collections.ObservableList;
import newengine.events.SpriteModelEvent;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;

/**
 * @author tahiaemran
 * 
 * Class used to translate data from the authoring environment to the game environment
 * Takes in a list of sprite maker models and translates them into sprites 
 *
 */
public class AuthDataTranslator implements Translator {

	private List<SpriteMakerModel> spritesToMake; 
	private BasicEventBus gameBus = new BasicEventBus(); 
	
	private List<Sprite> constructedSprites = new ArrayList<Sprite>(); 
	private SpriteModel constructedModel = new SpriteModel(gameBus); 

	
	public AuthDataTranslator(ObservableList<SpriteMakerModel> allObjectsOnScreen) {
		spritesToMake = new ArrayList<SpriteMakerModel>(allObjectsOnScreen); 
	}
	
	public AuthDataTranslator(List<SpriteMakerModel> spriteData){
		spritesToMake = spriteData;
	}
	
	
	@Override
	public void translate() {
		spritesToMake.stream().forEach(model -> {
				Sprite newSprite = handleComponents(model.getTransferComponents());
				constructedSprites.add(handleEventHandlers(newSprite, model.getEventHandlers()));				
			});
		gameBus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, constructedSprites));
	}

	private Sprite handleEventHandlers(Sprite newSprite, List<EventHandleData> eventHandlers) {
		// loop through 
		return newSprite; 
		
	}
	
	// again would 
	public SpriteModel getSprites(){
		return constructedModel;
	}

	
	private Sprite handleComponents(Map<String, List<String>> transferComponents) {
		Sprite sprite = new Sprite(); 
		// loop through each component in the map 
		// send the component and the parameters through parsing + factories 
		// add the component from the factory to the sprite 
		return sprite; 
	}

}
