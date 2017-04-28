package newengine.events.spawner;

import bus.BusEvent;
import bus.BusEventType;

public class SpawnPrefEvent extends BusEvent{
	public static final BusEventType<SpawnPrefEvent> SETPREFS = new BusEventType<>(SpawnPrefEvent.class.getName()+"SETPREFS");
	
	private double secondBetween;
	private int totalNumber;

	public SpawnPrefEvent(BusEventType<? extends BusEvent> busEventType, double secondBetween, int totalNumber) {
		super(busEventType);
		this.secondBetween = secondBetween;
		this.totalNumber = totalNumber;
	}
	
	public int getTotalNumber(){
		return totalNumber;
	}
	
	public double getSecondBetween(){
		return secondBetween;
	}



}
