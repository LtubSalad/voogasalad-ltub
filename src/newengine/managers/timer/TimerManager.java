package newengine.managers.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import newengine.events.timer.DelayedEvent;
import newengine.events.timer.PeriodicEvent;

public class TimerManager {

	private EventBus bus;
	private List<DelayedTimer> delayedTimers = new ArrayList<>();
	private List<PeriodicTimer> periodicTimers = new ArrayList<>();
	
	public TimerManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(DelayedEvent.ANY, (e) -> {
			delayedTimers.add(new DelayedTimer(bus, e.getDelayedTime(), e.getCallback()));
		});
		bus.on(PeriodicEvent.ANY, (e) -> {
			periodicTimers.add(new PeriodicTimer(bus, e.getRepeatingTimes(), e.getInterval(), e.getCallback()));
		});
	}
	
	private void removeFinishedTimers() {
		List<DelayedTimer> finishedDelayedTimers = delayedTimers.stream()
					.filter((timer) -> timer.isFinished())
					.collect(Collectors.toList());
		delayedTimers.removeAll(finishedDelayedTimers);
		List<PeriodicTimer> finishedPeriodicTimers = periodicTimers.stream()
				.filter((timer) -> timer.isFinished())
				.collect(Collectors.toList());
		delayedTimers.removeAll(finishedPeriodicTimers);
	}
	
	public void update(double dt) {
		removeFinishedTimers();
		for (DelayedTimer delayedTimer : delayedTimers) {
			delayedTimer.update(dt);
		}
		for (PeriodicTimer periodicTimer : periodicTimers) {
			periodicTimer.update(dt);
		}
	}
	
	
}
