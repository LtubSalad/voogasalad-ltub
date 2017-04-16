package newengine.timer;

import bus.EventBus;
import newengine.utils.Callback;

public class DelayedTimer { // one timer for one delayed event
	
	private EventBus bus;
	private double timeRemained;
	private Callback callback;
	private boolean finished = false;
	
	public DelayedTimer(EventBus bus, double delayedTime, Callback callback) {
		this.bus = bus;
		timeRemained = delayedTime;
		this.callback = callback;
	}
			
	public void update(double dt) {
		if (callback == null) { return; } // TODO exception
		timeRemained += - dt;
		if (timeRemained <= 0) {
			callback.execute();
			// add new PeriodicGameTimer()
			finished = true;
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	
}
