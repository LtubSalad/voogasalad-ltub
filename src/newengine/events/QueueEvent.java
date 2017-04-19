package newengine.events;

import bus.BusEvent;
import bus.BusEventType;

public class QueueEvent extends BusEvent{

	public static final BusEventType<QueueEvent> ADD = new BusEventType<>(
			QueueEvent.class.getName() + "ADD");
	public static final BusEventType<QueueEvent> NEXT = new BusEventType<>(
			QueueEvent.class.getName() + "NEXT"); 
	
	private BusEvent event;
	
	public QueueEvent(BusEventType<? extends BusEvent> busEventType, BusEvent event) {
		super(busEventType);
		this.event = event;
	}
	
	public BusEvent getEvent() {
		return event;
	}


}
