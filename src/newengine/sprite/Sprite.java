package newengine.sprite;

import java.util.HashMap;
import java.util.Map;

import bus.BasicEventBus;
import bus.BusEvent;
import bus.BusEventHandler;
import bus.BusEventType;
import bus.EventBus;
import newengine.component.Component;
import newengine.component.ComponentType;

public class Sprite {
	
	public Sprite() {
		
	}
	
	private EventBus spriteBus = new BasicEventBus();
	
	public EventBus getSpriteBus() {
		return spriteBus;
	}
	public <T extends BusEvent> void on(BusEventType<T> eventType, BusEventHandler<? super T> eventHandler) {
		spriteBus.on(eventType, eventHandler);
	}
	
	public void emit(BusEvent event) {
		spriteBus.emit(event);
	}
	
	private Map<ComponentType<? extends Component>, Component> components = new HashMap<>();
	
	public void addComponent(Component control) {
		components.put(control.getType(), control);
		control.onAdded(this);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(ComponentType<T> type) {
		return (T) components.get(type);
	}
	
	public <T extends Component> void removeComponent(ComponentType<T> type) {
		Component control = components.get(type);
		components.remove(type);
		control.onRemoved();
	}
	
	public <T extends Component> boolean hasComponent(ComponentType<T> type) {
		return components.containsKey(type);
	}
	
	public void update(double dt) {
		for (Component component: components.values()) {
			component.onUpdated(dt);
		}
	}

}
