package engine.sprite.attack;

import bus.EventBus;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;
import engine.sprite.attack.AttackEvent;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;
import engine.sprite.weapon.Weapon;

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
			LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
			ImageSet imageSet1 = new ImageSet();
			imageSet1.setImage(image1);
			weaponSprite.setImageSet(imageSet1);
			Weapon weaponAttribute = new Weapon(shooter, target);
			weaponAttribute.setAttackPower(60);
			Movable movableAttribute = new Movable();
			movableAttribute.setSpeed(600);
			weaponSprite.setWeapon(weaponAttribute);
			weaponSprite.setMovable(movableAttribute);
			//TODO set weapon attribute, set movable attribute, and then set the weapon's target
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, weaponSprite));
			
			
		});
	}
}
