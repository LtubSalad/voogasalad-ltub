package newengine.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;

public class TimerManager {

	private EventBus bus;
	private List<GameTimer> gameTimers = new ArrayList<>();
	
	public TimerManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(DelayedEvent.ANY, (e) -> {
			gameTimers.add(new GameTimer(e.getDelayedTime(), e.getCallback()));
		});
	}
	
	private void removeFinishedTimers() {
		List<GameTimer> finished = gameTimers.stream()
					.filter((timer) -> timer.isFinished())
					.collect(Collectors.toList());
		gameTimers.removeAll(finished);
	}
	
	public void update(double dt) {
		removeFinishedTimers();
		for (GameTimer gameTimer : gameTimers) {
			gameTimer.update(dt);
		}
	}
	
	
}
