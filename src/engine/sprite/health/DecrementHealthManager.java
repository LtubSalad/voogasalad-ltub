package engine.sprite.health;

import bus.EventBus;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;
import engine.sprite.weapon.Weapon;

public class DecrementHealthManager {

	private EventBus bus;

	public DecrementHealthManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(DecrementHealthEvent.ANY, (e) -> {
			Sprite attacker = e.getWeapon();
			Sprite target = e.getTarget();

			Weapon w = attacker.getWeapon().get();
			HealthHolder hh = target.getHealthHolder().get();

			double amt = w.getAttackPower();			
			hh.decrementHealth(amt);
			System.out.println("Health decremented");
			
			if (hh.getHealth() <= 0){
				bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, target));
			}
		});
	}


}
