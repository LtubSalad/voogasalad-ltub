package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class GamePauseResumeEvent extends BusEvent{
	
	public static final BusEventType<GamePauseResumeEvent> TOGGLE = new BusEventType<>(
			GamePauseResumeEvent.class.getName() + "TOGGLE");

	public GamePauseResumeEvent() {
		super(TOGGLE);
	}

}
