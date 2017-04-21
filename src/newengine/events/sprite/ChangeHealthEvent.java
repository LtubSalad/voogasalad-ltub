package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeHealthEvent extends BusEvent {

	public static final BusEventType<ChangeHealthEvent> ANY = new BusEventType<>(
			ChangeHealthEvent.class.getName() + "ANY");
	
	private int change;
	
	public ChangeHealthEvent(BusEventType<? extends BusEvent> busEventType, int value) {
		super(busEventType);
		this.change = value;
	}
	
	public int getChange(){
		return change;
	}

}
