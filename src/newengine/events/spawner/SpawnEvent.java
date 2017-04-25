package newengine.events.spawner;

import bus.BusEvent;
import bus.BusEventType;

public class SpawnEvent extends BusEvent{
	public static final BusEventType<SpawnEvent> SPAWN = new BusEventType<>(SpawnEvent.class.getName()+"SPAWN");
	
	public SpawnEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}
}
