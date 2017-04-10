package engine.sprite.range;

import bus.EventBus;
import engine.sprite.Sprite;
import engine.sprite.attack.AttackEvent;

/**
 * Manage actions to do when one sprite is in the detection range of another sprite.
 * @author Yilin Gao
 *
 */
public class MonsterInRangeManager {

	private EventBus bus;
	
	public MonsterInRangeManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(MonsterInRangeEvent.ANY, e -> {
			Sprite detector = e.getDetector();
			Sprite detectee = e.getDetectee();
			// TODO actions to do when one sprite gets into the range of another sprite
			System.out.println("Tower " + detector.toString() + " detects a monster " + detectee.toString() 
				+ " in range.");
			bus.emit(new AttackEvent(AttackEvent.ANY, detector, detectee));
		});
	}
}
