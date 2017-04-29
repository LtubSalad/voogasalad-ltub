package newengine.events.game;

import bus.BusEvent;
import bus.BusEventType;

public class StartLevelEvent extends BusEvent{
	public static final BusEventType<StartLevelEvent> START = new BusEventType<>(StartLevelEvent.class.getName() + "START");

	public StartLevelEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
		// TODO Auto-generated constructor stub
	}
}
