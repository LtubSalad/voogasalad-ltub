package engine.sprite.attacker;

import bus.EventBus;
import engine.sprite.Sprite;
import engine.sprite.attacker.AttackEvent;


/**
 * Manage actions to do when an attack is launched
 * @author Alison Huang
 *
 */
public class AttackManager {

	private EventBus bus;
	
	public AttackManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(AttackEvent.ANY, e -> {
			Sprite shooter = e.getShooter();
			Sprite target = e.getTarget();
			
			shooter.getAttacker().get().createWeapon(shooter, target);
			
		});
	}
}
