package engine.sprite.health;

import bus.EventBus;
import engine.model.SpriteModelEvent;
import engine.sprite.Sprite;
import engine.sprite.attack.Weapon;

public class DecrementHealthManager {

	private EventBus bus;

	public DecrementHealthManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(DecrementHealthEvent.ANY, (e) -> {
			Sprite weapon = e.getWeapon();
			Sprite target = e.getTarget();

			HealthHolder hh = (HealthHolder) target.getHealthHolder().get(); //TODO remove type-casting
			if (hh.getHealth() <= 0){
				bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, target));
			} else {
				Weapon w = (Weapon) weapon.getWeapon().get(); //TODO remove type-casting
				double amt = w.getDamageDealt();			
				hh.decrementHealth(amt);
				System.out.println("Health decremented");
			}
		});
	}


}
