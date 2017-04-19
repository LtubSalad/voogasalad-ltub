package newengine.managers.conditions;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.timer.DelayedEvent;

public class EndPlayEvent extends BusEvent {
	public static final BusEventType<EndPlayEvent> WIN = new BusEventType<>(DelayedEvent.class.getName() + "WIN");
	public static final BusEventType<EndPlayEvent> LOSE = new BusEventType<>(DelayedEvent.class.getName() + "LOSE");

	public EndPlayEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
		if(busEventType.equals(EndPlayEvent.WIN)){
			System.out.println("Win Game: Testing in EndPlayEvent");
		}
		if(busEventType.equals(EndPlayEvent.LOSE)){
			System.out.println("Los Game: Testing in EndPlayEvent");
		}
	}

}
