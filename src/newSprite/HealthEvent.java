package newSprite;

import bus.BusEvent;
import bus.BusEventType;

public class HealthEvent extends BusEvent {
	private int currentHealth;
	private int healthChange;

	//what if they have wrong Type? I could get instantiated with arbitrary type
	public HealthEvent(BusEventType<? extends HealthEvent> busEventType, int currentHealth, int healthChange) {
		super(busEventType);
		this.currentHealth=currentHealth;
		this.healthChange=healthChange;
	}

	public int getCurrentHealth(){
		return currentHealth;
	}
	
	public int getHealthChange(){
		return healthChange;
	}
}
