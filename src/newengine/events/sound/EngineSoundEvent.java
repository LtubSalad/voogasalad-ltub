package newengine.events.sound;

import bus.BusEvent;
import bus.BusEventType;

public class EngineSoundEvent extends BusEvent {

	public static final BusEventType<EngineSoundEvent> START_ENGINE = new BusEventType<>(EngineSoundEvent.class.getName()+"START_ENGINE");
	public static final BusEventType<EngineSoundEvent> UPDATE_ENGINE = new BusEventType<>(EngineSoundEvent.class.getName() + "UPDATE_ENGINE");
	
	private double speed;
	private double acceleration;
	
	public EngineSoundEvent(BusEventType<? extends BusEvent> busEventType,
			double speed,
			double acceleration) {
		super(busEventType);
		this.speed = speed;
		this.acceleration = acceleration;
	}
	
	public double getSpeed() {
		return speed;
	}

	public double getAcceleration() {
		return acceleration;
	}
}
