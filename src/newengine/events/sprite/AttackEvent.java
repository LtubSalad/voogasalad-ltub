package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;
import newengine.utils.Target;

public class AttackEvent extends BusEvent{
	
	public static final BusEventType<AttackEvent> FIRE = new BusEventType<>(
			AttackEvent.class.getName() + "FIRE");

	private Target target;
	
	public AttackEvent(BusEventType<? extends BusEvent> busEventType, Target target) {
		super(busEventType);
		this.target = target;
	}
	
	public Target getTarget() {
		return target;
	}

}
