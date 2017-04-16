package newengine.trigger;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.ai.Callback;

public class PeriodicEvent extends BusEvent {
	
	public static final BusEventType<PeriodicEvent> ANY = new BusEventType<>();

	private int repeatingTimes;
	private double interval;
	private Callback callback;

	public PeriodicEvent(int repeatingTimes, double interval, Callback callback) {
		super(ANY);
		this.repeatingTimes = repeatingTimes;
		this.interval = interval;
		this.callback = callback;
	}
	
	public int getRepeatingTimes() {
		return repeatingTimes;
	}
	
	public double getInterval() {
		return interval;
	}
	
	public Callback getCallback() {
		return callback;
	}

}
