package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class SpriteKilledEvent extends BusEvent {
	public static final BusEventType<SpriteKilledEvent> ANY = new BusEventType<>();
	
	public SpriteKilledEvent() {
		super(ANY);
	}


}
