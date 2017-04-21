package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.sprite.Sprite;
import newengine.utils.Target;

public class WeaponCollisionEvent extends BusEvent {
	
	public static final BusEventType<WeaponCollisionEvent> ANY = new BusEventType<>(WeaponCollisionEvent.class.getName() + "ANY");
	
	private Sprite weapon;
	private Sprite target;

	public WeaponCollisionEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite, Sprite target) {
		super(busEventType);
		this.weapon = sprite;
		this.target = target;
	}
	
	public Sprite getWeapon() {
		return weapon;
	}
	
	public Sprite getTarget() {
		return target;
	}

}
