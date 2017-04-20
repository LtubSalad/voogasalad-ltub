package newengine.events.sprite;

import java.util.List;

import bus.BusEvent;
import bus.BusEventType;
import newengine.events.HasTriggeringSprite;
import newengine.sprite.Sprite;
import newengine.utils.Target;

public class FireProjectileEvent extends BusEvent implements HasTriggeringSprite {
	
	public static final BusEventType<FireProjectileEvent> GAME = new BusEventType<>(FireProjectileEvent.class.getName() + "GAME");
	public static final BusEventType<FireProjectileEvent> ALL = new BusEventType<>(FireProjectileEvent.class.getName() + "ALL");
	public static final BusEventType<FireProjectileEvent> SPECIFIC = new BusEventType<>(FireProjectileEvent.class.getName() + "SPECIFIC");

	private Sprite sprite;
	private Target target;
	
	public FireProjectileEvent(BusEventType<? extends BusEvent> busEventType, Sprite source, Target target) {
		super(busEventType);
		this.sprite = source;
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
