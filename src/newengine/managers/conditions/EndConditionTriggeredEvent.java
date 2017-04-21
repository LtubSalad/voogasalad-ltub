package newengine.managers.conditions;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.timer.DelayedEvent;

public class EndConditionTriggeredEvent extends BusEvent {
	public static final BusEventType<EndConditionTriggeredEvent> WIN = new BusEventType<>(DelayedEvent.class.getName() + "WIN");
	public static final BusEventType<EndConditionTriggeredEvent> LOSE = new BusEventType<>(DelayedEvent.class.getName() + "LOSE");

	public EndConditionTriggeredEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
		if(busEventType.equals(EndConditionTriggeredEvent.WIN)){
			System.out.println("Win Game: Testing in EndPlayEvent");
		}
		if(busEventType.equals(EndConditionTriggeredEvent.LOSE)){
			System.out.println("Lose Game: Testing in EndPlayEvent");
		}
	}

}
