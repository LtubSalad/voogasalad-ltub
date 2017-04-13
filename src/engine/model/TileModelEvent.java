package engine.model;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

public class TileModelEvent extends BusEvent {
	
	public static final BusEventType<TileModelEvent> ADD = new BusEventType<>("ADD_TILE");
	public static final BusEventType<TileModelEvent> REMOVE = new BusEventType<>("REMOVE_TILE");

	private Sprite sprite;
	
	public TileModelEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

}
