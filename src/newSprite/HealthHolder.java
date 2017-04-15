package newSprite;

import bus.BusEvent;
import bus.BusEventType;

public class HealthHolder extends Component{
	/**
	 * Internally tracks health of a single Sprite. Has an internal event bus to
	 * Uses delegation design pattern so that the HealthHolder stores the handlers for how to respond to events
	 * such as health adjustment and depletion of health.
	 * 
	 * This, however, means that it needs to store a reference to the ComponentSprite, which is not ideal. 
	 * This does make it easy to take out and remove components from a sprite though, because the sprite 
	 * doesn't store anything concerning components in its own bus.
	 */
	public final BusEventType<HealthEvent> ADJUST_HEALTH=new BusEventType<>("ADJUST_HEALTH");
	public final BusEventType<HealthEvent> NO_HEALTH=new BusEventType<>("NO_HEALTH");
	private int maxHealth;
	private int health;

	public HealthHolder(ComponentSprite sprite, int currentHealth, int maxHealth) {
		super(sprite);
		this.health=currentHealth;
		this.maxHealth=maxHealth;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Hardcoded functionality that the video game designer can call. Triggers other events depending on health.
	 * @param value
	 */
	public void adjustHealth(int value){
		health+=value;
		this.getBus().emit(new HealthEvent(ADJUST_HEALTH,health,value));
		if(health<=0){
			this.getBus().emit(new HealthEvent(NO_HEALTH,health,value));
		}
	}
	
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

}
