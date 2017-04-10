package engine.sprite.health;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;
import engine.sprite.attack.Bullet;

public class DecrementHealthEvent extends BusEvent {

	public static final BusEventType<DecrementHealthEvent> ANY = new BusEventType<>("DECREMENT_HEALTH_EVENT");

	private Bullet bullet;
	private Sprite target;

	public DecrementHealthEvent(BusEventType<? extends BusEvent> busEventType, Bullet bullet, Sprite target) {
		super(busEventType);
		this.bullet = bullet;
		this.target = target;
	}
	
	public Bullet getBullet() {
		return bullet;
	}
	
	public Sprite getTarget(){
		return target;
	}
	
	
}
