package engine.sprite.range;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

public class MonsterInRangeEvent extends BusEvent {

public static final BusEventType<MonsterInRangeEvent> ANY = new BusEventType<>("MONSTER_IN_RANGE_EVENT");
	
	private Sprite detector;
	private Sprite detectee;
	
	public MonsterInRangeEvent(BusEventType<? extends BusEvent> busEventType, Sprite detector, Sprite detectee) {
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
