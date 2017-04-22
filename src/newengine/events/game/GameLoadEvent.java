package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class GameLoadEvent extends BusEvent {
	
	public static BusEventType<GameLoadEvent> LOAD = new BusEventType<>(GameLoadEvent.class.getName()+"LOAD");
	
	public GameLoadEvent() {
		super(LOAD);
	}
}
