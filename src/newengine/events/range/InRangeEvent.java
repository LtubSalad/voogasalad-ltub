package newengine.events.range;

import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

/**
 * Event for sprite-in-range.
 * 
 * @author Yilin Gao, Keping Wang
 *
 */
public class InRangeEvent extends BusEvent {

	public static final BusEventType<InRangeEvent> ANY = new BusEventType<>("IN_RANGE_EVENT");
	// TODO: there can be multiple types of ranges corresponding to different
	// behaviors

	private Sprite detector;
	private List<Sprite> detectees;

	public InRangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite detector, List<Sprite> detectees) {
		super(busEventType);
		this.detector = detector;
		this.detectees = detectees;
	}

	/**
	 * Get the {@code Sprite} which detects some other {@code Sprite} is in its
	 * detection range.
	 * 
	 * @return Sprite
	 */
	public Sprite getDetector() {
		return detector;
	}

	public List<Sprite> getDetectees() {
		return detectees;
	}

}
