package newengine.managers.timer;

import bus.EventBus;
import newengine.utils.Callback;

public class PeriodicTimer {
	
	private EventBus bus;
	private int repeatingTimes;
	private double interval;
	private double timeRemained;
	private Callback callback;
	private boolean finished = false;

	public PeriodicTimer(EventBus bus, int repeatingTimes, double interval, Callback callback) {
		this.bus = bus;
		this.repeatingTimes = repeatingTimes;
		this.interval = interval;
		this.timeRemained = interval;
		this.callback = callback;
	}
	
	public void update(double dt) {
		if (repeatingTimes == 0) {
			finished = true;
			return;
		}
		if (callback == null) {return;} // TODO
		timeRemained += -dt;
		if (timeRemained <= 0) {
			callback.execute();
			if (repeatingTimes > 0) {
				repeatingTimes--;
			}
			timeRemained = interval;
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
}
