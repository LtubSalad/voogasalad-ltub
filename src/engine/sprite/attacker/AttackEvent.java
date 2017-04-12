package engine.sprite.attacker;

import bus.BusEvent;
import bus.BusEventType;
import engine.sprite.Sprite;

/**
 * Event for attacking a monster.
 * @author Alison Huang
 *
 */
public class AttackEvent extends BusEvent {
	
	public static final BusEventType<AttackEvent> ANY = new BusEventType<>("ATTACK_EVENT");
	
	private Sprite shooter;
	private Sprite target;
	
	public AttackEvent(BusEventType<? extends BusEvent> busEventType, Sprite shooter, Sprite target) {
		super(busEventType);
		this.shooter = shooter;
		this.target = target;
	}

	public Sprite getShooter() {
		return shooter;
	}
	
	public Sprite getTarget() {
		return target;
	}

}
