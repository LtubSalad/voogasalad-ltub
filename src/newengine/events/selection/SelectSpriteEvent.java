package newengine.events.selection;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

public class SelectSpriteEvent extends BusEvent {

	public static final BusEventType<SelectSpriteEvent> SINGLE = new BusEventType<>(
			SelectSpriteEvent.class.getName() + "SINGLE");

	private Sprite sprite;

	public SelectSpriteEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite) {
		super(busEventType);
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
