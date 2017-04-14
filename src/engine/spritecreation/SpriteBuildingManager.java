package engine.spritecreation;

import bus.BasicEventBus;
import bus.EventBus;
import data.AttributeData;
import engine.model.SpriteModelEvent;
import engine.model.TileModelEvent;
import engine.sprite.Sprite;

/**
 * This class is the manager that encapsulates the sprite building process
 * @author Matt Tribby, Tahia Emran
 */
public class SpriteBuildingManager {
	private EventBus bus;

	/**
	 * Creates a SpriteBuldingManager with an EventBus instance which it will use to add Sprites to the model
	 * @param bus
	 */
	public SpriteBuildingManager(EventBus bus) {
		this.bus = bus;
	}
	
	/**
	 * Constructor which just makes a new event bus
	 * TODO remove? adapt?
	 */
	public SpriteBuildingManager(){
		this.bus = new BasicEventBus();
	}

	/**
	 * Creates a sprite based on the passed in AttributeData class which contains all the info for a sprite
	 * @param spriteData
	 */
	public void createSprite(AttributeData spriteData) {
		SpriteBuilder SB = new SpriteBuilder(spriteData);
		Sprite s = SB.getSprite();
		
		if(checkIfTile(spriteData))
			addTileToModel(s);
		else{
			System.out.println(s.getHealthHolder().isPresent() + " for HealthHolder");
			System.out.println(s.getAttacker().isPresent() + " for Attacker");
			System.out.println(s.getTeamMember().isPresent() + " for TeamMember");
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
