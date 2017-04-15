package newengine.trigger;

import bus.BusEvent;
import bus.BusEventType;

public class Action extends BusEvent {

	public Action(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
		// TODO Auto-generated constructor stub
	}

}
