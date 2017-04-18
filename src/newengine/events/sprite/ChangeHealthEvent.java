package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeHealthEvent extends BusEvent {

	public static final BusEventType<ChangeHealthEvent> ANY = new BusEventType<>(
			ChangeHealthEvent.class.getName() + "ANY");

	public ChangeHealthEvent() {
		super(ANY);
	}

}
