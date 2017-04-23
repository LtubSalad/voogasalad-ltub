package newengine.events.trigger;

import bus.BusEvent;
import bus.BusEventType;
import newengine.trigger.Trigger;

public class AddTriggerEvent extends BusEvent {

	public static final BusEventType<AddTriggerEvent> ADD = new BusEventType<>(AddTriggerEvent.class.getName()+"ADD");
	
	private Trigger trigger;
	
	public AddTriggerEvent(Trigger trigger) {
		super(ADD);
		this.trigger = trigger;
	}
	
	public Trigger getTrigger() {
		return trigger;
	}
	
}
