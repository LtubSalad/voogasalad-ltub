package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class GameSaveEvent extends BusEvent {

	public static final BusEventType<GameSaveEvent> ANY = new BusEventType<>(GameSaveEvent.class.getName()+"ANY");
	
	public GameSaveEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}

}
