package engine.sprite.healthholder;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

public class DecrementHealthEvent extends BusEvent {

	public static final BusEventType<DecrementHealthEvent> ANY = new BusEventType<>("DECREMENT_HEALTH_EVENT");

	private Sprite weapon;
	private Sprite target;

	public DecrementHealthEvent(BusEventType<? extends BusEvent> busEventType, Sprite s, Sprite target) {
		super(busEventType);
		this.weapon = s;
		this.target = target;
	}
	
	public Sprite getWeapon() {
		return weapon;
	}
	
	public Sprite getTarget(){
		return target;
	}
	
	
}
