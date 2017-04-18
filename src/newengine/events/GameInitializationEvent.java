package newengine.events;

import bus.BusEvent;
import bus.BusEventType;

public class GameInitializationEvent extends BusEvent {

	public static final BusEventType<GameInitializationEvent> ANY = new BusEventType<>(
			GameInitializationEvent.class.getName() + "ANY");

	public GameInitializationEvent() {
		super(ANY);
	}

}
