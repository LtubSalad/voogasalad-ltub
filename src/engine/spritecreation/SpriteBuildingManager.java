package engine.spritecreation;

import bus.EventBus;
import data.AttributeData;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;

public class SpriteBuildingManager {
	private EventBus bus;

	public SpriteBuildingManager(EventBus bus) {
		this.bus = bus;
	}
	
	public SpriteBuildingManager(){
		; 
	}

	public void createSprite(AttributeData spriteData) {
		SpriteBuilder SB = new SpriteBuilder(spriteData);
		System.out.println("sprite builder created");
		//addSpriteToModel(SB.getSprite());
	}
	
	private void addSpriteToModel(Sprite sprite){
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprite));
	}
}
