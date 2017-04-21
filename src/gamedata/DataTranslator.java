package gamedata;

import java.util.ArrayList;
import java.util.List;

import bus.BasicEventBus;
import data.SpriteMakerModel;
import newengine.events.SpriteModelEvent;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;

public class DataTranslator implements DataHandling {
	SpriteModel gameModel; 
	List<Sprite> spritesList; 
	
	SpriteMakerModel spriteModel; 
	
	BasicEventBus bus; 
	
	public DataTranslator(){
		bus = new BasicEventBus(); 
	}
	
	@Override
	public void toSprites(List<SpriteMakerModel> data) {
		// TODO: modify sprite add event to take a single sprite 
		gameModel = new SpriteModel(bus); 
		spritesList = new ArrayList<Sprite>(); 		
		for (SpriteMakerModel spriteData : data){
				Sprite newSprite = assembleSprite(spriteData.getComponents());
				spritesList.add(newSprite);
		}
		
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesList));		
	}
	
	
	public BasicEventBus getModelBus(){
		return bus; 
	}
	
	private Sprite assembleSprite(List<Component> components) {
		Sprite newSprite = new Sprite(); 
		components.stream().forEach(e -> newSprite.addComponent(e));
		return newSprite; 
		
	}

	@Override
	public void toModel(Sprite sprite) {
		// TODO implement this, clean this + controller up 
		
	}

	public SpriteModel getGameSprites() {
		return gameModel;
	}

}
