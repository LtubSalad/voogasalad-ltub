package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class SpriteKilledEvent extends BusEvent {

	public static final BusEventType<SpriteKilledEvent> ANY = new BusEventType<>(
			SpriteKilledEvent.class.getName() + "ANY");

	public SpriteKilledEvent() {
		super(ANY);
	}

}
