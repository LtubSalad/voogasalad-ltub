package engine.spritecreation;

import bus.BasicEventBus;
import bus.EventBus;
import data.AttributeData;
import engine.model.SpriteModelEvent;
import engine.model.TileModelEvent;
import engine.sprite.Sprite;

public class SpriteBuildingManager {
	private EventBus bus;

	public SpriteBuildingManager(EventBus bus) {
		this.bus = bus;
	}
	
	public SpriteBuildingManager(){
		this.bus = new BasicEventBus();
	}

	public void createSprite(AttributeData spriteData) {
		SpriteBuilder SB = new SpriteBuilder(spriteData);
		Sprite s = SB.getSprite();
		
		if(checkIfTile(spriteData))
			addTileToModel(s);
		else{
			addSpriteToModel(s);
		}
	}
	
	private boolean checkIfTile(AttributeData data){
		return data.getAttributes().size() == 1;
	}
	
	private void addTileToModel(Sprite sprite) {
		bus.emit(new TileModelEvent(TileModelEvent.ADD, sprite));
	}

	private void addSpriteToModel(Sprite sprite){
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, sprite));
	}
}
