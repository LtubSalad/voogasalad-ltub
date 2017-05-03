package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.HasTriggeringSprite;
import newengine.sprite.Sprite;
import newengine.utils.Target;

public class MoveEvent extends BusEvent implements HasTriggeringSprite {
	
	public static final BusEventType<MoveEvent> START_POSITION = new BusEventType<>(
			MoveEvent.class.getName() + "SPECIFIC");
	public static final BusEventType<MoveEvent> START_SPRITE = new BusEventType<>(
			MoveEvent.class.getName() + "SPRITE");
	public static final BusEventType<MoveEvent> STOP = new BusEventType<>(
			MoveEvent.class.getName() + "STOP");
	public static final BusEventType<MoveEvent> START_AUTO = new BusEventType<>(
			MoveEvent.class.getName() + "START_AUTO");
	
	private Sprite sprite;
	private Target target;

	public MoveEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, Target target) {
		super(busEventType);
		this.sprite = sprite;
		this.target = target;
	}
	
	public MoveEvent(BusEventType<? extends BusEvent> busEventType) {
		super(busEventType);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public Target getTarget() {
		return target;
	}

}
