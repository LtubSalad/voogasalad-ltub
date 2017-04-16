package newengine.managers.range;

import java.util.List;
import java.util.stream.Collectors;

import bus.EventBus;
import newengine.events.range.InRangeEvent;
import newengine.sprite.Sprite;
import newengine.sprite.components.RangeDetector;
import newengine.utils.checker.RangeChecker;

public class RangeManager {

	private EventBus bus;
	
	public RangeManager(EventBus bus) {
		this.bus = bus;
	}
	
	public void checkRanges(List<Sprite> sprites) {
		List<Sprite> detectors = sprites.stream().filter((s) -> {
			return s.getComponent(RangeDetector.TYPE).isPresent();
		}).collect(Collectors.toList());
		
		for (Sprite detector : detectors) {
			List<Sprite> detectees = RangeChecker.spritesInRange(detector, sprites);
			if (!detectees.isEmpty()) {
				detector.emit(new InRangeEvent(InRangeEvent.ANY, detector, detectees));
			}
		}
	}
	
}
