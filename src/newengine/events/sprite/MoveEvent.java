package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.HasTriggeringSprite;
import newengine.sprite.Sprite;
import newengine.sprite.components.Owner;
import newengine.utils.Target;

public class MoveEvent extends BusEvent implements HasTriggeringSprite {
	
	public static final BusEventType<MoveEvent> START_POSITION = new BusEventType<>(
			MoveEvent.class.getName() + "SPECIFIC");
	public static final BusEventType<MoveEvent> START_SPRITE = new BusEventType<>(
			MoveEvent.class.getName() + "SPRITE");
	public static final BusEventType<MoveEvent> STOP = new BusEventType<>(
			MoveEvent.class.getName() + "STOP");
	
	private Sprite sprite;
	private Target target;

	public MoveEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, Target target) {
		super(busEventType);
		this.sprite = sprite;
		this.target = target;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	public Target getTarget() {
		return target;
	}

}
