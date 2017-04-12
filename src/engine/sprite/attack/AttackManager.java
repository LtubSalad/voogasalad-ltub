package engine.sprite.attack;

import bus.EventBus;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;
import engine.sprite.attack.AttackEvent;
import engine.sprite.movable.Movable;

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
			System.out.println("Attacker " + shooter.toString() + " will shoot target " + target.toString() 
				+ ".");
			
			// TODO actions to fire a bullet at the target
			Sprite weaponSprite = new Sprite();
			Weapon weaponAttribute = new Weapon(shooter, target);
			Movable movableAttribute = new Movable();
			weaponSprite.setWeapon(weaponAttribute);
			weaponSprite.setMovable(movableAttribute);
			//TODO set weapon attribute, set movable attribute, and then set the weapon's target
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, weaponSprite));
			
			
		});
	}
}
