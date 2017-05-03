package newengine.events.sprite;

import bus.BusEvent;
import bus.BusEventType;

public class ChangeSpeedEvent extends BusEvent{

	public static final BusEventType<ChangeSpeedEvent> SPEED = new BusEventType<>(
			ChangeSpeedEvent.class.getName() + "SPEED");
	public static final BusEventType<ChangeSpeedEvent> DIRECTION = new BusEventType<>(
			ChangeSpeedEvent.class.getName() + "DIRECTION");
	
	private double dt;
	
	public ChangeSpeedEvent(BusEventType<? extends BusEvent> busEventType,
			double dt) {
		super(busEventType);
		this.dt = dt;
	}

	public double dt() {
		return dt;
	}
}
