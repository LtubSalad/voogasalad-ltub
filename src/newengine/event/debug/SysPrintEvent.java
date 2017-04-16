package newengine.event.debug;

import bus.BusEvent;
import bus.BusEventType;

public class SysPrintEvent extends BusEvent {

	public static final BusEventType<SysPrintEvent> ANY = new BusEventType<>();
	
	private String message;
	
	public SysPrintEvent(String message) {
		super(ANY);
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
