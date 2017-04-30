package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeHealthEvent extends BusEvent {

	public static final BusEventType<ChangeHealthEvent> ANY = new BusEventType<>(
			ChangeHealthEvent.class.getName() + "ANY");
	public static final BusEventType<ChangeHealthEvent> RESET = new BusEventType<>(
			ChangeHealthEvent.class.getName() + "RESET");
	
	private int value;
	
	public ChangeHealthEvent(BusEventType<? extends BusEvent> busEventType, int value) {
		super(busEventType);
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
