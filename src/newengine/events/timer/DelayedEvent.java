package newengine.events.timer;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.Callback;

public class DelayedEvent extends BusEvent {
	public static final BusEventType<DelayedEvent> ANY = new BusEventType<>();
	
	private Callback callback;
	private double delayedTime;
	
	public DelayedEvent(double delayedTime, Callback callback) {
		super(ANY);
		this.callback = callback;
		this.delayedTime = delayedTime;
	}
	
	public Callback getCallback() {
		return callback;
	}
	
	public double getDelayedTime() {
		return delayedTime;
	}
	
}
