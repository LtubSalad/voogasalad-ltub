package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeHealthEvent extends BusEvent {
	
	public ChangeHealthEvent() {
		super(ANY);
	}

	public static final BusEventType<ChangeHealthEvent> ANY = new BusEventType<>();

}
