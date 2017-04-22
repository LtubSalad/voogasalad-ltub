package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class GameSaveEvent extends BusEvent {

	public static final BusEventType<GameSaveEvent> SAVE = new BusEventType<>(GameSaveEvent.class.getName()+"SAVE");
	
	public GameSaveEvent() {
		super(SAVE);
	}

}
