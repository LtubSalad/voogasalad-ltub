package newengine.events;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class SpriteModelEvent extends BusEvent {

	public static final BusEventType<SpriteModelEvent> ADD = new BusEventType<>();
	public static final BusEventType<SpriteModelEvent> REMOVE = new BusEventType<>();
	
	private Sprite sprite;
	
	public SpriteModelEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
}
