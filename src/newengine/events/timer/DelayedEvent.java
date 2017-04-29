package newengine.events.timer;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.Callback;

public class DelayedEvent extends BusEvent {
	public static final BusEventType<DelayedEvent> ANY = new BusEventType<>(DelayedEvent.class.getName() + "ANY");

	private Callback callback;
	private double delayedTime;

	public DelayedEvent(BusEventType<? extends BusEvent> busEventType, double delayedTime, Callback callback) {
		super(busEventType);
		this.callback = callback;
		this.delayedTime = delayedTime;
	}
	
	public DelayedEvent(BusEventType<? extends BusEvent> busEventType, double delayedTime){
		super(busEventType);
		this.delayedTime = delayedTime;
		this.callback = null;
	}

	public Callback getCallback() {
		return callback;
	}

	public double getDelayedTime() {
		return delayedTime;
	}

}
