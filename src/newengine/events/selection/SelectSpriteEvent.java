package newengine.events.selection;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;
import newengine.sprite.SpriteID;

public class SelectSpriteEvent extends BusEvent {

	public static final BusEventType<SelectSpriteEvent> SINGLE = new BusEventType<>(
			SelectSpriteEvent.class.getName() + "SINGLE");
	public static final BusEventType<SelectSpriteEvent> SINGLE_BY_ID = new BusEventType<>(
			SelectSpriteEvent.class.getName() + "SINGLE_BY_ID");
	

	private SpriteID spriteID;
	private Sprite sprite;

	public SelectSpriteEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}
	public SelectSpriteEvent(SpriteID spriteID) {
		super(SINGLE_BY_ID);
		this.spriteID = spriteID;
	}

	public SpriteID getSpriteID() {
		return spriteID;
	}
	public Sprite getSprite() {
		return sprite;
	}

}
