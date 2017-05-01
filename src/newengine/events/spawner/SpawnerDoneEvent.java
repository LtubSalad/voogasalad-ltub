package newengine.events.spawner;

import bus.BusEvent;
import bus.BusEventType;

public class SpawnerDoneEvent extends BusEvent {
	public static final BusEventType<SpawnerDoneEvent> DONE = new BusEventType<>(SpawnerDoneEvent.class.getName()+"DONE");
	
	public SpawnerDoneEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}

	
}
