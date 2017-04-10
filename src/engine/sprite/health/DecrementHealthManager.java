package engine.sprite.health;

import bus.EventBus;
import engine.sprite.Sprite;

public class DecrementHealthManager {

	private EventBus bus;
	
	public DecrementHealthManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(DecrementHealthEvent.ANY, (e) -> {
			Sprite target = e.getTarget();
			double amt = e.getBullet().getDamageDealt();
			HealthHolder hh = (HealthHolder) target.getHealthHolder().get();
			hh.decrementHealth(amt);
			System.out.println("Health decremented");
		});
	}

	
}
