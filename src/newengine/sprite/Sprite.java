package newengine.sprite;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Sprite {
	
	private EventBus spriteBus = new BasicEventBus();
	private SpriteID spriteID = IDGenerator.generateID();
	private Map<ComponentType<? extends Component>, Component> components = new HashMap<>();

	
	public EventBus getSpriteBus() {
		return spriteBus;
	}
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		spriteBus.on(eventType, eventHandler);
	}
	
	public void emit(BusEvent event) {
		spriteBus.emit(event);
	}
	
	public SpriteID getID() {
		return spriteID;
	}
	
	public void addComponent(Component component) {
		components.put(component.getType(), component);
		component.onAdded(this);
	}
	@SuppressWarnings("unchecked")
	public <T extends Component> Optional<T> getComponent(ComponentType<T> type) {
		return Optional.ofNullable((T) components.get(type));
	}
	public <T extends Component> void removeComponent(ComponentType<T> type) {
		Component component = components.get(type);
		component.onRemoved();
		components.remove(type);		
	}
	public <T extends Component> boolean hasComponent(ComponentType<T> type) {
		return components.containsKey(type);
	}
	
	/**
	 * To be called in each frame.
	 * @param dt
	 */
	public void update(double dt) {
		for (Component component: components.values()) {
			component.onUpdated(dt);
		}
	}
	
	@Override
	public String toString() {
		return "sprite("+spriteID+")";
	}

}
