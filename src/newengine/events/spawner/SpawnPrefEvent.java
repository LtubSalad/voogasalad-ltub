package newengine.events.spawner;

import bus.BusEvent;
import bus.BusEventType;

public class SpawnPrefEvent extends BusEvent{
	public static final BusEventType<SpawnPrefEvent> SETPREFS = new BusEventType<>(SpawnPrefEvent.class.getName()+"SETPREFS");
	
	private double secondBetween;

	public SpawnPrefEvent(BusEventType<? extends BusEvent> busEventType, double secondBetween) {
		super(busEventType);
		this.secondBetween = secondBetween;
	}
	
	public double getSecondBetween(){
		return secondBetween;
	}



}
