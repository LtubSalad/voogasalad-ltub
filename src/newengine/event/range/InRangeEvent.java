package newengine.event.range;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;

/**
 * Event for sprite-in-range.
 * @author Yilin Gao
 *
 */
public class InRangeEvent extends BusEvent {
	
	public static final BusEventType<InRangeEvent> ANY = new BusEventType<>("IN_RANGE_EVENT");
	
	private Sprite detector;
	private Sprite detectee;
	
	public InRangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite detector, Sprite detectee) {
		super(busEventType);
		this.detector = detector;
		this.detectee = detectee;
	}

	public Sprite getDetector() {
		return detector;
	}
	
	public Sprite getDetectee() {
		return detectee;
	}

}
