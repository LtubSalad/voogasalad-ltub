package gamedata;

import java.util.ArrayList;
import java.util.List;

import bus.BasicEventBus;
import data.EventHandleData;
import data.SpriteMakerModel;
import javafx.collections.ObservableList;
import newengine.events.SpriteModelEvent;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;

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


	public AuthDataTranslator(SpriteMakerModel spriteToMake){
		spritesToMake = new ArrayList<SpriteMakerModel>();
		spritesToMake.add(spriteToMake);
	}

	@Override
	public void translate() {
		spritesToMake.stream().forEach(model -> {
			Sprite newSprite = handleComponents(model.getActualComponents());
			// skills
			/// triggers 
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


	private Sprite handleComponents(List<Component>transferComponents) {
		Sprite sprite = new Sprite(); 
		for (Component comp: transferComponents){
			sprite.addComponent(comp);
		}
		return sprite; 
	}

}
