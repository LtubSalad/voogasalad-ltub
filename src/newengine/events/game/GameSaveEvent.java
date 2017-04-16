package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class GameSaveEvent extends BusEvent {
	public GameSaveEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
		// TODO Auto-generated constructor stub
	}

	public static final BusEventType<GameSaveEvent> ANY = new BusEventType<>();
	
	

}
