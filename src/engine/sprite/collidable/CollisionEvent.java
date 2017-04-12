package engine.sprite.collidable;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

public class CollisionEvent extends BusEvent {

	public static final BusEventType<CollisionEvent> ANY = new BusEventType<>("COLLISION_EVENT");

	private Sprite sprite1;
	private Sprite sprite2;

	public CollisionEvent(BusEventType<? extends BusEvent> busEventType, Sprite sprite1, Sprite sprite2) {
		super(busEventType);
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	}
	
	public Sprite getSprite1() {
		return sprite1;
	}
	public Sprite getSprite2() {
		return sprite2;
	}
	
}
