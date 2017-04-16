package newengine.trigger;

import engine.sprite.ai.Callback;

public class GameTimer { // one timer for one delayed event
	
	private double timeRemained;
	private Callback callback;
	private boolean finished = false;
	
	public GameTimer(double delayedTime, Callback callback) {
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
