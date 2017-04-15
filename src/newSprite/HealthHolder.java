package newSprite;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import newSprite.ComponentSprite.Component;


public class HealthHolder extends Component<HealthEvent>{
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
	private Map<BusEventType<HealthEvent>,BusEventHandler<HealthEvent>> eventHandlers;
	private int maxHealth;
	private int health;

	public HealthHolder(ComponentSprite sprite, int currentHealth, int maxHealth) {
		sprite.super();
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

	/**
	 * Lets people see what this events are listened for in this Component's bus.
	 */
	public Collection<BusEventType<HealthEvent>> getEvents() {
		Set<BusEventType<HealthEvent>> myEvents= new HashSet<>();
		myEvents.add(NO_HEALTH);
		myEvents.add(ADJUST_HEALTH);
		return myEvents;
	}

	@Override
	public Map<HealthEvent, BusEventHandler<HealthEvent>> getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
