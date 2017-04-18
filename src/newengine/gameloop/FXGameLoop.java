package newengine.gameloop;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import javafx.animation.AnimationTimer;

public class FXGameLoop implements GameLoop {

	private EventBus bus; 
	private List<LoopComponent> loopComponents;
	private AnimationTimer timer;
	private long prevNanos;
	
	public FXGameLoop(EventBus bus) {
		this.bus = bus;
		prevNanos = 0;
		loopComponents = new ArrayList<>();
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// calculate elapsed time
				if (prevNanos == 0) {
					prevNanos = now;
					return;
				}
				long deltaNanos = now - prevNanos;
				prevNanos = now;
				double dt = deltaNanos / 1.0e9;
				// updates for each game system
				for (LoopComponent loopComponent : loopComponents) {
					loopComponent.update(dt);
				}
			}
			
		};
	}

	@Override
	public void addLoopComponent(LoopComponent loopComponent) {
		if (loopComponent != null && !loopComponents.contains(loopComponent)) {
			loopComponents.add(loopComponent);
		}
	}
	
	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void pause() {
		timer.stop();
	}




}
