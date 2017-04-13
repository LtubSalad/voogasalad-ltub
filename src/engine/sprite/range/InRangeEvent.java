package engine.sprite.range;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

/**
 * Event for sprite-in-range.
 * @author Yilin Gao
 *
 */
public class InRangeEvent extends BusEvent {
	
	public static final BusEventType<InRangeEvent> ANY = new BusEventType<>("IN_RANGE_EVENT");
	
	private Sprite detector;
	private Sprite detectee;
	/**
	 * Constructor for {@code InRangeEvent}.
	 * @param busEventType the {@code BusEventType} for the {@code InRangeEvent}.
	 * @param detector the {@code Sprite} which detects some other {@code Sprite} is in its detection range.
	 * @param detectee the {@code Sprite} which gets detected by some other {@code Sprite}.
	 */
	public InRangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite detector, Sprite detectee) {
		super(busEventType);
		this.detector = detector;
		this.detectee = detectee;
	}

	/**
	 * Get the {@code Sprite} which detects some other {@code Sprite} is in its detection range.
	 * @return Sprite
	 */
	public Sprite getDetector() {
		return detector;
	}
	
	/**
	 * Get the {@code Sprite} which gets detected by some other {@code Sprite}.
	 * @return Sprite
	 */
	public Sprite getDetectee() {
		return detectee;
	}

}
