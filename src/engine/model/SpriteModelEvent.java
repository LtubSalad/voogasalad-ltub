package engine.model;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

public class SpriteModelEvent extends BusEvent {

	public static final BusEventType<SpriteModelEvent> ADD = new BusEventType<>("ADD_SPRITE");
	public static final BusEventType<SpriteModelEvent> REMOVE = new BusEventType<>("REMOVE_SPRITE");

	private Sprite sprite;
	
	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
