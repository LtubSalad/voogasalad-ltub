package newengine.events;

import bus.BusEvent;
import bus.BusEventType;

public class MapInitializationEvent extends BusEvent {

	public static final BusEventType<MapInitializationEvent> ANY = new BusEventType<>();
	
	public MapInitializationEvent() {
		super(ANY);
	}
	
}
