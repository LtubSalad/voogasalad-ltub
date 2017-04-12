package engine.sprite.attack;

import java.util.LinkedList;
import java.util.Queue;

import bus.EventBus;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;
import engine.sprite.ai.AI;
import engine.sprite.attack.AttackEvent;
import engine.sprite.collision.Collidable;
import engine.sprite.collision.CollisionBound;
import engine.sprite.health.DecrementHealthEvent;
import engine.sprite.health.HealthHolder;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;
import engine.sprite.weapon.Weapon;
import gameDevelopmentInterface.Path;
import engine.camera.GamePoint;

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
