package newengine.events.levels;

import bus.BusEvent;
import bus.BusEventType;

public class WinGameEvent extends BusEvent{
	public static final BusEventType<WinGameEvent> WIN = new BusEventType<>(WinGameEvent.class.getName()+"WIN");

	public WinGameEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}
	
	

}
