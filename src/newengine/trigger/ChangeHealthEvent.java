package newengine.trigger;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeHealthEvent extends BusEvent {
	
	public ChangeHealthEvent() {
		super(ANY);
	}

	public static final BusEventType<ChangeHealthEvent> ANY = new BusEventType<>();

}
